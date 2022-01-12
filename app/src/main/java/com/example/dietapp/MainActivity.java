package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.dietapp.fragment.HomeFrag;
import com.example.dietapp.fragment.DietsFrag;
import com.example.dietapp.fragment.CalendarFrag;
import com.example.dietapp.fragment.SettingsFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFrag homeFrag;
    private DietsFrag dietsFrag;
    private CalendarFrag calendarFrag;
    private SettingsFrag settingsFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_diet:
                        setFrag(1);
                        break;
                    case R.id.action_calendar:
                        setFrag(2);
                        break;
                    case R.id.action_settings:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });
        homeFrag = new HomeFrag();
        dietsFrag = new DietsFrag();
        calendarFrag = new CalendarFrag();
        settingsFrag = new SettingsFrag();
        setFrag(0);
    }

    private void setFrag(int n){
        switch(n){
            case 0:
                replaceFragment(homeFrag);
                break;
            case 1:
                replaceFragment(dietsFrag);
                break;
            case 2:
                replaceFragment(calendarFrag);
                break;
            case 3:
                replaceFragment(settingsFrag);
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.main_frame, fragment).commit();

    }
}


//push test