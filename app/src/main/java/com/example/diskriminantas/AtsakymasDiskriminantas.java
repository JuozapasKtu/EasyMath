package com.example.diskriminantas;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AtsakymasDiskriminantas extends Fragment {

    LineGraphSeries<DataPoint> series;
    PointsGraphSeries<DataPoint> series1;
    PointsGraphSeries<DataPoint> series2;
    TextView resultField;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = new Bundle();
        bundle = this.getArguments();
        double a = bundle.getDouble("a");
        double b = bundle.getDouble("b");
        double c = bundle.getDouble("c");

        double diskriminantas = b * b - 4 * a * c;
        double x1, x2, virsuneX, virsuneY;

        virsuneX = round(-b / (2 * a),2);
        virsuneY = round(a * Math.pow(virsuneX, 2) + b * virsuneX + c, 2);

        if (diskriminantas >= 0) {
            if (diskriminantas > 0) {
                x1 = round((-b + Math.sqrt(diskriminantas)) / (2 * a), 2);
                x2 = round((-b - Math.sqrt(diskriminantas)) / (2 * a), 2);
            } else {
                x1 = round(-b / (2 * a), 2);
                x2 = x1;
            }
            drawGraphD(a, b, c, x1, x2, diskriminantas, virsuneX, virsuneY);

        } else {
            resultField = getActivity().findViewById(R.id.textView6);
            resultField.setText("Diskriminantas < 0 sprendinių nėra" + diskriminantas);
            drawGraphD(a, b, c, diskriminantas, virsuneX, virsuneY);
        }
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_atsakymas_diskriminantas, container, false);
    }

    public void drawGraphD(double a, double b, double c,double diskriminantas, double virsuneX, double virsuneY) {
        double x, y,xLast;
        resultField = getActivity().findViewById(R.id.textView6);
        resultField.setText("a  = " + fmt(a) + "; b = " + fmt(b) + "; c = " + fmt(c) +
                "\nDiskriminantas: D = " + fmt(diskriminantas) + "" +
                "\nViršunė (" + fmt(virsuneX) + "; " + fmt(virsuneY) + ")");


        GraphView graph = getActivity().findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        /*series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                resultField.setText("Series1: On Data Point clicked: " + dataPoint);
            }
        });*/
        x=virsuneX-5;
        xLast=virsuneX+5;
        while (x < xLast) {
            x += 0.1;
            y = a * Math.pow(x, 2) + b * x + c;
            series.appendData(new DataPoint(x, y), true, 1000);
        }
        graph.addSeries(series);
        graph.getViewport().setScalableY(true);
        // set manual X bounds
        //graph.getViewport().setXAxisBoundsManual(true);
        //graph.getViewport().setMinX(virsuneX-5);
        //graph.getViewport().setMaxX(xLast);

        // set manual Y bounds
        //graph.getViewport().setYAxisBoundsManual(true);
        //if(virsuneY>=0)
        //{
        //    graph.getViewport().setMinY(-5);
         //   graph.getViewport().setMaxY(virsuneY + 5);
        //}
        //else
        //{
        //    graph.getViewport().setMinY(virsuneY-5);
        //    graph.getViewport().setMaxY(5);
        //}


        series2 = new PointsGraphSeries<>(new DataPoint[]{
                new DataPoint(virsuneX, virsuneY)
        });
        graph.addSeries(series2);
        series2.setShape(PointsGraphSeries.Shape.POINT);
        series2.setColor(Color.parseColor("#a4c639"));

        series.setTitle(fmt(a) + "x²" + signA(b) + "x" + signA(c));
        series2.setTitle("Viršunė (" + fmt(virsuneX) + "; " + fmt(virsuneY) + ")");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


    }

    public void drawGraphD(double a, double b, double c, double x1, double x2, double diskriminantas, double virsuneX, double virsuneY) {
        double x, y,xLast;
        resultField = getActivity().findViewById(R.id.textView6);
        resultField.setText("a  = " + fmt(a) + "; b = " + fmt(b) + "; c = " + fmt(c) +
                "\nDiskriminantas: D = " + fmt(diskriminantas) + "" +
                "\nx1 = " + fmt(x1) + "; x2 = " + fmt(x2) +
                "\nViršunė (" + fmt(virsuneX) + ";" + fmt(virsuneY) + ")");

        List<Double> testList = new ArrayList<>();
        testList.add(x1);
        testList.add(x2);
        Collections.sort(testList);
        series1 = new PointsGraphSeries<>(new DataPoint[]{
                new DataPoint(testList.get(0), 0),
                new DataPoint(testList.get(1), 0)
        });

        GraphView graph = getActivity().findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        series1.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                resultField.setText("Taškas: " + dataPoint);
            }
        });
        x=testList.get(0)-5;
        xLast=testList.get(1)+5;

        double deltax=0.1;
        double ribos=xLast-x;
        // grafiko brezimas
        while(ribos/deltax>1000)
        {
            deltax=deltax*2;
        }
        while (x < xLast) {
            x += deltax;
            y = a * Math.pow(x, 2) + b * x + c;
            series.appendData(new DataPoint(x, y), true, 1000);
        }
        graph.addSeries(series);

        graph.getViewport().setScalableY(true);
        // set manual X bounds
        //graph.getViewport().setXAxisBoundsManual(true);
        //graph.getViewport().setMinX(testList.get(0)-5);
        //graph.getViewport().setMaxX(xLast);

        // set manual Y bounds
        //graph.getViewport().setYAxisBoundsManual(true);
        //if(virsuneY>=0)
        //{
        ///    graph.getViewport().setMinY(-5);
        ///    graph.getViewport().setMaxY(virsuneY + 5);
        //}
        //else
        //{
        //    graph.getViewport().setMinY(virsuneY-5);
        //    graph.getViewport().setMaxY(5);
        //}

        graph.addSeries(series1);
        series1.setShape(PointsGraphSeries.Shape.POINT);
        series1.setColor(Color.RED);

        series2 = new PointsGraphSeries<>(new DataPoint[]{
                new DataPoint(virsuneX, virsuneY)
        });
        graph.addSeries(series2);
        series2.setShape(PointsGraphSeries.Shape.POINT);
        series2.setColor(Color.parseColor("#a4c639"));

        series.setTitle(fmt(a) + "x²" + signA(b) + "x" + signA(c));
        series1.setTitle("x1 (" + fmt(x1) + "; " + 0 + ") x2 (" + fmt(x2) + "; " + 0 + ")");
        series2.setTitle("Viršunė (" + fmt(virsuneX) + "; " + fmt(virsuneY) + ")");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
}
