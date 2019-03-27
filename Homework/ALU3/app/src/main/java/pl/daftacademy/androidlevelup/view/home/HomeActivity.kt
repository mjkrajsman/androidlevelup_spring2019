package pl.daftacademy.androidlevelup.view.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import pl.daftacademy.androidlevelup.R
import kotlinx.android.synthetic.main.content_main.*
import pl.daftacademy.androidlevelup.view.movies.MoviesFragment


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
        return when (item.itemId) {
            R.id.nav_movies -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_movies -> filterMovies("All")
            R.id.nav_action -> filterMovies("Action")
            R.id.nav_comedy -> filterMovies("Comedy")
            R.id.nav_crime -> filterMovies("Crime")
            R.id.nav_horror -> filterMovies("Horror")
            R.id.nav_romance -> filterMovies("Romance")
        }

        main_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun filterMovies(genre: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, MoviesFragment.create(genre))
            .commit()
    }
}