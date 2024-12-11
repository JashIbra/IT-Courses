package com.effectivemobilett.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effectivemobilett.mainscreen.model.BaseCourseModel
import com.effectivemobilett.mainscreen.model.SortKeys
import com.effectivemobilett.mainscreen.usecases.GetBaseCourseByIdUseCase
import com.effectivemobilett.mainscreen.usecases.GetCourseIdsByCategoryId
import com.effectivemobilett.mainscreen.utils.CategoryIds.Companion.MOBILE_DEV_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getBaseCourseByIdUseCase: GetBaseCourseByIdUseCase,
    private val getCourseIdsByCategoryId: GetCourseIdsByCategoryId,
) : ViewModel() {

    @Volatile
    private var _state: MutableStateFlow<CoursesState> = MutableStateFlow(CoursesState())
    val state: StateFlow<CoursesState> = _state

    init {
        loadCourses()
    }

    fun searchByName(query: String) {
        if (query.isEmpty()) return
        _state.update {
            it.copy(
                courses = emptyList()
            )
        }
    }

    fun setSortKey(sortKey: Int) {
        if (sortKey == 0) return
        _state.update {
            it.copy(
                sortKey = sortKey,
            )
        }
    }

    fun updateReversState() {
        if (_state.value.sortKey == 0) return
        _state.update {
            it.copy(isReversed = !_state.value.isReversed)
        }
    }

   private fun loadCourses() {
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(loading = true)
                }
                val courseIds = getCourseIdsByCategoryId(MOBILE_DEV_ID)
                val coursesList = mutableListOf<BaseCourseModel>()
                courseIds.forEach { courseId ->
                    val course = getBaseCourseByIdUseCase(courseId)
                    coursesList.add(course)
                    val sortedList = sortedCourses(coursesList).toList()
                    _state.update {
                        it.copy(
                            courses = sortedList,
                            courseItem = course
                        )
                    }
                }
            } catch (e: Exception) {
                println("Error fetching courses: ${e.message}")
            } finally {
                _state.update {
                    it.copy(
                        loading = false
                    )
                }
            }
        }
    }

    private fun sortedCourses(courses: List<BaseCourseModel>): List<BaseCourseModel> {
        val sortKey = _state.value.sortKey
        return when (sortKey) {
            SortKeys.DATA_KEY -> courses.sortedBy { it.publishedDate }
            SortKeys.REVIEWS_KEY -> courses.sortedBy { it.reviews }
            SortKeys.RATING_KEY -> courses.sortedBy { it.rating }
            else -> courses
        }
    }
}
