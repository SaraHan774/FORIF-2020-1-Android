package com.forif.study20200525;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.forif.study20200525.DetailActivity.RESULT_DATA_KEY;

public class MainActivity extends AppCompatActivity {

    public static final String USER_ID_KEY = "user_id_key";
    private TextView tvLoginResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Edit text 객체를 참조해줍니다.
        final EditText editText = findViewById(R.id.et_main_id);
        tvLoginResult = findViewById(R.id.tv_main_login_result);

        Button btnGoToMenu = findViewById(R.id.btn_go_to_menu);
        btnGoToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = "";
                // 반드시 null 혹은 empty string 이 아닌지 확인해주어야 합니다.
                if(editText.getText() != null && !editText.getText().toString().equals("")){
                    userId = editText.getText().toString();
                }else{
                    userId = "anonymous";
                }

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                // intent 객체에 데이터를 붙여서 다음 화면으로 넘겨줍니다.
                intent.putExtra(USER_ID_KEY, userId);
                startActivityForResult(intent, 1010);
                //startActivity(intent);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.d("DEBUG", "requestCode : " + requestCode + " resultCode : " + resultCode + " data : " + data.getExtras().getString(RESULT_DATA_KEY));
        if(requestCode == 1010 && resultCode == RESULT_OK){
            tvLoginResult.append(data.getExtras().getString(RESULT_DATA_KEY));
        }else{
            tvLoginResult.append("No Data!");
        }
    }
}
