package com.effectivemobilett

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.effectivemobilett.api.CoursesApi
import com.effectivemobilett.extentions.dpToPx
import com.effectivemobilett.mainscreen.repository.MainScreenRepositoryImpl
import com.effectivemobilett.utils.CutOfLogo
import com.effectivemobilett.utils.getReadableDate
import com.example.effectivemobilett.R
import com.example.effectivemobilett.databinding.FragmentFirstBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        CoroutineScope(Dispatchers.Main).launch {
            fetchCourse()
            fetchCoursesModelList()
        }
    }

    private val coursesApi: CoursesApi by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        get(CoursesApi::class.java)
    }

    private val mainScreenRepository: com.effectivemobilett.mainscreen.repository.MainScreenRepository = MainScreenRepositoryImpl(coursesApi)

    private suspend fun fetchCoursesModelList() {
        try {
            binding.progressBar.isVisible = true
            val titleList = mutableListOf("")
            val startTime = System.nanoTime()
            val coursesModelList =
                mainScreenRepository.getSortedCoursesByName(sortKey = com.effectivemobilett.mainscreen.model.SortKeys.REVERS_DATA_KEY)
            coursesModelList.forEach { model ->
                val date = getReadableDate(model.publishedDate, model.language)
                titleList.add(
                    model.title.take(10) + "$date \n"
                )
                binding.textview7.text = titleList.toString()
            }
            val endTime = System.nanoTime()
            val durationInMillis = (endTime - startTime) / 1_000_000_000.0
            binding.textview7.text = titleList.toString()
            binding.textview6.text =
                "Количество курсов ${titleList.size - 1} Скорость $durationInMillis"
        } catch (e: Exception) {
            println("Error fetching catalog block: ${e.message}")
            binding.textview7.text = "Ошибочка вышла"
        } finally {
            binding.progressBar.isVisible = false
        }
    }

    private suspend fun fetchCourse() {
        try {
            binding.textview0.text = "Загрузка..."
            val imageView = binding.imageView
            val response = coursesApi.getCourseById(117314)
            response.courses.forEach { course ->
                val grade = coursesApi.getCourseGradeById(course.gradeId)
                val averageText = grade.gradeList[0].average.toString()
                val reviewsText = grade.gradeList[0].countOfReviews.toString()
                binding.textview1.text = course.title
                binding.textview2.text = course.summary
                binding.textview3.text = course.price
                binding.textview4.text = averageText.substring(0, 3)
                binding.textview5.text = getReadableDate(course.publishedDate, course.language)
                binding.textview8.text = "Количество отзывов: " + reviewsText
                Glide.with(imageView)
                    .load(course.coverUrl)
                    .transform(CutOfLogo(10), RoundedCornersTransformation(12.dpToPx, 0))
                    .into(imageView)
            }
        } catch (e: Exception) {
            println("Error fetching catalog block: ${e.message}")
        } finally {
            binding.textview0.text = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
