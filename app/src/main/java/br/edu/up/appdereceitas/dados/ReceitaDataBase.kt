package br.edu.up.appdereceitas.dados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.up.appdereceitas.dados.dao.ReceitaDao
import br.edu.up.appdereceitas.dados.model.Receita

@Database(entities = [Receita::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun receitaDao(): ReceitaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tastebook_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
