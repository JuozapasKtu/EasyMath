package com.example.diskriminantas;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class spreadSheet extends Fragment {

    int[] IMAGES;
    String[] NAMES;
    String[] DESCRIPTION;
    DatabaseHelper myDb;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String a;
        int funcCount= 13;
        DESCRIPTION = new String[funcCount];
        NAMES = new String[funcCount];
        int i =0;
        super.onViewCreated(view, savedInstanceState);
        myDb = new DatabaseHelper(getActivity());
        myDb.insertData("Tiesinė funkcija","f(x) = x");
        myDb.insertData("Absoliutinės reikšmės funkcija","y = |x|");
        myDb.insertData("Kvadratinė funkcija","f(x) = x²");
        myDb.insertData("Kvadratinės šaknies funkcija","f(x) = √x");
        myDb.insertData("Kūbinė funkcija","f(x) = x³");
        myDb.insertData("Kūbinės šaknies funkcija","f(x) = ∛x");
        myDb.insertData("Atvirkštinio proporcingumo funkcija","f(x) = 1/x");
        myDb.insertData("Atvirkštinio proporcingumo funkcija","f(x) = 1/x²");
        myDb.insertData("Logoritminė funkcija","f(x) = ln(x)");
        myDb.insertData("Eksponentinė funkcija","f(x) = eˣ");
        myDb.insertData("Sinusoidė","f(x) = sin(x)");
        myDb.insertData("Kosinusoidė","f(x) = cos(x)");
        myDb.insertData("Tangenoidė","f(x) = tan(x)");
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0 ){
            // show message
            Toast.makeText(getContext(), "ErrorNothing found", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(res.moveToNext()&&i<funcCount) {
                NAMES[i] = res.getString(1);
                DESCRIPTION[i] = res.getString(2);
                i++;
            }
        }


        IMAGES = new int[]{R.drawable.sheet1, R.drawable.sheet2, R.drawable.sheet3, R.drawable.sheet4,R.drawable.sheet5, R.drawable.sheet6
                ,R.drawable.sheet7, R.drawable.sheet8,R.drawable.sheet9, R.drawable.sheet10,R.drawable.sheet11, R.drawable.sheet12, R.drawable.sheet13};
        //DESCRIPTION = new String[]{"f(x) = x","y = |x|","f(x) = x²","f(x) = √x",
       //         "f(x) = x³","f(x) = ∛x", "f(x) = 1/x","f(x) = 1/x²",
       //         "f(x) = ln(x)","f(x) = eˣ","f(x) = sin(x)","f(x) = cos(x)","f(x) = tan(x)"};
        //NAMES = new String[]{"Tiesinė funkcija", "Absoliutinės reikšmės funkcija", "Kvadratinė funkcija", "Kvadratinės šaknies funkcija", "Kūbinė funkcija",
         //       "Kūbinės šaknies funkcija","Atvirkštinio proporcingumo funkcija", "Atvirkštinio proporcingumo funkcija", "Logoritminė funkcija", "Eksponentinė funkcija",
         //       "Sinusoidė", "Kosinusoidė", "Tangenoidė"};

        ListView resultListField = getActivity().findViewById(R.id.listViewId);

        CustomAdapter customAdapter= new CustomAdapter();
        resultListField.setAdapter(customAdapter);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_spread_sheet, container, false);
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.custom_layout, null);

            ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView textView_name = convertView.findViewById(R.id.nameText);
            TextView textView_description = convertView.findViewById(R.id.descriptionText);

            imageView.setImageResource(IMAGES[position]);
            textView_name.setText(NAMES[position]);
            textView_description.setText(DESCRIPTION[position]);
            return convertView;
        }
    }
}
