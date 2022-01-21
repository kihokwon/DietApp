package com.example.dietapp.fragment;

import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;

import com.example.dietapp.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

// activity of adding/searching diets page
// 식단 추가/검색 페이지 액티비티
public class DietsFrag extends Fragment {

    private View view;
    private float glyco=10;
    private float protein=10;
    private float fat=10;
    private float carbo=10;
    PieChart chart1;


    int[] colorArray = new int[] {Color.LTGRAY, Color.BLUE, Color.RED};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_diets,container,false);
        initView(view);
        return view;
    }

    public void initView(View v) {
        chart1 = (PieChart) v.findViewById(R.id.piechart);
        setPieChart();
    }

    private void setPieChart() {
        chart1.clearChart();

        chart1.addPieSlice(new PieModel("탄수화물", carbo, Color.parseColor("#CDA67F")));
        chart1.addPieSlice(new PieModel("단백질", protein, Color.parseColor("#00BFFF")));
        chart1.addPieSlice(new PieModel("지방", fat, Color.parseColor("#2FEA3B")));
        chart1.addPieSlice(new PieModel("당", glyco, Color.parseColor("#334455")));

        chart1.startAnimation();
    }
}
