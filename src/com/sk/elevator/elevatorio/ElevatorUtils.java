package com.sk.elevator.elevatorio;

import com.sk.elevator.button.Button;
import com.sk.elevator.fileio.FileUtils;
import com.sk.elevator.metrics.ElevatorMetrics;
import com.sk.elevator.person.Person;
import com.sk.elevator.stack.LinkedListStack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class ElevatorUtils {

    public static void loadPerson(Person person, LinkedListStack elevator, Button button, ElevatorMetrics eMetrics, String outfilepath) {
        elevator.push(person);
        button.pushFloorRequestedButton(person.getExitFloor(), eMetrics);
        eMetrics.setTotalPeopleWhoRodeElevator(eMetrics.getTotalPeopleWhoRodeElevator() + 1);
        try {
            FileUtils.writeFileLineByLine(new File(outfilepath), "Getting on Floor[" + person.getEntryFloor() +"] is [" + person.getName() + "] and will get off at [" + person.getExitFloor() + "]");
        } catch (IOException e) {
            System.err.println(e.toString()); //TODO clean up all of these
        }
    }

    public static void finalUnload(LinkedListStack elevator, Button button, BufferedWriter writer, String outfilepath) {
        if (!elevator.isEmpty()) {
            LinkedListStack auxStack = new LinkedListStack();

            while(!elevator.isEmpty()) {
                Person tmpPerson = elevator.pop();
                button.calculateMaxFloorForFinalUnload(tmpPerson.getExitFloor());
                auxStack.push(tmpPerson);
            }

            while(!auxStack.isEmpty()) {
                Person tmpPerson = auxStack.pop();
                elevator.push(tmpPerson);
            }

            while(button.getMaxFloor() > button.getCurrentFloor() && !elevator.isEmpty()) {
                LinkedListStack tmpStack = new LinkedListStack();
                button.setCurrentFloor(button.getCurrentFloor()+1);
                if (button.returnNumberOfFloorRequests(button.getCurrentFloor()) > 0) {
                    unloadPeople(button.getCurrentFloor(), tmpStack, elevator, button, writer, outfilepath);
                }
            }
            // now we are at max floor, so we should go down
            while (button.getCurrentFloor() !=1 || !elevator.isEmpty()) {
                LinkedListStack tmpStack = new LinkedListStack();
                button.setCurrentFloor(button.getCurrentFloor()-1);
                if (button.returnNumberOfFloorRequests(button.getCurrentFloor()) > 0) {
                    unloadPeople(button.getCurrentFloor(), tmpStack, elevator, button, writer, outfilepath);
                }

            }


        }
        System.out.println("current floor=" + button.getCurrentFloor());

        System.out.println("max floor=" + button.getMaxFloor());
        elevator.display();

    }

    public static void unloadPeople(int currentFloor, LinkedListStack auxStack, LinkedListStack elevator,
                                    Button button, BufferedWriter writer, String outfilepath) {
        int numOfPeopleToUnload = button.returnNumberOfFloorRequests(currentFloor);

        int i = 0;
        while (i < numOfPeopleToUnload) {
            System.out.println(button.toString());
            System.out.println("numOfppltounload=" + numOfPeopleToUnload);
            System.out.println("ELEVATOR SIZE="+elevator.size());
            Person person = elevator.pop();
            if (person.getExitFloor() == currentFloor) {
                System.out.println("OUTTA HERE=" + person.toString());
                try {
                    FileUtils.writeFileLineByLine(new File(outfilepath), "Exiting Floor[" + person.getExitFloor() +"] is [" + person.getName() + "] with [" + person.getExitTracker() + "] temporary exits");
                } catch (IOException e) {
                    System.err.println(e.toString()); //TODO clean up all of these
                }

                i++;
                // TODO add a print out to document
            } else {
                person.setExitTracker(person.getExitTracker()+1);
                auxStack.push(person);
                System.out.println("sadly this person had to temp get off=" + person.getName());
                try {
                    FileUtils.writeFileLineByLine(new File(outfilepath), "Temporarily Exiting Floor[" + currentFloor +"] is [" + person.getName() + "]");
                } catch (IOException e) {
                    System.err.println(e.toString()); //TODO clean up all of these
                }
                // TODO write this out to document
            }
        }
        button.zeroOutButtonForFloor(currentFloor);
        reloadPeople(auxStack, elevator);
    }

    public static void reloadPeople(LinkedListStack auxStack, LinkedListStack elevator) {
        while (!auxStack.isEmpty()) {
            Person person = auxStack.pop();
            elevator.push(person);
        }
    }
}
