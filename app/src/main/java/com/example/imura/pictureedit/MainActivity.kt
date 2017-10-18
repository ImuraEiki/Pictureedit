package com.example.imura.pictureedit

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val TAG ="abc"
    private var fileUri: Uri? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button) as Button
        button.setOnClickListener{ view ->
            launchGallery()
//              launchNextActivity()
        }
        val button2: Button = findViewById(R.id.button2) as Button
        button.setOnClickListener { view -> }
            launchCamera()


    }
    private fun launchNextActivity (){
        val intent = Intent()
        intent.setClass(this, ResultActivity::class.java)
        startActivity(intent)
    }

    abstract var mCurrentPhotoPath: String

    private fun launchCamera() {
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        val fileUri = contentResolver
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager) != null) {
            mCurrentPhotoPath = fileUri.toString()
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(intent, TAKE_PHOTO_REQUEST)
        }
    }




    private fun launchGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_PICK
        intent.data =
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQUEST_CODE_GALLERY)
    }
    companion object {
        val REQUEST_CODE_GALLERY = 30
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_GALLERY -> {
                    fileUri = data?.data
                    Log.d(TAG, "gallery fileUri: " + fileUri)
                }
            }
        }
    }
}

