package com.derskeal.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //launch the activities
    public void mplay_3x3(View v) {
        Intent intent = new Intent(this, MThreeActivity.class);
        startActivity(intent);
    }

    public void mplay_4x4(View v) {
        Intent intent = new Intent(this, MFourActivity.class);
        startActivity(intent);
    }

    public void mplay_5x5(View v) {
        Intent intent = new Intent(this, MFiveActivity.class);
        startActivity(intent);
    }

    public void splay_3x3(View v) {
        Intent intent = new Intent(this, SThreeActivity.class);
        startActivity(intent);
    }

    public void splay_4x4(View v) {
        /*Intent intent = new Intent(this, SFourActivity.class);
        startActivity(intent);*/
    }

    public void splay_5x5(View v) {
        /*Intent intent = new Intent(this, SFiveActivity.class);
        startActivity(intent);*/
    }

    public void scoreboard(View v) {
        Intent intent = new Intent(this, ScoreBoardActivity.class);
        startActivity(intent);

        /*SharedPreferences sp = getSharedPreferences("dded", this.MODE_PRIVATE);
        SharedPreferences.Editor storage = sp.edit();

        int vp = sp.getInt("one two", 0);
        vp++;
        storage.putInt("one two",56);
        storage.apply();


        int r = sp.getInt("one two",0);
        Toast.makeText(this, ""+r, Toast.LENGTH_SHORT).show();*/
    }

}
