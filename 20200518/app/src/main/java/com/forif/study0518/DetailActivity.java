package com.forif.study0518;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private ListView listView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        ArrayList<Person> personArrayList = getIntent().getParcelableArrayListExtra("PERSON_INFO");
        // 전달할때 넣은 Key 값과 동일한 키로 정보를 회수합니다.

        //listView 객체를 참조해줍니다.
        listView = findViewById(R.id.detail_list_view);
        //listView 의 어댑터에 회수한 Person 리스트를 넣어줍니다.
        myAdapter = new MyAdapter(this, personArrayList);
        //listView 에 우리의 어댑터를 알려줍니다.
        listView.setAdapter(myAdapter);

    }
}
