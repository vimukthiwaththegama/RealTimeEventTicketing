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
            Ticket ticket = ticketPool.removeTicket();
            if (ticket == null) {
                logger.info("Customer " + customerId + " stopped as the ticket pool is empty");
                break;
            }
            logger.info("Customer " + customerId + " retrieved Ticket ID: " + ticket.getTicketId());

            // Respecting ticket retrieval rate
            try {
                Thread.sleep(60000 / configuration.getTicketsRetrievalRate());
            } catch (InterruptedException e) {
                logger.severe("Customer " + customerId + " interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}