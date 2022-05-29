package com.abjt.runtimepermissionwithinheritence.child_activities

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.abjt.runtimepermissionwithinheritence.R
import com.abjt.runtimepermissionwithinheritence.base_activity.BaseActivity
import com.abjt.runtimepermissionwithinheritence.child_fragments.FragmentA
import com.abjt.runtimepermissionwithinheritence.child_fragments.FragmentB
import com.abjt.runtimepermissionwithinheritence.helpers.FRAGMENT_A_TAG
import com.abjt.runtimepermissionwithinheritence.helpers.FRAGMENT_B_TAG
import com.abjt.runtimepermissionwithinheritence.helpers.setOnClick
import com.abjt.runtimepermissionwithinheritence.helpers.showToast
import com.google.android.material.button.MaterialButton

class MainActivity : BaseActivity() {

    private lateinit var mbOpenFragmentA: MaterialButton
    private lateinit var mbOpenFragmentB: MaterialButton

    private var fragmentA: Fragment? = null
    private var fragmentB: Fragment? = null

    private val mSupportFragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        attachClickListeners()
    }

    private fun initViews() {
        mbOpenFragmentA = findViewById(R.id.mb_open_fragment_A)
        mbOpenFragmentB = findViewById(R.id.mb_open_fragment_B)
    }

    private fun attachClickListeners() {
        mbOpenFragmentA.setOnClick {
            fragmentA?.let {
                showToast(this, "Fragment A already created")
            } ?: let {
                fragmentA = FragmentA.getInstance()
                firstTransaction(R.id.fl_fragment_A_container, fragmentA as Fragment, FRAGMENT_A_TAG)
                window.statusBarColor = ContextCompat.getColor(this, R.color.fragment_A_background)
            }
        }

        mbOpenFragmentB.setOnClick {
            fragmentB?.let {
                showToast(this, "Fragment B already created")
            } ?: let {
                fragmentB = FragmentB.getInstance()
                firstTransaction(R.id.fl_fragment_B_container, fragmentB as Fragment, FRAGMENT_B_TAG)
                window.navigationBarColor = ContextCompat.getColor(this, R.color.fragment_B_background)
            }
        }
    }

    private fun firstTransaction(container: Int, fragment: Fragment, fragmentTag: String) {
        mSupportFragmentManager.beginTransaction().add(container, fragment, fragmentTag).commit()
    }
}