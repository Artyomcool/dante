package com.example.usernotes.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.github.artyomcool.dante.async.Async;
import com.github.artyomcool.dante.async.DaoTask;
import com.github.artyomcool.dante.async.UiDaoTask;
import com.github.artyomcool.dante.async.UiTaskCallback;
import com.github.artyomcool.dante.core.dao.DaoMaster;
import com.github.artyomcool.dante.core.db.DatabaseOpener;

import java.util.List;

public class DaoController {

    private static final DaoController INSTANCE = new DaoController();

    public static DaoController get(Context context) {
        INSTANCE.context = context.getApplicationContext();
        return INSTANCE;
    }

    private final Async async = new Async(new DatabaseOpener() {
        @Override
        public SQLiteDatabase open() {
            return context.openOrCreateDatabase("notes.db", 0, null);
        }
    });

    private Context context;

    private DaoController() {
    }

    public void execute(DaoTask<?> task) {
        async.execute(task);
    }

    public UiDaoTask<List<Note>> allNotes(UiTaskCallback<List<Note>> callback) {
        return new UiDaoTask<List<Note>>(callback) {
            @Override
            public List<Note> execute(DaoMaster daoMaster) throws Exception {
                return daoMaster.queries(Note.NoteQueries.class).all();
            }
        };
    }
    public UiDaoTask<List<Note>> newNote(final Note note, UiTaskCallback<List<Note>> callback) {
        return new UiDaoTask<List<Note>>(callback) {
            @Override
            public List<Note> execute(DaoMaster daoMaster) throws Exception {
                daoMaster.dao(Note.class).insert(note);
                return daoMaster.queries(Note.NoteQueries.class).all();
            }
        };
    }

    public UiDaoTask<Note> note(final long id, UiTaskCallback<Note> callback) {
        return new UiDaoTask<Note>(callback) {
            @Override
            public Note execute(DaoMaster daoMaster) throws Exception {
                return daoMaster.queries(Note.NoteQueries.class).byId(id);
            }
        };
    }

}
