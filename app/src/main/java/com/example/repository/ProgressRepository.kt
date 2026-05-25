package com.example.repository

import com.example.data.ProgressDao
import com.example.data.SubjectProgress
import com.example.data.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class ProgressRepository(val progressDao: ProgressDao) {

    val userProfile: Flow<UserProfile?> = progressDao.getUserProfileFlow()
    val allProgress: Flow<List<SubjectProgress>> = progressDao.getAllProgressFlow()

    suspend fun getProgressById(id: String): SubjectProgress? {
        return progressDao.getProgressById(id)
    }

    fun getSubjectProgress(subject: String): Flow<List<SubjectProgress>> {
        return progressDao.getSubjectProgressFlow(subject)
    }

    suspend fun getUserProfileDirect(): UserProfile? {
        return progressDao.getUserProfile()
    }

    suspend fun saveUserProfile(profile: UserProfile) {
        progressDao.insertUserProfile(profile)
    }

    suspend fun updateProgress(progress: SubjectProgress) {
        progressDao.updateSubjectProgress(progress)
    }

    suspend fun seedInitialDataIfNecessary() {
        // Only seed if no levels exist in the database
        val count = progressDao.getSubjectProgressCount()
        if (count == 0) {
            val defaultLevels = mutableListOf<SubjectProgress>()

            // 1. Math Levels (8 levels)
            val mathLevels = listOf(
                LevelInfo(1, "Addition", "Easy", true),
                LevelInfo(2, "Subtraction", "Easy", false),
                LevelInfo(3, "Multiplication", "Medium", false),
                LevelInfo(4, "Division", "Medium", false),
                LevelInfo(5, "Fractions", "Medium", false),
                LevelInfo(6, "Algebra", "Hard", false),
                LevelInfo(7, "Percentage", "Hard", false),
                LevelInfo(8, "Trigonometry Basic", "Hard", false)
            )
            for (level in mathLevels) {
                defaultLevels.add(
                    SubjectProgress(
                        id = "Math_${level.index}",
                        subject = "Math",
                        levelIndex = level.index,
                        levelName = level.name,
                        difficulty = level.difficulty,
                        starsScore = 0,
                        isUnlocked = level.isUnlocked,
                        highScore = 0
                    )
                )
            }

            // 2. English Levels (6 levels)
            val englishLevels = listOf(
                LevelInfo(1, "Alphabet", "Easy", true),
                LevelInfo(2, "Words", "Easy", false),
                LevelInfo(3, "Grammar", "Medium", false),
                LevelInfo(4, "Tenses", "Medium", false),
                LevelInfo(5, "Sentence Correction", "Hard", false),
                LevelInfo(6, "Vocabulary", "Hard", false)
            )
            for (level in englishLevels) {
                defaultLevels.add(
                    SubjectProgress(
                        id = "English_${level.index}",
                        subject = "English",
                        levelIndex = level.index,
                        levelName = level.name,
                        difficulty = level.difficulty,
                        starsScore = 0,
                        isUnlocked = level.isUnlocked,
                        highScore = 0
                    )
                )
            }

            // 3. Hindi Levels (6 levels)
            val hindiLevels = listOf(
                LevelInfo(1, "वर्णमाला", "Easy", true),
                LevelInfo(2, "शब्द", "Easy", false),
                LevelInfo(3, "व्याकरण", "Medium", false),
                LevelInfo(4, "पर्यायवाची", "Medium", false),
                LevelInfo(5, "विलोम", "Hard", false),
                LevelInfo(6, "वाक्य", "Hard", false)
            )
            for (level in hindiLevels) {
                defaultLevels.add(
                    SubjectProgress(
                        id = "Hindi_${level.index}",
                        subject = "Hindi",
                        levelIndex = level.index,
                        levelName = level.name,
                        difficulty = level.difficulty,
                        starsScore = 0,
                        isUnlocked = level.isUnlocked,
                        highScore = 0
                    )
                )
            }

            // Insert everything
            progressDao.insertSubjectProgressList(defaultLevels)
        }

        // Check if there is an existing UserProfile, if not save a default
        val existingProfile = progressDao.getUserProfile()
        if (existingProfile == null) {
            progressDao.insertUserProfile(
                UserProfile(
                    id = 1,
                    name = "",
                    email = "",
                    phoneNumber = "",
                    streakDays = 1,
                    lastActiveTimestamp = System.currentTimeMillis(),
                    totalStars = 0,
                    rank = "Novice Tutor",
                    registrationCompleted = false
                )
            )
        }
    }

    private data class LevelInfo(
        val index: Int,
        val name: String,
        val difficulty: String,
        val isUnlocked: Boolean
    )
}
