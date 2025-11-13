package healthcare_management_system;

import Config.config;
import java.util.Scanner;

public class Patient {
    Scanner sc = new Scanner(System.in);
    config con = new config();
  
     
    public void Patient() {
        while (true) {
            System.out.println("\n=== Welcome to Patient Dashboard ===");
            System.out.println("1. Book Appointment");
            System.out.println("2. View My Appointments");
            System.out.println("3. View My Services");
            System.out.println("0. Logout");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    addAppointment();
                    break;
                case 2:
                    viewAppointments();
                    break;
                case 3:
                    viewServices();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addAppointment() {
        System.out.println("\n--- Book Appointment ---");
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = sc.nextLine().trim();
        System.out.print("Enter Appointment Time (HH:MM): ");
        String time = sc.nextLine().trim();
       
        String status = "Pending";   
       
        con.addRecord(
            "INSERT INTO appointments (date, time, status) VALUES (?, ?, ?)",
            date, time, status
        );
        System.out.println(" Appointment successfully booked!");
    }

   private void viewAppointments() {
        System.out.println("\n--- My Appointments ---");
         System.out.print("Enter your User ID: ");
         int userId = sc.nextInt();
         sc.nextLine();
        String[] headers = {"ID", "Date", "Time", "Status"};
        String[] columns = {"appointment_id", "date", "time", "status"};

        con.viewRecords(
            "SELECT appointment_id, date, time, status FROM appointments WHERE user_id = " + userId,
            headers, columns
        );
    }

   
    private void viewServices() {
        System.out.println("\n--- My Services ---");
          System.out.print("Enter your User ID: ");
          int userId = sc.nextInt();
          sc.nextLine();
        String[] headers = {"ID", "Name", "Description", "Cost", "Amount", "Payment Date", "Status"};
        String[] columns = {"services_id", "name", "description", "cost", "amount", "payment_date", "status"};

        con.viewRecords(
            "SELECT services_id, name, description, cost, amount, payment_date, status FROM services WHERE user_id = " + userId,
            headers, columns
        );
    }
}
