package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dietapp.database.Diet;
import com.example.dietapp.database.DietDAO;
import com.example.dietapp.database.DietDatabase;
import com.example.dietapp.fragment.HomeFrag;
import com.example.dietapp.fragment.DietsFrag;
import com.example.dietapp.fragment.CalendarFrag;
import com.example.dietapp.fragment.SettingsFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
    TextView testTextView;
    Button file_btn;
    // asset Manager
    AssetManager assetManager;
    // database
    private DietDAO mUserDao;

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
                        // db 관련 함수 집어넣음.
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

        // Inner Database
        testTextView = findViewById(R.id.et_id);
        file_btn = findViewById(R.id.file_btn);

        // asset
        assetManager = getResources().getAssets();
        // database
        DietDatabase database = Room.databaseBuilder(getApplicationContext(),
                DietDatabase.class, "DietApp.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        mUserDao = database.dietDAO();

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
        // 작업 추가와 실제 수행 시점을 합쳐놓음.
        ft.replace(R.id.main_frame, fragment).commit();
    }

    // database functions

    public void mOnAssetData(View view){
        getDataFromAsset();
    }

    public void getDataFromAsset(){
        InputStream inputStream = null;
        try{
            //asset manager에게서 inputstream 가져오기
            inputStream = assetManager.open("DietDB2.txt", AssetManager.ACCESS_BUFFER);
            //문자로 읽어들이기
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //파일읽기
            String line = "";
            String[] token = new String[0];
            // create db entity
            if(mUserDao.getUserAll().size() == 0){
                while((line=reader.readLine()) != null){
                    token = line.split("\t");
                    // token -> db table
                    Diet diet = new Diet();
                    diet.setNo(Integer.parseInt(token[0]));
                    diet.setSubject(token[1]);
                    diet.setFoodName(token[2]);
                    diet.setYear(Integer.parseInt(token[3]));
                    diet.setRegion(token[4]);
                    diet.setVolume(Integer.parseInt(token[5]));
                    diet.setUnit(token[6]);
                    diet.setKcal(Integer.parseInt(token[7]));
                    diet.setProtein(Integer.parseInt(token[8]));
                    diet.setFat(Integer.parseInt(token[9]));
                    diet.setCal(Integer.parseInt(token[10]));
                    diet.setSugar(Integer.parseInt(token[11]));
                    diet.setNa(Integer.parseInt(token[12]));
                    diet.setChol(Integer.parseInt(token[13]));
                    diet.setSFat(Integer.parseInt(token[14]));
                    diet.setSource(token[15]);
                    diet.setIssuer(token[16]);
                    mUserDao.setInsertDiet(diet);
                }
            }
            else{
                // 내용 조회
                String text = "";
//                String textText = Integer.toString(mUserDao.getUserAll().size());
                Log.d("file_test", Arrays.toString(token));
                ArrayList<Diet> dietList = (ArrayList<Diet>) mUserDao.getUserAll();
                for(int i = 0; i < dietList.size(); i++) {
//                    Log.d("DietTableList", dietList.get(i).getNo() + " "
//                            + dietList.get(i).getSubject() + " "
//                            + dietList.get(i).getFoodName() +"\n");
                    // textview에 foodName만 나오게끔 처리.
                    text += dietList.get(i).getFoodName() + "\n";
                }
                Log.d("test", text);
                //내용 출력 // Bundle 써서 보냄.
                Fragment resultFragment = new HomeFrag();
                Bundle bd = new Bundle();
//                bd.putString("foodResult", text);
                bd.putParcelableArrayList("foodResult", (ArrayList<Diet>) dietList);
                resultFragment.setArguments(bd);
                dietsFrag.setArguments(bd);
                calendarFrag.setArguments(bd);
                settingsFrag.setArguments(bd);
                // textText는 올바른 값이 나오는 반면, textView가 자꾸 null이 나옴.
                //testTextView.setText(finalText);
                //ActionButton(finalText);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try { inputStream.close(); }
                catch (IOException ignored) {}
            }
        }

    }
    // transfer data to TextView
    public void ActionButton(String text){
        file_btn.setOnClickListener(v -> testTextView.setText(text));
    }

}
//push test