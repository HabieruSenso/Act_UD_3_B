package com.utad.act_ud_3_b

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.utad.act_ud_3_b.databinding.FragmentPlaylistBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Playlist : AppCompatActivity() {

    private lateinit var binding: FragmentPlaylistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_playlist)

        binding = FragmentPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val json = readJsonFromFile("users.json")
        val users = Gson().fromJson(json, UserResponse::class.java)
        Log.i("pepapig", users.data.toString())

        //clic datos
        val mAdapter = MainAdapter(users.data, {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()



            Log.i("pepapig", "entras?")
        })

        //formato datos

        val mainRecyclerView = findViewById<RecyclerView>(R.id.mainRecyclerView2)
        mainRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainRecyclerView.adapter = mAdapter

    }

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