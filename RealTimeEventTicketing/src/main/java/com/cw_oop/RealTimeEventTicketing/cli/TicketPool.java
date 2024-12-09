package com.cw_oop.RealTimeEventTicketing.cli;

import java.util.LinkedList;
import java.util.logging.Logger;

public class TicketPool {
        private static Integer totalNumberOfTickets;
        private static Integer maxTicketCapacity;
        private static final Logger logger = Logger.getLogger(TicketPool.class.getName());
        public static LinkedList<Ticket> ticketPool = new LinkedList<>();

        private Integer ticketCount = 0;

        public TicketPool(Integer totalNumberOfTickets, Integer maxTicketCapacity) {
            TicketPool.totalNumberOfTickets = totalNumberOfTickets;
            TicketPool.maxTicketCapacity = maxTicketCapacity;
        }

    public synchronized Boolean addTicket(Ticket ticket) throws InterruptedException {
        while (ticketPool.size() >= maxTicketCapacity) {
            logger.warning("Ticket pool is full. Vendor waiting...");
            wait();
        }

        if (ticketCount >= totalNumberOfTickets) {
            logger.info("All tickets have been released.");
            return false;
        }

        ticket.setTicketId(++ticketCount);
        ticketPool.add(ticket);
        logger.info("Ticket added to the ticket pool: Ticket ID " + ticket.getTicketId());
        notifyAll();
        return true;
    }


        public synchronized Ticket removeTicket() throws InterruptedException {
            while (ticketPool.isEmpty()) {
                if (ticketCount >= totalNumberOfTickets) {
                    logger.info("No more tickets to retrieve. Customer waiting stopped.");
                    return null;
                }
                logger.warning("Ticket pool is empty. Customer waiting...");
                wait();
            }

            Ticket ticket = ticketPool.removeFirst();
            logger.info("Ticket removed from ticket pool: Ticket ID " + ticket.getTicketId());
            notifyAll();
            return ticket;
        }
    }



