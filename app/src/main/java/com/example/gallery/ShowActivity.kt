package com.example.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.gallery.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        binding.showPicture.setImageBitmap(Utils.bytesToBitmap(intent.extras?.get("blob") as ByteArray))
        binding.showTitle.text = (intent.extras?.get("title") as String)

        binding.showLocation.setText(intent.extras?.get("location") as String)
        binding.showDescription.setText(intent.extras?.get("description") as String)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
