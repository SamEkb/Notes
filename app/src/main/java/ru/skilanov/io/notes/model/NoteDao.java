package ru.skilanov.io.notes.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Semen on 12.03.2018.
 */
@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note")
    List<Note> getAllNote();

    @Insert
    void insert(Note note);
}
