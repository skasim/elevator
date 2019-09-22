package com.sk.elevator.elevatorio;

import com.sk.elevator.Elevator;
import com.sk.elevator.button.Button;
import com.sk.elevator.person.Person;
import com.sk.elevator.stack.LinkedListStack;
import com.sk.elevator.stack.SNode;

public class ElevatorUtils {

    public static void loadPerson(Person person, LinkedListStack elevator, Button button) {
        elevator.push(person);
        button.pushFloorRequestedButton(person.getExitFloor());
        // TODO print name of person getting on plus floor
    }

    public static void unloadPeople(int currentFloor, LinkedListStack auxStack, LinkedListStack elevator,
                                    Button button) {
        int numOfPeopleToUnload = button.returnFloorRequests(currentFloor);

        for (int i=0; i<numOfPeopleToUnload; i++) {
            System.out.println(button.toString());
            System.out.println("numOfppltounload=" + numOfPeopleToUnload);
            System.out.println("ELEVATOR SIZE="+elevator.size());
            Person person = elevator.pop();
            if (person.getExitFloor() == currentFloor) {
                System.out.println("OUTTA HERE");
                // TODO add a print out to document
            } else {
                person.setExitTracker(person.getExitTracker()+1);
                auxStack.push(person);
                System.out.println("sadly this person had to temp get off=" + person.getName());
                // TODO write this out to document
            }
        }
        reloadPeople(auxStack, elevator);
    }

    public static void reloadPeople(LinkedListStack auxStack, LinkedListStack elevator) {
        while (!auxStack.isEmpty()) {
            Person person = auxStack.pop();
            elevator.push(person);
        }
    }
}
