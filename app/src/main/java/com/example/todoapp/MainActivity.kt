package com.example.todoapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.Toast
import android.content.SharedPreferences
import com.example.todoapp.data.QuestionData
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferences: SharedPreferences


    private val questions = QuestionData.questions
    private val correctAnswers = arrayOf(1 , 1 , 2)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("USER_NAME")
        binding.userNameText.text = "Welcome, Nassime!"


        sharedPreferences = getSharedPreferences("quiz_prefs", MODE_PRIVATE)

        val highestScore = sharedPreferences.getInt("highest_score", 0)

        binding.highScoreText.text = "Highest Score : $highestScore"

        displayQuestion()

        binding.option1Button.setOnClickListener {
            checkAnswer(0)
        }
         binding.option2Button.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener {
            checkAnswer(2)
        }
        binding.restartButton.setOnClickListener {
            restartQuiz()
        }

    }

    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }


    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3Button.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showResults(){
        val highestScore = sharedPreferences.getInt("highest_score" , 0)

        if (score > highestScore){
            sharedPreferences.edit().putInt("highest_score" , score).apply()
            Toast.makeText(this, "New High Score: $score!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Your score: $score (Highest: $highestScore)", Toast.LENGTH_LONG).show()
        }

        binding.restartButton.isEnabled = true
    }

    private fun displayQuestion(){
        val question = questions[currentQuestionIndex]
        
        binding.questionText.alpha = 0f
        binding.questionText.text = question.questionText
        binding.questionText.animate().alpha(1f).setDuration(500).start()

        binding.questionText.text = question.questionText
        binding.option1Button.text = question.options[0]
        binding.option2Button.text = question.options[1]
        binding.option3Button.text = question.options[2]

        binding.progressBar.progress = ((currentQuestionIndex + 1) * 100 / questions.size)

        resetButtonColors()

    }

    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex < questions.size - 1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()} , 1000)
        } else {
            showResults()
        }
    }

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
    }
}