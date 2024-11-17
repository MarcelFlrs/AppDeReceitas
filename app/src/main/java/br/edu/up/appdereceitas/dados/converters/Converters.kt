package br.edu.up.appdereceitas.dados.converters

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromListToString(list: List<String>?): String {
        return list?.joinToString(",") ?: ""
    }

    @TypeConverter
    fun fromStringToList(data: String?): List<String> {
        return data?.split(",") ?: emptyList()
    }
}