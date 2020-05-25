package com.forif.study0518;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyViewHolder extends LinearLayout {

        TextView name;
        TextView idNumber;

        public MyViewHolder(Context context){
            super(context);
            init(context);
        }

        public MyViewHolder(Context context, AttributeSet attributeSet){
            super(context, attributeSet);
            init(context);
        }

        private void init(Context context){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutInflater.inflate(R.layout.list_view_holder, this, true);

            name = findViewById(R.id.detail_list_name);
            idNumber = findViewById(R.id.detail_list_id_number);
        }

        public void setNameTextView(String data){
            name.setText(data);
        }

        public void setIdNumberTextView(String data){
            idNumber.setText(data);
        }
}
