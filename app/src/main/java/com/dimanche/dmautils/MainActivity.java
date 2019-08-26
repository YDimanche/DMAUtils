package com.dimanche.dmautils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dimanche.dmautils.widget.RoundTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RoundTextView roundTextView = findViewById(R.id.round_textview);
        roundTextView.setText("测试");
        roundTextView.setBackColor(getResources().getColor(R.color.colorAccent));
        roundTextView.setRadius((int) getResources().getDimension(R.dimen.custome));
//        roundTextView.setBoderWidth((int) getResources().getDimension(R.dimen.custome));
        roundTextView.setBorderColor(getResources().getColor(R.color.colorPrimaryDark));

    }
}
