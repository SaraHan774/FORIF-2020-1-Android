# Android Study 20200511 

### Inflation 

* 쉽게 말하면 "화면을 띄워주는 것"
* `LayoutInflater` 를 이용한다
* XML 파일을 메모리에 객체화 하는 과정 (동적으로 XML 파일에 대한 객체를 위한 메모리를 할당하여 해당 객체를 Java 참조할 수 있도록 하는 것)
* `setContentView()` 를 내부적으로 1순위로 컴파일 하도록 바뀌어서 이 함수 이전에 선언된 뷰 객체들에 대한 참조가 가능함 
* `FrameLayout` 으로 만들어진 컨테이너 안에다가 레이아웃을 동적으로 Inflate 해보자. 



```java
FrameLayout container = findViewById(R.id.container); 
// inflater 객체를 참조한다
LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATION_SERVICE); 
// inflater 객체에 inflate() 함수를 호출한다. 
inflater.inflate(R.layout.xml_file_name, container, true);
//inflate 되는 레이아웃 안에 있는 텍스트뷰를 참조해보자. 
TextView textView = contianer.findViewById(R.id.showText); 
textView.setText("제가 보인다구요?"); 
```

> 컨테이너 레이아웃 안에 또 다른 부분 화면을 inflate 시키는 과정. 



### ListView

* 같은 구조의 뷰가 반복되는 경우 ListView 를 사용한다. 
* 같은 모양의 액자 안에 데이터만 바뀌면서 띄워주는 것. 
* Adapter 는 액자에 데이터를 띄워주는 역할을 한다. 
* 어딘가에서 받아온 데이터 -> 어댑터에 전달 (보통 어댑터 클래스의 생성자를 통해서 데이터를 전달함) -> 액자에 데이터를 띄워줌 (data population)



* 참고 : https://recipes4dev.tistory.com/42
* 다큐먼트 : https://developer.android.com/reference/android/widget/ListView
* ListView 의 더 나은 버전인 RecyclerView : https://developer.android.com/guide/topics/ui/layout/recyclerview 
  * 현재는 긴 리스트인 경우 ListView 말고 RecyclerView 사용을 권장합니다. 데이터 양이 더 많을 경우 RecyclerView 는 ViewHolder 를 재사용하기 때문에 메모리를 더 적게 잡아먹기 때문입니다. 사용 방법은 거의 동일하니 ListView 를 아시면 RecyclerView 도 쉽게 배우실 수 있습니다. 



#### ListView 실습 

1. Activity 의 XML 파일 안에 ListView 를 추가해 줍니다. 

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Phone Book"/>

    <ListView
        android:id="@+id/main_listview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</LinearLayout>
```



2. 액자의 틀을 만들어 줍시다. 반복되는 이름과 폰 번호를 담고 있는 레이아웃 입니다. 

(phone_book_view.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:gravity="center"
    android:padding="16dp"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="horizontal">


    <TextView
        android:id="@+id/tv_main_list_name"
        tools:text="Sample Name"
        android:layout_marginEnd="80dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <TextView
        android:id="@+id/tv_main_list_mobile"
        tools:text="Sample Number"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>


</LinearLayout>
```



3. 액자의 틀을 위한 Java 파일을 만들어 줍니다. (ViewHolder.java)

```java
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
```



4. MainActivity 에다가 Adapter 를 위한 Java 클래스를 만들어 줍니다. 그리고 어댑터에 가짜 데이터를 만들어서 생성자로 데이터를 넘겨줍니다. 

```java
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

    //가짜 연락처 데이터를 만드는 메소드 입니다. 
    private ArrayList<PhoneInfo> generateDummyPhoneInfo(){
        ArrayList<PhoneInfo> phoneInfoArrayList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            phoneInfoArrayList.add(
                    new PhoneInfo("Mr. Dummy " + i, generateDummyMobileNumber())
            );
        }
        return phoneInfoArrayList;
    }

    // 가짜 전화번호를 만드는 메소드 입니다. 
    private String generateDummyMobileNumber(){
        Random random = new Random();
        String dummyMobileNumber = "010 - " + Math.abs(random.nextInt(10000)) + " - " + Math.abs(random.nextInt(10000));
        return dummyMobileNumber;
    }
}

```

<img src="./assets/Screenshot_1589194488.png" alt="Screenshot_1589194488" style="zoom: 33%;" />

> 결과 예시입니다. 