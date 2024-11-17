package br.edu.up.appdereceitas.dados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.edu.up.appdereceitas.dados.converters.Converters
import br.edu.up.appdereceitas.dados.dao.ReceitaDao
import br.edu.up.appdereceitas.dados.model.Receita


@Database(entities = [Receita::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TasteBookDatabase : RoomDatabase() {
    abstract fun receitaDao(): ReceitaDao
    companion object {

        fun abrirDataBase(context: Context): TasteBookDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                TasteBookDatabase::class.java, "tasteBook.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
