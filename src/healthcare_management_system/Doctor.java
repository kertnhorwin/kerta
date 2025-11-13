package healthcare_management_system;

import Config.config;
import static healthcare_management_system.Main.sc;
import java.util.Scanner;

public class Doctor {
    Scanner sc = new Scanner(System.in);
    config con = new config();

    public void Doctor() {
        while (true) {
            System.out.println("\n=== Doctor Dashboard ===");
            System.out.println("1. Manage Appointments");
            System.out.println("2. Manage Services");
            System.out.println("0. Logout");
            System.out.print("Enter choice: ");
            int doctorChoice = sc.nextInt();
            sc.nextLine();

            switch (doctorChoice) {
                case 1:
                    manageAppointments();
                    break;
                case 2:
                    manageServices();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    // ------------------- Appointments Section -------------------

    private void manageAppointments() {
        while (true) {
            System.out.println("\n--- Manage Appointments ---");
            System.out.println("1. Approve Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Delete Appointment");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    approveAppointment();
                    break;
                case 2:
                    viewAppointments();
                    break;
                case 3:
                    deleteAppointment();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void approveAppointment() {
        System.out.print("Enter Appointment ID to approve: ");
        int id = sc.nextInt();
        sc.nextLine();
        con.updateRecord(
            "UPDATE appointments SET status = 'Confirmed' WHERE appointment_id = ?",
            id
        );
        System.out.println("Appointment approved successfully!");
    }

    private void viewAppointments() {
        String[] headers = {"ID", "Date", "Time", "Status"};
        String[] columns = {"appointment_id", "date", "time", "status"};
        con.viewRecords("SELECT appointment_id, date, time, status FROM appointments", headers, columns);
    }

    private void deleteAppointment() {
        System.out.print("Enter Appointment ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        con.deleteRecord("DELETE FROM appointments WHERE appointment_id = ?", id);
        System.out.println("🗑 Appointment deleted successfully.");
    }

  

    private void manageServices() {
        while (true) {
            System.out.println("\n--- Manage Services ---");
            System.out.println("1. Add Services");
            System.out.println("2. Update Service");
            System.out.println("3. View all Services");
            System.out.println("4. Delete Service");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    
                    break;
                case 2:
                    updateService();
                    break;
                case 3:
                    viewServices();
                    break;
                case 4:
                    deleteService();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

     private  void addService() {
        System.out.print("Service Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Description: ");
        String description = sc.nextLine().trim();
        System.out.print("Cost: ");
        double cost = sc.nextDouble();
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        System.out.print("Payment Date (YYYY-MM-DD): ");
        String paymentDate = sc.nextLine().trim();
        
        String status = "Pending";

        con.addRecord(
            "INSERT INTO services (name, description, cost, amount, payment_date, status) VALUES (?, ?, ?, ?, ?, ?)",
            name, description, cost, amount, paymentDate, status
        );
    }
    private void viewServices() {
        String[] headers = {"ID", "Name", "Description", "Cost", "Amount", "Payment Date", "Status"};
        String[] columns = {"services_id", "name", "description", "cost", "amount", "payment_date", "status"};
        con.viewRecords("SELECT services_id, name, description, cost, amount, payment_date, status FROM services", headers, columns);
    }

    private void updateService() {
        System.out.print("Enter Service ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("New Status (e.g., Active/Inactive): ");
        String status = sc.nextLine();

        con.updateRecord(
            "UPDATE services SET status = ? WHERE services_id = ?",
            status, id
        );
        System.out.println("✅ Service updated successfully!");
    }

    private void deleteService() {
        System.out.print("Enter Service ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        con.deleteRecord("DELETE FROM services WHERE services_id = ?", id);
        System.out.println("🗑 Service deleted successfully.");
    }
}
