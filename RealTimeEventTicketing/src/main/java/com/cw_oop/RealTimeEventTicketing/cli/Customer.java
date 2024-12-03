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
        int i=1;
        while (true){
            if(TicketPool.ticketPool.isEmpty()){
                break;
            }
        if(i%configuration.getTicketsRetrievalRate()==0){
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
       // System.out.println("Ticket Id "+TicketPool.ticketPool.getFirst().getTicketId() + " removed from TicketPool");
        ticketPool.removeTicket(TicketPool.ticketPool.getFirst());
        i++;

}
    }
}
