package com.example.moseum10ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moseum10ui.R;
import com.example.moseum10ui.models.Exhibits;

import java.util.List;

public class ExhibitsAdapter extends ArrayAdapter<Exhibits> {
    private LayoutInflater inflater;
    private int layout;
    private List<Exhibits> exhibits;

    public ExhibitsAdapter(@NonNull Context context, int layout , List<Exhibits> exhibits) {
        super(context, layout, exhibits);
        this.inflater = LayoutInflater.from(context);
        this.layout = layout;
        this.exhibits = exhibits;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(layout, parent, false);

        TextView idEx = convertView.findViewById(R.id.idEx);
        TextView nameEx = convertView.findViewById(R.id.nameEx);
        TextView dateEx = convertView.findViewById(R.id.dateEx);
        TextView statusEx = convertView.findViewById(R.id.statusEx);
        TextView placeEx = convertView.findViewById(R.id.placeEx);

        Exhibits exhibit = exhibits.get(position);
        idEx.setText(String.valueOf(exhibit.getId()));
        nameEx.setText(exhibit.getName());
        dateEx.setText(exhibit.getDateReceipt());
        statusEx.setText(exhibit.getStatus());
        placeEx.setText(exhibit.getPlace());

        return convertView;
    }
}
