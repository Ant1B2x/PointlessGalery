package com.example.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.room.Room
import com.example.gallery.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowBinding
    private lateinit var pictureDao: PictureDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // Database connection
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "picturedatabase"
        ).allowMainThreadQueries().enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration().build()
        pictureDao = db.pictureDao()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        binding.showPicture.setImageBitmap(Utils.bytesToBitmap(intent.extras?.get("blob") as ByteArray))
        binding.showTitle.text = (intent.extras?.get("title") as String)

        binding.showLocation.setText(intent.extras?.get("location") as String)
        binding.showDescription.setText(intent.extras?.get("description") as String)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_show, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionFavorite -> {
                pictureDao.updateFavorite(
                    intent.extras?.get("pictureID") as Int,
                    !(intent.extras?.get("favorite") as Boolean)
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
