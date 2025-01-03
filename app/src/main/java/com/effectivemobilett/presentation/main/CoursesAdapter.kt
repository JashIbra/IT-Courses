package com.effectivemobilett.presentation.main

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.effectivemobilett.mainscreen.model.BaseCourseModel
import com.effectivemobilett.presentation.utils.CutOfLogo
import com.effectivemobilett.presentation.utils.dpToPx
import com.effectivemobilett.presentation.utils.getReadableDate
import com.example.effectivemobilett.R
import com.example.effectivemobilett.databinding.ItemCourseBinding
import eightbitlab.com.blurview.BlurAlgorithm
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.util.Locale

class CoursesAdapter(private val activity: Activity) :
    RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder>() {


    var courses: List<BaseCourseModel> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCourseBinding.inflate(inflater, parent, false)
        return CoursesViewHolder(binding)
    }

    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        setupBlurView(holder.binding.itemCourseBlurBackgroundRating, holder.binding.root)
        setupBlurView(holder.binding.itemCourseBlurBackgroundDate, holder.binding.root)
        setupBlurView(holder.binding.itemCourseBlurBackgroundButtonSave, holder.binding.root)
        val course = courses[position]
        with(holder.binding) {
            if (!course.coverUrl.isNullOrBlank()) {
                Glide.with(itemCourseImageBackground)
                    .load(course.coverUrl)
                    .transform(CutOfLogo(10), RoundedCornersTransformation(12.dpToPx, 0))
                    .error(R.drawable.ic_image_placeholder)
                    .into(itemCourseImageBackground)
            } else {
                itemCourseImageBackground.setImageResource(R.drawable.ic_image_placeholder)
            }

            itemCourseImgButtonSave.setOnClickListener {

            }
            itemCourseRatingText.text = String.format(Locale.getDefault(), "%.1f", course.rating)
            itemCourseTextDate.text = getReadableDate(course.publishedDate, course.language)
            itemCourseTextTitle.text = course.title
            itemCourseTextDescription.text = course.shortDesc
            itemCourseTextPrice.text = course.price
        }
    }

    private fun setupBlurView(blurView: BlurView, view: ViewGroup) {
        val windowBackground = activity.window?.decorView?.background
        blurView.apply {
            outlineProvider = ViewOutlineProvider.BACKGROUND;
            clipToOutline = true
            setBlurRadius(16f)
            setupWith(view, getBlurAlgorithm()).setFrameClearDrawable(windowBackground)
        }
    }

    private fun getBlurAlgorithm(): BlurAlgorithm {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            RenderEffectBlur()
        } else {
            RenderScriptBlur(activity)
        }
    }

    class CoursesViewHolder(val binding: ItemCourseBinding) : RecyclerView.ViewHolder(binding.root)
}
