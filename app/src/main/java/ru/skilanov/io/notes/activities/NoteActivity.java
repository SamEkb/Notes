package ru.skilanov.io.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import ru.skilanov.io.notes.R;
import ru.skilanov.io.notes.dataInjection.AppModule;
import ru.skilanov.io.notes.dataInjection.DaggerAppComponent;
import ru.skilanov.io.notes.dataInjection.RoomModule;
import ru.skilanov.io.notes.database.NoteService;
import ru.skilanov.io.notes.model.Note;

/**
 * Класс заметки.
 */

public class NoteActivity extends AppCompatActivity {
    @Inject
    NoteService noteService;
    private EditText mTitle;
    private EditText mDescription;
    private Button mSave;
    private Button mDelete;

    /**
     * Метод жизненного цикла заполняющий create_note
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);

        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .roomModule(new RoomModule(getApplication()))
                .build().injectNoteActivity(this);

        getIncomingIntent();

        mTitle = findViewById(R.id.tetle_edit_id);
        mDescription = findViewById(R.id.description_edit_id);
        mSave = findViewById(R.id.save_button_id);

        mSave.setOnClickListener(new View.OnClickListener() {
            /**
             * Метод заводит в базу данных новую заметку и возвращает на страницу главной
             * активности.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                noteService.insert(new Note(mTitle.getText().toString(), mDescription.getText().toString()));
                startActivity(new Intent(NoteActivity.this, MainActivity.class));
            }
        });

        mDelete = findViewById(R.id.delete_button_id);
        mDelete.setOnClickListener(new View.OnClickListener() {
            /**
             * Метод получает id, затем удаляет заметку из базы данных.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                int noteId = getIntent().getIntExtra("noteId", 0);
                noteService.delete(noteId);
                startActivity(new Intent(NoteActivity.this, MainActivity.class));
            }
        });

    }

    /**
     * Метод проверяет и получает дополнительную информацию от  MainActivity.
     */
    public void getIncomingIntent() {
        if (getIntent().hasExtra("noteTitle") && getIntent().hasExtra("noteDescription")) {
            String titleIntent = getIntent().getStringExtra("noteTitle");
            String descriptionIntent = getIntent().getStringExtra("noteDescription");

            setEditTexts(titleIntent, descriptionIntent);
        }
    }

    /**
     * Задает текст для полученной дополнительной информации.
     *
     * @param title       String
     * @param description String
     */
    private void setEditTexts(String title, String description) {
        mTitle = findViewById(R.id.tetle_edit_id);
        mTitle.setText(title);
        mDescription = findViewById(R.id.description_edit_id);
        mDescription.setText(description);
    }
}
