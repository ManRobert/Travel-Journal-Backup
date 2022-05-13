package com.example.traveljournal.View;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveljournal.Model.Trip;
import com.example.traveljournal.Model.TripViewModel;
import com.example.traveljournal.R;
import com.example.traveljournal.View.ui.details.DetailsFragment;
import com.example.traveljournal.View.ui.details.FragmentHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class DrawerActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView number;
    private CheckBox bookmark;
    private TripViewModel tripViewModel;
    private RecyclerView recyclerViewTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
       // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //View headerView = navigationView.getHeaderView(0);
        handleNavigationDrawer();
        handleFabButton();
        bookmark = findViewById(R.id.bookmark);
        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        recyclerViewTrips = findViewById(R.id.recyclerViewTrips);
    }

    private void handleNavigationDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_about_us, R.id.nav_contact, R.id.fav_trips)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    private void handleFabButton() {
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(view -> Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer).navigate(R.id.fab));
    }


    public void callEugen(View view) {
        number = findViewById(R.id.text_contact);
        String EugenNumber = number.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + EugenNumber));
        startActivity(intent);
    }

    public void callRobert(View view) {
        number = findViewById(R.id.text_contact2);
        String RobertNumber = number.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + RobertNumber));
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addFavouriteTrip(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            CheckBox idText = view.findViewById(R.id.bookmark);
            int id = Integer.parseInt(idText.getText().toString());
            ((CheckBox) view).setButtonDrawable(R.drawable.favourite1);
            tripViewModel.updateFavourite(id, 1);

        } else {
            CheckBox idText = view.findViewById(R.id.bookmark);
            int id = Integer.parseInt(idText.getText().toString());
            ((CheckBox) view).setButtonDrawable(R.drawable.favourite0);
            tripViewModel.updateFavourite(id, 0);
        }
    }

    public void showDetails(View view) {
        TextView idText = view.findViewById(R.id.tripId);
        int id = Integer.parseInt(idText.getText().toString());
        Trip trip = tripViewModel.loadTrip(id);
        Bundle bundle = new Bundle();
        bundle.putString("name", trip.getName());
        bundle.putString("destination", trip.getDestination());
        bundle.putString("type", trip.getType());
        bundle.putInt("price", trip.getPrice());
        bundle.putString("startDate", trip.getStartDate());
        bundle.putString("endDate", trip.getEndDate());
        bundle.putFloat("rate", trip.getRate());
        bundle.putString("image", trip.getImage());
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer).navigate(R.id.details);
        FragmentHelper.displayFragment(DrawerActivity.this, R.id.nav_host_fragment_content_drawer, detailsFragment);
    }


    public void settings(View view)
    {
        Toast.makeText(DrawerActivity.this, "It will be implemented soon!", Toast.LENGTH_SHORT).show();

    }
    public void editUsername(MenuItem item) {
        Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer).navigate(R.id.settings);
    }

    public void share(View view)
    {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, "Share link");
        share.putExtra(Intent.EXTRA_TEXT,"http://linkfordownload_Travel_Journal(nonfunctional)");
        startActivity(Intent.createChooser(share, "Share using"));
    }
}
