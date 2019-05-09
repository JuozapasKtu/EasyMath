package com.example.diskriminantas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DiskriminantasDuomenys extends Fragment {

    double a,b,c;
    EditText inputField1,inputField2,inputField3;
    String inputString1,inputString2,inputString3;
    Button button;
    Bundle bundle;
    Intent dIntent;
    boolean wrong1,wrong2,wrong3;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadSettings();
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wrong1=wrong2=wrong3=false;
                inputString1 = inputField1.getText().toString();
                inputString2 = inputField2.getText().toString();
                inputString3 = inputField3.getText().toString();


                if (TextUtils.isEmpty(inputString1)) {
                    inputField1.setError("Įveskite koeficiantą a");
                    wrong1=true;
                }
                if (TextUtils.isEmpty(inputString2)) {
                    inputField2.setError("Įveskite koeficiantą b");
                    wrong2=true;
                }
                if (TextUtils.isEmpty(inputString3)) {
                    inputField3.setError("Įveskite koeficiantą c");
                    wrong3=true;
                }
                if(wrong1||wrong2||wrong3)
                {
                    return;
                }
                a = Double.parseDouble(inputString1);
                b = Double.parseDouble(inputString2);
                c = Double.parseDouble(inputString3);
                bundle.putDouble("a",a);
                bundle.putDouble("b",b);
                bundle.putDouble("c",c);

                AtsakymasDiskriminantas fragInfo = new AtsakymasDiskriminantas();
                fragInfo.setArguments(bundle);
                closeKeyboard();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,
                        fragInfo).addToBackStack(null).commit();
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_diskriminantas_duomenys, container, false);
    }

    public void loadSettings() {
        inputField1 = getView().findViewById(R.id.aId);
        inputField2 = getView().findViewById(R.id.bId);
        inputField3 = getView().findViewById(R.id.cId);
        button = getView().findViewById(R.id.button1);
        bundle =new Bundle();
        //ListView resultListField =findViewById()
        //        ArrayAdapter<String>  resultsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,guess_results);
        //resultListField.setAdapter(resultsAdapter);
        // guess results add
    }
    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0);
        }
    }

}
