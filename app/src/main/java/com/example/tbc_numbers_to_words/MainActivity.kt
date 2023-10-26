package com.example.tbc_numbers_to_words

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.tbc_numbers_to_words.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.transformBtn.setOnClickListener {
            if (binding.integerET.text.toString().contains(".")){
                Toast.makeText(this, "Please enter an Integer, not a Double", Toast.LENGTH_SHORT).show()
            }else{
                binding.resultTV.text = transform(binding.integerET.text.toString().toInt())
            }

        }

    }

    private fun transform(num: Int): String {
        val firstTwenty = arrayOf(
            "",
            "ერთი",
            "ორი",
            "სამი",
            "ოთხი",
            "ხუთი",
            "ექვსი",
            "შვიდი",
            "რვა",
            "ცხრა",
            "ათი",
            "თერთმეტი",
            "თორმეტი",
            "ცამეტი",
            "თოთხმეტი",
            "თხუთმეტი",
            "თექვსმეტი",
            "ჩვიდმეტი",
            "თვრამეტი",
            "ცხრამეტი",
        )
        val aboveTwenty = arrayOf("ოცდა", "ორმოცდა", "სამოცდა", "ოთხმოცდა")
        val exactTwenties = arrayOf("", "ოცი", "ორმოცი", "სამოცი", "ოთხმოცი")
        val aboveHundred = arrayOf(
            "ას ", "ორას ", "სამას ", "ოთხას ", "ხუთას ",
            "ექვსას ", "შვიდას ", "რვაას ", "ცხრაას "
        )
        val exactHundreds = arrayOf(
            "ასი", "ორასი", "სამასი", "ოთხასი", "ხუთასი",
            "ექვსასი", "შვიდასი", "რვაასი", "ცხრაასი"
        )
        var number = num
        var res = ""
        //erteuli, tu sakmarisad gasagebi araa :D
        val single = number % 10
        //ateuli
        val tens = (number % 100) / 10
        if (number == 1000) return "ათასი"
        if (number in 1..19) return firstTwenty[number]
        if (number % 100 > 0) {
            //bolo cifri tu nulia, ricxvis sworad chawera
            if (single == 0) {
                //when-shi mowmdeba ateulebis tanrigze cifri da shesabamisi moqmedebebi aqvs
                when (tens) {
                    1 -> {
                        res = firstTwenty[10]
                        number /= 100
                    }
                    3, 5, 7, 9 -> {
                        res = aboveTwenty[tens / 2 - 1].plus(firstTwenty[10])
                        number /= 100
                    }
                    else -> {
                        res = exactTwenties[tens / 2]
                        number /= 100
                    }
                }
                if (number > 0) res = aboveHundred[number - 1].plus(res)
            } else {
                when (tens) {
                    0 -> {
                        res = firstTwenty[single]
                        number /= 100
                    }
                    1 -> {
                        res = firstTwenty[10 + single]
                        number /= 100
                    }
                    3, 5, 7, 9 -> {
                        res = aboveTwenty[tens / 2 - 1].plus(firstTwenty[10 + single])
                        number /= 100
                    }
                    2, 4, 6, 8 -> {
                        res = aboveTwenty[tens / 2 - 1].plus(firstTwenty[single])
                        number /= 100
                    }
                }
                if (number > 0) res = aboveHundred[number - 1].plus(res)
            }
        } else {
            res = exactHundreds[number / 100 - 1]
        }
        return res
    }
}