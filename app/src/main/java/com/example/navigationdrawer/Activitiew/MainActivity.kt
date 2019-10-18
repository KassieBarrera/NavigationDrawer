package com.example.navigationdrawer.Activitiew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.navigationdrawer.Fragments.Email_Fragment
import com.example.navigationdrawer.Fragments.Fotos_Fragment
import com.example.navigationdrawer.Fragments.PerfilF
import com.example.navigationdrawer.R
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {


    var drawerLayout: DrawerLayout? = null
    var naView: NavigationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<View>(R.id.toolBar) as Toolbar
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawerlayout)
        naView = findViewById(R.id.navigationView)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.menu_foreground) //solo activamos que al dar click abra
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)                  //el menu

        naView!!.setNavigationItemSelectedListener { item ->

            var fragment: Fragment? = null
            var gestor: Boolean = false

            when (item.itemId) {
                R.id.menu_perejil -> {
                    fragment = PerfilF()
                    gestor = true
                }
                R.id.photos -> {
                    fragment = Fotos_Fragment()
                    gestor = true
                }
                R.id.email -> {
                    fragment = Email_Fragment()
                    gestor = true
                }
                R.id.help -> {
                    fragment = PerfilF()
                    gestor = true
                }
                R.id.borrar -> {
                    Toast.makeText(this, "Borrar", Toast.LENGTH_SHORT).show()
                }
                R.id.otro -> {
                    Toast.makeText(this, "Otro", Toast.LENGTH_SHORT).show()
                }
            }
            if (gestor) {
                cambiarFragment(fragment!!, item)
                drawerLayout!!.closeDrawers()
            }

            true
        }
    }

    fun cambiarFragment(fragment: Fragment, item: MenuItem) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit()
        item.isChecked = true
        supportActionBar!!.title = item.title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout!!.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
