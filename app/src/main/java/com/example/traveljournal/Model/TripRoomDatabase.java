package com.example.traveljournal.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Trip.class}, version = 1)
public abstract class TripRoomDatabase extends RoomDatabase {

    public abstract TripDao tripDao();

    private static volatile TripRoomDatabase INSTANCE;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    static TripRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TripRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            // !!!!!!                                               !!!!!!
                            TripRoomDatabase.class, "trip_database_test6").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
