package com.example.controledejogos.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.controledejogos.data.dao.GamesDao
import com.example.controledejogos.data.entity.Player
import com.example.controledejogos.data.entity.Team

@Database(entities = [Player::class, Team::class], version = 1, exportSchema = false)
abstract class GamesDatabase: RoomDatabase() {

    abstract fun gamesDao(): GamesDao

    companion object{
        @Volatile
        private var INSTANCE: GamesDatabase? = null

        fun getDatabase(context: Context): GamesDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GamesDatabase::class.java,
                    "Jogos"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}