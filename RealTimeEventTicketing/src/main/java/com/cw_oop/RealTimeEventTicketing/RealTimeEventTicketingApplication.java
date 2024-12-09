package com.cw_oop.RealTimeEventTicketing;

import com.cw_oop.RealTimeEventTicketing.cli.Configuration;
import com.cw_oop.RealTimeEventTicketing.cli.Customer;
import com.cw_oop.RealTimeEventTicketing.cli.TicketPool;
import com.cw_oop.RealTimeEventTicketing.cli.Vendor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class RealTimeEventTicketingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Total Number of Tickets:");
        int totalTickets = scanner.nextInt();

        System.out.println("Enter Ticket Release Rate (tickets per minute):");
        int releaseRate = scanner.nextInt();

        System.out.println("Enter Ticket Retrieval Rate (tickets per minute):");
        int retrievalRate = scanner.nextInt();

        System.out.println("Enter Max Capacity:");
        int maxCapacity = scanner.nextInt();

        System.out.println("Enter Number of Vendors:");
        int numberOfVendors = scanner.nextInt();

        System.out.println("Enter Number of Customers:");
        int numberOfCustomers = scanner.nextInt();

        Configuration config = new Configuration(totalTickets, releaseRate, retrievalRate, maxCapacity);

        TicketPool ticketPool = new TicketPool(totalTickets, maxCapacity);

        List<Thread> vendorThreads = new ArrayList<>();
        List<Thread> customerThreads = new ArrayList<>();

        // Create and start vendor threads
        for (int i = 1; i <= numberOfVendors; i++) {
            Vendor vendor = new Vendor(config, ticketPool);
            vendor.setVendorId(i);
            Thread thread = new Thread(vendor);
            vendorThreads.add(thread);
            thread.start();
        }

        // Create and start customer threads
        for (int i = 1; i <= numberOfCustomers; i++) {
            Customer customer = new Customer(config, ticketPool);
            customer.setCustomerId(i);
            Thread thread = new Thread(customer);
            customerThreads.add(thread);
            thread.start();
        }

        // Wait for vendor threads to complete
        for (Thread thread : vendorThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Vendor thread interrupted: " + e.getMessage());
            }
        }

        // Wait for customer threads to complete
        for (Thread thread : customerThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Customer thread interrupted: " + e.getMessage());
            }
        }

        System.out.println("Simulation complete!");
    }
}