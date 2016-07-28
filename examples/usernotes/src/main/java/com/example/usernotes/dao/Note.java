package com.example.usernotes.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.github.artyomcool.dante.annotation.Entity;
import com.github.artyomcool.dante.annotation.Id;
import com.github.artyomcool.dante.annotation.Queries;
import com.github.artyomcool.dante.annotation.Query;

import java.util.List;

@Entity
public class Note {

    @Id(treatZeroAsNull = true)
    long id;

    @NonNull String title;

    String text;

    byte[] image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    @Queries(Note.class)
    public interface NoteQueries {

        @Query(where = "")
        List<Note> all();

        @Query(where = "id = $id")
        Note byId(long id);

    }

}
