package com.example.gallery

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.room.Room
import com.example.gallery.databinding.ActivityMainBinding
import android.app.Activity
import android.view.View

import android.widget.AdapterView

import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pictureDao: PictureDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set Layout and Toolbar
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // Database connection
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "picturedatabase"
        ).allowMainThreadQueries().enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration().build()
        pictureDao = db.pictureDao()

        // Load saved pictures
        reloadPicturesList()

        // Set listener on list
        binding.picturesList.setOnItemClickListener { parent, _, position, _ ->
            val item = parent.adapter.getItem(position) as Picture
            val intent = Intent(this, ShowActivity::class.java)
            intent.putExtra("blob", item.blob)
            intent.putExtra("title", item.title)
            intent.putExtra("description", item.description)
            intent.putExtra("location", item.location)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        reloadPicturesList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                runAboutActivity(window.decorView.rootView)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun runAddActivity(view: android.view.View) {
        var intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    fun runAboutActivity(view: android.view.View) {
        var intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    fun reloadPicturesList() {
        val pictures: List<Picture> = pictureDao.getAll()
        val pictureAdapter = PictureAdapter(this, pictures)
        binding.picturesList.adapter = pictureAdapter
    }
}
