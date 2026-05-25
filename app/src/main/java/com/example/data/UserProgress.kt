package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey val id: Int = 1,
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val streakDays: Int = 1,
    val lastActiveTimestamp: Long = System.currentTimeMillis(),
    val totalStars: Int = 0,
    val rank: String = "Beginner",
    val registrationCompleted: Boolean = false
)

@Entity(tableName = "subject_progress")
data class SubjectProgress(
    @PrimaryKey val id: String, // format: "Subject_LevelIndex" (e.g. "Math_1")
    val subject: String,        // "Math", "English", "Hindi"
    val levelIndex: Int,        // 1, 2, 3...
    val levelName: String,
    val difficulty: String,     // "Easy", "Medium", "Hard"
    val starsScore: Int = 0,    // number of stars (0 to 3)
    val isUnlocked: Boolean = false,
    val highScore: Int = 0      // out of 10
)
