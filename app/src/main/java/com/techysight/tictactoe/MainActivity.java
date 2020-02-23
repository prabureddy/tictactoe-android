package com.techysight.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red

    int activePlayer = 0;

    // 2 means unplayed

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    boolean gameIsActive = true;

    boolean scoreCounterIsActive = true;

    // these are the winning positions

    int[][] winningPositions = {
                                {0,1,2},
                                {3,4,5},
                                {6,7,8},
                                {0,3,6},
                                {1,4,7},
                                {2,5,8},
                                {0,4,8},
                                {2,4,6}
                                        };

    public void addElement(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive == true) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.red);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 0;

            }

            counter.animate().translationYBy(1000f).setDuration(500);

        }

        for (int[] winningPosition: winningPositions) {

            if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                    && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2) {

                gameIsActive = false;

                LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutId);

                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessageId);

                String winnerText = "";

                if (gameState[winningPosition[0]] == 1) {

                    if(scoreCounterIsActive) {

                        scoreCounterIsActive = false;

                        TextView count = (TextView) findViewById(R.id.yellowCount);

                        count.setText(String.valueOf(Integer.parseInt(count.getText().toString()) + 1));

                    }

                    winnerText = "Yellow Has Won";

                } else {

                    if(scoreCounterIsActive) {

                        scoreCounterIsActive = false;

                        TextView count = (TextView) findViewById(R.id.redCount);

                        count.setText(String.valueOf(Integer.parseInt(count.getText().toString()) + 1));

                    }

                    winnerText = "Red Has Won";

                }

                winnerMessage.setText(winnerText);

                layout.setVisibility(View.VISIBLE);

            } else{

                boolean gameIsOver = true;

                for(int counterState : gameState){

                    if (counterState == 2) {

                        gameIsOver = false;

                        break;

                    }

                }

                if (gameIsOver){

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessageId);

                    winnerMessage.setText("Game is Draw!!!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutId);

                    layout.setVisibility(View.VISIBLE);

                }

            }

        }


    }

    public void playAgain(View view) {

        scoreCounterIsActive = true;

        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutId);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length ; i++) gameState[i] = 2;

        GridLayout giddier = (GridLayout) findViewById(R.id.gridview);

        for (int i = 0; i < giddier.getChildCount(); i++)
            ((ImageView) giddier.getChildAt(i)).setImageResource(0);

        gameIsActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
