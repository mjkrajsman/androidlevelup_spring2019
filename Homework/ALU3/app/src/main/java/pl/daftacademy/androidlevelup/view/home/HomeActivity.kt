package pl.daftacademy.androidlevelup.view.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import pl.daftacademy.androidlevelup.R
import kotlinx.android.synthetic.main.app_bar_main.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(main_toolbar)

        //---===DrawerLayout===---
        val toggle = ActionBarDrawerToggle(
            this, main_drawer_layout, main_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        main_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        main_nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (main_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            main_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_movies -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_movies -> {
//                supportFragmentManager
//                    .beginTransaction()
//                    .add(R.id.container, TestFragment())
//                    .addToBackStack(null)
//                    .commitNow()
            }
            R.id.nav_action -> {

            }
            R.id.nav_comedy -> {

            }
            R.id.nav_crime -> {

            }
            R.id.nav_horror -> {

            }
            R.id.nav_romance -> {

            }
        }

        main_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}