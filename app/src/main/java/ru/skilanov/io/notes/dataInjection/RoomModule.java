package ru.skilanov.io.notes.dataInjection;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.skilanov.io.notes.database.AppDatabase;
import ru.skilanov.io.notes.database.NoteService;
import ru.skilanov.io.notes.database.NoteServiceImpl;
import ru.skilanov.io.notes.model.NoteDao;

/**
 * Класс модуль предоставляющий базу данных и DAO.
 */

@Module
public class RoomModule {
    private AppDatabase appDatabase;

    /**
     * Создаем базу данных.
     *
     * @param mApplication Application
     */
    public RoomModule(Application mApplication) {
        appDatabase = Room.databaseBuilder(mApplication, AppDatabase.class, "note")
                .allowMainThreadQueries().build();
    }

    /**
     * Метод синглтон возвращает базу данных.
     *
     * @return appDatabase
     */
    @Singleton
    @Provides
    AppDatabase providesRoomDatabase() {
        return appDatabase;
    }

    /**
     * Метод синглтон возвращает DAO
     *
     * @param noteDatabase AppDatabase
     * @return noteDao
     */
    @Singleton
    @Provides
    NoteDao providesNoteDao(AppDatabase noteDatabase) {
        return noteDatabase.noteDao();
    }

    /**
     * Метод синглтон возвращает реализацию сервиса.
     *
     * @param noteDao NoteDao
     * @return NoteServiceImpl(noteDao)
     */
    @Singleton
    @Provides
    NoteService noteService(NoteDao noteDao) {
        return new NoteServiceImpl(noteDao);
    }
}
