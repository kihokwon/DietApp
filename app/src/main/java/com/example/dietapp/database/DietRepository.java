package com.example.dietapp.database;

import android.app.Application;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.dietapp.fragment.SettingsFrag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class DietRepository {
    private DietDAO dietDAO;
    private List<Diet> allDiets;
    private SettingsFrag settingsFrag;
    AssetManager assetManager;

    public DietRepository(Application application){
        DietDatabase db = DietDatabase.getDatabase(application);
        dietDAO = db.dietDAO();
        allDiets = dietDAO.getUserAll();

    }
    public List<Diet> getAllDiets(){return allDiets;}
    public void insert(Diet diet){
        DietDatabase.databaseWriteExecutor.execute(() ->{
            getDataFromAsset();
        });
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
            if(dietDAO.getUserAll().size() == 0){
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
                    dietDAO.setInsertDiet(diet);
                }
            }
            else{
                // 내용 조회
                //ArrayList<String> foodList = new ArrayList<>();
                String text = "";
//                String textText = Integer.toString(mUserDao.getUserAll().size());
                Log.d("file_test", Arrays.toString(token));
                List<Diet> dietList = dietDAO.getUserAll();
                for(int i = 0; i < dietList.size(); i++) {
//                    Log.d("DietTableList", dietList.get(i).getNo() + " "
//                            + dietList.get(i).getSubject() + " "
//                            + dietList.get(i).getFoodName() +"\n");
                    // textview에 foodName만 나오게끔 처리.
                    //foodList.add(dietList.get(i).getFoodName());
                    text += dietList.get(i).getFoodName() + "\n";
                }
                //MainActivity -> HomeFrag
                ActionButton(text);

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
    public void ActionButton(String foodList){
        Bundle bd = new Bundle();
        bd.putString("foodResult", foodList);
        settingsFrag.setArguments(bd);

    }
}
