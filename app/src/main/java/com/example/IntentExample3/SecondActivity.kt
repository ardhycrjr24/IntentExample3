package id.ac.smpn8bks.ardiansyah.IntentExample3
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ac.smpn8bks.ardiansyah.IntentExample3.databinding.ActivitySecondBinding // <-- DIUBAH
import kotlin.math.pow

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weight = intent.getDoubleExtra("weight", 0.0)
        val height = intent.getDoubleExtra("height", 0.0)

        binding.tvDisplay.text = "Height: $height | Weight: $weight"

        binding.btnCalcBmi.setOnClickListener {
            // BMI formula: (weight lb / (height in)^2) * 703
            val bmi = if (height > 0) (weight / (height.pow(2))) * 703 else 0.0
            val category = when {
                bmi <= 0.0 -> "Invalid"
                bmi < 18.5 -> "Underweight"
                bmi < 25 -> "Normal weight"
                bmi < 30 -> "Overweight"
                else -> "Obese"
            }
            val result = "BMI : %.2f %s".format(bmi, category)

            val returnIntent = Intent().apply {
                putExtra("bmi_result", result)
            }
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}