package com.sk.elevator.button;

import com.sk.elevator.metrics.ElevatorMetrics;

/**
 * Class creates a Button object, which functions similar to the button panel in an elevator. This object tracks
 * for which floor a rider presses a button, which direction the elevator is traveling inm and provides methods that
 * facilitate determining, which floor to go to next. It's key method include determineNextFloor, which is used to
 * identify the next floor the elevator will move to and pushFloorRequestButton, which mimics the pressing of buttons
 * by riders as they get on the elevator and captures each riders desired exit floor.
 *
 * @author Samra Kasim
 */
public class Button {

    private int fl1;
    private int fl2;
    private int fl3;
    private int fl4;
    private int fl5;
    private boolean goingUp;    // Represents whether the elevator is ascending or descending
    private int currentFloor;   // Represents the current floor of the elevator at a point in time
    private int maxFloor;       // Used to calculate the highest floor requested at a point in time

    /**
     * Constructor method creates a Button object.
     */
    public Button() {
        this.fl1 = 0;
        this.fl2 = 0;
        this.fl3 = 0;
        this.fl4 = 0;
        this.fl5 = 0;
        this.goingUp = true;
        this.currentFloor = 1;
        this.maxFloor = currentFloor;
    }

    /**
     * Method that zeroes out a button once the floor has been reached. This is similar to how in an elevator button
     * panel, once you reach a requested floor the button is no longer lit.
     * @param floor: int value representing the floor reached.
     */
    public void zeroOutButtonForFloor(int floor) {
        switch(floor) {
            case 1:
                this.fl1 = 0;
                break;
            case 2:
                this.fl2 = 0;
                break;
            case 3:
                this.fl3 = 0;
                break;
            case 4:
                this.fl4 = 0;
                break;
            case 5:
                this.fl5 = 0;
                break;
            default:
                break;
        }
    }

    /**
     * Method tracks how many times a floor request has been made by incrementing the current value by 1.
     * @param floorRequested: int value representing the requested floor.
     * @param eMetrics: ElevatorMetrics object tracking overall simulation metrics related to the elevator.
     */
    public void pushFloorRequestedButton(int floorRequested, ElevatorMetrics eMetrics) {
        switch(floorRequested) {
            case 1:
                this.fl1++;
                eMetrics.setFl1ReqTracker(eMetrics.getFl1ReqTracker() + 1);
                break;
            case 2:
                this.fl2++;
                eMetrics.setFl2ReqTracker(eMetrics.getFl2ReqTracker() + 1);
                break;
            case 3:
                this.fl3++;
                eMetrics.setFl3ReqTracker(eMetrics.getFl3ReqTracker() + 1);
                break;
            case 4:
                this.fl4++;
                eMetrics.setFl4ReqTracker(eMetrics.getFl4ReqTracker() + 1);
                break;
            case 5:
                this.fl5++;
                eMetrics.setFl5ReqTracker(eMetrics.getFl5ReqTracker() + 1);
                break;
        }
    }

    /**
     * Method returns output for the Button object
     * @return String object representing the output for the Button object
     */
    public String toString() {
        return (" Fl1:" + fl1 + "\n Fl2:" + fl2 + "\n Fl3:" + fl3 + "\n Fl4:" + fl4 + "\n Fl5:" + fl5 +
                "\n Going up:" + goingUp + "\n Current Floor:" + currentFloor);
    }

    /**
     * Method calculates the maximum (i.e., highest) floor value for the final unloading of passengers in the
     * simulation by determining if provided floor is greater than maxfloor. If provided floor is greater then it
     * becomes the new max floor.
     * @param floorInStack: int value representing a floor
     * @return int value representing the maximum floor.
     */
    public int calculateMaxFloorForFinalUnload(int floorInStack) {

        if (floorInStack > maxFloor) {
            maxFloor = floorInStack;
        }
        return maxFloor;
    }

    /**
     * The method determines, which floor to travel to next based on three things 1) whether the elevator is ascending
     * or descending. If ascending the value of current floor is incremented and if descending the value of the
     * current flor is decremented; 2) The desired exit floor of a passenger currently on the elevator; and 3)
     * The desire entry floor of the next rider waiting to get on the elevator.
     * @param nextPersonEntryFloor: int value representing the desired entry floor of the next rider waiting to get on
     *                            the elevator
     * @return int value representing the next floor the elevator should travel to next.
     */
    public int determineNextFloor(int nextPersonEntryFloor) {
        boolean haveNextFloor = false;
        int nextFloor = currentFloor;

        while (!haveNextFloor) {
            if (goingUp) {      // If the elevator is going up
                nextFloor++;    // increment nextFloor by 1
                if (nextFloor == 5) goingUp = false;    // But if nextFloor is 5 switch goingUp boolean to false
                // If someone inside the elevator requested the next floor or if the next person waiting outside has
                // requested the next floor, then that is the next floor. Otherwise, increment one more time and test
                // again
                if (returnNumberOfFloorRequests(nextFloor) != 0 || nextFloor == nextPersonEntryFloor) {
                    if (nextFloor != currentFloor) {
                        haveNextFloor = true;
                    }
                }
            } else {
                nextFloor--;    // If the elevator is going down decrement the next floor by 1
                if (nextFloor == 1) goingUp = true;     // If nextFloor is 1, then switch goingUp boolean to true
                // If someone inside the elevator requested the next floor or if the next person waiting outside has
                // requested the next floor, then that is the next floor. Otherwise, increment one morre time and test
                // again.
                if (returnNumberOfFloorRequests(nextFloor) != 0 || nextFloor == nextPersonEntryFloor) {
                    if (nextFloor != currentFloor) {
                        haveNextFloor = true;
                    }
                }
            }

        }

        return nextFloor;
    }

    /**
     * Helper method to return the number of floor requests made based on the floor provided.
     * @param floor: int value representing the floor.
     * @return int value representing the number of riders who have requested a floor
     */
    public int returnNumberOfFloorRequests(int floor) {
        switch(floor) {
            case 1:
                return getFl1();
            case 2:
                return getFl2();
            case 3:
                return getFl3();
            case 4:
                return getFl4();
            case 5:
                return getFl5();
            default:
                return 0;
        }
    }

    // Getters and Setters

    public int getFl1() {
        return fl1;
    }

    public int getFl2() {
        return fl2;
    }

    public int getFl3() {
        return fl3;
    }

    public int getFl4() {
        return fl4;
    }

    public int getFl5() {
        return fl5;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setFl1(int fl1) {
        this.fl1 = fl1;
    }

    public void setFl2(int fl2) {
        this.fl2 = fl2;
    }

    public void setFl3(int fl3) {
        this.fl3 = fl3;
    }

    public void setFl4(int fl4) {
        this.fl4 = fl4;
    }

    public void setFl5(int fl5) { this.fl5 = fl5; }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }
}
