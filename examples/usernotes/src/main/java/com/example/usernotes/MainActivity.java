package com.example.usernotes;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;
import com.example.usernotes.dao.DaoController;
import com.example.usernotes.dao.Note;
import com.github.artyomcool.dante.async.UiDaoTask;
import com.github.artyomcool.dante.async.UiTaskCallback;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private UiDaoTask<?> task;

    private final List<Note> notes = new ArrayList<>();

    private final RecyclerView.Adapter<RecyclerView.ViewHolder> adapter =
            new RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            int padding = (int) textView.getTextSize();
            textView.setPadding(padding, padding, padding, padding);
            return new RecyclerView.ViewHolder(textView) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView textView = (TextView) holder.itemView;
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final Note entity = notes.get(position);
            textView.setText(entity.getId() + ": " + entity.getTitle());

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                    intent.putExtra(NoteActivity.NOTE_ID, entity.getId());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }
    };

    private final UiTaskCallback<List<Note>> updateListCallback = new UiTaskCallback<List<Note>>() {
        @Override
        public void onSuccess(List<Note> notes) {
            onNotesLoaded(notes);
        }

        @Override
        public void onError(Throwable throwable) {
            Toast.makeText(MainActivity.this, "oups", Toast.LENGTH_SHORT).show();
            throwable.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DaoController daoController = DaoController.get(this);
        task = daoController.allNotes(updateListCallback);
        daoController.execute(task);
    }

    private void onNotesLoaded(List<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Note note = new Note();
                note.setTitle("Some text");
                task.clearCallback();

                DaoController daoController = DaoController.get(this);
                task = daoController.newNote(note, updateListCallback);
                daoController.execute(task);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        task.clearCallback();
    }
}
