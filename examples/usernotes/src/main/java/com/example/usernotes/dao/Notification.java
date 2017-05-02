package com.example.usernotes.dao;

import com.github.artyomcool.dante.annotation.DbQueries;
import com.github.artyomcool.dante.annotation.Entity;
import com.github.artyomcool.dante.annotation.Id;
import com.github.artyomcool.dante.annotation.Query;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Entity
public class Notification {

    @Id(treatZeroAsNull = true)
    long id;

    String text;

    long noteId;

    long when;

    @DbQueries
    public interface NotificationQueries {

        long DAY = TimeUnit.DAYS.toMillis(1);

        @Query(where = "noteId = $noteId")
        List<Notification> byNoteId(long noteId);

        @Query(where = "when < $[nowTime + DAY]")
        List<Notification> soon(long nowTime);

    }

}
