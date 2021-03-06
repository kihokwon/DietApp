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
    public SearchDialog(List<Diet> foodlist ) {
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
                // input?????? ????????? ?????????????????? ????????????.
                // search ???????????? ????????????.
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


    // ????????? ???????????? ?????????
    public void search(String charText) {

        // ?????? ??????????????? ???????????? ????????? ?????? ????????????.
        searchlist.clear();
        System.out.println(123);
        // ?????? ????????? ???????????? ?????? ???????????? ????????????.
        if (charText.length() == 0) {
            searchlist.addAll(foodlist);
        }
        // ?????? ????????? ??????..
        else
        {
            // ???????????? ?????? ???????????? ????????????.
            for(int i = 0;i < foodlist.size(); i++)
            {
                // arraylist??? ?????? ???????????? ???????????? ??????(charText)??? ???????????? ????????? true??? ????????????.
                if (foodlist.get(i).getFoodName().toLowerCase().contains(charText))
                {
                    // ????????? ???????????? ???????????? ????????????.
                    searchlist.add(foodlist.get(i));
                }
            }
        }
        // ????????? ???????????? ????????????????????? ???????????? ???????????? ????????? ???????????? ????????? ????????????.
        searchAdapter.setList(searchlist);
        searchListView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }
}
