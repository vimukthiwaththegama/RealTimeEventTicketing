package com.cw_oop.RealTimeEventTicketing.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VendorInfo {
    private Integer vendorId;
    private Integer  totalTicketsSell;
}
