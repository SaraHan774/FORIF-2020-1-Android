package com.forif.study20200525;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// Main 에서 import 한 KEY 값을 사용합니다.
import static com.forif.study20200525.MainActivity.USER_ID_KEY;

public class DetailActivity extends AppCompatActivity {

    public static final String RESULT_DATA_KEY = "result_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String userId = getIntent().getExtras().getString(USER_ID_KEY);
        Toast welcomeToast = Toast.makeText(this, "Welcome! " + userId, Toast.LENGTH_LONG);
        welcomeToast.setGravity(Gravity.CENTER, 0, 0);
        welcomeToast.show();

        Button btnFinish = findViewById(R.id.btn_finish_activity);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(RESULT_DATA_KEY, "Sara is logged in.");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
