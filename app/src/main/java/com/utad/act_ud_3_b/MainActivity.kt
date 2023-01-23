package com.utad.act_ud_3_b

import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.utad.act_ud_3_b.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, Playlist::class.java)
        startActivity(intent)

        binding.dame.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.dame2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.dame3.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.dame4.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

       //supportFragmentManager.beginTransaction().replace(R.id.main_container, PremiumFragment()).addToBackStack(null).commit()

        //datos json

        val json = readJsonFromFile("users.json")
        val users = Gson().fromJson(json, UserResponse::class.java)
        Log.i("pepapig", users.data.toString())

        //clic datos
        val mAdapter = MainAdapter(users.data, {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()



            Log.i("pepapig", "entras?")
        })

        //formato datos

        val mainRecyclerView = findViewById<RecyclerView>(R.id.mainRecyclerView)
        mainRecyclerView.layoutManager = GridLayoutManager(this, 2)
        mainRecyclerView.adapter = mAdapter

        //menu botones abajo

        var bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.page_1-> {
                    goToFragment(HomeFragment())
                    true
                }
                R.id.page_2 -> {
                    goToFragment(SearchFragment())
                    true
                }
                R.id.page_3 -> {
                    goToFragment(LibraryFragment())
                    true
                }
                R.id.page_4 -> {
                    goToFragment(PremiumFragment())
                    true
                }
                else -> false
            }
        }
        bottom_navigation.selectedItemId = R.id.page_2

    }

    fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()
    }

    //lee json

    private fun readJsonFromFile(fileName: String): String {
        var json = ""
        try {
            val bufferedReader = BufferedReader(
                InputStreamReader(assets.open(fileName))
            )
            val paramsBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                paramsBuilder.append(line)
                line = bufferedReader.readLine()
            }
            json = paramsBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }

}