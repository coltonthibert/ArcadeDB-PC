package com.example.arcadedb.Beans;

public class GameStats {
    private int id;
    private String machineName;
    private double costToPlay;
    private int timesPlayed;

    public GameStats(int id, String machineName, double costToPlay, int timesPlayed) {
        this.id = id;
        this.machineName = machineName;
        this.costToPlay = costToPlay;
        this.timesPlayed = timesPlayed;
    }

    @Override
    public String toString() {
        return machineName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public double getCostToPlay() {
        return costToPlay;
    }

    public void setCostToPlay(double costToPlay) {
        this.costToPlay = costToPlay;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }
}
