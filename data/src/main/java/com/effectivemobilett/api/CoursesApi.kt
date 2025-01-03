package com.effectivemobilett.api

import com.effectivemobilett.network.CatalogBlockResponse
import com.effectivemobilett.network.CoursesListResponse
import com.effectivemobilett.network.CursesResponse
import com.effectivemobilett.network.SummaryGradeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CoursesApi {

    @GET("catalog-blocks/{id}")
    suspend fun getCatalog(
        @Path("id") id: Long
    ): CatalogBlockResponse

    @GET("courses/{id}")
    suspend fun getCourseById(
        @Path("id") id: Long
    ): CursesResponse

    @GET("course-review-summaries/{id}")
    suspend fun getCourseGradeById(
        @Path("id") id: Long
    ): SummaryGradeResponse

    @GET("course-lists/{id}")
    suspend fun getTargetedCourses(
        @Path("id") id: Long
    ): CoursesListResponse
}
