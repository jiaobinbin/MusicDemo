package com.test.musicdemo.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.test.musicdemo.R;

/**
 * Created by Administrator on 2016/3/12.
 */
public class WelcomeActivity extends AppCompatActivity {

    boolean isf = true;
    public final String isfirst = "isfirst";
    private Button btnLogin = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences file = getSharedPreferences("file",MODE_PRIVATE);
        isf = file.getBoolean(isfirst,true);
        if(isf == false){
            Intent it = new Intent(getApplicationContext(),Main_activity.class);
            startActivity(it);
            finish();

        }else{
            SharedPreferences.Editor e = file.edit();
            e.putBoolean(isfirst,false);
            e.commit();
        }
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.btnStart);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
