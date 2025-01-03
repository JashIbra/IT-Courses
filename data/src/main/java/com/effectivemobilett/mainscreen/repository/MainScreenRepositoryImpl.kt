package com.effectivemobilett.mainscreen.repository

import com.effectivemobilett.api.CoursesApi
import com.effectivemobilett.mainscreen.model.CourseModel
import com.effectivemobilett.mainscreen.model.SummaryGradeModel
import com.effectivemobilett.mainscreen.model.toDomain

class MainScreenRepositoryImpl(private val coursesApi: CoursesApi) : MainScreenRepository {

//    override suspend fun getFilteredCourses(
//        courseName: String?,
//        sortKey: Int?
//    ): List<BaseCourseModel> {
//        val listOfCourses = mutableListOf<BaseCourseModel>()
//
//        courseIds().forEach { courseId ->
//            val courseModel = getCourseById(courseId)
//            val grade = getCourseGradeBySummaryGradeId(courseModel.summaryGradeId)
//            listOfCourses.add(createFrom(courseModel, grade))
//            if (sortKey != null) sortCoursesByKey(sortKey, listOfCourses)
//        }
//        return if (courseName != null) {
//            listOfCourses.filter { it.title.lowercase().contains(courseName.lowercase()) }
//        } else listOfCourses
//    }

    override suspend fun getSummaryGradeById(id: Long): SummaryGradeModel {
        return coursesApi.getCourseGradeById(id).summaryGradeList.first().toDomain()
    }

    override suspend fun getCourseIdsByCategoryId(id: Long): List<Long> {
        val idsList = mutableListOf<Long>()
        coursesApi.getTargetedCourses(id).courseLists.first().courseIds.forEach {
            idsList.add(it)
        }
        return idsList
    }

     override suspend fun getCourseById(id: Long): CourseModel {
        return coursesApi.getCourseById(id).courses[0].toDomain()
    }

//    private suspend fun getCourseById(id: Long): CourseModel {
//        return coursesApi.getCourseById(id).courses[0].toDomain()
//    }

//    private suspend fun getCourseGradeBySummaryGradeId(gradeId: Long): SummaryGradeModel {
//        return coursesApi.getCourseGradeById(gradeId).gradeList.first().toDomain()
//    }

//    private suspend fun courseIds(): List<Long> {
//        val idsList = mutableListOf<Long>()
//        coursesApi.getTargetedCourses(MOBILE_DEV_ID).courseLists.first().courseIds.forEach {
//            idsList.add(it)
//        }
//        return idsList
//    }

//    private fun sortCoursesByKey(
//        key: Int,
//        coursesList: MutableList<BaseCourseModel>
//    ) {
//        when (key) {
//            SortKeys.DATA_KEY -> coursesList.sortBy { it.publishedDate }
//            SortKeys.REVIEWS_KEY -> coursesList.sortBy { it.countOfReviews }
//            SortKeys.RATE_KEY -> coursesList.sortBy { it.average }
//
//            SortKeys.REVERS_DATA_KEY -> {
//                coursesList.sortBy { it.publishedDate }
//                coursesList.reverse()
//            }
//
//            SortKeys.REVERS_REVIEWS_KEY -> {
//                coursesList.sortBy { it.countOfReviews }
//                coursesList.reverse()
//            }
//
//            SortKeys.REVERS_RATE_KEY -> {
//                coursesList.sortBy { it.average }
//                coursesList.reverse()
//            }
//        }
//    }

//    private fun createFrom(course: CourseModel, grade: SummaryGradeModel) =
//        BaseCourseModel(
//            id = course.id,
//            title = course.title,
//            summary = course.summary,
//            language = course.language,
//            publishedDate = course.publishedDate,
//            targetAudience = course.targetAudience,
//            average = grade.average,
//            countOfReviews = grade.countOfReviews,
//            readableDate = getReadableDate(course.publishedDate, course.language),
//            price = course.price,
//            coverUrl = course.coverUrl,
//            difficulty = course.difficulty,
//            certificateCoverUrl = course.certificateCoverUrl
//        )

    companion object {
        private const val KOTLIN = "Kotlin"
        private const val ANDROID = "Android"
    }
}
