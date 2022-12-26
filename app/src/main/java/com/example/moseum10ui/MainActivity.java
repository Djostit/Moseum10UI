package com.example.moseum10ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moseum10ui.models.Employee;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText textCode;
    ArrayList<Employee> employees = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCode = findViewById(R.id.textCode);


        InputStream inputStream = getResources().openRawResource(R.raw.employee);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while(ctr != -1){
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONArray jsonArray = new JSONArray(byteArrayOutputStream.toString());
            String Id, FullName;

            for(int i = 0; i < jsonArray.length(); i++){
                Id = jsonArray.getJSONObject(i).getString("Code");
                FullName = jsonArray.getJSONObject(i).getString("FullName");

                employees.add(new Employee(Id, FullName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onClickSignIn(View view) {
        if(textCode.getText().toString().isEmpty()){
            Toast.makeText(this, "Поле пустое", Toast.LENGTH_SHORT).show();
        }
        else {
            employees.forEach(employee -> {
                if(employee.getCode().equals(textCode.getText().toString())){
                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}