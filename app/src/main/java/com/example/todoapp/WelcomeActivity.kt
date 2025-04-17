package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val userName = nameInput.text.toString().trim()

            if (userName.isEmpty()) {
                Toast.makeText(this, "Please enter your name!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USER_NAME", userName)
                startActivity(intent)
                finish()
            }
        }
    }
}
