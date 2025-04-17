package com.example.todoapp.data

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)