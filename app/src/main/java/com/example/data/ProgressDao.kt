package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfileFlow(): Flow<UserProfile?>

    @Query("SELECT * FROM user_profile WHERE id = 1")
    suspend fun getUserProfile(): UserProfile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(profile: UserProfile)

    @Query("SELECT * FROM subject_progress")
    fun getAllProgressFlow(): Flow<List<SubjectProgress>>

    @Query("SELECT * FROM subject_progress WHERE subject = :subject ORDER BY levelIndex ASC")
    fun getSubjectProgressFlow(subject: String): Flow<List<SubjectProgress>>

    @Query("SELECT * FROM subject_progress WHERE id = :id LIMIT 1")
    suspend fun getProgressById(id: String): SubjectProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectProgressList(list: List<SubjectProgress>)

    @Update
    suspend fun updateSubjectProgress(progress: SubjectProgress)

    @Query("SELECT COUNT(*) FROM subject_progress")
    suspend fun getSubjectProgressCount(): Int
}
