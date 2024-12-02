package com.cw_oop.RealTimeEventTicketing.cli;

public class Vendor implements Runnable {
    Configuration configuration;
    public Vendor(Configuration configuration) {
        this.configuration = configuration;
    }
    @Override
    public void run() {
        try {
            TicketPool ticketPool = new TicketPool(configuration.getTotalNumberOfTickets(), configuration.getMaxTicketCapacity());
            int i=0;
            while (true) {
                if(i%configuration.getTicketsReleaseRate()==0){
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Ticket ticket = new Ticket();
                ticket.setTicketId(ticket.getTicketId());
                ticketPool.addTicket(ticket);
                i++;
            }
        }catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
