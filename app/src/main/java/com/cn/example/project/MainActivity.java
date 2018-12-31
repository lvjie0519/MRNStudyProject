package com.cn.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;

import com.cn.example.project.rn.MyFirstRnActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goRnPage(View view){
        Intent intent = new Intent(this, MyFirstRnActivity.class);
        startActivity(intent);
    }

    // js 调用 java  需要返回值

    public void testBtn(View view){
        boolean is24 = DateFormat.is24HourFormat(this);
        Log.i("lvjie", "is24="+is24);
    }

}
