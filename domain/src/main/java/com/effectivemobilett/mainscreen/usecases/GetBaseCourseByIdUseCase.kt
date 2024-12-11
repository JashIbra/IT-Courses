package com.effectivemobilett.mainscreen.usecases

import com.effectivemobilett.mainscreen.model.BaseCourseModel
import com.effectivemobilett.mainscreen.repository.MainScreenRepository

class GetBaseCourseByIdUseCase(private val mainScreenRepository: MainScreenRepository) {

    suspend operator fun invoke(id: Long): BaseCourseModel {
        val courseModel = mainScreenRepository.getCourseById(id)
        val summaryGradeModel = mainScreenRepository.getSummaryGradeById(courseModel.summaryGradeId)
        return BaseCourseModel(
            id = courseModel.id,
            title = courseModel.title,
            shortDesc = courseModel.summary,
            language = courseModel.language,
            publishedDate = courseModel.publishedDate,
            targetAudience = courseModel.targetAudience,
            rating = summaryGradeModel.average,
            reviews = summaryGradeModel.countOfReviews,
            price = courseModel.price,
            coverUrl = courseModel.coverUrl,
            difficulty = courseModel.difficulty,
            certificateCoverUrl = courseModel.certificateCoverUrl
        )
    }
}
