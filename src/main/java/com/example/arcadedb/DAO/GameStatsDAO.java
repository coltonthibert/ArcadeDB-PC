package com.example.arcadedb.DAO;

import com.example.arcadedb.Beans.GameStats;

import java.util.ArrayList;

public interface GameStatsDAO {
    void createGameStats(GameStats gameStats);
    ArrayList<GameStats> getAllGameStats();
    GameStats getGameStats(int id);
    void updateGameStats(GameStats gameStats, double costToPlay, int timesPlayed);
    void deleteGameStats(int id);
}