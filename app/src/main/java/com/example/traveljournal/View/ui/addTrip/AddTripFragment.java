package com.example.traveljournal.View.ui.addTrip;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.traveljournal.Model.Trip;
import com.example.traveljournal.Model.TripViewModel;
import com.example.traveljournal.R;
import com.example.traveljournal.View.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.Slider;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Currency;

public class AddTripFragment extends Fragment {
    private EditText editTextName, editTextDestination;
    private TextView textViewDate, textViewDate2, textViewSelect1, textViewSelect2, save;
    private RadioButton button1, button2, button3;
    private RatingBar ratingBar;
    private String image;
    private EditText stringImg;
    int year, month, day, hour, minute;
    private Slider slider;
    private TripViewModel tripViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_trip, container, false);
        initialize(view);
        setOnClickListeners();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
    }

    public void initialize(View view) {
        editTextName = view.findViewById(R.id.nametrip);
        editTextDestination = view.findViewById(R.id.destination);
        textViewDate = view.findViewById(R.id.textviewDate);
        textViewDate2 = view.findViewById(R.id.textviewEndDate);
        textViewSelect1 = view.findViewById(R.id.textViewSelect1);
        textViewSelect2 = view.findViewById(R.id.textViewSelect2);
        button1 = view.findViewById(R.id.buton1);
        button2 = view.findViewById(R.id.buton2);
        button3 = view.findViewById(R.id.button3);
        slider = view.findViewById(R.id.slider);
        ratingBar = view.findViewById(R.id.ratingBar);
        save = view.findViewById(R.id.saveButton);
        stringImg = view.findViewById(R.id.addAnImage);

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        slider.setLabelFormatter(value -> {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            format.setCurrency(Currency.getInstance("EUR"));
            return format.format(value);
        });

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    private void setOnClickListeners() {
        textViewSelect1.setOnClickListener(view -> {
            @SuppressLint("DefaultLocale") DatePickerDialog datePickerDialog =
                    new DatePickerDialog(getContext(),
                            (datePicker, selectedYear, selectedMonth, selectedDay)
                                    -> textViewDate.setText(String.format("%d-%d-%d", selectedDay, selectedMonth + 1,
                                    selectedYear)), year, month, day);
            datePickerDialog.show();
        });

        textViewSelect2.setOnClickListener(view -> {
            @SuppressLint("DefaultLocale") DatePickerDialog datePickerDialog =
                    new DatePickerDialog(getContext(), (datePicker,
                                                        selectedYear, selectedMonth, selectedDay)
                            -> textViewDate2.setText(String.format("%d-%d-%d", selectedDay, selectedMonth + 1,
                            selectedYear)), year, month, day);
            datePickerDialog.show();
        });

        save.setOnClickListener(view -> {
            String name = editTextName.getText().toString();
            String destination = editTextDestination.getText().toString();
            String tripType = "";
            if (button1.isChecked())
                tripType = button1.getText().toString();
            if (button2.isChecked())
                tripType = button2.getText().toString();
            if (button3.isChecked())
                tripType = button3.getText().toString();
            String startDate = textViewDate.getText().toString();
            String endDate = textViewDate2.getText().toString();
            int price = (int) slider.getValue();
            float rate = ratingBar.getRating();
            image = stringImg.getText().toString();
            Trip trip = new Trip(name, destination, tripType, price, startDate, endDate, rate, image);
            tripViewModel.insert(trip);
            tripViewModel.getTrips().observe(requireActivity(), trips -> HomeFragment.setTrips(tripViewModel.getTrips().getValue()));
            Toast.makeText(getContext(), "Trip added!", Toast.LENGTH_SHORT).show();
        });
    }
}
