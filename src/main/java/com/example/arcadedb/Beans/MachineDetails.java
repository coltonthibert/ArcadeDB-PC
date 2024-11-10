package com.example.arcadedb.Beans;

public class MachineDetails {
    private int id;
    private String machineName;
    private int machineWeightLbs;
    private String machineSizeInches;
    private double price;
    private int maintenanceRequired;

    public MachineDetails(int id, String machineName, int machineWeightLbs, String machineSizeInches, double price, int maintenanceRequired) {
        this.id = id;
        this.machineName = machineName;
        this.machineWeightLbs = machineWeightLbs;
        this.machineSizeInches = machineSizeInches;
        this.price = price;
        this.maintenanceRequired = maintenanceRequired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmachineName() {
        return machineName;
    }

    public void setmachineName(String machineName) {
        this.machineName = machineName;
    }

    public int getmachineWeightLbs() {
        return machineWeightLbs;
    }

    public void setmachineWeightLbs(int machineWeightLbs) {
        this.machineWeightLbs = machineWeightLbs;
    }

    public String getmachineSizeInches() {
        return machineSizeInches;
    }

    public void setmachineSizeInches(String machineSizeInches) {
        this.machineSizeInches = machineSizeInches;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getmaintenanceRequired() {
        return maintenanceRequired;
    }

    public void setmaintenanceRequired(int maintenanceRequired) {
        this.maintenanceRequired = maintenanceRequired;
    }

    @Override
    public String toString() {
        return machineName;
    }
}
