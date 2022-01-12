package com.example.dietapp.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dietapp.MainActivity;
import com.example.dietapp.R;

public class TotalDiet extends Fragment implements View.OnClickListener{
    private View view;
    private TextView btn_manual;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings_total,container,false);

        initView(view);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_manual:
                ((MainActivity)getActivity()).replaceFragment(new TotalDietManual());
                break;
        }
    }

    public void initView(View v){
        btn_manual = v.findViewById(R.id.btn_manual);
        btn_manual.setOnClickListener(this);
    }
}
