package com.example.diskriminantas;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

public class draw_function extends Fragment {

    int spinnerPos1, spinnerPos2;
    double a1, b1, c1, a2, b2, c2;
    String inputString1, inputString2, inputString3, inputString4, inputString5, inputString6;
    LineGraphSeries<DataPoint> series, series1;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] functionsNames = {"-----","y=ax²+bx+c", "y=k/x", "y=km", "y=logₐx,a>0,a≠0", "y=a", "y=ax+b", "y=aˣ,a>0,a≠1", "y=xᵃ", "y=sin(x)", "y=cox(x)", "y=tg(x)"};

        double[][] m = new double[11][3];

        final Spinner spinner1 = getView().findViewById(R.id.spinner1);
        final Spinner spinner2 = getView().findViewById(R.id.spinner2);
        Button button = getView().findViewById(R.id.buttonSpiner1);
        final EditText EditText1 = getView().findViewById(R.id.editTextFunc1);
        final EditText EditText2 = getView().findViewById(R.id.editTextFunc2);
        final EditText EditText3 = getView().findViewById(R.id.editTextFunc3);
        final EditText EditText4 = getView().findViewById(R.id.editTextFunc4);
        final EditText EditText5 = getView().findViewById(R.id.editTextFunc5);
        final EditText EditText6 = getView().findViewById(R.id.editTextFunc6);

        final TextView TextView1 = getView().findViewById(R.id.textFunkcija1);
        final TextView TextView2 = getView().findViewById(R.id.textFunkcija2);
        final TextView TextView3 = getView().findViewById(R.id.textFunkcija3);
        final TextView TextView4 = getView().findViewById(R.id.textFunkcija4);
        final TextView TextView5 = getView().findViewById(R.id.textFunkcija5);
        final TextView TextView6 = getView().findViewById(R.id.textFunkcija6);

        final LinearLayout first = getView().findViewById(R.id.layout1);
        final LinearLayout second = getView().findViewById(R.id.layout2);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item, functionsNames);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item, functionsNames);

        adapter1.setDropDownViewResource(R.layout.custom_spinner_dropdownitem);
        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdownitem);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String text = parent.getItemAtPosition(pos).toString();
                switch (pos) {
                    case 0:
                        setVisabilityAllInvisible(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);
                        break;
                    case 1:
                        setVisability3(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);
                        TextView1.setText("y=");
                        TextView2.setText("x²+");
                        TextView3.setText("x+");
                        break;
                    case 2:
                        setVisabilityOneInMid(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);
                        TextView1.setText("y=");
                        TextView2.setText("/x");
                        break;
                    case 3:
                        setVisabilityOneInMid(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);
                        TextView1.setText("y=");
                        TextView2.setText("m");
                        break;
                    case 4:
                        setVisabilityOneInMid(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);
                        TextView1.setText("y=log");
                        TextView2.setText("x,a>0,a≠0");
                        break;
                    case 5:
                        setVisabilityOne(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);
                        TextView1.setText("y=");
                        break;
                    case 6:
                        setVisabilityTwo(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);
                        TextView1.setText("y=");
                        TextView2.setText("x+");
                        break;
                    case 7:
                        setVisabilityOneInMid(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);
                        TextView1.setText("y=");
                        TextView2.setText("ˣ,a>0,a≠1");
                        break;
                    case 8:
                        setVisabilityOne(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);
                        TextView1.setText("y=x");
                        break;
                    case 9:
                        setVisabilityAllInvisible(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);

                        break;
                    case 10:
                        setVisabilityAllInvisible(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);

                        break;
                    case 11:
                        setVisabilityAllInvisible(EditText1,EditText2,EditText3,TextView1,TextView2,TextView3);
                        break;
                }
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String text = parent.getItemAtPosition(pos).toString();
                switch (pos) {
                    case 0:
                        setVisabilityAllInvisible(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);
                        break;
                    case 1:
                        setVisability3(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);
                        TextView4.setText("y=");
                        TextView5.setText("x²+");
                        TextView6.setText("x+");
                        break;
                    case 2:
                        setVisabilityOneInMid(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);
                        TextView4.setText("y=");
                        TextView5.setText("/x");
                        break;
                    case 3:
                        setVisabilityOneInMid(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);
                        TextView4.setText("y=");
                        TextView5.setText("m");
                        break;
                    case 4:
                        setVisabilityOneInMid(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);
                        TextView4.setText("y=log");
                        TextView5.setText("x,a>0,a≠0");
                        break;
                    case 5:
                        setVisabilityOne(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);
                        TextView4.setText("y=");
                        break;
                    case 6:
                        setVisabilityTwo(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);
                        TextView4.setText("y=");
                        TextView5.setText("x+");
                        break;
                    case 7:
                        setVisabilityOneInMid(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);
                        TextView4.setText("y=");
                        TextView5.setText("ˣ,a>0,a≠1");
                        break;
                    case 8:
                        setVisabilityOne(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);
                        TextView4.setText("y=x");
                        break;
                    case 9:
                        setVisabilityAllInvisible(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);

                        break;
                    case 10:
                        setVisabilityAllInvisible(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);

                        break;
                    case 11:
                        setVisabilityAllInvisible(EditText4,EditText5,EditText6,TextView4,TextView5,TextView6);
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputString1 = EditText1.getText().toString();
                inputString2 = EditText2.getText().toString();
                inputString3 = EditText3.getText().toString();
                inputString4 = EditText4.getText().toString();
                inputString5 = EditText5.getText().toString();
                inputString6 = EditText6.getText().toString();

                if (dataIncorrect(EditText1, EditText2, EditText3, inputString1, inputString2, inputString3) || dataIncorrect(EditText4, EditText5, EditText6, inputString4, inputString5, inputString6)) {
                    return;
                }
                if (EditText1.getVisibility() == v.VISIBLE)
                    a1 = Double.parseDouble(inputString1);
                if (EditText2.getVisibility() == v.VISIBLE)
                    b1 = Double.parseDouble(inputString2);
                if (EditText3.getVisibility() == v.VISIBLE)
                    c1 = Double.parseDouble(inputString3);
                if (EditText4.getVisibility() == v.VISIBLE)
                    a2 = Double.parseDouble(inputString4);
                if (EditText5.getVisibility() == v.VISIBLE)
                    b2 = Double.parseDouble(inputString5);
                if (EditText6.getVisibility() == v.VISIBLE)
                    c2 = Double.parseDouble(inputString6);

                GraphView graph = getActivity().findViewById(R.id.graph2);
                graph.removeAllSeries();
                spinnerPos1 = spinner1.getSelectedItemPosition();
                spinnerPos2 = spinner2.getSelectedItemPosition();

                if(spinnerPos1!=0)
                createGraph(a1, b1, c1, spinnerPos1, graph, EditText1,1);
                if(spinnerPos2!=0)
                createGraph(a2, b2, c2, spinnerPos2, graph, EditText4,0);

                graph.getViewport().setScalableY(true);
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setMinX(-10);
                graph.getViewport().setMaxX(10);
                graph.getViewport().setYAxisBoundsManual(true);
                graph.getViewport().setMinY(-10);
                graph.getViewport().setMaxY(10);

            }
        });

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_draw_function, container, false);
    }

    public boolean dataIncorrect(EditText editText1, EditText editText2, EditText editText3
            , String check1, String check2, String check3) {
        boolean wrong1, wrong2, wrong3;
        wrong1 = wrong2 = wrong3 = false;
        if (editText1.getVisibility() == View.VISIBLE & TextUtils.isEmpty(check1)) {
            editText1.setError("Įveskite koeficiantą a");
            wrong1 = true;
        }
        if (editText2.getVisibility() == View.VISIBLE & TextUtils.isEmpty(check2)) {
            editText2.setError("Įveskite koeficiantą b");
            wrong2 = true;
        }
        if (editText3.getVisibility() == View.VISIBLE & TextUtils.isEmpty(check3)) {
            editText3.setError("Įveskite koeficiantą c");
            wrong3 = true;
        }
        if (wrong1 || wrong2 || wrong3) {
            return true;
        }
        return false;
    }

    public void createGraph(double a, double b, double c, int spinnerPosition, GraphView myGraph, EditText editText1, int gen) {
        series = new LineGraphSeries<DataPoint>();
        double xfirst = -10, xLast = 10, y, deltaX = 0.1;

        switch (spinnerPosition) {
            case 1:
                while (xfirst < xLast) {
                    xfirst += deltaX;

                    y = a * Math.pow(xfirst, 2) + b * xfirst + c;
                    series.appendData(new DataPoint(xfirst, y), true, 1000);
                }
                series.setTitle(fmt(a) + "x²" + signA(b) + "x" + signA(c));
                break;
            case 2:
                while (xfirst < xLast) {
                    xfirst += deltaX;
                    y = a / xfirst;
                    series.appendData(new DataPoint(xfirst, y), true, 1000);
                }
                series.setTitle(fmt(a) + "/x");
                break;
            case 3:
                while (xfirst < xLast) {
                    xfirst += deltaX;
                    y = a * xfirst;
                    series.appendData(new DataPoint(xfirst, y), true, 1000);
                }
                series.setTitle(fmt(a) + "x");

                break;
            case 4:
                if (a > 0 && a != 1) {
                    while (xfirst < xLast) {
                        xfirst += deltaX;
                        y = Math.log(xfirst) / Math.log(a);
                        series.appendData(new DataPoint(xfirst, y), true, 1000);
                    }
                    series.setTitle("log"+subscript(fmt(a))+"(x)");
                }
                else
                    editText1.setError("a>0 ir a!=0");

                break;
            case 5:
                while (xfirst < xLast) {
                    xfirst += deltaX;
                    y = a;
                    series.appendData(new DataPoint(xfirst, y), true, 1000);
                }
                series.setTitle(fmt(a));
                break;
            case 6:
                while (xfirst < xLast) {
                    xfirst += deltaX;
                    y = a * xfirst + b;
                    series.appendData(new DataPoint(xfirst, y), true, 1000);
                }
                series.setTitle(fmt(a) + "x" + signA(b));
                break;
            case 7:
                if (a > 0 && a != 1) {
                    while (xfirst < xLast) {
                        xfirst += deltaX;
                        y = Math.pow(a, xfirst);
                        series.appendData(new DataPoint(xfirst, y), true, 1000);
                    }
                    series.setTitle(fmt(a) + "ˣ");
                }
                else
                    editText1.setError("a>0 ir a!=0");
                break;
            case 8:
                while (xfirst < xLast) {
                    xfirst += deltaX;
                    y = Math.pow(xfirst, a);
                    series.appendData(new DataPoint(xfirst, y), true, 1000);
                }
                series.setTitle("X"+superscript(fmt(a)));
                break;
            case 9:
                while (xfirst < xLast) {
                    xfirst += deltaX;
                    y = Math.sin(xfirst);
                    series.appendData(new DataPoint(xfirst, y), true, 1000);
                }
                series.setTitle("sin(X)");
                break;
            case 10:
                while (xfirst < xLast) {
                    xfirst += deltaX;
                    y = Math.cos(xfirst);
                    series.appendData(new DataPoint(xfirst, y), true, 1000);
                }
                series.setTitle("cos(X)");
                break;
            case 11:
                while (xfirst < xLast) {
                    xfirst += deltaX;
                    y = Math.tan(xfirst);
                    series.appendData(new DataPoint(xfirst, y), true, 1000);
                }
                series.setTitle("tg(X)");
                break;
        }
        if(gen==1)
        {
            series.setColor(Color.RED);
        }
        myGraph.addSeries(series);
        myGraph.getLegendRenderer().setVisible(true);
        myGraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }

    public static String fmt(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }
    public static String signA(double a) {
        if (a > 0)
            return String.format("+%s", fmt(a));
        else if (a == 0)
            return "";
        else
            return String.format("%s", fmt(a));
    }
    public static String superscript(String str) {
        str = str.replaceAll("0", "⁰");
        str = str.replaceAll("1", "¹");
        str = str.replaceAll("2", "²");
        str = str.replaceAll("3", "³");
        str = str.replaceAll("4", "⁴");
        str = str.replaceAll("5", "⁵");
        str = str.replaceAll("6", "⁶");
        str = str.replaceAll("7", "⁷");
        str = str.replaceAll("8", "⁸");
        str = str.replaceAll("9", "⁹");
        return str;
    }
    public static String subscript(String str) {
        str = str.replaceAll("0", "₀");
        str = str.replaceAll("1", "₁");
        str = str.replaceAll("2", "₂");
        str = str.replaceAll("3", "₃");
        str = str.replaceAll("4", "₄");
        str = str.replaceAll("5", "₅");
        str = str.replaceAll("6", "₆");
        str = str.replaceAll("7", "₇");
        str = str.replaceAll("8", "₈");
        str = str.replaceAll("9", "₉");
        return str;
    }
    public void setVisability3(EditText text1, EditText text2, EditText text3,TextView textView1,TextView textView2,TextView textView3)
    {
        if (text1.getVisibility() == getView().GONE) {
            text1.setVisibility(getView().VISIBLE);
        }
        if (text2.getVisibility() == getView().GONE) {
            text2.setVisibility(getView().VISIBLE);
        }
        if (text3.getVisibility() == getView().GONE) {
            text3.setVisibility(getView().VISIBLE);
        }
        if (textView1.getVisibility() == getView().GONE) {
            textView1.setVisibility(getView().VISIBLE);
        }
        if (textView2.getVisibility() == getView().GONE) {
            textView2.setVisibility(getView().VISIBLE);
        }
        if (textView3.getVisibility() == getView().GONE) {
            textView3.setVisibility(getView().VISIBLE);
        }

    }
    public void setVisabilityOneInMid(EditText text1, EditText text2, EditText text3,TextView textView1,TextView textView2,TextView textView3)
    {
        if (textView1.getVisibility() == getView().GONE) {
            textView1.setVisibility(getView().VISIBLE);
        }
        if (text1.getVisibility() == getView().GONE) {
            text1.setVisibility(getView().VISIBLE);
        }
        if (textView2.getVisibility() == getView().GONE) {
            textView2.setVisibility(getView().VISIBLE);
        }
        if (text2.getVisibility() == getView().VISIBLE) {
            text2.setVisibility(getView().GONE);
        }
        if (textView3.getVisibility() == getView().VISIBLE) {
            textView3.setVisibility(getView().GONE);
        }
        if (text3.getVisibility() == getView().VISIBLE) {
            text3.setVisibility(getView().GONE);
        }


    }
    public void setVisabilityAllInvisible(EditText text1, EditText text2, EditText text3,TextView textView1,TextView textView2,TextView textView3)
    {
        if (text1.getVisibility() == getView().VISIBLE) {
            text1.setVisibility(getView().GONE);
        }
        if (text2.getVisibility() == getView().VISIBLE) {
            text2.setVisibility(getView().GONE);
        }
        if (text3.getVisibility() == getView().VISIBLE) {
            text3.setVisibility(getView().GONE);
        }
        if (textView1.getVisibility() == getView().VISIBLE) {
            textView1.setVisibility(getView().GONE);
        }
        if (textView2.getVisibility() == getView().VISIBLE) {
            textView2.setVisibility(getView().GONE);
        }
        if (textView3.getVisibility() == getView().VISIBLE) {
            textView3.setVisibility(getView().GONE);
        }

    }
    public void setVisabilityOne(EditText text1, EditText text2, EditText text3,TextView textView1,TextView textView2,TextView textView3)
    {
        if (textView1.getVisibility() == getView().GONE) {
            textView1.setVisibility(getView().VISIBLE);
        }
        if (text1.getVisibility() == getView().GONE) {
            text1.setVisibility(getView().VISIBLE);
        }
        if (textView2.getVisibility() == getView().VISIBLE) {
            textView2.setVisibility(getView().GONE);
        }
        if (text2.getVisibility() == getView().VISIBLE) {
            text2.setVisibility(getView().GONE);
        }
        if (textView3.getVisibility() == getView().VISIBLE) {
            textView3.setVisibility(getView().GONE);
        }
        if (text3.getVisibility() == getView().VISIBLE) {
            text3.setVisibility(getView().GONE);
        }

    }
    public void setVisabilityTwo(EditText text1, EditText text2, EditText text3,TextView textView1,TextView textView2,TextView textView3)
    {
        if (textView1.getVisibility() == getView().GONE) {
            textView1.setVisibility(getView().VISIBLE);
        }
        if (text1.getVisibility() == getView().GONE) {
            text1.setVisibility(getView().VISIBLE);
        }
        if (textView2.getVisibility() == getView().GONE) {
            textView2.setVisibility(getView().VISIBLE);
        }
        if (text2.getVisibility() == getView().GONE) {
            text2.setVisibility(getView().VISIBLE);
        }
        if (textView3.getVisibility() == getView().VISIBLE) {
            textView3.setVisibility(getView().GONE);
        }
        if (text3.getVisibility() == getView().VISIBLE) {
            text3.setVisibility(getView().GONE);
        }

    }

}

