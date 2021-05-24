package com.example.connect3;

import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0:red, 1:black, 2:blank
    int activePlayer = 0;
    int[] gameState = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };
    int[][] gameWinningPosition = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameIsOver = false;

    public void dropIn(View view) {
        MediaPlayer mediaPlayer;

        // GridLayout gameBoard = (GridLayout) findViewById(R.id.gameboardGridLayout);
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        String currentPlayer = "";

        if (!gameIsOver && gameState[tappedCounter] == 2) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1200);
            if (activePlayer == 0) {
                currentPlayer = "red";
                counter.setImageResource(R.drawable.redchips);

                activePlayer = 1;
            } else {
                currentPlayer = "black";
                counter.setImageResource(R.drawable.blackchips);
                activePlayer = 0;
            }
            mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(currentPlayer,"raw", getPackageName()));
            mediaPlayer.start();

            counter.animate().translationYBy(1200).rotation(3600).setDuration(300);
            String winner = "";
            for (int[] winningPostion : gameWinningPosition) {
                if (gameState[winningPostion[0]]==gameState[winningPostion[1]] && gameState[winningPostion[1]]==gameState[winningPostion[2]] && gameState[winningPostion[2]]!=2) {
                    if (activePlayer == 0) {
                        winner = "Black";
                    } else {
                        winner = "Red";
                    }
                    gameIsOver = true;
                }
            }
            if (gameIsOver) {

                mediaPlayer = MediaPlayer.create(this, R.raw.winning);
                mediaPlayer.start();

                Button replayButton = (Button) findViewById(R.id.playAgainButton);

                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                winnerTextView.setText(winner + " has won!");

                replayButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);

            }
        }
    }

    public void playAgain(View view) {


        Button replayButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        replayButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gameBoard = (GridLayout) findViewById(R.id.boardGridLayout);
        for (int i=0; i<gameBoard.getChildCount(); i++) {
            ImageView counter = (ImageView) gameBoard.getChildAt(i);
            counter.setImageDrawable(null);
        }

        activePlayer = 0;
        for (int i=0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        gameIsOver = false;

    }

    public void playAgain2(View view) {

        Button replayButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        replayButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        for (int i=0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        gameIsOver = false;

        /*
        // GridLayout boardLayout = findViewById(R.id.boardGridLayout);
        ImageView counter = (ImageView) findViewById(R.id.imageView0);
        counter.setImageDrawable(null);
        counter = (ImageView) findViewById(R.id.imageView1);
        counter.setImageDrawable(null);
        counter = (ImageView) findViewById(R.id.imageView2);
        counter.setImageDrawable(null);
        counter = (ImageView) findViewById(R.id.imageView3);
        counter.setImageDrawable(null);
        counter = (ImageView) findViewById(R.id.imageView4);
        counter.setImageDrawable(null);
        counter = (ImageView) findViewById(R.id.imageView5);
        counter.setImageDrawable(null);
        counter = (ImageView) findViewById(R.id.imageView6);
        counter.setImageDrawable(null);
        counter = (ImageView) findViewById(R.id.imageView7);
        counter.setImageDrawable(null);
        counter = (ImageView) findViewById(R.id.imageView8);
        counter.setImageDrawable(null); */

        for (int i=0; i<=8; i++) {
            String imgViewTag = "imageView" + String.valueOf(i);
            ImageView counter = (ImageView) findViewById(getResources().getIdentifier(imgViewTag,"id", getPackageName()));
            counter.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
