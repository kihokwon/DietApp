package com.example.dietapp.diets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dietapp.R;
import com.example.dietapp.database.Diet;

import java.util.List;

public class SearchAdapter extends BaseAdapter {

    private List<Diet> list;
    private ViewHolder viewHolder;
    ListView listView;
    private SearchDialog dialog;

    public SearchAdapter(SearchDialog dialog){
        this.dialog = dialog;
    }

    public void setList(List<Diet> list) {
        this.list = list;
    }

    public void addItem(Diet diet) {
        list.add(diet);
    }

    public void setListView(ListView listView) {

        this.listView = listView;
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.listUpdate((Diet)listView.getItemAtPosition(position));
                dialog.dismiss();
            }
        });
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Diet getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_row, viewGroup, false);

        }
        TextView foodView = (TextView) convertView.findViewById(R.id.row_label);
        Diet diet = getItem(position);
        foodView.setText(diet.getFoodName());
        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.        
        return convertView;
    }

    class ViewHolder{
        public TextView label;
    }

}