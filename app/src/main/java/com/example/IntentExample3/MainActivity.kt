package id.ac.smpn8bks.ardiansyah.IntentExample3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ac.smpn8bks.ardiansyah.IntentExample3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val SECOND_ACTIVITY_REQUEST = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            val weight = binding.etWeight.text.toString().toDoubleOrNull()
            val height = binding.etHeight.text.toString().toDoubleOrNull()

            if (weight == null || height == null) {
                binding.tvResult.text = "Masukkan data dengan benar!"
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("weight", weight)
                putExtra("height", height)
            }
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SECOND_ACTIVITY_REQUEST && resultCode == Activity.RESULT_OK) {
            val bmiResult = data?.getStringExtra("bmi_result")
            binding.tvResult.text = bmiResult ?: "Tidak ada hasil"
        }
    }
}