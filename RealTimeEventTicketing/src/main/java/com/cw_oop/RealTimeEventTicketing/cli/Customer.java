package com.cw_oop.RealTimeEventTicketing.cli;

public class Customer implements Runnable{
    Configuration configuration;

    public Customer(Configuration configuration){
        this.configuration = configuration;
    }
    @Override
    public void run() {
        TicketPool ticketPool = new TicketPool(configuration.getTotalNumberOfTickets(), configuration.getMaxTicketCapacity());
        int i=0;
        while (true){
        if(i%configuration.getTicketsRetrievalRate()==0){
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }
        }
        ticketPool.removeTicket(TicketPool.ticketPool.removeFirst());
        i++;
}
    }
}
