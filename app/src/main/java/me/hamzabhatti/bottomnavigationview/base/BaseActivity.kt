package me.hamzabhatti.bottomnavigationview.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import me.hamzabhatti.bottomnavigationview.R

open class BaseActivity : AppCompatActivity() {

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount < 1) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    protected fun replaceFragment(targetFragment: Fragment, tagName: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, targetFragment, tagName)
            .addToBackStack(tagName)
            .commit()
    }


    protected fun addFragment(targetFragment: Fragment, tagName: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_fragment, targetFragment, tagName)
            .addToBackStack(tagName)
            .commit()
    }


    protected fun addFragmentWithNoBackStack(targetFragment: Fragment, tagName: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_fragment, targetFragment, tagName)

            .commit()
    }

    protected fun replaceFragmentWithNoBackStack(targetFragment: Fragment, tagName: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, targetFragment, tagName)

            .commit()
    }

    fun changeFragmentWithoutReCreation(fragment: Fragment, tagFragmentName: String) {

        val mFragmentManager = supportFragmentManager
        val fragmentTransaction = mFragmentManager.beginTransaction()

        val currentFragment = mFragmentManager.primaryNavigationFragment
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment)
        }

        var fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName)
        if (fragmentTemp == null) {
            fragmentTemp = fragment
            fragmentTransaction.add(R.id.nav_host_fragment, fragmentTemp, tagFragmentName)
        } else {
            fragmentTransaction.show(fragmentTemp)
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commitNowAllowingStateLoss()
    }


}