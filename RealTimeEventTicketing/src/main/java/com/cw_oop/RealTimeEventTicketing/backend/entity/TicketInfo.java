package com.cw_oop.RealTimeEventTicketing.backend.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketInfo {
    private Integer sessionId;
    private Integer totalSoldTicket;
    private Integer totalReleasedTicketCount;
    private Integer totalUnsoldTickets = totalSoldTicket-totalReleasedTicketCount;
}
