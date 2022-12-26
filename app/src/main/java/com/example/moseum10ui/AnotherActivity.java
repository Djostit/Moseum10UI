package com.example.moseum10ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.moseum10ui.adapter.ExhibitsAdapter;
import com.example.moseum10ui.models.Exhibits;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AnotherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        Spinner spinnerEx = findViewById(R.id.spinnerEx);
        ListView listEx = findViewById(R.id.listEx);

        InputStream inputStream = getResources().openRawResource(R.raw.exhibits);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ArrayList<String> ids = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(byteArrayOutputStream.toString());
            int Id;
            String Name, DateReceipt, Status, Place;

            ArrayList<Exhibits> exhibits = new ArrayList<>();


            for (int i = 0; i < jsonArray.length(); i++) {
                Id = jsonArray.getJSONObject(i).getInt("Id");
                Name = jsonArray.getJSONObject(i).getString("Name");
                DateReceipt = jsonArray.getJSONObject(i).getString("DateReceipt");
                Status = jsonArray.getJSONObject(i).getString("Status");
                Place = jsonArray.getJSONObject(i).getString("Place");

                exhibits.add(new Exhibits(Id, Name, DateReceipt, Status, Place));
                ids.add(String.valueOf(Id));
            }

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, ids);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerEx.setAdapter(spinnerAdapter);

            AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String item = (String) adapterView.getItemAtPosition(i);

                    ArrayList<Exhibits> exhibits_in_id = new ArrayList<>();
                    exhibits.forEach(exhibits1 -> {
                        if(String.valueOf(exhibits1.getId()).equals(item)){
                            exhibits_in_id.add(exhibits1);
                        }
                    });
                    ExhibitsAdapter adapter = new ExhibitsAdapter(getApplicationContext(), R.layout.item_list, exhibits_in_id);
                    listEx.setAdapter(adapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            };
            spinnerEx.setOnItemSelectedListener(itemSelectedListener);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}