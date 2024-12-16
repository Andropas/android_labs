package com.lab5.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lab5.data.dao.SubjectDao
import com.lab5.data.dao.SubjectLabsDao
import com.lab5.data.entity.SubjectEntity
import com.lab5.data.entity.SubjectLabEntity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(entities = [SubjectEntity::class, SubjectLabEntity::class], version = 1)
abstract class Lab5Database : RoomDatabase() {
    abstract val subjectsDao: SubjectDao
    abstract val subjectLabsDao: SubjectLabsDao
}

object DatabaseStorage {
    private val coroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        },
    )

    private var _database: Lab5Database? = null

    fun getDatabase(context: Context): Lab5Database {
        if (_database != null) return _database as Lab5Database

        else {
            _database = Room.databaseBuilder(
                context,
                Lab5Database::class.java, "lab4Database"
            ).build()

            preloadData()

            return _database as Lab5Database
        }
    }

    private fun preloadData() {
        val listOfSubject = listOf(
            SubjectEntity(id = 1, title = "Програмування мобільних додатків"),
            SubjectEntity(id = 2, title = "Розгортання інформаційно-комунікаційних систем"),
            SubjectEntity(id = 3, title = "Економіка"),
        )

        val listOfSubjectLabs = listOf(
            SubjectLabEntity(
                id = 1,
                subjectId = 1,
                title = "Встановлення Android Studio",
                description = "Встановлення середовища розробки Android Studio",
                comment = "Виконано",
                isCompleted = true
            ),
            SubjectLabEntity(
                id = 2,
                subjectId = 1,
                title = "Вивчення Navigation Controller",
                description = "Створити додаток з прикладом навігації між двома екранами",
                comment = "Потрібно поправити звіт",
                inProgress = true
            ),
            SubjectLabEntity(
                id = 3,
                subjectId = 2,
                title = "Встановлення середовища VirtualBox та Docker",
                description = "Встановити програми, які знадовбляться для подальшого вивчення",
                comment = "Оцінено",
                isCompleted = true
            ),
            SubjectLabEntity(
                id = 4,
                subjectId = 2,
                title = "Розгортання першого контейнера",
                description = "Розгорнути контейнер на play.with.docker.com",
                comment = "",
                inProgress = true
            ),
            SubjectLabEntity(
                id = 5,
                subjectId = 3,
                title = "Розробка стартапу",
                description = "Розробити стартап на власну тему",
                comment = "",
                isCompleted = true
            )
        )

        listOfSubject.forEach { subject ->
            coroutineScope.launch {
                _database?.subjectsDao?.addSubject(subject)
            }
        }
        listOfSubjectLabs.forEach { lab ->
            coroutineScope.launch {
                _database?.subjectLabsDao?.addSubjectLab(lab)
            }
        }
    }
}