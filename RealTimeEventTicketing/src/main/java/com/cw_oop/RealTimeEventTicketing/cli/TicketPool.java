package com.cw_oop.RealTimeEventTicketing.cli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class TicketPool {
    private static Integer totalNumberOfTickets;
    private static Integer maxTicketCapacity;
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());

    public TicketPool(Integer totalNumberOfTickets, Integer maxTicketCapacity) {
        TicketPool.totalNumberOfTickets = totalNumberOfTickets;
        TicketPool.maxTicketCapacity = maxTicketCapacity;
    }

    public static List<Ticket> ticketPool = Collections.synchronizedList(new ArrayList<Ticket>());

    public synchronized Boolean addTicket(Ticket ticket) {
        if (ticketPool.size() >= maxTicketCapacity || ticketPool.size() >= totalNumberOfTickets) {
            logger.info("Ticket pool is full " + ticketPool.size());
            return false;
        } else {
            ticketPool.add(ticket);
            logger.info("Ticket added: Ticket ID " + ticket.getTicketId());
            return true;
        }
    }

    public synchronized void removeTicket(Ticket ticket) {
        ticketPool.remove(ticket);
        logger.info("Ticket removed: Ticket ID " + ticket.getTicketId());
    }
}
