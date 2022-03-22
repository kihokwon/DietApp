package com.example.dietapp.diets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dietapp.R;
import com.example.dietapp.fragment.DietsFrag;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    int resourceId;
    ArrayList<FoodLayout> data;
    ListView listView;

    public CustomAdapter() {
        data = new ArrayList<FoodLayout>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String food, int kcal){
        FoodLayout newFood = new FoodLayout();
        newFood.setFoodName(food);
        newFood.setKcal(kcal);
        data.add(newFood);
    }
    public void addItem(String food, int kcal, int protein, int carbo, int fat, int glyco){
        FoodLayout newFood = new FoodLayout();
        newFood.setFoodName(food);
        newFood.setKcal(kcal);
        newFood.setProtein(protein);
        newFood.setFat(fat);
        newFood.setGlyco(glyco);
        newFood.setCarbo(carbo);
        data.add(newFood);
    }
    public void setListView(ListView listView){
        this.listView = listView;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_layout, parent, false);
        }

        TextView foodView = (TextView) convertView.findViewById(R.id.food);
        TextView kcalView = (TextView) convertView.findViewById(R.id.food_kcal);

        final FoodLayout foodLayout = (FoodLayout) getItem(position);

        foodView.setText(foodLayout.getFoodName());
        kcalView.setText(foodLayout.getKcalString() + "kcal");

        TextView button = convertView.findViewById(R.id.btn_delete_food);

        // x클릭 시 삭제
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data.remove(pos);
                notifyDataSetChanged();
                DietsFrag.setListViewHeightBasedOnChildren(listView);
            }
        });

        return convertView;
    }
}
