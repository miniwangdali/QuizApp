package io.github.miniwangdali.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        val finishButton = findViewById<Button>(R.id.finishButton)

        nameTextView?.text = intent.getStringExtra(Constants.USER_NAME)
        scoreTextView?.text =
            "Your score is ${intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)} out of ${
                intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
            }"

        finishButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}