package com.cw_oop.RealTimeEventTicketing.cli;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private static Integer totalNumberOfTickets;
    private static Integer maxTicketCapacity;

    public TicketPool(Integer totalNumberOfTickets, Integer maxTicketCapacity) {
        TicketPool.totalNumberOfTickets = totalNumberOfTickets;
        TicketPool.maxTicketCapacity = maxTicketCapacity;
    }

    public static List<Ticket> ticketPool = Collections.synchronizedList(new ArrayList<Ticket>());

    public synchronized Boolean addTicket(Ticket ticket) {
        if(ticketPool.size() >= maxTicketCapacity || ticketPool.size() >= totalNumberOfTickets) {
            System.out.println("Ticket pool is full " + ticketPool.size());
            return false;
        }else {
            ticketPool.add(ticket);
            return true;
        }
    }
    public synchronized void removeTicket(Ticket ticket) {
        ticketPool.remove(ticket);
    }
}