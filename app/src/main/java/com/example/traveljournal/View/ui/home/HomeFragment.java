package com.example.traveljournal.View.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.Model.Trip;
import com.example.traveljournal.Model.TripViewModel;
import com.example.traveljournal.R;
import com.example.traveljournal.Controller.recicler_view.TripAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewTrips;
    public static List<Trip> trips = new ArrayList<>();
    private TripViewModel tripViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        recyclerViewTrips = view.findViewById(R.id.recyclerViewTrips);
        setupRecyclerViewTrips();
        tripViewModel.getTrips().observe(requireActivity(), trips -> setTrips(tripViewModel.getTrips().getValue()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
    }

    public static void setTrips(List<Trip> trips) {
        HomeFragment.trips = trips;
    }

    private void getTrips() {
        /*String urlImage1 = "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80";
        String urlImage2 = "https://images.unsplash.com/photo-1574864745093-5566c5be5855?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80";
        String urlImage3 = "https://images.unsplash.com/photo-1509713086752-a3747b6ee54a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1331&q=80";
        String urlImage4 = "https://images.unsplash.com/photo-1492571350019-22de08371fd3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1053&q=80";
*/
        trips = tripViewModel.getAllTrips();
    }

    private TripAdapter getTripsAdapter() {
        return new TripAdapter(trips);
    }


    private void setupLayoutManager() {
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupRecyclerViewTrips() {
        if (trips.size() == 0)
            getTrips();
        setupLayoutManager();
        recyclerViewTrips.setAdapter(getTripsAdapter());
    }
}