package com.cw_oop.RealTimeEventTicketing.cli;

public class Vendor implements Runnable {
    Configuration configuration;
    private Integer vendorId;

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Vendor(Configuration configuration) {
        this.configuration = configuration;
    }

    private boolean isRunning;

    @Override
    public void run() {
        TicketPool ticketPool = new TicketPool(configuration.getTotalNumberOfTickets(), configuration.getMaxTicketCapacity());
        int i = 1;
        while (true) {
            if (i % configuration.getTicketsReleaseRate() == 0) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Ticket ticket = new Ticket();
            ticket.setTicketId(i);
            if (!ticketPool.addTicket(ticket)) {
                return; // Stop if the pool is full
            }
            System.out.println("Vendor " + vendorId + " released Ticket ID: " + ticket.getTicketId());
            i++;
        }
    }

}
