package com.example.arcadedb.Beans;

public class Machine {
    private int id;
    private String machineName;
    private String description;
    private String location;
    private String publisher;
    private String genre;

    public Machine(int id, String machineName, String description, String location, String publisher, String genre) {
        this.id = id;
        this.machineName = machineName;
        this.description = description;
        this.location = location;
    }

    public Machine(int id, String machineName, String description, String publisher, String genre) {
        this.id = id;
        this.machineName = machineName;
        this.description = description;
        this.publisher = publisher;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String machineName() {
        return machineName;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return machineName;
    }
}
