package ru.skilanov.io.notes.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.skilanov.io.notes.model.Note;
import ru.skilanov.io.notes.model.NoteDao;

/**
 * Created by Semen on 12.03.2018.
 */
@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract NoteDao noteDao();
}
