package ru.skilanov.io.notes.database;

import java.util.List;

import javax.inject.Inject;

import ru.skilanov.io.notes.model.Note;
import ru.skilanov.io.notes.model.NoteDao;

/**
 * Класс реализация интерфейса NoteService.
 */

public class NoteServiceImpl implements NoteService {

    private NoteDao noteDao;

    /**
     * Конструктор.
     *
     * @param noteDao NoteDao
     */
    @Inject
    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    /**
     * Поиск по id.
     *
     * @param id int
     * @return Note
     */
    @Override
    public Note findById(int id) {
        return noteDao.getById(id);
    }

    /**
     * Возвращает список заметок.
     *
     * @return List
     */
    @Override
    public List<Note> findAll() {
        return noteDao.getAllNote();
    }

    /**
     * Вводит новую заметку.
     *
     * @param note Note
     */
    @Override
    public void insert(Note note) {
        noteDao.insert(note);
    }

    /**
     * Удаляет заметку.
     *
     * @param note Note
     * @return int
     */
    @Override
    public void delete(Note note) {
        noteDao.delete(note);
    }
}
