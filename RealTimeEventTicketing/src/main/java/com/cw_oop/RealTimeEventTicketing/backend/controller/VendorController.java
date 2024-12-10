package com.cw_oop.RealTimeEventTicketing.backend.controller;

import com.cw_oop.RealTimeEventTicketing.backend.entity.VendorInfo;
import com.cw_oop.RealTimeEventTicketing.backend.service.VendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping("/addVendor")
    public void setVendor(@RequestBody VendorInfo vendorInfo) {
        vendorInfo.setVendorId(++VendorService.vendorId);
        vendorService.saveToFile(vendorInfo);

    }
    @DeleteMapping("/clearVendors")
    public static void  clearVendors() {
        File file = new File("vendor.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try (FileWriter writer = new FileWriter(file)) {
            objectMapper.writeValue(writer, new Object[]{});
            System.out.println("deleted:All vendors");;
        } catch (IOException e) {
            System.out.println("deleteAll:failed: ");
        }
    }
}
