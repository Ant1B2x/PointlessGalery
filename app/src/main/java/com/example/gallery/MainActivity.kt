package com.example.gallery

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.room.Room
import com.example.gallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "picturedatabase"
        ).allowMainThreadQueries().enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration().build()
        val pictureDao = db.pictureDao()

        val pictures: List<Picture> = pictureDao.getAll()
        for (picture in pictures){
            //Log.i("FlowerApp",flower.toString())
        }
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
}
