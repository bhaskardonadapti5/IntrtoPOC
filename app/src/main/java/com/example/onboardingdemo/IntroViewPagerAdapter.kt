package com.example.onboardingdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter

class IntroViewPagerAdapter(context: Context):PagerAdapter(){

    private var context: Context? = null
    init {
        this.context = context
    }
    private val layouts = intArrayOf(
        R.layout.intro_page_1,
        R.layout.intro_page_2,
        R.layout.intro_page_3
    )
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
    override fun getCount(): Int {
            return layouts.size
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(layouts[position], container, false)
        view.tag = position
        container.addView(view)
        return view
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

}