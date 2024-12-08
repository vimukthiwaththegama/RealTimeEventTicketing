package com.cw_oop.RealTimeEventTicketing.cli;

public class Ticket {
    private static Integer ticketId ;

    public String getTicketId() {
        return "T" + ticketId;
    }

    public void setTicketId(Integer ticketId) {
        Ticket.ticketId = ticketId;
    }
}
