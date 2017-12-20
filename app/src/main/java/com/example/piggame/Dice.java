package com.example.piggame;

import java.util.Random;

/**
 * Created by hanaa on 6/29/17.
 */
public class Dice {

    private int rollResult;

    private void roll() {
        Random rand = new Random();
        rollResult = rand.nextInt(6) + 1; //rand generates int from 0-5, add one to get 1-6
    }

    public int getLastRoll() {
        //returns result without initiating a roll
        return rollResult;
    }

    public int rollAndReturn() {
        //roll and return result
        this.roll();
        return rollResult;
    }
}
