package com.personal.movies.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.personal.movies.R

fun FragmentManager.transitionTo(fragment: Fragment, isBackStack: Boolean = true) {
    val transition = beginTransaction().replace(R.id.fcvContainer, fragment)
    if (isBackStack) {
        transition.addToBackStack(fragment.javaClass.simpleName)
    }
    transition.commit()
}