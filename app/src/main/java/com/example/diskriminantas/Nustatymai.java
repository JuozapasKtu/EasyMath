package com.example.diskriminantas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class Nustatymai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_nustatymai);
        loadSettings();


    }
    public void loadSettings()
    {
        SharedPreferences sharedPref= getSharedPreferences("Options", Context.MODE_PRIVATE);
        String name = sharedPref.getString("name","");
        int age = sharedPref.getInt("age",'0');

        EditText nameField = findViewById(R.id.nameId);
        if(name=="")
        {
            name = "guest";
        }
        getSupportActionBar().setTitle("Sveiki "+name);
        nameField.setText(name);

        EditText ageField = findViewById(R.id.ageId);
        ageField.setText(age+"");

    }
    public void onSaveClick(View view)
    {
        EditText nameField = findViewById(R.id.nameId); // pagal id paimame lauka
        EditText ageField = findViewById(R.id.ageId);

        String name = nameField.getText().toString(); // is lauko isgauname informacija
        String ageString = ageField.getText().toString();
        int age = Integer.parseInt(ageString);

        SharedPreferences.Editor sharedPrefEditor = getSharedPreferences("Options", Context.MODE_PRIVATE).edit(); // sukuriame prieiga prie failo
        sharedPrefEditor.putString("name",name); // i faila surasome varda ir amziu
        sharedPrefEditor.putInt("age",age);
        sharedPrefEditor.apply(); // stumti
        finish();
    }
}
