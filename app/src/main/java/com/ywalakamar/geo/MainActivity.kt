package com.ywalakamar.geo

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ywalakamar.geo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    private val QuestionBank=listOf(
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
        questionTextView=findViewById(R.id.question_text_view)

        /*Setting listeners*/
        trueButton.setOnClickListener { view:View->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view:View->
            checkAnswer(false)
        }

        nextButton.setOnClickListener { view:View->
            currentIndex=(currentIndex+1)%QuestionBank.size
            updateQuestion()
        }

        val questionId=QuestionBank[currentIndex].id
        questionTextView.setText(questionId)

    }

    private fun updateQuestion(){
        val questionId=QuestionBank[currentIndex].id
        questionTextView.setText(questionId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer=QuestionBank[currentIndex].answer
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
