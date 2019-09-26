package com.sk.elevator.metrics;

/**
 * A class representing the ElevatorMetrics object to track metrics pretaining to the elevator. These metrics are
 * printed to the final output at the end of program execution. The metrics track key features of an elevator such as
 * the number of floors requested, the number of times elevator was empty, total people who weren't able to ride
 * elevator due to max capacity, total people who rode the elevator, etc. These methods provide an overall picture of
 * the functioning of the elevator during the simulation and reveal any areas prime for improvement. A simple object
 * class is used to conduct this metric gathering and each key input is incremented over time.
 *
 * @author Samra Kasim
 */
public class ElevatorMetrics {

    private int fl1ReqTracker;
    private int fl2ReqTracker;
    private int fl3ReqTracker;
    private int fl4ReqTracker;
    private int fl5ReqTracker;
    private int totalPeopleWhoWantedToRideElevator;
    private int totalPeopleWhoRodeElevator;
    private int totalTurnaways;     // Represents people unable to ride elevator
    private int totalEmptyElevator; // Times when elevator was empty
    private int totalTemporaryExits;

    /**
     * Constructor for the ElevatorMetrics job used in instantiating the object at the beginning of the simulation.
     */
    public ElevatorMetrics() {
        this.fl1ReqTracker = 0;
        this.fl2ReqTracker = 0;
        this.fl3ReqTracker = 0;
        this.fl4ReqTracker = 0;
        this.fl5ReqTracker = 0;
        this.totalPeopleWhoWantedToRideElevator = 0;
        this.totalPeopleWhoRodeElevator = 0;
        this.totalTurnaways = 0;
        this.totalEmptyElevator = 0;
        this.totalTemporaryExits = 0;
    }

    /**
     * toString method to return the final key metrics
     * @return String returns key metrics to output
     */
    public String toString() {
        return (" * Floor 1 Total Requests: " + fl1ReqTracker +
                "\n * Floor 2 Total Requests: " + fl2ReqTracker +
                "\n * Floor 3 Total Requests: " + fl3ReqTracker +
                "\n * Floor 4 Total Requests: " + fl4ReqTracker +
                "\n * Floor 5 Total Requests: " + fl3ReqTracker +
                "\n * Total Riders: " + totalPeopleWhoWantedToRideElevator +
                "\n * Total Rides: " + totalPeopleWhoRodeElevator +
                "\n * Total Riders Unable to Ride: " + totalTurnaways +
                "\n * Total Times Elevator was Empty: " + totalEmptyElevator +
                "\n * Total Temporary Exits: " + totalTemporaryExits);
    }

    // Getters and Setters
    public int getFl1ReqTracker() {
        return fl1ReqTracker;
    }

    public int getFl2ReqTracker() {
        return fl2ReqTracker;
    }

    public int getFl3ReqTracker() {
        return fl3ReqTracker;
    }

    public int getFl4ReqTracker() {
        return fl4ReqTracker;
    }

    public int getFl5ReqTracker() {
        return fl5ReqTracker;
    }

    public int getTotalPeopleWhoWantedToRideElevator() {
        return totalPeopleWhoWantedToRideElevator;
    }

    public int getTotalPeopleWhoRodeElevator() {
        return totalPeopleWhoRodeElevator;
    }

    public int getTotalEmptyElevator() {
        return totalEmptyElevator;
    }

    public int getTotalTurnaways() {
        return totalTurnaways;
    }

    public int getTotalTemporaryExits() {
        return totalTemporaryExits;
    }

    public void setFl1ReqTracker(int fl1ReqTracker) {
        this.fl1ReqTracker = fl1ReqTracker;
    }

    public void setFl2ReqTracker(int fl2ReqTracker) {
        this.fl2ReqTracker = fl2ReqTracker;
    }

    public void setFl3ReqTracker(int fl3ReqTracker) {
        this.fl3ReqTracker = fl3ReqTracker;
    }

    public void setFl4ReqTracker(int fl4ReqTracker) {
        this.fl4ReqTracker = fl4ReqTracker;
    }

    public void setFl5ReqTracker(int fl5ReqTracker) {
        this.fl5ReqTracker = fl5ReqTracker;
    }

    public void setTotalPeopleWhoWantedToRideElevator(int totalPeopleWhoWantedToRideElevator) {
        this.totalPeopleWhoWantedToRideElevator = totalPeopleWhoWantedToRideElevator;
    }

    public void setTotalPeopleWhoRodeElevator(int totalPeopleWhoRodeElevator) {
        this.totalPeopleWhoRodeElevator = totalPeopleWhoRodeElevator;
    }

    public void setTotalEmptyElevator(int totalEmptyElevator) {
        this.totalEmptyElevator = totalEmptyElevator;
    }

    public void setTotalTurnaways(int totalTurnaways) {
        this.totalTurnaways = totalTurnaways;
    }

    public void setTotalTemporaryExits(int totalTemporaryExits) {
        this.totalTemporaryExits = totalTemporaryExits;
    }
}
