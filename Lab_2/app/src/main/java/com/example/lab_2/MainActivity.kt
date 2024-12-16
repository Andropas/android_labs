package com.example.lab_2

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var buttonAddTask: Button
    private lateinit var taskListView: LinearLayout

    private val tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setPadding(16, 16, 16, 16)
        }

        editTextTask = EditText(this).apply {
            hint = "Введіть завдання"
            inputType = InputType.TYPE_CLASS_TEXT
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        buttonAddTask = Button(this).apply {
            text = "Додати"
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        taskListView = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        mainLayout.addView(editTextTask)
        mainLayout.addView(buttonAddTask)
        mainLayout.addView(taskListView)

        setContentView(mainLayout)

        buttonAddTask.setOnClickListener {
            val taskText = editTextTask.text.toString().trim()
            if (taskText.isNotEmpty()) {
                addTask(taskText)
                editTextTask.text.clear()
            } else {
                Toast.makeText(this, "Поле не може бути порожнім", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addTask(task: String) {
        tasks.add(task)

        val taskView = TextView(this).apply {
            text = task
            textSize = 18f
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 8, 0, 8)
            }
            setPadding(8, 8, 8, 8)
            setBackgroundResource(android.R.drawable.edit_text)
        }

        taskView.setOnClickListener {
            taskListView.removeView(taskView)
            tasks.remove(task)
            Toast.makeText(this, "Завдання видалено", Toast.LENGTH_SHORT).show()
        }

        taskListView.addView(taskView)
    }
}
