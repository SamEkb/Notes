package ru.skilanov.io.notes.dataInjection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import ru.skilanov.io.notes.activities.MainActivity;
import ru.skilanov.io.notes.activities.NoteActivity;
import ru.skilanov.io.notes.database.AppDatabase;
import ru.skilanov.io.notes.database.NoteService;
import ru.skilanov.io.notes.model.NoteDao;

/**
 * Класс компонент, ктороый содежит инфрмацию о модулях.
 */

@Singleton
@Component(dependencies = {}, modules = {AppModule.class, RoomModule.class})
public interface AppComponent {
    /**
     * Зависимостья для MainActivity
     *
     * @param mainActivity MainActivity
     */
    void injectMainActivity(MainActivity mainActivity);

    /**
     * Зависимость для NoteActivity
     *
     * @param noteActivity NoteActivity
     */
    void injectNoteActivity(NoteActivity noteActivity);

    NoteDao noteDao();

    AppDatabase appDatabase();

    NoteService noteRepository();

    Application application();
}

