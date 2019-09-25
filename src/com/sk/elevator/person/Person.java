package com.sk.elevator.person;

/**
 * Person object representing the rider.
 *
 * @author Samra Kasim
 */
public class Person {

    String name;
    int entryFloor;
    int exitFloor;
    int exitTracker;    // track the number of temporary exits made by the rider

    /**
     * Constructor to instantiate object with no parameters provided.
     */
    public Person() {
        this.exitTracker = 0;
    }

    /**
     * Constructor to instantiate object by providing certain parameters.
     * @param name: String representing rider's name.
     * @param entryFloor: int representing the floor the rider got on the elevator.
     * @param exitFloor: int representing the floor the rider will get off.
     */
    public Person(String name, int entryFloor, int exitFloor) {
        this.name = name;
        this.entryFloor = entryFloor;
        this.exitFloor = exitFloor;
        this.exitTracker = 0;
    }


    /**
     * Return string representing the Person object
     * @return String
     */
    public String toString( ) {
        return "Name: " + name + "; Entry Floor: " + entryFloor + "; Exit Floor: " + exitFloor + "; " +
                "Exit Tracker: " + exitTracker;
    }

    // Getters and Setters
    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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
}
