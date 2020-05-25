package com.forif.study0518;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter{

        private ArrayList<Person> personArrayList;
        private Context context;

        public MyAdapter(Context context, ArrayList<Person> personArrayList){
            this.context = context;
            this.personArrayList = personArrayList;
        }

        @Override
        public int getCount() {
            // 띄울 아이켐들의 개수를 알려줍니다.
            return personArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            // 해당 포지션의 아이템을 가져온다.
            return personArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // 지정한 위치에 있는 데이터의 아이디 리턴
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 액자를 만들어 줍니다.
            MyViewHolder view = new MyViewHolder(context);

            view.setNameTextView(personArrayList.get(position).getName());
            view.setIdNumberTextView(personArrayList.get(position).getIdNumber());

            return view;
        }
}
