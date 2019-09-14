package com.sk.elevator.person;

public class Person {

    String name;
    int entryFloor;
    int exitFloor;
    int exitTracker;

    public Person() {}

    public Person(String name, int entryFloor, int exitFloor) {
        this.name = name;
        this.entryFloor = entryFloor;
        this.exitFloor = exitFloor;
        this.exitTracker = 0;
    }

    public int getEntryFloor() {
        return entryFloor;
    }

    public int getExitFloor() {
        return exitFloor;
    }

    public int getExitTracker() {
        return exitTracker;
    }

    public void setEntryFloor(int entryFloor) {
        this.entryFloor = entryFloor;
    }

    public void setExitFloor(int exitFloor) {
        this.exitFloor = exitFloor;
    }

    public void setExitTracker(int exitTracker) {
        this.exitTracker = exitTracker;
    }

    public String toString( ) {
        return "Name: " + name + "; Entry Floor: " + entryFloor + "; Exit Floor: " + exitFloor + "; Exit Tracker: " + exitTracker;
    }
}
