package com.sk.elevator.elevatorio;

import com.sk.elevator.Elevator;
import com.sk.elevator.button.Button;
import com.sk.elevator.fileio.FileUtils;
import com.sk.elevator.metrics.ElevatorMetrics;
import com.sk.elevator.person.Person;
import com.sk.elevator.stack.LinkedListStack;

import java.io.File;
import java.io.IOException;

import static com.sk.elevator.fileio.FileUtils.writeFileLineByLine;

/**
 * Class featuring utility methods to assist in performing tasks related to loading riders on and off an elevator.
 *
 * @author Samra Kasim
 */
public class ElevatorUtils {

    /**
     * Method to load a person, after checking if maximum elevator capacity is reached, onto the elevator.
     * @param person: Person object representing the person to load onto the elevator.
     * @param elevator: Elevator object representing the elevator.
     * @param button: Button object representing the button panel in an elevator.
     * @param eMetrics: ElevatorMetrics object representing overall metrics tracked for the elevator.
     * @param outFile: File object representing the output file.
     */
    public static void loadPerson(Person person, LinkedListStack elevator, Button button, ElevatorMetrics eMetrics,
                                  File outFile) {
        // check if max elevator capacity is reached. If true, print output to text file and increment appropriate
        // metric
        if (maxCapacityReached(elevator)) {
            try {
                writeFileLineByLine(outFile, "=xxx= Elevator at max capacity! ["
                        + person.getName() + "] was not able to get on at floor [" +
                        button.getCurrentFloor() + "]");
                eMetrics.setTotalTurnaways(eMetrics.getTotalTurnaways() + 1);
            } catch (IOException e) {
                System.err.println(e.toString());
            }

        } else {
            // if max capacity is not reached, then load the person, output action to text file, and increment
            // appropriate metrics
            elevator.push(person);
            button.pushFloorRequestedButton(person.getExitFloor(), eMetrics);
            eMetrics.setTotalPeopleWhoRodeElevator(eMetrics.getTotalPeopleWhoRodeElevator() + 1);
            try {
                FileUtils.writeFileLineByLine(outFile, "<==== Getting on Floor[" +
                        person.getEntryFloor() +"] is [" + person.getName() + "] and will get off at ["
                        + person.getExitFloor() + "]");
            } catch (IOException e) {
                System.err.println(e.toString());
            }
        }
    }

    /**
     * Method representing the final unload process for the elevator. This is an extra feature included in this
     * simulation and was not directly outlined in the project requirements. In office hours, Dr. Chlan said that
     * the simulation should always start with the elevator on floor 1, which made me think consider that the elevator
     * should always end on floor1. So after the simulation ends and the last row is read in the input file. This
     * method calculates the highest floor button pushed by a rider in the elevator and ascendas to that floor dropping
     * riders off and then descends to the first floor while dropping riders off.
     * @param elevator: Elevator object representing the elevator.
     * @param button: Button object representing the button panel in an elevator.
     * @param outFile: String representing the path to the output file.
     */
    public static void finalUnload(LinkedListStack elevator, Button button, File outFile) {
        // only perform this method if the elevator is not empty
        if (!elevator.isEmpty()) {
            LinkedListStack auxStack = new LinkedListStack();

            // while the elevator is not empty, push every rider in the elevator to an auxiliary stack so as to
            // calculate the highest floor of hte people still remaining in the elevator
            while(!elevator.isEmpty()) {
                Person tmpPerson = elevator.pop();
                button.calculateMaxFloorForFinalUnload(tmpPerson.getExitFloor());
                auxStack.push(tmpPerson);
            }

            // then push riders in auxiliary stack back to elevator stack
            while(!auxStack.isEmpty()) {
                Person tmpPerson = auxStack.pop();
                elevator.push(tmpPerson);
            }

            // work the elevator up to the highest floor dropping people off only at the requested floors
            while(button.getMaxFloor() > button.getCurrentFloor() && !elevator.isEmpty()) {
                button.setCurrentFloor(button.getCurrentFloor()+1);
                if (button.returnNumberOfFloorRequests(button.getCurrentFloor()) > 0) {
                    unloadPeople(button.getCurrentFloor(), elevator, button, outFile);
                }
            }
            // once max floor is reached, begin final descent and drop riders off at requested floors
            // finally stopping at floor 1
            while (button.getCurrentFloor() !=1 || !elevator.isEmpty()) {
                button.setCurrentFloor(button.getCurrentFloor()-1);
                if (button.returnNumberOfFloorRequests(button.getCurrentFloor()) > 0) {
                    unloadPeople(button.getCurrentFloor(), elevator, button, outFile);
                }
            }
        }
    }

    /**
     * Method to unload people from an elevator. Specifically, method identifies the number of riders who want to get
     * off on a specific floor and then only moves riders from elevator stack to auxiliary stack until all the riders
     * who want to get off on that floor have been let out. This prevents unnecessarily removing riders from the
     * elevator stack to the auxiliary stack.
     *
     * @param currentFloor: int value representing the current floor.
     * @param elevator: Elevator object representing the elevator.
     * @param button: Button object representing the button panel in an elevator.
     * @param outFile: File object representing the output file.
     */
    public static void unloadPeople(int currentFloor, LinkedListStack elevator, Button button, File outFile) {

        LinkedListStack auxStack = new LinkedListStack();
        int numOfPeopleToUnload = button.returnNumberOfFloorRequests(currentFloor);
        int i = 0;

        // continue number popping riders from elevator stack to auxiliary stack until all the riders who want to get
        // off the elevator have been let out
        while (i < numOfPeopleToUnload) {
            Person person = elevator.pop();
            // if a rider wants to get off on a floor then print to output and not push onto auxiliary stack
            if (person.getExitFloor() == currentFloor) {
                try {
                    FileUtils.writeFileLineByLine(outFile, "====> Exiting Floor[" +
                            person.getExitFloor() +"] is [" + person.getName() + "] with [" + person.getExitTracker()
                            + "] temporary exits");
                } catch (IOException e) {
                    System.err.println(e.toString());
                }
                i++;    // tracks number of people who wanted to exit at a floor and have been let off // TODO CHECK
            } else {
                // track the number of temporary exits a rider has to make
                person.setExitTracker(person.getExitTracker()+1);
                // push the rider onto the auxiliary stack and capture action to output
                auxStack.push(person);
                try {
                    FileUtils.writeFileLineByLine(outFile, "<===> Temporarily Exiting Floor[" +
                            currentFloor +"] is [" + person.getName() + "]");
                } catch (IOException e) {
                    System.err.println(e.toString()); //
                }
            }
        }
        // once floor has been unloaded, zero out the floor button, which is similar to when a lit button in an
        // elevator becomes unlit after a floor has been reached
        button.zeroOutButtonForFloor(currentFloor);
        // reload riders from auxiliary stack to elevator stack
        reloadPeople(auxStack, elevator);
    }

    /**
     * Helper method to reload riders form auxiliary stack to elevator stack.
     * @param auxStack: LinkedListStack object representing a temporary stack.
     * @param elevator: Elevator object representing the elevator.
     */
    private static void reloadPeople(LinkedListStack auxStack, LinkedListStack elevator) {
        while (!auxStack.isEmpty()) {
            Person person = auxStack.pop();
            elevator.push(person);
        }
    }
    /**
     * Method to check if max capacity of the elevator is reached
     * @return boolean
     */
    public static boolean maxCapacityReached(LinkedListStack elevator) {
        return (elevator.size() >= 5);
    }
}
