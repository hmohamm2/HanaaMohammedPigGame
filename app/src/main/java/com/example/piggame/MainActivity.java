package com.example.piggame;

import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private Dice dice;
    private Players players;

    private EditText playerOne, playerTwo;
    private TextView playerOneScore, playerTwoScore, viewName, displayPoints;
    private ImageView diceImage;
    private Button rollButton, endButton, newGameButton;
    private int turnScore, playerOneTurn, playerTwoTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne = (EditText) findViewById(R.id.playerOneName);
        playerTwo = (EditText) findViewById(R.id.playerTwoName);

        viewName = (TextView) findViewById(R.id.displayPlayerName);
        displayPoints = (TextView) findViewById(R.id.displayPoints);
        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        diceImage = (ImageView) findViewById(R.id.diceImage);

        rollButton = (Button) findViewById(R.id.rollButton);
        endButton = (Button) findViewById(R.id.endButton);
        newGameButton = (Button) findViewById(R.id.newGameButton);



        startNewGame();

        //set the listeners
        playerOne.setOnEditorActionListener(this);
        playerTwo.setOnEditorActionListener(this);

        rollButton.setOnClickListener(this);
        newGameButton.setOnClickListener(this);
        endButton.setOnClickListener(this);

    }

    public void startNewGame() {
        players = new Players(); //player class will create two players
        dice = new Dice();

        //instantiate app w these values
        playerOneScore.setText(Integer.toString(0));
        playerTwoScore.setText(Integer.toString(0));

        diceImage.setImageResource(R.mipmap.ic_dice1);

        resetTurnScore(); //sets scores to 0

    }

    //on click listeners
    public void rollClick() {
        rollDice();
    }

    public void newGameClick() {
        if (players.getScore() > 100) {
            startNewGame();
        } else {
            startNewGame();
        }
    }

    public void endClick() {
        if (players.getPlayer() == 1) {
            playerOneScore.setText(Integer.toString(players.getScore()));
        } else if (players.getPlayer() == 2) {
            playerTwoScore.setText(Integer.toString(players.getScore()));
        }
        changePlayer();

    }


    private void setDiceImage(int generatedNum) {
        int[] imageRes = {R.mipmap.ic_dice1, R.mipmap.ic_dice2, R.mipmap.ic_dice3,
                R.mipmap.ic_dice4, R.mipmap.ic_dice5, R.mipmap.ic_dice6};

        diceImage.setImageResource(imageRes[generatedNum - 1]); //generatedNum is between 0-5
    }


    private void resetTurnScore() { //method specifically used for display
        turnScore = 0; //turn score is always 0 in this method
        displayPoints.setText(Integer.toString(turnScore));

        int current = players.getPlayer();
        if (current == 1) {
            playerOneTurn = 0;
            playerOneScore.setText(Integer.toString(turnScore));
            players.setScore(playerOneTurn);
        } else if (current == 2) {
            playerTwoTurn = 0;
            playerTwoScore.setText(Integer.toString(turnScore));
            players.setScore(playerTwoTurn);
        }
    }


    private void rollDice() {
        int rollResult = dice.rollAndReturn();
        setDiceImage(rollResult);

        String[] savedNames = saveName();
        String playerOneName = savedNames[0];
        String playerTwoName = savedNames[1];

        if (rollResult == 1) {
            resetTurnScore();
            changePlayer();

        } else {
            turnScore += rollResult; // turnScore changes value in this method
            //adds to score
            displayPoints.setText(Integer.toString(rollResult)); //displays numeric value for dice roll
            //get current player, display score
            int current = players.getPlayer();
            if (current == 1) {
                viewName.setText(playerOneName);
                playerOneTurn += rollResult;
                playerOneScore.setText(Integer.toString(playerOneTurn));
                players.setScore(playerOneTurn);
            } else if (current == 2) {
                viewName.setText(playerTwoName);
                playerTwoTurn += rollResult;
                playerTwoScore.setText(Integer.toString(playerTwoTurn));
                players.setScore(playerTwoTurn);
            }
        }
    }

    private void changePlayer() {

        players.changePlayer();
    }


    private String[] saveName() {
        String[] names = new String[2];

        if ( (playerOne.getText() != null) && (playerTwo.getText() != null) ) {
            String playerOneName = playerOne.getText().toString();
            String playerTwoName = playerTwo.getText().toString();

            names[0] = playerOneName;
            names[1] = playerTwoName;
        }
        return names;
    }


    @Override
    public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            String[] savedNames = saveName();
            String playerOneName = savedNames[0];
            String playerTwoName = savedNames[1];

//            switch (view.getId()) {
//                case R.id.playerOneName:
//                    if (players.getPlayer() == 1) {
//                        viewName.setText(playerOneName);
//                    }
//                    break;
//                case R.id.playerTwoName:
//                    if (players.getPlayer() == 2) {
//                        viewName.setText(playerTwoName);
//                    }
//                    break;
//            }

        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newGameButton:
                newGameClick();
                break;
            case R.id.rollButton:
                rollClick();
                break;
            case R.id.endButton:
                endClick();
                break;
        }

    }
}






