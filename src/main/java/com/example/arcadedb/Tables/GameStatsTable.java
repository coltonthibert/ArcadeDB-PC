package com.example.arcadedb.Tables;

import com.example.arcadedb.Beans.GameStats;
import com.example.arcadedb.DAO.GameStatsDAO;
import com.example.arcadedb.Database.DBConst;
import com.example.arcadedb.Database.Database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GameStatsTable implements GameStatsDAO {

    private static GameStatsTable instance;
    Database database = Database.getInstance();
    ArrayList<GameStats> gameStats;

    public static GameStatsTable getInstance(){
        if (instance == null){
            instance = new GameStatsTable();
        }
        return instance;
    }

    @Override
    public void createGameStats(GameStats gameStats) {
        String query = "INSERT INTO " + DBConst.GAME_STATS_TABLE_NAME +
                "(" + DBConst.COST_TO_PLAY + ", " +
                DBConst.TIMES_PLAYED + ") VALUES ('" +
                gameStats.getCostToPlay() + "','" +
                gameStats.getTimesPlayed() + "')";
        try {
            Statement insertGameStats = database.getConnection().createStatement();
            insertGameStats.executeUpdate(query);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<GameStats> getAllGameStats() {
        return null;
    }

    @Override
    public GameStats getGameStats(int id) {
        return null;
    }

    @Override
    public void updateGameStats(GameStats gameStats, double costToPlay, int timesPlayed) {
        return;
    }

    @Override
    public void deleteGameStats(int id) {
        return;
    }
}