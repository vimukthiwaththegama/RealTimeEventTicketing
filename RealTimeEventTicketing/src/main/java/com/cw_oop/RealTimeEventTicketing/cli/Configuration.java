package com.cw_oop.RealTimeEventTicketing.cli;

public class Configuration {
    private final Integer totalNumberOfTickets;
    private final Integer ticketsReleaseRate;
    private final Integer ticketsRetrievalRate;
    private final Integer maxTicketCapacity;

    public Integer getTotalNumberOfTickets() {
        return totalNumberOfTickets;
    }

    public Integer getTicketsReleaseRate() {
        return ticketsReleaseRate;
    }

    public Integer getTicketsRetrievalRate() {
        return ticketsRetrievalRate;
    }

    public Integer getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public Configuration(Integer totalNumberOfTickets, Integer ticketsReleaseRate, Integer ticketsRetrievalRate, Integer maxTicketCapacity) {
        this.totalNumberOfTickets = totalNumberOfTickets;
        this.ticketsReleaseRate = ticketsReleaseRate;
        this.ticketsRetrievalRate = ticketsRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;


    }
}
