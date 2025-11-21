package healthcare_management_system;

import Config.config;
import java.util.Scanner;

public class Admin {

    Scanner sc = new Scanner(System.in);
    config con = new config();

    // Method to view all users
    public void viewUsers() {
        String[] headers = {"User ID", "Name", "Email", "Phone", "Reg Date", "Role"};
        String[] columns = {"user_id", "name", "email", "phone", "date", "type"};
        con.viewRecords("SELECT user_id, name, email, phone, date, type FROM users", headers, columns);
    }

    // Method to view all appointments
    public void viewAppointments() {
        String[] headers = {"ID", "User ID", "Date", "Time", "Status"};
        String[] columns = {"appointment_id", "user_id", "date", "time", "status"};
        con.viewRecords("SELECT appointment_id, user_id, date, time, status FROM appointments", headers, columns);
    }

    // Method to view all services
    public void viewServices() {
        String[] headers = {"ID", "User ID", "Appointment ID", "Name", "Description", "Cost", "Amount", "Payment Date", "Status"};
        String[] columns = {"services_id", "user_id", "appointment_id", "name", "description", "cost", "amount", "payment_date", "status"};
        con.viewRecords("SELECT services_id, user_id, appointment_id, name, description, cost, amount, payment_date, status FROM services", headers, columns);
    }

    // Main Admin Dashboard Method
    public void Admin() {
        while (true) {
            System.out.println("\n=== Welcome to Admin Dashboard ===");
            System.out.println("1. Approve Account");
            System.out.println("2. Manage Account");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Manage Services");
            System.out.println("0. Logout");
            System.out.print("Enter Choice: ");
            int respo = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (respo) {
                case 1: 
                    // Approve User Account
                    viewUsers();
                    System.out.print("Enter ID to approve: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    String userSql = "UPDATE users SET status = ? WHERE user_id = ?";
                    con.updateRecord(userSql, "Approved", userId);
                    System.out.println("✅ Account Approved.");
                    break;

                case 2: 
                    // Manage User Account (View/Delete)
                    System.out.println("1. View Account");
                    System.out.println("2. Delete Account");
                    System.out.print("Enter choice: ");
                    int manageChoice = sc.nextInt();
                    sc.nextLine();
                    switch (manageChoice) {
                        case 1:
                            viewUsers();
                            break;
                        case 2:
                            System.out.print("User ID to delete: ");
                            int deleteUserId = sc.nextInt();
                            sc.nextLine();
                            con.deleteRecord("DELETE FROM users WHERE user_id = ?", deleteUserId);
                            System.out.println("✅ User deleted.");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 3: 
                    // Manage Appointments (View/Delete)
                    System.out.println("1. View Appointments");
                    System.out.println("2. Delete Appointment");
                    System.out.print("Enter choice: ");
                    int appChoice = sc.nextInt();
                    sc.nextLine();
                    switch (appChoice) {
                        case 1:
                            viewAppointments();
                            break;
                        case 2:
                            System.out.print("Appointment ID to delete: ");
                            int appointmentId = sc.nextInt();
                            sc.nextLine();
                            con.deleteRecord("DELETE FROM appointments WHERE appointment_id = ?", appointmentId);
                            System.out.println("✅ Appointment deleted.");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 4: 
                    // Manage Services (Approve/View/Delete)
                    System.out.println("1. Approve Service");
                    System.out.println("2. View Services");
                    System.out.println("3. Delete Service");
                    System.out.print("Enter choice: ");
                    int serviceChoice = sc.nextInt();
                    sc.nextLine();
                    switch (serviceChoice) {
                        case 1:
                            System.out.print("Enter Service ID to approve: ");
                            int serviceId = sc.nextInt();
                            sc.nextLine();
                            String serviceSql = "UPDATE services SET status = ? WHERE services_id = ?";
                            con.updateRecord(serviceSql, "Approved", serviceId);
                            System.out.println("✅ Service Approved.");
                            break;
                        case 2:
                            viewServices();
                            break;
                        case 3:
                            System.out.print("Service ID to delete: ");
                            int serviceToDelete = sc.nextInt();
                            sc.nextLine();
                            con.deleteRecord("DELETE FROM services WHERE services_id = ?", serviceToDelete);
                            System.out.println("✅ Service deleted.");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 0: 
                    // Logout
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
