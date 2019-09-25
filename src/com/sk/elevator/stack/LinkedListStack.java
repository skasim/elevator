package com.sk.elevator.stack;

import com.sk.elevator.person.Person;

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

    public Person pop() {
        SNode temp = this.top;
        this.top = this.top.next;
        temp.next = null;
        this.size--;
        return temp.data;
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public Person peek() {
        return this.top.data;
    }

    public int size() {
        return this.size;
    }

    public boolean maxCapacityReached() {
        return  (this.size >= 5);
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
