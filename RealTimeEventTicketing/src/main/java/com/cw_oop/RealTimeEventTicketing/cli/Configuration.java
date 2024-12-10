package com.cw_oop.RealTimeEventTicketing.cli;

public class Configuration {
    private final int totalNumberOfTickets;
    private final int ticketsReleaseRate;
    private final int ticketsRetrievalRate;
    private final int maxTicketCapacity;

    public int getTotalNumberOfTickets() {
        return totalNumberOfTickets;
    }

    public int getTicketsReleaseRate() {
        return ticketsReleaseRate;
    }

    public int getTicketsRetrievalRate() {
        return ticketsRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public Configuration(int totalNumberOfTickets, int ticketsReleaseRate, int ticketsRetrievalRate, int maxTicketCapacity) {
        this.totalNumberOfTickets = totalNumberOfTickets;
        this.ticketsReleaseRate = ticketsReleaseRate;
        this.ticketsRetrievalRate = ticketsRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;


    }
}
