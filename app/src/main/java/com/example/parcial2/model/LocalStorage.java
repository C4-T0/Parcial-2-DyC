package com.example.parcial2.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.parcial2.model.daos.ThoughtDao;
import com.example.parcial2.model.pojo.Thought;

@Database(entities = {Thought.class}, version = 2)
public abstract class LocalStorage extends RoomDatabase {

    public abstract ThoughtDao thoughtDao();

    private static LocalStorage localStorage;
    public static LocalStorage getLocalStorage(Context context) {
        if (localStorage == null) {
            localStorage = Room.databaseBuilder(context, LocalStorage.class, "parcial2-db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return localStorage;
    }
}
