package com.cw_oop.RealTimeEventTicketing.cli;

import java.util.LinkedList;
import java.util.logging.Logger;

public class TicketPool {
    private static Integer totalNumberOfTickets;
    private static Integer maxTicketCapacity;
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());

    // Using LinkedList to allow FIFO (First In, First Out) behavior
    public static LinkedList<Ticket> ticketPool = new LinkedList<>();

    public TicketPool(Integer totalNumberOfTickets, Integer maxTicketCapacity) {
        TicketPool.totalNumberOfTickets = totalNumberOfTickets;
        TicketPool.maxTicketCapacity = maxTicketCapacity;
    }

    // Thread-safe addition of tickets
    public synchronized Boolean addTicket(Ticket ticket) {
        if (ticketPool.size() >= maxTicketCapacity || ticketPool.size() >= totalNumberOfTickets) {
            logger.info("Ticket pool is full " + ticketPool.size());
            return false;
        } else {
            ticketPool.add(ticket);
            logger.info("Ticket added to the ticket pool: Ticket ID " + ticket.getTicketId());
            return true;
        }
    }
    // Thread-safe removal of tickets
    public synchronized Ticket removeTicket() {
        if (ticketPool.isEmpty()) {
            logger.warning("Ticket pool is empty");
            return null;
        }
        Ticket ticket = ticketPool.removeFirst();
        logger.info("Ticket removed from ticket pool: Ticket ID " + ticket.getTicketId());
        return ticket;
    }
}

