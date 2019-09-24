package com.sk.elevator.metrics;

public class ElevatorMetrics {

    int fl1ReqTracker;
    int fl2ReqTracker;
    int fl3ReqTracker;
    int fl4ReqTracker;
    int fl5ReqTracker;

    int totalPeopleWhoWantedToRideElevator;
    int totalPeopleWhoRodeElevator;
    int totalTurnaways;
    int totalEmptyElevator;

    public ElevatorMetrics() {
        this.fl1ReqTracker = 0;
        this.fl2ReqTracker = 0;
        this.fl3ReqTracker = 0;
        this.fl4ReqTracker = 0;
        this.fl5ReqTracker = 0;

        this.totalPeopleWhoWantedToRideElevator = 0;
        this.totalPeopleWhoRodeElevator = 0;
        this.totalTurnaways = 0;
        this.totalEmptyElevator= 0;
    }

    public String toString() { //TODO clean up the overflow
        return (" * Fl1 Total Requests:" + fl1ReqTracker + "\n * Fl2 Total Req:" + fl2ReqTracker + "\n * Fl3 Total Requests:" + fl3ReqTracker +
                "\n * Fl4 Total Requests:" + fl4ReqTracker + "\n * Fl5 Total Requests:" + fl3ReqTracker + "\n * Total Riders:" +
                totalPeopleWhoWantedToRideElevator + "\n * Total Rides:" + totalPeopleWhoRodeElevator +
                "\n * Total Turnaways:" + totalTurnaways + "\n * Total Empty Elevator:" + totalEmptyElevator);
    }

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
}
