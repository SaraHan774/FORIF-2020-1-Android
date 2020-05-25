package com.forif.study0511;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<PhoneInfo> dummyPhoneInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ListView 에 대한 참조를 한다.
        ListView listView = findViewById(R.id.main_listview);

        //어댑터에 데이터를 넘겨준다.
        dummyPhoneInfo = generateDummyPhoneInfo();

        //ListView 에 어댑터를 걸어준다. 데이터는 얘한테서 넘어올거야 ! 알려주는 것
        PhoneBookAdapter phoneBookAdapter = new PhoneBookAdapter(this, dummyPhoneInfo);
        listView.setAdapter(phoneBookAdapter);

    }

    //빨간 줄에 커서 올려두고 alt + enter -> implement methods -> OK 클릭
    class PhoneBookAdapter extends BaseAdapter{

        private ArrayList<PhoneInfo> phoneInfoArrayList;
        private Context context;

        public PhoneBookAdapter(Context context, ArrayList<PhoneInfo> phoneInfoArrayList){
            this.context = context;
            this.phoneInfoArrayList = phoneInfoArrayList;
        }

        @Override
        public int getCount() {
            // 띄울 아이켐들의 개수를 알려줍니다.
            return phoneInfoArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            // 해당 포지션의 아이템을 가져온다.
            return phoneInfoArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // 지정한 위치에 있는 데이터의 아이디 리턴
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 액자를 만들어 줍니다.
            ViewHolder view = new ViewHolder(context);

            view.setNameTextView(dummyPhoneInfo.get(position).getName());
            view.setMobileTextView(dummyPhoneInfo.get(position).getMobile());

            return view;
        }
    }

    private ArrayList<PhoneInfo> generateDummyPhoneInfo(){
        ArrayList<PhoneInfo> phoneInfoArrayList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            phoneInfoArrayList.add(
                    new PhoneInfo("Mr. Dummy " + i, generateDummyMobileNumber())
            );
        }
        return phoneInfoArrayList;
    }

    private String generateDummyMobileNumber(){
        Random random = new Random();
        String dummyMobileNumber = "010 - " + Math.abs(random.nextInt(10000)) + " - " +
                Math.abs(random.nextInt(10000));
        return dummyMobileNumber;
    }
}
