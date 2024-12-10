package com.cw_oop.RealTimeEventTicketing.cli;

import java.util.logging.Logger;

public class Customer implements Runnable {
    private Configuration configuration;
    private Integer customerId;
    private static final Logger logger = Logger.getLogger(Customer.class.getName());
    private TicketPool ticketPool;

    public Customer(Configuration configuration, TicketPool ticketPool) {
        this.configuration = configuration;
        this.ticketPool = ticketPool;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public void run() {
        while (true) {
            Ticket ticket = null;
            try {
                ticket = ticketPool.removeTicket();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (ticket == null) {
                logger.info("Customer " + customerId + " stopped as there are no more tickets.");
                break;
            }
            logger.info("Customer " + customerId + " retrieved Ticket ID: " + ticket.getTicketId()+"-Ticket status is: "+ticket.getTicketStatus());
            try {
                if(ticketPool.getTicketRetrievingCount()%configuration.getTicketsRetrievalRate()==0) {
                    Thread.sleep(4000);
                }
            } catch (InterruptedException e) {
                logger.severe("Customer " + customerId + " interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

}