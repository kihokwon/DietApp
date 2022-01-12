package com.example.dietapp.settings;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.dietapp.R;

import java.util.HashMap;

public class TotalDietManual extends Fragment implements View.OnClickListener, NumberPicker.OnValueChangeListener {
    private View view;
    private Activity activity;
    private ConstraintLayout btn_age;
    private ConstraintLayout btn_gender;
    private ConstraintLayout btn_activity;
    private ConstraintLayout btn_weight;
    private ConstraintLayout btn_height;
    private ConstraintLayout btn_goal;
    private TextView btn_calc;


    final String[] items1 = {"남자", "여자"};
    final String[] items2 = {"활동이 거의 없음", "낮은 활동량", "활동적","매우 활동적","과도한 활동적"};
    final String[] items3 = {"체중 증가", "체중 유지", "체중 감소"};
    String result = "";

    private HashMap<Integer, Integer> values;

    private int height=175;
    private int age=26;
    private String activity_level="활동적";
    private String gender="남자";
    private String goal="체중 유지";
    private int weight=72;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings_total_manual, container, false);

        initView(view);

        return view;
    }


    public int calculation() {
        double result;
        System.out.println(gender);
        if (gender.equals(items1[0])) {
            result = 66.47 + 13.75*weight + 5*height - 6.76*age;
        } else {
            result = 65.51 + 9.56 *weight + 1.85*height - 4.68*age;
            System.out.println(result);
        }
        switch (activity_level){
            case "활동이 거의 없음":
                result=result*1.2;
                result -= 500;
                break;
            case "낮은 활동량":
                result=result*1.375;
                result -= 300;
                break;
            case "활동적":
                result=result*1.555;
                result -= 100;
                break;
            case "매우 활동적":
                result=result*1.725;
                break;
            case "과도한 활동적":
                result=result*1.9;
        }

        switch (goal) {
            case "체중 증가":
                result += 200;
                break;
            case "체중 유지":
                break;
            case "체중 감소":
                result -= 200;
                break;
        }

        System.out.println(result);
        return (int)result;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_age:
                showNumberPicker(v, "나이", "변경", 150, 0, 1, 20, R.id.age_value);
                break;
            case R.id.change_height:
                showNumberPicker(v, "신장", "변경", 220, 0, 1, 150, R.id.height_value);
                break;
            case R.id.change_weight:
                showNumberPicker(v, "체중", "변경", 170, 0, 1, 35, R.id.weight_value);
                break;
            case R.id.calculation:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("사용자 총대사량 계산")
                        .setMessage(String.format("총대사량 %dkcal",calculation()))
                        .setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.change_gender:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("성별").setSingleChoiceItems(items1, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // TODO Auto-generated method stub

                        Toast.makeText(getActivity(), items1[which], Toast.LENGTH_SHORT).show();
                        result = items1[which];
                    }

                }).setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                        .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TextView genderView = view.findViewById(R.id.gender_value);
                                gender = result;
                                genderView.setText(result);
                            }
                        }).show();
                break;

            case R.id.change_activity:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("활동레벨").setSingleChoiceItems(items2, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // TODO Auto-generated method stub

                        Toast.makeText(getActivity(), items2[which], Toast.LENGTH_SHORT).show();
                        result = items2[which];
                    }

                }).setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                        .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("활동레벨" + result);
                                TextView activityView = view.findViewById(R.id.activity_value);
                                activity_level = result;
                                activityView.setText(result);
                            }
                        }).show();
                break;

            case R.id.change_goal:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(getActivity());
                builder3.setTitle("체중 변화 목표").setSingleChoiceItems(items3, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // TODO Auto-generated method stub

                        Toast.makeText(getActivity(), items3[which], Toast.LENGTH_SHORT).show();
                        result = items3[which];
                    }

                }).setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                        .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TextView goalView = view.findViewById(R.id.goal_value);
                                goal = result;
                                goalView.setText(result);
                            }
                        }).show();
                break;
        }

    }

    public void initView(View v) {
        activity = getActivity();
        btn_age = v.findViewById(R.id.change_age);
        btn_age.setOnClickListener(this);
        btn_gender = v.findViewById(R.id.change_gender);
        btn_gender.setOnClickListener(this);
        btn_activity = v.findViewById(R.id.change_activity);
        btn_activity.setOnClickListener(this);
        btn_weight = v.findViewById(R.id.change_weight);
        btn_weight.setOnClickListener(this);
        btn_height = v.findViewById(R.id.change_height);
        btn_height.setOnClickListener(this);
        btn_goal = v.findViewById(R.id.change_goal);
        btn_goal.setOnClickListener(this);
        btn_calc = v.findViewById(R.id.calculation);
        btn_calc.setOnClickListener(this);
    }

    @Override
    public void onValueChange(NumberPicker picker, int value, int id) {
        int setting_value = value;
        TextView text = view.findViewById(id);
        text.setText("" + setting_value);

        switch (id){
            case R.id.activity_value:
//                activity_level = value;
                break;
            case R.id.height_value:
                height = value;
                break;
            case R.id.weight_value:
                weight = value;
                break;
            case R.id.age_value:
                age = value;
                break;
            case R.id.goal_value:
//                goal = value;
                break;
            case R.id.gender_value:
//                gender = value;
        }
        //원하는 동작 수행
    }

    public void showNumberPicker(View view, String title, String subtitle, int maxvalue, int minvalue, int step, int defvalue, int id) {
        NumberPickerDialog newFragment = new NumberPickerDialog();

        //Dialog에는 bundle을 이용해서 파라미터를 전달한다
        Bundle bundle = new Bundle(7); // 파라미터는 전달할 데이터 개수
        bundle.putString("title", title); // key , value
        bundle.putString("subtitle", subtitle); // key , value
        bundle.putInt("maxvalue", maxvalue); // key , value
        bundle.putInt("minvalue", minvalue); // key , value
        bundle.putInt("step", step); // key , value
        bundle.putInt("defvalue", defvalue); // key , value
        bundle.putInt("id", id);
        newFragment.setArguments(bundle);
        //class 자신을 Listener로 설정한다
        newFragment.setValueChangeListener(this);
        newFragment.show(activity.getFragmentManager(), "number picker");
//        newFragment.
    }
}
