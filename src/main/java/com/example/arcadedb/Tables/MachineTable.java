package com.example.arcadedb.Tables;

import com.example.arcadedb.Beans.Machine;
import com.example.arcadedb.DAO.MachineDAO;
import com.example.arcadedb.Database.DBConst;
import com.example.arcadedb.Database.Database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MachineTable implements MachineDAO {

    private static MachineTable instance;
    Database database = Database.getInstance();
    ArrayList<Machine> machines;

    public static MachineTable getInstance(){
        if (instance == null){
            instance = new MachineTable();
        }
        return instance;
    }
    @Override
    public void createMachine(Machine machine) {
        String query = "INSERT INTO " + DBConst.MACHINES_TABLE_NAME +
                "(" + DBConst.MACHINE_NAME + ", " +
                DBConst.MACHINE_DESCRIPTION + ", " +
                DBConst.MACHINE_PUBLISHER + ", " +
                DBConst.MACHINE_GENRE + ") VALUES ('" +
                machine.getMachineName() + "','" +
                machine.getDescription() + "','" +
                machine.getPublisher() + "','" +
                machine.getGenre() + "')";
        try {
            Statement insertMachine = database.getConnection().createStatement();
            insertMachine.executeUpdate(query);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Machine> getAllMachines() {
        String query = "SELECT * FROM " + DBConst.MACHINES_TABLE_NAME;

        machines = new ArrayList<>();
        try {
            Statement getMachines = database.getConnection().createStatement();
            ResultSet data = getMachines.executeQuery(query);
            while (data.next()) {
                machines.add(new Machine(
                        data.getInt(DBConst.MACHINES_ID),
                        data.getString(DBConst.MACHINE_NAME),
                        data.getString(DBConst.MACHINE_DESCRIPTION),
                        data.getString(DBConst.MACHINE_PUBLISHER),
                        data.getString(DBConst.MACHINE_GENRE)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return machines;
    }

    @Override
    public Machine getMachine(int id) {
        String query = "SELECT * FROM " + DBConst.MACHINES_TABLE_NAME +
                " WHERE " + DBConst.MACHINES_ID + " = " + id;
        try {
            Statement getMachine = database.getConnection().createStatement();
            ResultSet data = getMachine.executeQuery(query);
            if (data.next()){
                Machine machine = new Machine(
                        data.getInt(DBConst.MACHINES_ID),
                        data.getString(DBConst.MACHINE_NAME),
                        data.getString(DBConst.MACHINE_DESCRIPTION),
                        data.getString(DBConst.MACHINE_PUBLISHER),
                        data.getString(DBConst.MACHINE_GENRE)
                );
                return machine;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateMachine(Machine machine, String name, String description, String genre, String publisher) {
        String query = "UPDATE " + DBConst.MACHINES_TABLE_NAME + " SET " +
                DBConst.MACHINE_NAME + " = '" + name + "', " +
                DBConst.MACHINE_DESCRIPTION + " = '" + description + "', " +
                DBConst.MACHINE_PUBLISHER + " = '" + publisher + "', " +
                DBConst.MACHINE_GENRE + " = '" + genre + "' " +
                "WHERE " + DBConst.MACHINES_ID + " = " + machine.getId();
        try {
            Statement updateMachine = database.getConnection().createStatement();
            updateMachine.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteMachine(int id) {
        String query = "DELETE FROM " + DBConst.MACHINES_TABLE_NAME +
                " WHERE " + DBConst.MACHINES_ID + " = " + id;
        try {
            Statement deleteMachine = database.getConnection().createStatement();
            deleteMachine.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void clearMachineTable(){
        String query = "DELETE FROM " + DBConst.MACHINES_TABLE_NAME + ";";
        try {
            Statement deleteMachine = database.getConnection().createStatement();
            deleteMachine.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
