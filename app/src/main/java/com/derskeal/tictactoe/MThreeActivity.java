package com.derskeal.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MThreeActivity extends AppCompatActivity {

    public boolean valtouse = true;
    public int taptimes = 0;

    public String[] asv = new String[10]; //asv - all squares value
    public boolean gamewon = false;
    public boolean gamedrawn = false;

    public String[] player = {"","",""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mthree);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                reset_game();
            }
        });


        avatar_select();
        //avatar_select(2, "Player 2");

        //TextView v = (TextView) findViewById(R.id.player_turn_id);
        //v.setText("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mthree, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_main_menu) {
            //finish();
            //System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void avatar_select() {
        //Toast.makeText(this, "chai", Toast.LENGTH_SHORT).show();
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("Please select your Player Symbol")
                .setTitle("Player 1");

        builder.setPositiveButton("X", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set the player symbol to X
                /*playsym = "X";
                defplaysym = "X";*/
                player[1] = "X";
                player[2] = "O";
                valtouse = true;
                /*TextView v = (TextView) findViewById(R.id.player_turn_id);
                v.setText("X");*/
            }
        });

        builder.setNegativeButton("O", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set the player symbol to O
                /*playsym = "O";
                defplaysym = "O";*/
                player[1] = "O";
                player[2] = "X";
                valtouse = false;
                /*TextView v = (TextView) findViewById(R.id.player_turn_id);
                v.setText("O");*/
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MThreeActivity.this, "Player Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void cell_clicked(View v) {
        cell_clicked2(v);

        TextView k = (TextView)findViewById(R.id.player_turn_id);
        if(gamewon || gamedrawn) {
            k.setText("Game Over");
        }

    }

    public void cell_clicked2(View vv) {
        SharedPreferences sharedPref = getSharedPreferences( "tttm3", Context.MODE_PRIVATE);
        SharedPreferences.Editor storage = sharedPref.edit();

        if (gamewon) {

        } else if (taptimes == 9 && !gamewon) {

        }

        else {

            //check if the cell is filled
        /*
        * if filled, do nothing
        * if empty, fill it with the appropriate player symbol and,
        * check if there is a winner
        *
        *
        * */

            TextView v = (TextView) vv;

            if (taptimes < 10 && TextUtils.isEmpty(v.getText().toString())) {
                String playsym;
                if (valtouse) {
                    playsym = player[1];
                    //playsym = "X";
                } else {
                    playsym = player[2];
                    //playsym = "O";
                }

                int tag = Integer.parseInt(v.getTag().toString());
                asv[tag] = playsym;

                v.setText(playsym);
                valtouse = !valtouse;


            } else if (taptimes >= 9) {
                Toast.makeText(this, "Game Over. It's a draw", Toast.LENGTH_SHORT).show();

                //String pp = p == "Player 1" ? "player1" : "player2";

            }

            else {
                Toast.makeText(this, "Already played cell", Toast.LENGTH_SHORT).show();
            }

            TextView pti = (TextView) findViewById(R.id.player_turn_id);
            if(!gamedrawn) {
                String pt = valtouse ? "Player 1" : "Player 2";
                pti.setText(pt); //this guy sets the player turn
            } else {
                String pt = "Game Over";
                pti.setText(pt);
            }

            taptimes++;

            //
            int[][] wi = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9},
                    {1, 4, 7},
                    {2, 5, 8},
                    {3, 6, 9},
                    {1, 5, 9},
                    {3, 5, 7},
            };


            for (int[] i : wi) {
                int a, b, c;
                a = i[0];
                b = i[1];
                c = i[2];

                if (!TextUtils.isEmpty(asv[a]) && asv[a] == asv[b] && asv[a] == asv[c]) {

                    //// TODO: 4/6/18 delete this comment
                    /*String p1 = "player1";
                    String p2 = "player2";*/

                    String p = asv[a] == player[1] ? "Player 1" : "Player 2";
                    String winner = "Winner: " + p;



                    String pp = p == "Player 1" ? "player1" : "player2";
                    String op = p == "Player 1" ? "player2" : "player1";

                    int vp = sharedPref.getInt(pp+"wins", 0);
                    int vpl = sharedPref.getInt(op+"losses", 0);
                    vp++;
                    vpl++;


                    storage.putInt(pp+"wins",vp);
                    storage.putInt(op+"losses",vpl);
                    storage.apply();



                    Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
                    gamewon = true;

                    TextView win = (TextView)findViewById(R.id.winner_status);
                    win.setText(winner);

                    break;
                }

                /*if (!TextUtils.isEmpty(asv[a]) && asv[a] != asv[b] && asv[a] != asv[c]) {

                }*/

            }

            if (taptimes == 9 && !gamewon) {
                //if the game ends in a draw, the below code executes
                Toast.makeText(this, "Game Over. It's a draw", Toast.LENGTH_SHORT).show();
                gamedrawn = true;

                int vp1 = sharedPref.getInt("player1draws", 0);
                int vp2 = sharedPref.getInt("player2draws", 0);
                vp1++;
                vp2++;
                storage.putInt("player1draws",vp1);
                storage.putInt("player2draws",vp2);
                storage.apply();


                String pt = "Game Over";
                pti.setText(pt);

            }


        }
    }

    public void reset_game() {
        gamewon = false;
        TextView win = (TextView)findViewById(R.id.winner_status);
        win.setText("");
        taptimes = 0;

        valtouse = true;
        TextView pti = (TextView) findViewById(R.id.player_turn_id);
        String pt = valtouse ? "Player 1" : "Player 2";
        pti.setText(pt);

        asv = new String[10];

        TextView tv = (TextView) findViewById(R.id.c1);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c2);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c3);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c4);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c5);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c6);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c7);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c8);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c9);
        tv.setText("");

    }

}
