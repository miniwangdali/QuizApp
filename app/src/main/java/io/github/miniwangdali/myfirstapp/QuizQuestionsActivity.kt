package io.github.miniwangdali.myfirstapp

import android.content.Intent
import android.content.res.Resources
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity() {
    private var quizProgress: ProgressBar? = null;
    private var quizProgressText: TextView? = null;
    private var quizText: TextView? = null;
    private var quizImage: ImageView? = null;
    private var quizLinearLayout: LinearLayout? = null;
    private var quizSubmitButton: Button? = null;

    private var currentQuestion = 1
    private var selectedOption = -1
    private var correctQuestionCount = 0
    private var questionsList = Constants.getQuestions()
    private var optionsButtonList = ArrayList<ToggleButton>();

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        username = intent.getStringExtra(Constants.USER_NAME)

        quizProgress = findViewById(R.id.quizProgress)
        quizProgressText = findViewById(R.id.quizProgressText)
        quizText = findViewById(R.id.quizText)
        quizImage = findViewById(R.id.quizImage)
        quizLinearLayout = findViewById(R.id.quizLinearLayout)
        quizSubmitButton = findViewById(R.id.quizSubmit);

        quizSubmitButton?.setOnClickListener {
            onSubmitClick(it)
        }

        prepareQuestion()
    }

    private fun prepareQuestion() {
        val question = questionsList[currentQuestion - 1]
        quizProgress?.max = questionsList.size
        quizProgress?.progress = currentQuestion
        quizProgressText?.text = "${currentQuestion}/${questionsList.size}"
        quizText?.text = question.question
        quizImage?.setImageResource(question.image)
        optionsButtonList.forEach { v -> quizLinearLayout?.removeView(v) }
        optionsButtonList.clear()
        for (option in question.options) {
            val toggleButton = ToggleButton(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.topMargin = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                8f,
                Resources.getSystem().displayMetrics
            ).toInt()
            toggleButton.isAllCaps = false
            toggleButton.layoutParams = layoutParams
            toggleButton.isChecked = false
            toggleButton.background =
                AppCompatResources.getDrawable(this, R.drawable.default_option_border_bg)
            toggleButton.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
            toggleButton.setTypeface(toggleButton.typeface, Typeface.NORMAL)
            toggleButton.text = option
            toggleButton.textOn = option
            toggleButton.textOff = option

            toggleButton.setOnClickListener { v -> onOptionClick(v) }

            quizLinearLayout?.addView(toggleButton, quizLinearLayout!!.childCount - 2)
            optionsButtonList.add(toggleButton)
        }
        quizSubmitButton?.text = "Submit"
        quizSubmitButton?.isEnabled = false
    }

    private fun onSubmitClick(view: View) {
        if (selectedOption >= 0) {
            val question = questionsList[currentQuestion -1];
            for (i in 0 until optionsButtonList.size) {
                val optionButton = optionsButtonList[i];
                if (i === question.correctAnswer) {
                    optionButton.background =
                        AppCompatResources.getDrawable(this, R.drawable.correct_option_border_bg)
                } else if (i === selectedOption) {
                    optionButton.background =
                        AppCompatResources.getDrawable(this, R.drawable.incorrect_option_border_bg)
                } else {
                    optionButton.background =
                        AppCompatResources.getDrawable(this, R.drawable.default_option_border_bg)
                }
                optionButton.isEnabled = false
            }
            if (currentQuestion == questionsList.size) {
                quizSubmitButton?.text = "Finish"
                quizSubmitButton?.setOnClickListener { goToResult() }
            } else {
                quizSubmitButton?.text = "Next question"
            }
            if (selectedOption == question.correctAnswer) {
                correctQuestionCount ++
            }
            selectedOption = -1
        } else {
            currentQuestion ++
            prepareQuestion()
        }
    }

    private fun onOptionClick(view: View) {
        for (i in 0 until optionsButtonList.size) {
            val optionButton = optionsButtonList[i];
            if (optionButton === view) {
                optionButton.background =
                    AppCompatResources.getDrawable(this, R.drawable.selected_option_border_bg)
                optionButton.setTextColor(ContextCompat.getColor(this, R.color.dark_gray))
                optionButton.setTypeface(optionButton.typeface, Typeface.BOLD)
                selectedOption = i
            } else {
                optionButton.background =
                    AppCompatResources.getDrawable(this, R.drawable.default_option_border_bg)
                optionButton.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
                optionButton.setTypeface(optionButton.typeface, Typeface.NORMAL)
            }
        }
        quizSubmitButton?.isEnabled = true
    }

    private fun goToResult () {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(Constants.USER_NAME, username)
        intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
        intent.putExtra(Constants.CORRECT_ANSWERS, correctQuestionCount)
        startActivity(intent)
        finish()
    }

}