package com.example.dietapp.settings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.dietapp.R;

public class Profile extends Fragment implements View.OnClickListener{
    private View view;
    private TextView btn_account_delete;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings_profile,container,false);

        initView(view);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.profile_account_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("계정 삭제")
                        .setMessage("정말로 계정을 삭제하시겠습니까?")
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();

        }

    }

    public void initView(View v){
        btn_account_delete = v.findViewById(R.id.profile_account_delete);
        btn_account_delete.setOnClickListener(this);
    }
}
