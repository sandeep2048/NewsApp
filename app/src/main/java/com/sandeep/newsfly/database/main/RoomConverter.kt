

package com.sandeep.newsfly.database.main

import androidx.room.TypeConverter
import com.sandeep.newsfly.model.responses.Source

class RoomConverter {

    @TypeConverter
    fun fromSource(source: Source) : String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }

}