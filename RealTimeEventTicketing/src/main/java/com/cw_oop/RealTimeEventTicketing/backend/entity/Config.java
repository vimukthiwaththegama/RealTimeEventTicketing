package com.cw_oop.RealTimeEventTicketing.backend.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Config {
    @JsonProperty("session_id")
    private Integer sessionId;
    private Integer totalTickets;
    private Integer ticketReleaseRate;
    private Integer ticketsRetrievalRate;
    private Integer maxPoolCapacity;
}
