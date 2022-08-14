package io.github.miniwangdali.myfirstapp

data class Question(
    val id: Int,
    val question: String,
    val image: Int,
    val options: ArrayList<String>,
    val correctAnswer: Int
)
