
package com.cw_oop.RealTimeEventTicketing.cli;

import java.util.logging.Logger;

public class Vendor implements Runnable {
    private Configuration configuration;
    private Integer vendorId;
    private static final Logger logger = Logger.getLogger(Vendor.class.getName());
    private TicketPool ticketPool;

    public Vendor(Configuration configuration, TicketPool ticketPool) {
        this.configuration = configuration;
        this.ticketPool = ticketPool;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public void run() {
        int ticketId = 1;
        while (true) {
            Ticket ticket = new Ticket();
            ticket.setTicketId(ticketId);
            boolean added = ticketPool.addTicket(ticket);
            if (!added) {
                logger.info("Vendor " + vendorId + " stopped as the ticket pool is full");
                break;
            }
            logger.info("Vendor " + vendorId + " released Ticket ID: " + ticket.getTicketId());

            ticketId++;

            // Respecting ticket release rate
            try {
                Thread.sleep(60000 / configuration.getTicketsReleaseRate());
            } catch (InterruptedException e) {
                logger.severe("Vendor " + vendorId + " interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}