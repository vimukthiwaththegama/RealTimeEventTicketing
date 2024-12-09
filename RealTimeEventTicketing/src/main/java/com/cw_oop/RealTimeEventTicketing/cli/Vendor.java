
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
        while (true) {
            Ticket ticket = new Ticket();
            boolean added;
            try {
                added = ticketPool.addTicket(ticket);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!added) {
                logger.info("Vendor " + vendorId + " stopped as all tickets have been released.");
                break;
            }
            logger.info("Vendor " + vendorId + " released Ticket ID: " + ticket.getTicketId());

            try {
                Thread.sleep(15000 / configuration.getTicketsReleaseRate());
            } catch (InterruptedException e) {
                logger.severe("Vendor " + vendorId + " interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

}