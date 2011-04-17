package ch.netgeek.travelmaster.route;

import java.util.ArrayList;
import java.util.Calendar;

public class TimeTable {

    private ListNode head;
    private ListNode tail;

    public ListNode newListNode(Calendar calendar) throws NullPointerException {
        ListNode listNode = new ListNode();
        listNode.setCalendar(calendar);
        return listNode;
    }

    public Calendar getNextDeparture(Calendar calendar) {
        
        int calendarTimeInSec = calendar.get(Calendar.HOUR) * 3600 
                                + calendar.get(Calendar.MINUTE) * 60;
        
        ListNode currentNode = head;
        while (currentNode != null) {
            int currentDepartureInSec = currentNode.getCalendar().get(
                    Calendar.HOUR) * 3600 + currentNode.getCalendar().get(
                    Calendar.MINUTE) * 60;
            if (currentDepartureInSec >= calendarTimeInSec) {
                return currentNode.getCalendar();
            }
            currentNode = currentNode.getNext();
        }
        
        return head.getCalendar();
    }

    public ArrayList<Calendar> getDepartures() {
        
        ArrayList<Calendar> departures = new ArrayList<Calendar>();
        
        ListNode currentNode = head;
        while (currentNode != null) {
            departures.add(currentNode.getCalendar());
            currentNode = currentNode.getNext();
        }
        
        return departures;
    }

    public void addDeparture(Calendar departure) {
        ListNode node = new ListNode();
        node.setCalendar(departure);
        sortedInsert(node);
    }
    
    private void prepend(ListNode node) throws NullPointerException {
        
        if (head == null || tail == null) {
            head = node;
            tail = node;
        } else {
            head.setPrevious(node);
            ListNode oldHead = head;
            head = node;
            head.setNext(oldHead);
        }
    }

    private void append(ListNode node) throws NullPointerException {
        
        if (head == null || tail == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            ListNode oldTail = tail;
            tail = node;
            tail.setPrevious(oldTail);
        }
    }
    
    private void sortedInsert(ListNode node) throws NullPointerException {
        //TODO  Check if sorted insert really works!!!
        
        if (head == null || node.getCalendar().compareTo(head.getCalendar()) < 0) {
            prepend(node);
        } else if (node.getCalendar().compareTo(tail.getCalendar()) > 0) {
            append(node);
        } else {
            ListNode pointer = head;
            ListNode lastPointer = null;
            while ((node.getCalendar().compareTo(pointer.getCalendar()) > 0) && pointer.getNext() != null) {
                lastPointer = pointer;
                pointer = pointer.getNext();
            }
            lastPointer.setNext(node);
            node.setPrevious(lastPointer);
            node.setNext(pointer);
            pointer.setPrevious(node);
        }
    }
    
    // Inner class
    private class ListNode {

        private Calendar calendar;
        private ListNode next;
        private ListNode previous;
        
        public ListNode getNext() {
            return next;
        }
        
        public ListNode getPrevious() {
            return previous;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
        
        public void setPrevious(ListNode previous) {
            this.previous = previous;
        }

        public Calendar getCalendar() {
            return calendar;
        }

        public void setCalendar(Calendar calendar) throws NullPointerException {
            this.calendar = calendar;
        }
        
    }

}
