package com.example.myinternalexternalstorage

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.myinternalexternalstorage.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {

            } else {

            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addListener()
    }
    private fun addListener(){
        binding.save.setOnClickListener {
            val text=binding.editText.text.toString()
            if(ContextCompat.checkSelfPermission(applicationContext,Manifest.permission.WRITE_EXTERNAL_STORAG)== PackageManager.PERMISSION_GRANTED){

                saveTextFile(text)
                Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
            }
            else{
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }

        }
    }

    private fun saveTextFile(text: String) {
        val path=Environment.getExternalStorageDirectory().path
        val fileName="sample.txt"
        val file=File("$path/$fileName")
        file.writeText(text)
    }
}