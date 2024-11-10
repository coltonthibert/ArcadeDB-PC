package com.example.arcadedb.Database;

/**
 * This class contains constants for the database.
 */
public class DBConst {
    // Machines table
    public static final String MACHINES_TABLE_NAME = "machines";

    public static final String MACHINES_ID = "id";
    public static final String MACHINE_NAME = "machine_name";
    public static final String MACHINE_DESCRIPTION = "description";
    public static final String MACHINE_PUBLISHER = "publisher";
    public static final String MACHINE_GENRE = "genre";

    // Machine_Details table
    public static final String MACHINE_DETAILS_TABLE_NAME = "machine_details";

    public static final String MACHINE_DETAILS_ID = "id";
    public static final String MACHINE_DETAILS_NAME = "machine_name";
    public static final String MACHINE_WEIGHT = "machine_weight_lbs";
    public static final String MACHINE_SIZE = "machine_size_inches";
    public static final String MACHINE_PRICE = "price";
    public static final String MAINTENANCE_REQUIRED = "maintenance_required";

    // Game_Stats table
    public static final String GAME_STATS_TABLE_NAME = "game_stats";
    public static final String GAME_STATS_ID = "id";
    public static final String GAME_STATS_NAME = "machine_name";
    public static final String COST_TO_PLAY = "cost_to_play";
    public static final String TIMES_PLAYED = "times_played";

    // SQL create table statements
    public static final String CREATE_MACHINES_TABLE = "CREATE TABLE " + MACHINES_TABLE_NAME + " (" +
            MACHINES_ID + " INT AUTO_INCREMENT PRIMARY KEY," +
            MACHINE_NAME + " VARCHAR(50)," +
            MACHINE_DESCRIPTION + " TEXT," +
            MACHINE_PUBLISHER + " VARCHAR(50)," +
            MACHINE_GENRE + " VARCHAR(50)" +
            ");";

    public static final String CREATE_MACHINE_DETAILS_TABLE = "CREATE TABLE " + MACHINE_DETAILS_TABLE_NAME + " (" +
            MACHINE_DETAILS_ID + " INT PRIMARY KEY," +
            MACHINE_DETAILS_NAME + " VARCHAR(50)," +
            MACHINE_WEIGHT + " INT," +
            MACHINE_SIZE + " VARCHAR(20)," +
            MACHINE_PRICE + " DECIMAL(10, 2)," +
            MAINTENANCE_REQUIRED + " BOOLEAN," +
            "FOREIGN KEY (" + MACHINE_DETAILS_ID + ") REFERENCES " + MACHINES_TABLE_NAME + "(" + MACHINES_ID + ")" +
            ");";

    public static final String CREATE_GAME_STATS_TABLE = "CREATE TABLE " + GAME_STATS_TABLE_NAME + " (" +
            GAME_STATS_ID + " INT PRIMARY KEY," +
            GAME_STATS_NAME + " VARCHAR(50)," +
            COST_TO_PLAY + " DECIMAL(6, 2)," +
            TIMES_PLAYED + " INT," +
            "FOREIGN KEY (" + GAME_STATS_ID + ") REFERENCES " + MACHINES_TABLE_NAME + "(" + MACHINES_ID + ")" +
            ");";
}
