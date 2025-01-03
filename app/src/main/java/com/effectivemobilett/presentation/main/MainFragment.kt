package com.effectivemobilett.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.effectivemobilett.mainscreen.model.BaseCourseModel
import com.effectivemobilett.presentation.main.searchfilter.SearchCategoryFilterBottomSheet
import com.example.effectivemobilett.R
import com.example.effectivemobilett.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<MainViewModel>()
    private var coursesAdapter: CoursesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val priorities = resources.getStringArray(R.array.main_screen_sort_by)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_sort_menu, priorities)
        binding.mainSortByMenuTextButton.setAdapter(arrayAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initItemViews(view)

        binding.mainChoseSearchFilterImageButton.setOnClickListener {
            SearchCategoryFilterBottomSheet().show(childFragmentManager, "SearchFilterTag")
        }

        coursesAdapter = CoursesAdapter(requireActivity())

        val layoutManager = LinearLayoutManager(requireContext())
        binding.mainCoursesListRecycler.layoutManager = layoutManager
        binding.mainCoursesListRecycler.adapter = coursesAdapter

        viewModel.state.onEach { coursesState ->
            val list = mutableListOf<BaseCourseModel>()
            coursesState.courses?.forEach { course ->
                list.add(course)
            }
            coursesAdapter?.courses = list
        }.launchIn(lifecycleScope)

//        viewModel.loadCourses(coursesAdapter as CoursesAdapter)
//
//        binding.searchBtn.setOnClickListener {
//            val query = binding.searchField.text.toString()
//            viewModel.searchByName(query)
//        }
//
//        binding.reversBtn.setOnClickListener {
//            viewModel.updateReversState()
//        }

//        lifecycleScope.launch {
//            fetchCoursesModelList()
//        }
    }

    private fun fetchCourses() {
        viewModel.state.onEach {
            val list = emptyList<BaseCourseModel>()
        }
    }

    private fun initItemViews(view: View) {

    }


//    private fun fetchCoursesModelList() {
//        val state = viewModel.state
//        val startTime = System.nanoTime()
//        state.onEach {
//            binding.progressBar.isVisible = state.value.loading
//            val titleList = mutableListOf("")
//            val reversCondition = it.isReversed && it.sortKey != 0
//            val courses = if (reversCondition) it.courses?.reversed() else it.courses
//            courses?.forEach { model ->
//                val date = getReadableDate(model.publishedDate, model.language)
//                val countReview = model.reviews
//                titleList.add(
//                    model.title.take(25) +
//                            " $countReview \n"
//                )
//                binding.textview7.text = titleList.toString()
//                val endTime = System.nanoTime()
//                val durationInMillis = (endTime - startTime) / 1_000_000_000.0
//                binding.textview6.text =
//                    "Количество курсов ${titleList.size - 1} Скорость $durationInMillis"
//            }
//        }.launchIn(lifecycleScope)
//    }

    private suspend fun fetchCourse() {
//        try {
//            binding.textview0.text = "Загрузка..."
//            val imageView = binding.imageView
//            val response = coursesApi.getCourseById(117314)
//            response.courses.forEach { course ->
//                val grade = coursesApi.getCourseGradeById(course.gradeId)
//                val averageText = grade.gradeList[0].average.toString()
//                val reviewsText = grade.gradeList[0].countOfReviews.toString()
//                binding.textview1.text = course.title
//                binding.textview2.text = course.summary
//                binding.textview3.text = course.price
//                binding.textview4.text = averageText.substring(0, 3)
//                binding.textview5.text = getReadableDate(course.publishedDate, course.language)
//                binding.textview8.text = "Количество отзывов: " + reviewsText
//                Glide.with(imageView)
//                    .load(course.coverUrl)
//                    .transform(CutOfLogo(10), RoundedCornersTransformation(12.dpToPx, 0))
//                    .into(imageView)
//            }
//        } catch (e: Exception) {
//            println("Error fetching catalog block: ${e.message}")
//        } finally {
//            binding.textview0.text = null
//        }
    }
}
