package com.example.traveljournal.Controller.recicler_view;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.R;


public class TripViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewName, textViewDestination, textViewPrice, textTripId;
    private CheckBox bookmark;
    private ImageView imageView;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewDestination = itemView.findViewById(R.id.textViewDestination);
        textViewPrice = itemView.findViewById(R.id.textViewPRICE);
        imageView = itemView.findViewById(R.id.imageViewCard);
        textTripId = itemView.findViewById(R.id.tripId);
        bookmark = itemView.findViewById(R.id.bookmark);
    }

    public TextView getTextViewPrice() {
        return textViewPrice;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextViewName() {
        return textViewName;
    }

    public TextView getTripId() {
        return textTripId;
    }

    public CheckBox getBookmark() {
        return bookmark;
    }

    public TextView getTextViewDestination() {
        return textViewDestination;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
