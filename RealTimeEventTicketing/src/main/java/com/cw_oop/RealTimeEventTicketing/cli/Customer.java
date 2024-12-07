package com.cw_oop.RealTimeEventTicketing.cli;

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
    @Override
    public void run() {
        TicketPool ticketPool = new TicketPool(configuration.getTotalNumberOfTickets(), configuration.getMaxTicketCapacity());
        while (true) {
            if (TicketPool.ticketPool.isEmpty()) {
                break;
            }
            if (ticketPool.ticketPool.size() % configuration.getTicketsRetrievalRate() == 0) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Ticket ticket = TicketPool.ticketPool.get(0); // Get the first ticket
            ticketPool.removeTicket(ticket);
            System.out.println("Customer " + customerId + " retrieved Ticket ID: " + ticket.getTicketId());
        }
    }


}
