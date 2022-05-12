package com.example.traveljournal.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepository {
    private TripDao tripDao;
    private LiveData<List<Trip>> trips;
    private LiveData<List<Trip>> favTrips;
    private Trip trip;
    private List<Trip> allTrips, favTrips2;

    TripRepository(Application application) {
        TripRoomDatabase wordRoomDatabase = TripRoomDatabase.getDatabase(application);
        tripDao = wordRoomDatabase.tripDao();
        trips = tripDao.getTrips();
        favTrips = tripDao.getFavouriteTrips();
        allTrips = tripDao.getAllTrips();
        favTrips2 = tripDao.getAllFavouriteTrips();
    }

    public LiveData<List<Trip>> getTrips() {
        return trips;
    }

    // we must call this method on a different thread
    // it is a long running operations that should be done on a different thread than the UI(main) thread
    void insert(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> tripDao.insert(trip));
    }

    public LiveData<List<Trip>> getFavouriteTrips() {
        return favTrips;
    }

    public void updateFavourite(int id, int favourite) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> tripDao.updateFavourite(id, favourite));
    }

    public Trip loadTrip(int id) {
        return tripDao.loadTrip(id);
    }

    public List<Trip> getAllTrips() {
        return allTrips;
    }

    public List<Trip> getAllFavouriteTrips() {
        return favTrips2;
    }

}