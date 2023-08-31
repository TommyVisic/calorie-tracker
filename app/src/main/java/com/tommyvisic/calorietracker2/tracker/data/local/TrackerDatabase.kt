package com.tommyvisic.calorietracker2.tracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Our Room database that holds tracked food records.
 */
@Database(
    entities = [TrackedFoodEntity::class],
    version = 1
)
abstract class TrackerDatabase : RoomDatabase() {
    abstract val dao: TrackerDao
}