package com.example.traveljournal.View.ui.favTrips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.Controller.recicler_view.TripAdapter;
import com.example.traveljournal.Model.Trip;
import com.example.traveljournal.Model.TripViewModel;
import com.example.traveljournal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FavouriteTripsFragment extends Fragment {
    private RecyclerView recyclerViewTrips;
    public static List<Trip> favTrips = new ArrayList<>();
    private TripViewModel favTripViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav_trips, container, false);
        favTripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        recyclerViewTrips = view.findViewById(R.id.recyclerViewFavTrips);
        setupRecyclerViewTrips();
        favTripViewModel.getFavouriteTrips().observe(requireActivity(), trips -> setFavTrips(favTripViewModel.getFavouriteTrips().getValue()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
    }

    public static void setFavTrips(List<Trip> favTrips) {
        FavouriteTripsFragment.favTrips = favTrips;
    }


    private void getTrips() {
        favTrips = favTripViewModel.getAllFavouriteTrips();
    }

    private TripAdapter getTripsAdapter() {
        return new TripAdapter(favTrips);
    }


    private void setupLayoutManager() {
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupRecyclerViewTrips() {
        if (favTrips.size() == 0)
            getTrips();
        setupLayoutManager();
        recyclerViewTrips.setAdapter(getTripsAdapter());
    }
}
