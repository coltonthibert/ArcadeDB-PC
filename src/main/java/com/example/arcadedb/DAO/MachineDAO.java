package com.example.arcadedb.DAO;

import com.example.arcadedb.Beans.Machine;

import java.util.ArrayList;

public interface MachineDAO {
    public void createMachine(Machine machine);
    public ArrayList<Machine> getAllMachines();
    public Machine getMachine(int id);
    public void updateMachine(Machine machine, String name, String description, String genre, String publisher);
    public void deleteMachine(int id);
}
