package io.github.miniwangdali.myfirstapp

object Constants {
    const val USER_NAME = "username"
    const val TOTAL_QUESTIONS = "total_questions"
    const val CORRECT_ANSWERS = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>();
        questionsList.add(
            Question(
                1,
                "What country does this flag belong to?",
                R.drawable.ic_flag_of_argentina,
                arrayListOf("Argentina", "Austrialia", "Armenia", "Austria"),
                0
            )
        )
        questionsList.add(
            Question(
                2, "What country does this flag belong to?",
                R.drawable.ic_flag_of_australia,
                arrayListOf("Angola", "Austria", "Australia", "Armenia"),
                2
            )
        )
        questionsList.add(
            Question(
                3, "What country does this flag belong to?",
                R.drawable.ic_flag_of_brazil,
                arrayListOf("Belarus", "Belize", "Brunei", "Brazil"),
                3
            )
        )
        questionsList.add(
            Question(
                4, "What country does this flag belong to?",
                R.drawable.ic_flag_of_belgium,
                arrayListOf("Bahamas", "Belgium", "Barbados", "Belize"),
                1
            )
        )
        questionsList.add(
            Question(
                5, "What country does this flag belong to?",
                R.drawable.ic_flag_of_fiji,
                arrayListOf("Gabon", "France", "Fiji", "Finland"),
                2
            )
        )
        questionsList.add(
            Question(
                6, "What country does this flag belong to?",
                R.drawable.ic_flag_of_germany,
                arrayListOf("Germany", "Georgia", "Greece", "none of these"),
                0
            )
        )
        questionsList.add(
            Question(
                7, "What country does this flag belong to?",
                R.drawable.ic_flag_of_denmark,
                arrayListOf("Dominica", "Egypt", "Denmark", "Ethiopia"),
                2
            )
        )
        questionsList.add(
            Question(
                8, "What country does this flag belong to?",
                R.drawable.ic_flag_of_india,
                arrayListOf("Ireland", "Iran", "Hungary", "India"),
                3
            )
        )
        questionsList.add(
            Question(
                9, "What country does this flag belong to?",
                R.drawable.ic_flag_of_new_zealand,
                arrayListOf("Australia", "New Zealand", "Tuvalu", "United States of America"),
                1
            )
        )
        questionsList.add(
            Question(
                10, "What country does this flag belong to?",
                R.drawable.ic_flag_of_kuwait,
                arrayListOf("Kuwait", "Jordan", "Sudan", "Palestine"),
                0
            )
        )
        return questionsList;
    }
}