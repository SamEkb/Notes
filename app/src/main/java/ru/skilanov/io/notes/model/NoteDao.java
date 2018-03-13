package ru.skilanov.io.notes.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Data access object интерфейс, содержащий операции над заметками.
 */
@Dao
public interface NoteDao {

    /**
     * Получить список заметок.
     *
     * @return List
     */
    @Query("SELECT * FROM Note")
    List<Note> getAllNote();

    /**
     * Метод выбора по id.
     *
     * @param id int
     * @return Note
     */
    @Query("SELECT * FROM note WHERE id = :id")
    Note getById(int id);

    /**
     * Ввести новую заметку.
     *
     * @param note Note
     */
    @Insert
    void insert(Note note);

    /**
     * Метод удаления заметки.
     *
     * @param id int
     */
    @Query("DELETE FROM note WHERE id = :id")
    int delete(int id);
}
