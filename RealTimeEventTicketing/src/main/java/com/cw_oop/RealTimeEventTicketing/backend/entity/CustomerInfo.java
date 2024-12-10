package com.cw_oop.RealTimeEventTicketing.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerInfo {
    private Integer customerId;
    private Integer totalTicketsBuy;

}
