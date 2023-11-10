package com.testapp.styling_templates

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import androidx.core.view.setPadding
import com.appsamurai.storyly.StoryGroup
import com.appsamurai.storyly.StoryGroupType
import com.appsamurai.storyly.styling.StoryGroupView
import com.appsamurai.storyly.styling.StoryGroupViewFactory
import com.testapp.R
import com.testapp.databinding.StylingWideLandscapeBinding
import com.testapp.styling_templates.ui.dpToPixel
import com.bumptech.glide.Glide

@SuppressLint("ViewConstructor")
class WideLandscapeView(context: Context) : StoryGroupView(context) {

    private val listViewBinding: StylingWideLandscapeBinding = StylingWideLandscapeBinding.inflate(LayoutInflater.from(context))

    init {
        addView(listViewBinding.root)
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    val url = "https://cdn.pixabay.com/photo/2020/04/17/18/31/apple-5056412_1280.jpg"
    override fun populateView(storyGroup: StoryGroup?) {
        val storyCover = storyGroup?.stories?.firstOrNull { !it.seen }?.media?.previewUrl ?: storyGroup?.iconUrl
        Glide.with(context.applicationContext).load(storyGroup?.stories?.firstOrNull { !it.seen }?.media?.previewUrl ?: storyGroup?.iconUrl).into(listViewBinding.backgroundStory)
        Glide.with(context.applicationContext).load(url).into(listViewBinding.groupIcon)

        if (storyGroup?.type == StoryGroupType.Vod) {
            listViewBinding.vodIcon.visibility = VISIBLE
            listViewBinding.vodIcon.setImageResource(R.drawable.st_ivod_sg_icon)
            listViewBinding.vodIcon.background = GradientDrawable().apply {
                colors = listOf(Color.parseColor("#7651FF"), Color.parseColor("#7651FF")).toIntArray()
                cornerRadii = List(8) { dpToPixel(6f) }.toFloatArray()
            }
            listViewBinding.vodIcon.setPadding(3)
        } else if (storyGroup?.pinned == true) {
            listViewBinding.pinIcon.visibility = VISIBLE
            listViewBinding.pinIcon.setImageResource(R.drawable.st_pin_icon)
            listViewBinding.pinIcon.avatarBackgroundColor = Color.parseColor("#00E0E4")
        }

        listViewBinding.groupTitle.text = storyGroup?.title
        when (storyGroup?.seen) {
            true -> listViewBinding.groupIcon.borderColor = arrayOf(Color.parseColor("#FFDBDBDB"), Color.parseColor("#FFDBDBDB"))
            false -> listViewBinding.groupIcon.borderColor = arrayOf(Color.parseColor("#FFFED169"), Color.parseColor("#FFFA7C20"), Color.parseColor("#FFC9287B"), Color.parseColor("#FF962EC2"), Color.parseColor("#FFFED169"))
        }
    }
}

class WideLandscapeViewFactory(val context: Context): StoryGroupViewFactory() {
    override fun createView(): StoryGroupView {
        return WideLandscapeView(context)
    }
}