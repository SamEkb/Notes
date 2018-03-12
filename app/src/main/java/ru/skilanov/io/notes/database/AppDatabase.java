package ru.skilanov.io.notes.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.skilanov.io.notes.model.Note;
import ru.skilanov.io.notes.model.NoteDao;

/**
 * Абстратный класс базы данных.
 */
@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    /**
     * Абстрантный метод dao.
     *
     * @return NoteDao
     */
    public abstract NoteDao noteDao();
}
