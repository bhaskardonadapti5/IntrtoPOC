package com.example.onboardingdemo

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout


class IntroActivity : AppCompatActivity() {

    private lateinit var screenPager: ViewPager
    var introViewPagerAdapter: IntroViewPagerAdapter? = null
    private lateinit var tabIndicator: TabLayout
    private lateinit var btnNext: MaterialButton
    private lateinit var btnSkip: MaterialButton
    private lateinit var btnGetStarted:MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_intro)

        //Set full screen after setting layout content
        @Suppress("DEPRECATION")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController

            if(controller != null) {
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        makeStatusbarTransparent()
        btnNext = findViewById(R.id.btn_next)
        btnSkip = findViewById(R.id.btn_skip)
        btnGetStarted = findViewById(R.id.btn_get_started)
        tabIndicator = findViewById(R.id.tab_indicator)
        screenPager = findViewById(R.id.screen_viewpager)

        introViewPagerAdapter = IntroViewPagerAdapter(this)
        screenPager.adapter = introViewPagerAdapter

        //Setup tab indicator
        tabIndicator.setupWithViewPager(screenPager)

        //Button Next
        btnNext.setOnClickListener {
            screenPager.setCurrentItem(screenPager.currentItem + 1, true)
        }
        //Button Get Started
        btnGetStarted.setOnClickListener {
            val mainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainActivity)
            finish()
        }

        tabIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == introViewPagerAdapter!!.count -1){
                    loadLastScreen()
                } else
                {
                    loadBeforeScreen()
                }
            }
        })
    }

    private fun makeStatusbarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun loadLastScreen() {
        btnNext.visibility = View.INVISIBLE
        btnSkip.visibility = View.INVISIBLE
        btnGetStarted.visibility = View.VISIBLE
    }

    private fun loadBeforeScreen() {
        btnNext.visibility = View.VISIBLE
        btnSkip.visibility = View.VISIBLE
        btnGetStarted.visibility = View.INVISIBLE
    }
}