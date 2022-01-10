package com.example.gallery

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gallery.databinding.ActivityAddBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import android.graphics.Bitmap

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.room.Room

import java.io.ByteArrayOutputStream


class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var pictureDao: PictureDao
    private var pictureSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "picturedatabase"
        ).allowMainThreadQueries().enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration().build()

        pictureDao = db.pictureDao()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun pickImage(view: android.view.View) {
        ImagePicker.with(this)
            .crop()
            .compress(1024)
            .maxResultSize(1080, 1080)
            .start()
    }

    // Handle when picture is selected
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                val uri: Uri = data?.data!!

                // Use Uri object instead of File to avoid storage permissions
                binding.imageViewPicture.setImageURI(uri)

                pictureSelected = true
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Selection Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }



    fun savePicture(view: android.view.View) {
        if (pictureSelected) {
            println(binding.editTextTitle)
            println(binding.editTextLocation)
            println(binding.editTextDescription)

            val blob = Utils.imageViewToBytes(binding.imageViewPicture)
            val picture = Picture (
                blob = blob,
                title = binding.editTextTitle.text.toString(),
                description = binding.editTextDescription.text.toString(),
                location = binding.editTextLocation.text.toString(),
                favorite = false
            )
            pictureDao.insertAll(picture)

            onBackPressed()
        } else {
            Toast.makeText(this, "You have to select a picture first!", Toast.LENGTH_LONG).show()
        }
    }

}
