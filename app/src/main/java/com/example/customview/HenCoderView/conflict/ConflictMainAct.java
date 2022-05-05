package com.example.customview.HenCoderView.conflict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.customview.MainActivity;
import com.example.customview.R;

public class ConflictMainAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conflict_main);
    }

    public void jump_wrongXY(View view) {
        Intent intent = new Intent();
        intent.setClass(ConflictMainAct.this, xyWrongAct.class);
        startActivity(intent);
    }

    public void jump_xyByInner(View view) {
        Intent intent1 = new Intent();
        intent1.setClass(ConflictMainAct.this, xySolverByInnerAct.class);
        startActivity(intent1);
    }

    public void jump_xyByOutter(View view) {
        Intent intent1 = new Intent();
        intent1.setClass(ConflictMainAct.this, xySolverByOutterAct.class);
        startActivity(intent1);
    }
}