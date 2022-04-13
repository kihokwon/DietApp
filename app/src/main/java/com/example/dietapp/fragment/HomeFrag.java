package com.example.dietapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dietapp.R;


// activity on the main page
// 메인 페이지 액티비티
public class HomeFrag extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 여기에 food result를 표시할 xml 생성
        view = inflater.inflate(R.layout.frag1,container,false);

        Bundle foodList = this.getArguments();
        if(foodList != null){
            //foodList = getArguments();
            String result = foodList.getString("foodResult");

            Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
        }
        return view;
    }

}
