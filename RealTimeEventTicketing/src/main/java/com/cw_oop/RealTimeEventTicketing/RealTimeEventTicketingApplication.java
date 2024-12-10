package com.cw_oop.RealTimeEventTicketing;

import com.cw_oop.RealTimeEventTicketing.cli.Configuration;
import com.cw_oop.RealTimeEventTicketing.cli.Customer;
import com.cw_oop.RealTimeEventTicketing.cli.TicketPool;
import com.cw_oop.RealTimeEventTicketing.cli.Vendor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@SpringBootApplication
public class RealTimeEventTicketingApplication {
    public static void main(String[] args) {
//        int totalTickets ;
//        int releaseRate ;
//        int retrievalRate ;
//        int maxCapacity ;
//        int numberOfVendors ;
//        int numberOfCustomers ;
//
//        while (true) {
//            Scanner scanner = new Scanner(System.in);
//            try {
//                System.out.println("Enter Total Number of Tickets:");
//                totalTickets = scanner.nextInt();
//
//                System.out.println("Enter Ticket Release Rate (tickets per minute):");
//                releaseRate = scanner.nextInt();
//
//                System.out.println("Enter Ticket Retrieval Rate (tickets per minute):");
//                retrievalRate = scanner.nextInt();
//                while(true) {
//                    System.out.println("Enter Max Capacity(It should be below or same amount as Total Number Of Tickets-" + totalTickets + "):");
//                    maxCapacity = scanner.nextInt();
//                    if(totalTickets>=maxCapacity) {
//                        break;
//                    }else {
//                        System.out.println("Max Capacity must be less than or equal to Total Number of Tickets."+"("+totalTickets+")"+ " Try again.");
//                    }
//                }
//                System.out.println("Enter Number of Vendors:");
//                numberOfVendors = scanner.nextInt();
//
//                System.out.println("Enter Number of Customers:");
//                numberOfCustomers = scanner.nextInt();
//            } catch (Exception e) {
//                System.out.println("Invalid Input.Enter again!");
//                continue;
//            }
//            break;
//        }
//
//        Configuration config = new Configuration(totalTickets, releaseRate, retrievalRate, maxCapacity);
//
//        TicketPool ticketPool = new TicketPool(totalTickets, maxCapacity);
//
//        List<Thread> vendorThreads = new ArrayList<>();
//        List<Thread> customerThreads = new ArrayList<>();
//
//        for (int i = 1; i <= numberOfVendors; i++) {
//            Vendor vendor = new Vendor(config, ticketPool);
//            vendor.setVendorId(i);
//            Thread thread = new Thread(vendor);
//            vendorThreads.add(thread);
//            thread.start();
//        }
//
//        for (int i = 1; i <= numberOfCustomers; i++) {
//            Customer customer = new Customer(config, ticketPool);
//            customer.setCustomerId(i);
//            Thread thread = new Thread(customer);
//            customerThreads.add(thread);
//            thread.start();
//        }
//
//        for (Thread thread : vendorThreads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                System.err.println("Vendor thread interrupted: " + e.getMessage());
//            }
//        }
//
//        for (Thread thread : customerThreads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                System.err.println("Customer thread interrupted: " + e.getMessage());
//            }
//        }
//
//        System.out.println("Simulation complete!");
        SpringApplication.run(RealTimeEventTicketingApplication.class, args);

    }
}