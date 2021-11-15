package com.example.dietapp.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dietapp.R;
import com.example.dietapp.calendar.CalendarAdapter;
import com.example.dietapp.calendar.util.DateUtil;
import com.example.dietapp.calendar.util.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

// activity of calendar page
// 캘린더 페이지 액티비티
public class CalendarFrag extends Fragment implements View.OnClickListener{

    static int i = 0;
    public int mCenterPosition;
    private long mCurrentTime;
    public ArrayList<Object> mCalendarList = new ArrayList<>();

    public TextView textView;
    public RecyclerView recyclerView;
    private CalendarAdapter mAdapter;
    private StaggeredGridLayoutManager manager;
    public Button btn_prev;
    public Button btn_next;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_calendar, container, false);

        initView(rootView);

        initSet();

        setRecycler();

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_prev: {
                i-=1;
                initSet();
                setRecycler();
                break;
            }
            case R.id.btn_next:
            {
                i+=1;
                initSet();
                setRecycler();
                break;
            }
        }
    }

    public void initView(View v){

        textView = (TextView)v.findViewById(R.id.month);
        recyclerView = (RecyclerView)v.findViewById(R.id.calendar);
        btn_next = v.findViewById(R.id.btn_next);
        btn_prev = v.findViewById(R.id.btn_prev);
        btn_next.setOnClickListener(this);
        btn_prev.setOnClickListener(this);
    }

    public void initSet(){

        initCalendarList();

    }

    public void initCalendarList() {
        GregorianCalendar cal = new GregorianCalendar();
        setCalendarList(cal, i);
    }

    private void setRecycler() {

        if (mCalendarList == null) {
            Log.w("tag","No Query, not initializing RecyclerView");
        }

        manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);

        mAdapter = new CalendarAdapter(mCalendarList);

        mAdapter.setCalendarList(mCalendarList);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        if (mCenterPosition >= 0) {
            recyclerView.scrollToPosition(mCenterPosition);
        }
    }

    public void setCalendarList(GregorianCalendar cal, int i) {

        setTitle(cal.getTimeInMillis());

        ArrayList<Object> calendarList = new ArrayList<>();

        try {
            GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+i, 1, 0, 0, 0);

            mCenterPosition = calendarList.size();


            // 타이틀인듯
            calendarList.add(calendar.getTimeInMillis());

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; //해당 월에 시작하는 요일 -1 을 하면 빈칸을 구할 수 있겠죠 ?
            int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월에 마지막 요일

            // EMPTY 생성
            for (int j = 0; j < dayOfWeek; j++) {
                calendarList.add(Keys.EMPTY);
            }
            for (int j = 1; j <= max; j++) {
                calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j));
            }

            // TODO : 결과값 넣을떄 여기다하면될듯

        } catch (Exception e) {
            e.printStackTrace();
        }

        mCalendarList = calendarList;
    }


    public void setTitle(long date) {
        String title = DateUtil.getDate(date + i, DateUtil.CALENDAR_HEADER_FORMAT);
        textView.setText(title);
    }
}
