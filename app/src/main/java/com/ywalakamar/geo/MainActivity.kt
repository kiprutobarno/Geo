package com.ywalakamar.geo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView

    private val questionBank=listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_middle_east, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

    private var currentIndex=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Reference widgets*/
        trueButton=findViewById(R.id.true_button)
        falseButton=findViewById(R.id.false_button)
        nextButton=findViewById(R.id.next_button)
        prevButton=findViewById(R.id.previous_button)
        questionTextView=findViewById(R.id.question_text_view)

        /*Setting listeners*/
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex=(currentIndex+1)%questionBank.size
            updateQuestion()
        }

        prevButton.setOnClickListener {

            currentIndex=(currentIndex+questionBank.size-1)%questionBank.size
            updateQuestion()
        }

        val questionId=questionBank[currentIndex].id
        questionTextView.setText(questionId)

    }

    private fun updateQuestion(){
        val questionId=questionBank[currentIndex].id
        questionTextView.setText(questionId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer=questionBank[currentIndex].answer
        val feedback=if(userAnswer==correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(
            this,
            feedback,
            Toast.LENGTH_SHORT
        ).show()
    }
}
