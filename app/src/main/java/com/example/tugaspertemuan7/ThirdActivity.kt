package com.example.tugaspertemuan7

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugaspertemuan7.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("TITLE")
        binding.txtTitle.text = title

        val date = intent.getStringExtra("SELECTED_DATE")
        binding.txtdate.text = date

        val time = intent.getStringExtra("SELECTED_TIME")
        binding.txtTime.text = time

        val repeat = intent.getStringExtra("SELECTED_REPEAT")
        binding.txtRepeat.text = repeat

        binding.btnAddTask.setOnClickListener {

            val intent = Intent(this, secondActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
