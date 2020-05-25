package com.forif.study0518;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayList<Person> personArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Person 이라는 모델 클래스를 통해서 객체들을 만들어 냅니다.
        Person sara = new Person("Sara", "9508082052052");
        Person mike = new Person("Mike", "9503081052052");
        Person jack = new Person("Jack", "9506101052052");

        personArrayList.add(sara);
        personArrayList.add(mike);
        personArrayList.add(jack);

        // 위의 객체들에 대한 정보에 접근하려면 getter 를 사용해야 한다.
        // 안드로이드 스투디오에서 값을 출력해 볼 때는 Log.d 메소드를 사용한다.
        Log.d(TAG, sara.getName());
        // sara.name 은 접근되지 않는다.
        Log.d(TAG, sara.getIdNumber());

        printAllPeople(personArrayList);

        // 위에서 만든 사람들에 대한 정보를 DetailActivity 로 넘겨줍시다.
        // 인텐트를 통해서 우리가 만든 Person 객체들을 넘겨주려면 이 객체들은 Parcelable 이어야 합니다.
        // 안드로이드는 우리가 만든 Person 클래스에 대한 정보를 모르므로 이를 우리가 Parcelable 이라는
        // 이름으로 넘겨주는 것입니다.
        Button button = findViewById(R.id.main_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putParcelableArrayListExtra("PERSON_INFO", personArrayList);
                startActivity(intent);
            }
        });
    }


    private void printAllPeople(ArrayList<Person> personArrayList){
        for (int i = 0; i < personArrayList.size(); i++) {
            // i 번째 Person 객체의 이름을 출력하라
            Log.d(TAG, personArrayList.get(i).getName());
        }
    }


}
