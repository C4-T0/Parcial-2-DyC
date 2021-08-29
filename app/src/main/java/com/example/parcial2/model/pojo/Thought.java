package com.example.parcial2.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "thoughts")
public class Thought {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer _id;
    @NonNull
    public String title;
    @NonNull
    public String description;
    @NonNull
    public Long creationDate;
    @NonNull
    public Integer category;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@NonNull Long creationDate) {
        this.creationDate = creationDate;
    }

    @NonNull
    public Integer getCategory() {
        return category;
    }

    public void setCategory(@NonNull Integer category) {
        this.category = category;
    }
}

