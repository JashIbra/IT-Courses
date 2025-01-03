package com.effectivemobilett.mainscreen.repository

import com.effectivemobilett.mainscreen.model.CourseModel
import com.effectivemobilett.mainscreen.model.SummaryGradeModel

interface MainScreenRepository {

    // TODO возможно этот методод должен уйти на presentation
//    suspend fun getFilteredCourses(
//        courseName: String? = null,
//        sortKey: Int? = null,
//    ): List<BaseCourseModel>

    suspend fun getCourseIdsByCategoryId(id: Long): List<Long>

    suspend fun getCourseById(id: Long): CourseModel

    suspend fun getSummaryGradeById(id: Long): SummaryGradeModel
}
