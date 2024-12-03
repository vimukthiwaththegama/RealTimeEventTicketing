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
        isRunning = true;
        try {
            TicketPool ticketPool = new TicketPool(configuration.getTotalNumberOfTickets(), configuration.getMaxTicketCapacity());
            int i=1;
            while (true) {
                if(i%configuration.getTicketsReleaseRate()==0){
                    try {
                        Thread.sleep(5000);
                    }catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Ticket ticket = new Ticket();
                ticket.setTicketId(i);
                if(!ticketPool.addTicket(ticket)){
                    isRunning = false;
                    return;
                }
                System.out.println("Vendor Number "+vendorId+" released Ticket Id: "+ticket.getTicketId()+" to the pool");
                i++;
            }
        }catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public boolean checkRunning() {
        return isRunning;
    }
}
