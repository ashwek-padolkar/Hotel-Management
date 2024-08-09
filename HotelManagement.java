// Main Code (You make changes in Hm.cpp code and paste the changes in this code)

/*
    count :- keeps track of the number of rooms added by the hotel manager.
    ci_count :- keeps track of the number of customers checked-in.
    ci_status :- 0: indicates that the customer has either checked-out or not yet checked-in, 1: checked-in successfully.
    ci_flag :- in checkInRoom(), 0: room no. does not exists, 1: exists.
    search_flag :- in searchCustomer(), 0: person not found, 1: person found.
    co_flag :- in checkOutRoom(), 0: room no. does not exists OR it is not reserved, 1: checked-out successfully.
*/

package com.mycompany.javaprojecthm;

import java.util.Scanner;

public class HotelManagement {

    public static void main(String[] args) {
        int choice = 0;
        Room r1 = new Room();
        Scanner sc = new Scanner(System.in);

        while (choice != 7) {
            System.out.println("\n######## Hotel Management #########\n");
            System.out.println("1. Add Rooms");
            System.out.println("2. Check-In Room");
            System.out.println("3. Available Rooms");
            System.out.println("4. Search Customer");
            System.out.println("5. Check-Out Room");
            System.out.println("6. Guest Summary Report");
            System.out.println("7. Exit");
            System.out.print("\nEnter Option: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    r1.addRooms();
                    break;
                case 2:
                    r1.checkInRoom();
                    break;
                case 3:
                    r1.getAvailRoom();
                    break;
                case 4:
                    r1.searchCustomer();
                    break;
                case 5:
                    r1.checkOutRoom();
                    break;
                case 6:
                    r1.guestSummaryReport();
                    break;
                case 7:
                    System.out.println("\nTHANK YOU!");
                    break;
                default:
                    System.out.println("\nPlease Enter correct option");
                    break;
            }
        }
    }
}
