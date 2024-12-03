package com.cw_oop.RealTimeEventTicketing.cli;

public class Ticket {
    private static Integer ticketId = 0;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        Ticket.ticketId = ticketId;
    }
}
