package ru.skilanov.io.notes.activities;

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

import javax.inject.Inject;

import ru.skilanov.io.notes.R;
import ru.skilanov.io.notes.dataInjection.AppModule;
import ru.skilanov.io.notes.dataInjection.DaggerAppComponent;
import ru.skilanov.io.notes.dataInjection.RoomModule;
import ru.skilanov.io.notes.database.NoteService;
import ru.skilanov.io.notes.model.Note;


/**
 * Главная активность, отвечающая за отображение списка заметок.
 */
public class MainActivity extends AppCompatActivity {
    @Inject
    public NoteService noteService;
    private FloatingActionButton mAddNoteFloatingActionButton;
    private RecyclerView mNotesRecyclerView;
    private RecyclerView.Adapter adapter;

    /**
     * Метод жизненного цикла активности, заполняющий activity_main и реализующий recycler view.
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .roomModule(new RoomModule(getApplication()))
                .build()
                .injectMainActivity(this);

        mNotesRecyclerView = findViewById(R.id.recycler_view_note_id);
        mNotesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesAdapter(noteService.findAll());
        mNotesRecyclerView.setAdapter(adapter);

        mAddNoteFloatingActionButton = findViewById(R.id.add_note_floating_action_button_id);
        mAddNoteFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Метод запускает NoteActivity нажатием кнопки добавления заметки.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Класс адаптер, отвечает за передачу виджетов в recycler view.
     */
    private class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesHolder> {
        List<Note> notes;

        /**
         * Конструктор.
         *
         * @param notes список заметок.
         */
        NotesAdapter(List<Note> notes) {
            this.notes = notes;
        }

        /**
         * Создает и заполняет view NotesHolder.
         *
         * @param parent   ViewGroup
         * @param viewType int
         * @return заполненное view
         */
        @Override
        public NotesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_row,
                    parent, false);
            return new NotesHolder(view);
        }

        /**
         * Отвечает за связывание виджетов NotesHolder с позициями в recycler view.
         *
         * @param holder   NotesHolder
         * @param position позиция в списке
         */
        @Override
        public void onBindViewHolder(NotesHolder holder, int position) {
            holder.mTitleTextView.setText(notes.get(position).getTitle());
            holder.mDescriptionTextView.setText(notes.get(position).getDescription());
        }

        /**
         * Возвращает количество заметок.
         *
         * @return размер List<Note>
         */
        @Override
        public int getItemCount() {
            return notes.size();
        }

        /**
         * Класс для хранения ссылок на виджеты.
         */
        public class NotesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            int position = 0;
            private TextView mTitleTextView;
            private TextView mDescriptionTextView;
            private Note note;

            /**
             * Конструктор.
             *
             * @param itemView View
             */
            private NotesHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);

                mTitleTextView = itemView.findViewById(R.id.note_title_id);
                mDescriptionTextView = itemView.findViewById(R.id.note_description_id);
            }

            /**
             * Метод запускает другую активность и передает дополнительную информацию, после щелчка
             * на заметку из писка recycler view.
             *
             * @param v View
             */
            @Override
            public void onClick(View v) {
                position = getAdapterPosition();
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("noteTitle", String.valueOf(notes.get(position).getTitle()));
                intent.putExtra("noteDescription", String.valueOf(notes.get(position).getDescription()));
                startActivity(intent);
            }
        }
    }
}
