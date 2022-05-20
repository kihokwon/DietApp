package com.example.dietapp.diets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.dietapp.R;
import com.example.dietapp.database.Diet;
import com.example.dietapp.fragment.DietsFrag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressLint("ValidFragment")
public class SearchDialog extends DialogFragment {

    private List<Diet> foodlist;
    private List<Diet> searchlist;
    private EditText editSearch;
    private MyDialogListener myListener;
    private CustomAdapter adapter;
    private ListView listView;
    private Activity activity;

    private ListView searchListView;
    private SearchAdapter searchAdapter;
    private DietsFrag originalFrag;

    public interface MyDialogListener {

        public void myCallback(String cityName);

    }

    public void setOriginalFrag(DietsFrag originalFrag) {
        this.originalFrag = originalFrag;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setListView(ListView listView) {
        this.listView = listView;



    }


    public void setAdapter(CustomAdapter adapter) {
        this.adapter = adapter;
    }

    @SuppressLint("ValidFragment")
    public SearchDialog(Activity activity, List<Diet> foodlist ) {
        this.activity = activity;
        this.foodlist = foodlist;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        try {
            myListener = (MyDialogListener) getTargetFragment();

        } catch (ClassCastException e) {

            throw new ClassCastException();

        }

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_search, null)).setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }

                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
//
        AlertDialog alert = builder.show();

        searchlist = new ArrayList<>();
        editSearch = alert.findViewById(R.id.editSearch);
        searchListView = alert.findViewById(R.id.search_result);
        searchAdapter = new SearchAdapter(this);
        searchAdapter.setListView(searchListView);



        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });



        return alert;

    }

    public void listUpdate(Diet diet) {
                        adapter.addItem(diet);
                        listView.setAdapter(adapter);
                        originalFrag.computeNutrients(diet.getKcal(), diet.getProtein(), diet.getCal(), diet.getFat(), diet.getSugar());
                        DietsFrag.setListViewHeightBasedOnChildren(listView);

    }


    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        searchlist.clear();
        System.out.println(123);
        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            searchlist.addAll(foodlist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < foodlist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (foodlist.get(i).getFoodName().toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    searchlist.add(foodlist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        searchAdapter.setList(searchlist);
        searchListView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }
}
