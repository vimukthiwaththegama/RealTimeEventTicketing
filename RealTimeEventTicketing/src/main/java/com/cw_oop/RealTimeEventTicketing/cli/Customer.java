package com.cw_oop.RealTimeEventTicketing.cli;

import java.util.logging.Logger;

public class Customer implements Runnable{
    Configuration configuration;
    private Integer customerId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer(Configuration configuration){
        this.configuration = configuration;
    }
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());
    @Override
    public void run() {
        TicketPool ticketPool = new TicketPool(configuration.getTotalNumberOfTickets(), configuration.getMaxTicketCapacity());
        while (!TicketPool.ticketPool.isEmpty()) {
            if (TicketPool.ticketPool.size() % configuration.getTicketsRetrievalRate() == 0) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Ticket ticket = TicketPool.ticketPool.get(0); // Get the first ticket
            ticketPool.removeTicket(ticket);
            logger.info("Customer " + customerId + " retrieved Ticket ID: " + ticket.getTicketId() + " from ticket pool");

        }
    }


}
