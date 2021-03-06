package com.example.dietapp.fragment;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dietapp.MainActivity;
import com.example.dietapp.settings.*;


import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.dietapp.R;

// activity of settings page
// 환경설정 액티비티
public class SettingsFrag extends Fragment implements View.OnClickListener {
    private View view;
    private TextView btn_profile;
    private TextView btn_total;
    private TextView btn_logout;
    private Button showFoodBtn;
    private TextView showTextView;
    // listView로 나타내야 하는데 MainActivity에서 String[] 데이터가 꼬임.
    //private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings,container,false);
        initView(view);

        showFoodBtn = view.findViewById(R.id.showFoodBtn);
        showTextView = view.findViewById(R.id.showTextView);
        Bundle foodList = this.getArguments();

        if(foodList != null){
            showFoodBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("foodList Output Test");
                    String result = foodList.getString("foodResult");
                    //Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                    showTextView.setText(result);
                }
            });
        }


        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_profile:
                ((MainActivity)getActivity()).replaceFragment(new Profile());
                break;
            case R.id.btn_total:
                ((MainActivity)getActivity()).replaceFragment(new TotalDiet());
                break;
            case R.id.logout:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("로그아웃 하시겠습니까?")
                        .setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();

        }
    }

    public void initView(View v){
        btn_profile = v.findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(this);

        btn_total = v.findViewById(R.id.btn_total);
        btn_total.setOnClickListener(this);

        btn_logout=v.findViewById(R.id.logout);
        btn_logout.setOnClickListener(this);

        showTextView = v.findViewById(R.id.showTextView);
        showTextView.setOnClickListener(this);
    }
}
