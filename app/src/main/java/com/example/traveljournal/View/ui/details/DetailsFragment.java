package com.example.traveljournal.View.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.traveljournal.Controller.recicler_view.TripViewHolder;
import com.example.traveljournal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;


public class DetailsFragment extends Fragment {
    private String name, destination, type, startDate, endDate, image;
    private int price;
    private float rate;
    private TextView textViewName, textViewDestination, textViewType,
            textViewStartDate, textViewEndDate, textViewPrice;
    private RatingBar ratingBarRate;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString("name");
            if (name.equals(""))
                name = "Unset name";

            destination = getArguments().getString("destination");
            if (destination.equals(""))
                destination = "Unset destination";

            type = getArguments().getString("type");
            if (type.equals(""))
                type = "Unset type";

            price = getArguments().getInt("price");
            startDate = getArguments().getString("startDate");
            if (startDate.equals("Start Date"))
                startDate = "Unset Start Date";

            endDate = getArguments().getString("endDate");
            if (endDate.equals("End Date"))
                endDate = "Unset End Date";

            rate = getArguments().getFloat("rate");

            image = getArguments().getString("image");
            if (image.equals(""))
                image = "NO URL";
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        textViewName = view.findViewById(R.id.nameTripDetails);
        textViewDestination = view.findViewById(R.id.destinationTripDetails);
        textViewType = view.findViewById(R.id.typeTripDetails);
        textViewPrice = view.findViewById(R.id.priceTripDetails);
        textViewStartDate = view.findViewById(R.id.startDateTripDetails);
        textViewEndDate = view.findViewById(R.id.endDateTripDetails);
        ratingBarRate = view.findViewById(R.id.rateDetails);
        imageView = view.findViewById(R.id.imageViewDetails);

        textViewName.setText(name);
        textViewDestination.setText(destination);
        textViewType.setText(type);
        textViewPrice.setText(String.valueOf(price));
        textViewStartDate.setText(startDate);
        textViewEndDate.setText(endDate);
        ratingBarRate.setRating(rate);
        TripViewHolder tripViewHolder = new TripViewHolder(view);
        if (!(image == null)) {
            Picasso.get().load(image).into(imageView);
            Picasso.get().load(image).error(R.drawable.default_img1).into(imageView);
            tripViewHolder.setImageView(imageView);
        }

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
    }
}
