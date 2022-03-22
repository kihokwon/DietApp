package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.dietapp.database.User;
import com.example.dietapp.database.UserDatabase;
import com.example.dietapp.fragment.HomeFrag;
import com.example.dietapp.fragment.DietsFrag;
import com.example.dietapp.fragment.CalendarFrag;
import com.example.dietapp.fragment.SettingsFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFrag homeFrag;
    private DietsFrag dietsFrag;
    private CalendarFrag calendarFrag;
    private SettingsFrag settingsFrag;
    // db
//    private UserDao mUserDao;

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

        // 내부 데이터베이스
        // 얘를 일단 MainActivity로 해놨는데 DB로 생성되는 걸 확인함.
//        UserDatabase database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "dietapp_db")
//                .fallbackToDestructiveMigration() // 스키마(db) 버전 변경 가능
//                .allowMainThreadQueries() // Main Thread에서 DB에 io(입출력)을 가능하게 함.
//                .build();
//        mUserDao = database.userDao();

        // insert data
//        User user = new User(); // 객체 인스턴스 생성
//        user.setName("kihokwon");
//        user.setAge("27");
//        user.setPhoneNumber("72162055");
//        mUserDao.setInsertUser(user);

        // select data
//        List<User> userList = mUserDao.getUserAll();
//        for (int i = 0; i < userList.size(); i++) {
//            Log.d("TEST", userList.get(i).getName() + "\n"
//            + userList.get(i).getAge() + "\n"
//            + userList.get(i).getPhoneNumber() + "\n");
//        }

        // delete data
//        User user2 = new User();
//        user2.setId(2);
//        mUserDao.setDeleteUser(user2);

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