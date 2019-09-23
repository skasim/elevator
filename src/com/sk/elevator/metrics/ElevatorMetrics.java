package com.sk.elevator.metrics;

public class ElevatorMetrics {

    int fl1ReqTracker;
    int fl2ReqTracker;
    int fl3ReqTracker;
    int fl4ReqTracker;
    int fl5ReqTracker;

    int totalRequests;
    int totalPeopleRidingElevator;
    int totalTurnaways;
    int totalEmptyElevator;
    int totalNumberOfStops;

    public ElevatorMetrics() {
        this.fl1ReqTracker = 0;
        this.fl2ReqTracker = 0;
        this.fl3ReqTracker = 0;
        this.fl4ReqTracker = 0;
        this.fl5ReqTracker = 0;

        this.totalRequests = 0;
        this.totalPeopleRidingElevator = 0;
        this.totalTurnaways = 0;
        this.totalEmptyElevator= 0;
        this.totalNumberOfStops= 0;
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

    public int getTotalRequests() {
        return totalRequests;
    }

    public int getTotalPeopleRidingElevator() {
        return totalPeopleRidingElevator;
    }

    public int getTotalEmptyElevator() {
        return totalEmptyElevator;
    }

    public int getTotalTurnaways() {
        return totalTurnaways;
    }

    public int getTotalNumberOfStops() {
        return totalNumberOfStops;
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

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public void setTotalPeopleRidingElevator(int totalPeopleRidingElevator) {
        this.totalPeopleRidingElevator = totalPeopleRidingElevator;
    }

    public void setTotalEmptyElevator(int totalEmptyElevator) {
        this.totalEmptyElevator = totalEmptyElevator;
    }

    public void setTotalNumberOfStops(int totalNumberOfStops) {
        this.totalNumberOfStops = totalNumberOfStops;
    }

    public void setTotalTurnaways(int totalTurnaways) {
        this.totalTurnaways = totalTurnaways;
    }
}
