package com.cw_oop.RealTimeEventTicketing;

import com.cw_oop.RealTimeEventTicketing.cli.Configuration;
import com.cw_oop.RealTimeEventTicketing.cli.Vendor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RealTimeEventTicketingApplication {

	public static void main(String[] args) {
		int totalTickets;
		int releaseRate;
		int retrievalRate;
		int maxCapacity;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Total Number of Tickets:");
		totalTickets = scanner.nextInt();

		System.out.println("Enter Ticket Release Rate:");
		releaseRate = scanner.nextInt();

		System.out.println("Enter Ticket Retrieval Rate:");
		retrievalRate = scanner.nextInt();

		System.out.println("Enter Max Capacity:");
		maxCapacity = scanner.nextInt();

		Configuration config = new Configuration(totalTickets, releaseRate, retrievalRate, maxCapacity);

		Scanner scanner1 = new Scanner(System.in);
		System.out.println("Enter 'start' to start the tickets selling:");
			String command = scanner1.nextLine();
			if (command.equals("start")) {
				System.out.println("Ticket selling started. Enter 'stop' to stop selling tickets:");
				while (true) {
					Vendor vendor = new Vendor(config);
					vendor.run();
					command = scanner.nextLine();
					if (command.equals("stop")) {
						System.out.println("Ticket selling stopped.");
						break;
					} else {
						System.out.println("Enter stop to stop selling tickets:");
					}
				}
			} else {
				System.out.println("Invalid command. Enter 'start' to begin:");
			}

		scanner.close();
		SpringApplication.run(RealTimeEventTicketingApplication.class, args);
	}

}
