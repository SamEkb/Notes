package ru.skilanov.io.notes;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.skilanov.io.notes.database.AppDatabase;
import ru.skilanov.io.notes.model.Note;

public class MainActivity extends AppCompatActivity {
    //    List<Note> notes;
    private FloatingActionButton mAddNoteFloatingActionButton;
    private RecyclerView mNotesRecyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        notes = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            Note note = new Note("test", "teeest");
//            notes.add(note);
//        }

        AppDatabase dp = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "note").allowMainThreadQueries().build();

        List<Note> notes = dp.noteDao().getAllNote();

        mNotesRecyclerView = findViewById(R.id.recycler_view_note_id);
        mNotesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesAdapter(notes);
        mNotesRecyclerView.setAdapter(adapter);

        mAddNoteFloatingActionButton = findViewById(R.id.add_note_floating_action_button_id);
        mAddNoteFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);
            }
        });
    }

    private class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesHolder> {
        List<Note> notes;

        public NotesAdapter(List<Note> notes) {
            this.notes = notes;
        }

        @Override
        public NotesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_row,
                    parent, false);
            return new NotesHolder(view);
        }

        @Override
        public void onBindViewHolder(NotesHolder holder, int position) {
            holder.mTitleTextView.setText(notes.get(position).getTitle());
            holder.mDescriptionTextView.setText(notes.get(position).getDescription());
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }

        public class NotesHolder extends RecyclerView.ViewHolder {
            private TextView mTitleTextView;
            private TextView mDescriptionTextView;

            public NotesHolder(View itemView) {
                super(itemView);

                mTitleTextView = itemView.findViewById(R.id.note_title_id);
                mDescriptionTextView = itemView.findViewById(R.id.note_description_id);
            }
        }
    }
}
