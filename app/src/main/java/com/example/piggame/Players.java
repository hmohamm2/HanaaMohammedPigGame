package com.example.piggame;

/**
 * Created by hanaa on 6/29/17.
 */
public class Players {

    private int currentPlayer;
    private int[] playerScores;

    public Players() {
        currentPlayer = 0;
        playerScores = new int[2];
    }

    public int getScore() {
        return this.playerScores[currentPlayer];
    }

    public void setScore(int score) {
        this.playerScores[currentPlayer] = score;
    }

    public void addScore(int points) {
        this.playerScores[currentPlayer] += points;
    }

    public int getPlayer() {
        return currentPlayer + 1;
    }

    public void changePlayer() {
        this.currentPlayer += 1;
        if (this.currentPlayer >= 2) {
            this.currentPlayer = 0;
        }
    }
}
