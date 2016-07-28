package com.example.usernotes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.usernotes.dao.DaoController;
import com.example.usernotes.dao.Note;
import com.github.artyomcool.dante.async.UiDaoTask;
import com.github.artyomcool.dante.async.UiTaskCallback;

public class NoteActivity extends Activity {

    public static final String NOTE_ID = "NOTE_ID";

    private UiDaoTask<?> task;

    private TextView title;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long id = getIntent().getLongExtra(NOTE_ID, -1);
        if (id == -1) {
            finish();
            return;
        }

        setContentView(R.layout.activity_note);

        title = (TextView) findViewById(R.id.title);
        text = (TextView) findViewById(R.id.text);

        DaoController daoController = DaoController.get(this);

        task = daoController.note(id, new UiTaskCallback<Note>() {
            @Override
            public void onSuccess(Note note) {
                onNoteLoaded(note);
            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(NoteActivity.this, "oups", Toast.LENGTH_SHORT).show();
            }
        });

        daoController.execute(task);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        task.clearCallback();
    }

    private void onNoteLoaded(Note note) {
        if (note == null) {
            finish();
            return;
        }

        title.setText(note.getTitle());
        text.setText(note.getText());
    }
}
