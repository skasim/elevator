package com.sk.elevator.stack;

import com.sk.elevator.person.Person;

import java.util.List;

public class LinkedListStack {

    private SNode top;
    private int size;

    public LinkedListStack() {
        this.top = null;
        this.size = 0;
    }

    public void push(Person person) {
        SNode node = new SNode();
        node.data = person;
        SNode temp = this.top;
        node.next = temp;
        this.top = node;
        this.size++;
    }

    public SNode pop() {
        SNode temp = this.top;
        this.top = this.top.next;
        temp.next = null;
        this.size--;
        return temp;
    }

    public boolean isEmpty() {
        return (top.next == null);
    }

    public Person peek() {
        return this.top.data;
    }

    public int size() {
        return this.size;
    }

    // TODO Delete this
    public void display(){
        try {
            SNode temp = top;
            int count = this.size;
            while (count >= 0) {
                System.out.printf(temp.data.toString() + " ==> ");
                temp = temp.next;
                count--;
            }
        } catch (NullPointerException e) {
            System.out.println("null-node");
        }
    }
}
