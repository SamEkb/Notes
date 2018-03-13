package ru.skilanov.io.notes.database;

import java.util.List;

import ru.skilanov.io.notes.model.Note;

/**
 * Интерфес содержащий операции над заметками.
 */

public interface NoteService {
    /**
     * Поиск по id.
     *
     * @param id int
     * @return Note
     */
    Note findById(int id);

    /**
     * Возвращает список заметок.
     *
     * @return List
     */
    List<Note> findAll();

    /**
     * Вводит новую заметку.
     *
     * @param note Note
     */
    void insert(Note note);

    /**
     * Удаляет заметку.
     *
     * @param note Note
     * @return int
     */
    void delete(Note note);
}
