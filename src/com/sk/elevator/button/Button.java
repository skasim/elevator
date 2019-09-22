package com.sk.elevator.button;

public class Button {

    private int fl1;
    private int fl2;
    private int fl3;
    private int fl4;
    private int fl5;
    private boolean goingUp;
    private int currentFloor;
    private int lowestFloor;
    private int highestFloor;

    public Button() {
        this.fl1 = 0;
        this.fl2 = 0;
        this.fl3 = 0;
        this.fl4 = 0;
        this.fl5 = 0;
        this.goingUp = true;
        this.currentFloor = 1;
        this.lowestFloor = 5;
        this.highestFloor = 1;
    }

    public void zeroOutButtonForFloor(int floor) {
        switch(floor) {
            case 1:
                this.fl1=0;
                break;
            case 2:
                this.fl2=0;
                break;
            case 3:
                this.fl3=0;
                break;
            case 4:
                this.fl4=0;
                break;
            case 5:
                this.fl5=0;
        }
    }

    public void pushFloorRequestedButton(int floorRequested) {
//        calculateHighestLowestFloors(floorRequested); TODO clean up
        switch(floorRequested) {
            case 1:
                this.fl1++;
                break;
            case 2:
                this.fl2++;
                break;
            case 3:
                this.fl3++;
                break;
            case 4:
                this.fl4++;
                break;
            case 5:
                this.fl5++;
        }
    }

    public String toString() {
        return (" Fl1:" + fl1 + "\n Fl2:" + fl2 + "\n Fl3:" + fl3 + "\n Fl4:" + fl4 + "\n Fl5:" + fl5 +
                "\n Going up:" + goingUp + "\n Current Floor:" + currentFloor + "\n Lowest Floor:" + lowestFloor +
                "\n Highest Floor:" + highestFloor);
    }

    // TODO could be made private
    public void calculateHighestLowestFloors(int floorRequested) {

        if (floorRequested > this.highestFloor) {
            this.highestFloor = floorRequested;
        }
        if (floorRequested < this.lowestFloor) {
            this.lowestFloor = floorRequested;
        }
    }

//    // TODO you have not solved problem of ascending or descending
//    public int determineNextFloor(int nextPersonEntryFloor) {
//        int nextFloor;
//        if (goingUp) {
//            if (lowestFloor > nextPersonEntryFloor) {
//                nextFloor = nextPersonEntryFloor;
//            } else {
//                nextFloor = lowestFloor;
//            }
//            if (nextFloor < currentFloor) {
//                goingUp = false;
//            }
//        } else {
//            if (highestFloor < nextPersonEntryFloor) {
//                nextFloor = nextPersonEntryFloor;
//            } else {
//                nextFloor = highestFloor;
//            }
//            if (nextFloor > currentFloor) {
//                goingUp = true;
//            }
//        }
//        currentFloor = nextFloor;
//        return nextFloor;
//    }


    // TODO you have not solved problem of ascending or descending
    public int determineNextFloor(int nextPersonEntryFloor) {
        boolean haveNextFloor = false;
        int nextFloor = currentFloor;

        while (!haveNextFloor) { //TODO means haveNextFloor == false or not true
            if (goingUp) {
                nextFloor++;
                if (nextFloor == 5) goingUp = false;
                if(returnFloorRequests(nextFloor) == 0 && nextFloor != nextPersonEntryFloor) {
//                    nextFloor = (goingUp) ? nextFloor + 1 : nextFloor - 1; //TODO clean up
                } else {
                    if (nextFloor != currentFloor) {
                        haveNextFloor = true;
                    }
                }
            } else {
                nextFloor--;
                if (nextFloor == 1) goingUp = true;
                if (returnFloorRequests(nextFloor) == 0 && nextFloor != nextPersonEntryFloor) {
//                    nextFloor = (goingUp) ? nextFloor + 1 : nextFloor - 1; // TODO clean up
                }
                else {
                    if (nextFloor != currentFloor) {
                        haveNextFloor = true;
                    }
                }
            }

        }

        return nextFloor;
    }

    // TODO fix this case statement's return
    public int returnFloorRequests(int floor) {
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
        }
        return 0;
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

    public int getLowestFloor() {
        return lowestFloor;
    }

    public int getHighestFloor() {
        return highestFloor;
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

    public void setFl5(int fl5) {
        this.fl5 = fl5;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setLowestFloor(int lowestFloor) {
        this.lowestFloor = lowestFloor;
    }

    public void setHighestFloor(int highestFloor) {
        this.highestFloor = highestFloor;
    }
}
