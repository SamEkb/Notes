package ru.skilanov.io.notes;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.skilanov.io.notes.database.AppDatabase;
import ru.skilanov.io.notes.model.Note;

/**
 * Created by Semen on 12.03.2018.
 */

public class NoteActivity extends AppCompatActivity {
    private EditText mTitle;
    private EditText mDescription;
    private Button mSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);

        final AppDatabase dp = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "note").allowMainThreadQueries().build();

        mTitle = findViewById(R.id.tetle_edit_id);
        mDescription = findViewById(R.id.description_edit_id);
        mSave = findViewById(R.id.save_button_id);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp.noteDao().insert(new Note(mTitle.getText().toString(), mDescription.getText().toString()));
                startActivity(new Intent(NoteActivity.this, MainActivity.class));
            }
        });

    }
}
