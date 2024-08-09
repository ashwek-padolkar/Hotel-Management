package com.mycompany.javaprojecthm;

import java.util.Scanner;

public class Room {
    // Add Rooms
    private static final int MAX_ROOMS = 50;
    int[] room_no = new int[MAX_ROOMS];
    int[] room_rent = new int[MAX_ROOMS];
    String[] room_btype = new String[MAX_ROOMS];
    String[] room_stype = new String[MAX_ROOMS];
    String[] room_ac = new String[MAX_ROOMS];

    // Check-In Room
    int ci_room_no;
    int[] advance_payment = new int[MAX_ROOMS];
    int ci_count = 0;
    int[] ci_status = new int[50];
    String[] cust_name = new String[MAX_ROOMS];
    String[] cust_address = new String[MAX_ROOMS];
    String[] ci_date = new String[MAX_ROOMS];
    String[] co_date = new String[MAX_ROOMS];

    int count = 0;

    void addRooms(){
        if(count >= 50){
            System.out.println("Cannot add more rooms. Maximum limit reached.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int new_room_no;
        boolean room_exists;

        do{
            room_exists = false;
            System.out.print("Enter Room No.: ");
            new_room_no = sc.nextInt();

            for(int i = 0; i < count; i++){
                if(room_no[i] == new_room_no){
                    room_exists = true;
                    break;
                }
            }

            if(room_exists){
                System.out.println("Room number " + new_room_no + " already exists. Please enter a different room number.");
            }
            else{
                room_no[count] = new_room_no;

                System.out.print("\nType AC/Non-AC (A/N) : ");
                room_ac[count] = sc.next();
                System.out.print("\nType Beds Single/Double (S/D) : ");
                room_btype[count] = sc.next();
                System.out.print("\nType Size (B/S) : ");
                room_stype[count] = sc.next();
                System.out.print("\nDaily Rent : ");
                room_rent[count] = sc.nextInt();

                ci_status[count] = 0;

                count++;
                System.out.println("\nRoom Added Successfully!\n");
            }
        } while(room_exists);
    }

    void checkInRoom(){
        int ci_flag = 0;

        if(count == 0){ // if rooms are not added then
            System.out.println("Please add the rooms first.");
            return;
        }
        else{ // if rooms are added then
            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter Room number : ");
            ci_room_no = sc.nextInt();

            for(int i = 0; i < count; i++){
                if(ci_room_no == room_no[i]){ // if the room you want to check-in == room you have already added
                    if(ci_status[i] == 0){
                        System.out.print("\nEnter Customer Name (First Name): ");
                        cust_name[i] = sc.next();
                        System.out.print("\nEnter Address (only city): ");
                        cust_address[i] = sc.next();

                        String digits;
                        System.out.print("\nPlease enter your phone number :\n");
                        digits = sc.next();

                        while(true){ // Verify if phone number has 10 digits or not using exception handling
                            try{
                                if(digits.length() != 10){
                                    throw new Exception();
                                }
                                System.out.println("\n\t\t\tPhone number is valid ");
                                break;
                            } catch(Exception e){
                                System.out.println("\n\t\t\tPhone number is not valid. Try Again!!!");
                                System.out.print("\nPlease enter your phone number :\n");
                                digits = sc.next();
                            }
                        }

                        System.out.print("\nEnter Check-in Date: ");
                        ci_date[i] = sc.next();
                        System.out.print("\nEnter Check-out Date: ");
                        co_date[i] = sc.next();
                        System.out.print("\nEnter Advance Payment: ");
                        advance_payment[i] = sc.nextInt();
                        System.out.println("\n Customer Checked-in Successfully...\n");

                        ci_status[i] = 1;
                        ci_flag = 1;
                        ci_count++;
                    }
                    else{
                        System.out.println("Room Number " + ci_room_no + " is already occupied!");
                        return;
                    }
                }
            }
            if(ci_flag == 0){ // if check-in flag = 0 then the room you want to check-in is not present in the added rooms
                System.out.println("Room Number " + ci_room_no + " does not exists! Please add the room first.");
                return;
            }
        }
    }

    void getAvailRoom(){
        if(count == 0){ // if rooms are not added then
            System.out.println("Please add the rooms first.");
            return;
        }
        else if(count == ci_count) { // if the number of rooms added == number of customers checked in (means all rooms are reserved)
            System.out.println("All rooms are reserved! Please add more rooms.");
            return;
        }
        else{
            for(int i = 0; i < count; i++){
                if(ci_status[i] == 0){ // if the rooms are added but have not yet checked-in or when the customers have checked-out after checking in (means the room is available)
                    System.out.println("\n\nRoom No.: " + room_no[i]);
                    System.out.println("Type AC/Non-AC (A/N) : " + room_ac[i]);
                    System.out.println("Type Beds Single/Double (S/D) : " + room_btype[i]);
                    System.out.println("Type Size (B/S) : " + room_stype[i]);
                    System.out.println("Daily Rent : " + room_rent[i]);
                }
            }
        }
    }

    void searchCustomer(){
        int search_flag = 0;
        String cname;

        if(count == 0){ // if rooms are not added then
            System.out.println("Please add the rooms first.");
            return;
        }
        else if(ci_count == 0){ // if rooms are added but the customers have not yet checked-in
            System.out.println("\n No Guests in Hotel !!");
            return;
        }
        else{
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Customer Name: ");
            cname = sc.next();

            for(int i = 0; i < count; i++){
                if(cname.equals(cust_name[i])){
                    System.out.println("\nCustomer Name: " + cust_name[i]);
                    System.out.println("Room Number: " + room_no[i]);
                    search_flag = 1;
                }
            }
            if(search_flag == 0){ // if the person you are searching for is not present in our hotel
                System.out.println("\nPerson not found.\n");
                return;
            }
        }
    }

    void checkOutRoom(){
        int billAmount = 0;
        int co_flag = 0;
        int co_room_no;

        if(count == 0){ // if rooms are not added then
            System.out.println("Please add the rooms first.");
            return;
        }
        if(ci_count == 0){
            System.out.println("\n No Guest in Hotel !!");
            return;
        }
        else{
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Room Number : ");
            co_room_no = sc.nextInt();

            for(int i = 0; i < count; i++){
                if((co_room_no == room_no[i]) && (ci_status[i] == 1)){
                    System.out.println("\n\t######## CheckOut Details ########\n");
                    System.out.println("Customer Name : " + cust_name[i]);
                    System.out.println("Room Number : " + room_no[i]);
                    System.out.println("Address : " + cust_address[i]);
                    System.out.println("Total Amount Due : " + room_rent[i] + "/-");
                    System.out.println("Advance Paid: " + advance_payment[i] + "/-");
                    System.out.println("*** Total Payable: " + (room_rent[i] - advance_payment[i]) + "/- only");

                    ci_status[i] = 0; // sets check-in status = 0 to make sure that after customer has checked-out, the check-in status of that room remains same as before checking in
                    co_flag = 1;
                    ci_count--;
                }
            }

            if(co_flag == 0){
                System.out.println("Room Number " + co_room_no + " does not exists OR it is not reserved!");
                return;
            }
        }
    }

    void guestSummaryReport(){
        if(count == 0){ // if rooms are not added then
            System.out.println("Please add the rooms first.");
            return;
        }
        else{
            if(ci_count == 0){ // if rooms are added but the customers have not yet checked-in
                System.out.println("\n No Guest in Hotel !!");
                return;
            }
            else{
                for(int i = 0; i < ci_count; i++){
                    if(ci_status[i] == 1){ // if customers have checked-in
                        System.out.println("\n Customer Name : " + cust_name[i]);
                        System.out.println(" Room Number : " + room_no[i]);
                        System.out.println(" Address (only city) : " + cust_address[i]);
                        System.out.println("---------------------------------------");
                    }
                }
            }
        }
    }
}