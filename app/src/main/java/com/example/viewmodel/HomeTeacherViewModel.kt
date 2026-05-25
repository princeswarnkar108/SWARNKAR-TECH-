package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.util.SoundEffects
import com.example.data.Question
import com.example.data.QuestionProvider
import com.example.data.SubjectProgress
import com.example.data.UserProfile
import com.example.repository.ProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeTeacherViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProgressRepository

    init {
        val database = AppDatabase.getDatabase(application)
        repository = ProgressRepository(database.progressDao)
        viewModelScope.launch {
            repository.seedInitialDataIfNecessary()
        }
    }

    // Expose Data Flows
    val userProfile: StateFlow<UserProfile?> = repository.userProfile
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val mathProgress: StateFlow<List<SubjectProgress>> = repository.getSubjectProgress("Math")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val englishProgress: StateFlow<List<SubjectProgress>> = repository.getSubjectProgress("English")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val hindiProgress: StateFlow<List<SubjectProgress>> = repository.getSubjectProgress("Hindi")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val allProgress: StateFlow<List<SubjectProgress>> = repository.allProgress
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Quiz states
    private val _currentSubject = MutableStateFlow("Math")
    val currentSubject: StateFlow<String> = _currentSubject.asStateFlow()

    private val _currentLevelIndex = MutableStateFlow(1)
    val currentLevelIndex: StateFlow<Int> = _currentLevelIndex.asStateFlow()

    private val _quizQuestions = MutableStateFlow<List<Question>>(emptyList())
    val quizQuestions: StateFlow<List<Question>> = _quizQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedOption = MutableStateFlow<String?>(null)
    val selectedOption: StateFlow<String?> = _selectedOption.asStateFlow()

    private val _showFeedback = MutableStateFlow(false)
    val showFeedback: StateFlow<Boolean> = _showFeedback.asStateFlow()

    private val _isCorrect = MutableStateFlow(false)
    val isCorrect: StateFlow<Boolean> = _isCorrect.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _quizCompleted = MutableStateFlow(false)
    val quizCompleted: StateFlow<Boolean> = _quizCompleted.asStateFlow()

    private val _hasFailedAttemptOnCurrent = MutableStateFlow(false)

    // Dynamic UI states
    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    fun toggleDarkMode() {
        _isDarkMode.value = !_isDarkMode.value
    }

    // Operations
    fun loginOrSignUp(name: String, email: String, phone: String) {
        viewModelScope.launch {
            val currentProfile = repository.getUserProfileDirect() ?: UserProfile()
            val updatedProfile = currentProfile.copy(
                name = name,
                email = email,
                phoneNumber = phone,
                registrationCompleted = true
            )
            repository.saveUserProfile(updatedProfile)
        }
    }

    fun logout() {
        viewModelScope.launch {
            val currentProfile = repository.getUserProfileDirect() ?: UserProfile()
            val resetProfile = currentProfile.copy(
                registrationCompleted = false
            )
            repository.saveUserProfile(resetProfile)
        }
    }

    fun startQuiz(subject: String, levelIndex: Int) {
        _currentSubject.value = subject
        _currentLevelIndex.value = levelIndex
        
        val questions = QuestionProvider.getQuestions(subject, levelIndex)
        _quizQuestions.value = questions
        
        _currentQuestionIndex.value = 0
        _selectedOption.value = null
        _showFeedback.value = false
        _isCorrect.value = false
        _score.value = 0
        _quizCompleted.value = false
        _hasFailedAttemptOnCurrent.value = false
    }

    fun selectOption(option: String) {
        if (!_showFeedback.value) {
            _selectedOption.value = option
        }
    }

    fun submitAnswer() {
        val questions = _quizQuestions.value
        val index = _currentQuestionIndex.value
        val selected = _selectedOption.value

        if (questions.isEmpty() || index >= questions.size || selected == null) return

        val correctOption = questions[index].correctAnswer
        val correct = selected == correctOption

        _isCorrect.value = correct
        _showFeedback.value = true

        if (correct) {
            SoundEffects.playCorrectSound()
            // If they got it right on the first try, award a point
            if (!_hasFailedAttemptOnCurrent.value) {
                _score.value += 1
            }
        } else {
            SoundEffects.playWrongSound()
            _hasFailedAttemptOnCurrent.value = true
        }
    }

    fun retryQuestion() {
        _selectedOption.value = null
        _showFeedback.value = false
        _isCorrect.value = false
    }

    fun nextQuestion() {
        val nextIndex = _currentQuestionIndex.value + 1
        if (nextIndex < _quizQuestions.value.size) {
            _currentQuestionIndex.value = nextIndex
            _selectedOption.value = null
            _showFeedback.value = false
            _isCorrect.value = false
            _hasFailedAttemptOnCurrent.value = false
        } else {
            completeQuizAndSaveProgress()
        }
    }

    private fun completeQuizAndSaveProgress() {
        _quizCompleted.value = true
        SoundEffects.playSuccessSound()

        val subject = _currentSubject.value
        val levelIdx = _currentLevelIndex.value
        val finalScore = _score.value // out of 10

        // Calculate stars
        // 10 correct -> 3 stars, 8-9 -> 2 stars, 5-7 -> 1 star, otherwise 0 stars
        val earnedStars = when {
            finalScore == 10 -> 3
            finalScore >= 8 -> 2
            finalScore >= 5 -> 1
            else -> 0
        }

        viewModelScope.launch {
            // Update level score in DB
            val progressId = "${subject}_$levelIdx"
            val currentProgress = repository.getProgressById(progressId) ?: SubjectProgress(
                id = progressId,
                subject = subject,
                levelIndex = levelIdx,
                levelName = "Level $levelIdx",
                difficulty = if (levelIdx <= 2) "Easy" else if (levelIdx <= 5) "Medium" else "Hard"
            )

            val updatedProgress = currentProgress.copy(
                starsScore = maxOf(currentProgress.starsScore, earnedStars),
                highScore = maxOf(currentProgress.highScore, finalScore)
            )
            repository.updateProgress(updatedProgress)

            // Unlock next level if they passed current level (earned at least 1 star / score >= 5)
            if (finalScore >= 5) {
                val nextLevelIdx = levelIdx + 1
                val nextLevelId = "${subject}_$nextLevelIdx"
                val nextLevel = repository.getProgressById(nextLevelId)
                if (nextLevel != null && !nextLevel.isUnlocked) {
                    repository.updateProgress(nextLevel.copy(isUnlocked = true))
                }
            }

            // Recalculate total stars for user profile
            val allProg = repository.getUserProfileDirect() ?: UserProfile()
            // We can fetch list of all progress
            // Wait, we can get list of progress from the direct flow or query
            // Let's perform a simple recalculation
            _currentQuestionIndex.value = 0 // use standard operations
            
            // To compute total stars, let's query all progress
            var calculatedStars = 0
            val allList = repository.progressDao.getUserProfile() // actually we want to get progress
            // Let's fetch all progress items and sum their scores
            val itemsStream = repository.progressDao.getSubjectProgressFlow("Math")
            // Instead, we can just do a recalculation inside a simple query or let's update profile directly
            // Let's retrieve all progress items from DB
            var mathStars = 0
            var englishStars = 0
            var hindiStars = 0

            // Query items simple
            val allItems = repository.progressDao.getUserProfile() // Placeholder query
            // To make it simple, let's query all progress records and sum the stars score!
            // Let's do that
        }

        // Streak & Total Stars update
        viewModelScope.launch {
            val currentProfile = repository.getUserProfileDirect() ?: UserProfile()
            
            // Recalculate total stars from DAO
            // Since we can query progress inside viewModel, let's do a simple update of profile
            var starsSum = 0
            val mathP = repository.progressDao.getSubjectProgressFlow("Math")
            // Instead of dealing with multiple flows in suspended coroutine, we can write a dedicated suspend function or query in DAO or do direct sum logic. Let's do a fast direct database query sum, or let's sum them programmatically
            
            // Let's compute stars from our current knowledge or update
            // Let's check: can we write a DAO query for total stars? Or sum them locally in memory.
            // Since we have allProgress Flow, we can get its latest state or query once
            // Let's query all progress items in database:
            val allProgressItems = mutableListOf<SubjectProgress>()
            for (sub in listOf("Math", "English", "Hindi")) {
                // Let's fetch progress of each subject
                // Wait! ProgressDao has `getAllProgressFlow()`. We can query it or simply fetch.
                // Let's check how many total stars are recorded now
            }
            
            // Let's look at updating the streak
            val now = System.currentTimeMillis()
            val lastActive = currentProfile.lastActiveTimestamp
            var newStreak = currentProfile.streakDays

            if (isDifferentDay(lastActive, now)) {
                if (isYesterday(lastActive, now)) {
                    newStreak += 1
                } else {
                    newStreak = 1
                }
            }

            // Calculate total stars sum sequentially
            var totalStarsNew = 0
            // Since we want to update the profile with recalculated stars, let's look through progress of each
            for (levelI in 1..8) {
                val p = repository.progressDao.getProgressById("Math_$levelI")
                if (p != null) totalStarsNew += p.starsScore
            }
            for (levelI in 1..6) {
                val p = repository.progressDao.getProgressById("English_$levelI")
                if (p != null) totalStarsNew += p.starsScore
                val ph = repository.progressDao.getProgressById("Hindi_$levelI")
                if (ph != null) totalStarsNew += ph.starsScore
            }

            // Save refreshed profile
            val updatedRank = when {
                totalStarsNew >= 30 -> "Grandmaster Achiever"
                totalStarsNew >= 15 -> "Scholar Tutor"
                totalStarsNew >= 5 -> "Active Learner"
                else -> "Beginner"
            }

            val savedProfile = currentProfile.copy(
                streakDays = newStreak,
                lastActiveTimestamp = now,
                totalStars = totalStarsNew,
                rank = updatedRank
            )
            repository.saveUserProfile(savedProfile)
        }
    }

    private fun isDifferentDay(time1: Long, time2: Long): Boolean {
        val cal1 = Calendar.getInstance().apply { timeInMillis = time1 }
        val cal2 = Calendar.getInstance().apply { timeInMillis = time2 }
        return cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR) ||
                cal1.get(Calendar.DAY_OF_YEAR) != cal2.get(Calendar.DAY_OF_YEAR)
    }

    private fun isYesterday(time1: Long, time2: Long): Boolean {
        val cal1 = Calendar.getInstance().apply { timeInMillis = time1 }
        val cal2 = Calendar.getInstance().apply { timeInMillis = time2 }
        cal1.add(Calendar.DAY_OF_YEAR, 1)
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeTeacherViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeTeacherViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
