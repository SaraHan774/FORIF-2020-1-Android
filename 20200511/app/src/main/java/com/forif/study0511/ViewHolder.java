package com.forif.study0511;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewHolder extends LinearLayout {

    TextView name;
    TextView mobile;

    public ViewHolder(Context context){
        super(context);
        init(context);
    }

    public ViewHolder(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.phone_book_view, this, true);

        name = findViewById(R.id.tv_main_list_name);
        mobile = findViewById(R.id.tv_main_list_mobile);
    }

    public void setNameTextView(String data){
        name.setText(data);
    }

    public void setMobileTextView(String data){
        mobile.setText(data);
    }
}
