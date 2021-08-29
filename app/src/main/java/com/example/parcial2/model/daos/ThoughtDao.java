package com.example.parcial2.model.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.parcial2.model.pojo.Thought;

import java.util.List;

@Dao
public interface ThoughtDao {
    @Query("SELECT * FROM thoughts")
    List<Thought> getAll();

    @Insert
    void insert(Thought thought);

    @Update
    void update(Thought thought);

    @Insert
    void insertAll(List<Thought> thought);

    @Delete
    void delete(Thought thought);

    @Query("DELETE FROM thoughts")
    void macheteandoAndo();
}