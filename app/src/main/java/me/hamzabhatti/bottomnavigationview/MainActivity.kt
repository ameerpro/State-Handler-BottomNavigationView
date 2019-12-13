package me.hamzabhatti.bottomnavigationview

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import me.hamzabhatti.bottomnavigationview.base.BaseActivity
import me.hamzabhatti.bottomnavigationview.ui.dashboard.DashboardFragment
import me.hamzabhatti.bottomnavigationview.ui.home.HomeFragment
import me.hamzabhatti.bottomnavigationview.ui.notifications.NotificationsFragment

class MainActivity : BaseActivity() {

    private lateinit var navView: BottomNavigationView
    private lateinit var currentFragment: Fragment
    lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)

//        val navController = findNavController(R.id.nav_host_fragment)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        //Set First Fragment
        fm = supportFragmentManager


        var fragment =
            supportFragmentManager.findFragmentByTag(HomeFragment::class.java.canonicalName)
        if (fragment == null)
        {
            fragment = HomeFragment()
        }

        changeFragmentWithoutReCreation(fragment, HomeFragment::class.java.canonicalName!!)
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                currentFragment = HomeFragment()
                changeFragmentWithoutReCreation(
                    currentFragment,
                    HomeFragment::class.java.canonicalName!!
                )

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                currentFragment = DashboardFragment()
                changeFragmentWithoutReCreation(
                    currentFragment,
                    DashboardFragment::class.java.canonicalName!!
                )

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                currentFragment = NotificationsFragment()
                changeFragmentWithoutReCreation(
                    currentFragment,
                    NotificationsFragment::class.java.canonicalName!!
                )

                return@OnNavigationItemSelectedListener true
            }

        }

        false
    }

}
