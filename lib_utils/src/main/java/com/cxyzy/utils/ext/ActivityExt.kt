package com.cxyzy.utils.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.addFragment(fragment: Fragment, containerId: Int) =
        supportFragmentManager.inTransaction { add(containerId, fragment) }

fun AppCompatActivity.replaceFragment(fragment: Fragment, containerId: Int) =
        supportFragmentManager.inTransaction { replace(containerId, fragment) }

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().func().commit()

