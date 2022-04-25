package com.example.dietapp.fragment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.dietapp.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.w3c.dom.Text;

import com.example.dietapp.diets.*;

// activity of adding/searching diets page
// 식단 추가/검색 페이지 액티비티
public class DietsFrag extends DialogFragment implements View.OnClickListener{

    private View view;
    private Activity activity;
    private int glyco=10;
    private int protein=10;
    private int fat=10;
    private int carbo=5;

    private TextView btn_morning;
    private TextView btn_lunch;
    private TextView btn_dinner;
    private TextView btn_snack;
    private TextView btn_result;

    private TextView goal_kcal_txt;
    private TextView current_kcal_txt;
    private TextView left_kcal_txt;

    private TextView total_carbo_txt;
    private TextView total_glyco_txt;
    private TextView total_protein_txt;
    private TextView total_fat_txt;

    private int goal_kcal = 3000;
    private int current_kcal = 0;
    private int left_kcal = 3000;

    private int activity_level = 2;
    private int weight = 70;


    ListView listview_breakfast;
    ListView listview_lunch;
    ListView listview_dinner;
    ListView listview_snacks;

    CustomAdapter adapter_breakfast;
    CustomAdapter adapter_lunch;
    CustomAdapter adapter_dinner;
    CustomAdapter adapter_snacks;

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

        activity = getActivity();

        btn_morning = v.findViewById(R.id.btn_breakfast);
        btn_morning.setOnClickListener(this);
        btn_lunch = v.findViewById(R.id.btn_lunch);
        btn_lunch.setOnClickListener(this);
        btn_dinner = v.findViewById(R.id.btn_dinner);
        btn_dinner.setOnClickListener(this);
        btn_snack = v.findViewById(R.id.btn_snack);
        btn_snack.setOnClickListener(this);
        btn_result = v.findViewById(R.id.btn_result);
        btn_result.setOnClickListener(this);

        goal_kcal_txt = v.findViewById(R.id.goal_calorie);
        goal_kcal_txt.setText("목표 칼로리 : "+Integer.toString(goal_kcal));
        current_kcal_txt = v.findViewById(R.id.used_calorie);
        goal_kcal_txt.setText("사용된 칼로리 : "+Integer.toString(current_kcal));
        left_kcal_txt = v.findViewById(R.id.left_calorie);
        goal_kcal_txt.setText("남은 칼로리 : "+Integer.toString(left_kcal));

        total_protein_txt = v.findViewById(R.id.total_protein);
        total_protein_txt.setText("단백질 : "+Integer.toString(protein));
        total_glyco_txt = v.findViewById(R.id.total_glyco);
        total_glyco_txt.setText("당 : "+Integer.toString(glyco));
        total_fat_txt = v.findViewById(R.id.total_fat);
        total_fat_txt.setText("지방 : "+Integer.toString(fat));
        total_carbo_txt = v.findViewById(R.id.total_carbo);
        total_carbo_txt.setText("탄수화물 : "+Integer.toString(carbo));

        listview_breakfast = v.findViewById(R.id.breakfasts);
        listview_lunch = v.findViewById(R.id.lunches);
        listview_dinner = v.findViewById(R.id.dinners);
        listview_snacks = v.findViewById(R.id.snacks);

        adapter_breakfast = new CustomAdapter();
        adapter_breakfast.setListView(listview_breakfast);
        adapter_lunch = new CustomAdapter();
        adapter_lunch.setListView(listview_lunch);
        adapter_dinner = new CustomAdapter();
        adapter_dinner.setListView(listview_dinner);
        adapter_snacks = new CustomAdapter();
        adapter_snacks.setListView(listview_snacks);


        listview_breakfast.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodLayout food = (FoodLayout) parent.getItemAtPosition(position);

                String foodName = food.getFoodName();
                int kcal = food.getKcal();
                System.out.println(foodName + " : " + kcal);
            }

        });


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


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_breakfast:
                openDialog(adapter_breakfast, listview_breakfast);
                break;
            case R.id.btn_lunch:
                openDialog(adapter_lunch,listview_lunch);
                break;
            case R.id.btn_dinner:
                openDialog(adapter_dinner, listview_dinner);
                break;
            case R.id.btn_snack:
                openDialog(adapter_snacks, listview_snacks);
                break;
            case R.id.btn_result:
                calculate();
                break;
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void openDialog(CustomAdapter adapter, ListView listView) {
        SearchDialog dialog = new SearchDialog(activity);
        dialog.setAdapter(adapter);
        dialog.setListView(listView);
        dialog.setOriginalFrag(this);

        dialog.show(activity.getFragmentManager(), "search");
    }

    public void computeNutrients(int kcal, int protein, int carbo, int fat, int glyco) {
        current_kcal += kcal;
        left_kcal = goal_kcal - current_kcal;
        this.protein += protein;
        this.carbo += carbo;
        this.fat += fat;
        this.glyco += glyco;
        current_kcal_txt.setText("사용된 칼로리 : "+Integer.toString(current_kcal));
        left_kcal_txt.setText("남은 칼로리 : "+Integer.toString(left_kcal));

        total_protein_txt.setText("단백질 : "+Float.toString(this.protein));
        total_glyco_txt.setText("당 : "+Float.toString(this.glyco));
        total_fat_txt.setText("지방 : "+Float.toString(this.fat));
        total_carbo_txt.setText("탄수화물 : "+Float.toString(this.carbo));

        setPieChart();
    }

    public String calculate() {
        double goal_fat = goal_kcal*0.2/9;
        double gap_fat= Math.abs((goal_fat - fat)/goal_fat);
        double goal_pro = 0;
        switch (activity_level){
            case 0:
                goal_pro = weight;
                break;
            case 1:
                goal_pro = weight*1.2;
                break;
            case 2:
                goal_pro = weight*1.5;
                break;
            case 3:
            case 4:
                goal_pro = weight*2.0;
        }

        double gap_pro = Math.abs((goal_pro-protein)/goal_pro);
        double goal_carbo = (goal_kcal*0.8 - goal_pro*4)/4;
        double gap_carbo = Math.abs((goal_carbo - carbo)/goal_carbo);

        if(gap_fat<=0.05 && gap_carbo<=0.05 && gap_pro<=0.05) {
            return ("perfect");
        } else if(gap_fat<=0.1 && gap_carbo<=0.1 && gap_pro<=0.1) {
            return ("very good");
        } else if(gap_fat<=0.15 && gap_carbo<=0.15 && gap_pro<=0.15) {
            return ("good");
        } else if(gap_fat<=0.2 && gap_carbo<=0.2 && gap_pro<=0.2) {
            return ("not bad");
        } else {
            return "bad";
        }
    }
}
