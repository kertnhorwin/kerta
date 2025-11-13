package healthcare_management_system;

import Config.config;
import java.util.Scanner;

public class Admin {

    Scanner sc = new Scanner(System.in);
    config con = new config();

    public void viewUsers() {
        String[] headers = {"User ID", "Name", "Email", "Phone", "Reg Date", "Role"};
        String[] columns = {"user_id", "name", "email", "phone", "date", "Type"};
        con.viewRecords("SELECT user_id, name, email, phone, date, Type FROM users", headers, columns);
    }

    public void viewAppointments() {
        String[] headers = {"ID", "User ID", "Date", "Time", "Status"};
        String[] columns = {"appointment_id", "user_id", "date", "time", "status"};
        con.viewRecords("SELECT appointment_id, user_id, date, time, status FROM appointments", headers, columns);
    }


    public void viewServices() {
        String[] headers = {"ID", "User ID", "Appointment ID", "Name", "Description", "Cost", "Amount", "Payment Date", "Status"};
        String[] columns = {"services_id", "user_id", "appoinment_id", "name", "description", "cost", "amount", "payment_date", "status"};
        con.viewRecords("SELECT services_id, user_id, appointment_id, name, description, cost, amount, payment_date, status FROM services", headers, columns);
    }


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
            sc.nextLine(); 

            switch (respo) {
                case 1: 
                    viewUsers();
                    System.out.print("Enter ID to approve: ");
                    int ids = sc.nextInt();
                    sc.nextLine();
                    String sql = "UPDATE users SET u_status = ? WHERE user_id = ?";
                    con.updateRecord(sql, "Approved", ids);
                    System.out.println("✅ Account Approved.");
                    break;

                case 2: 
                    System.out.println("1. View Account");
                    System.out.println("2. Delete Account");
                    System.out.print("Enter choice: ");
                    int ads_choice = sc.nextInt();
                    sc.nextLine();
                    switch (ads_choice) {
                        case 1:
                            viewUsers();
                            break;
                        case 2:
                            System.out.print("User ID to delete: ");
                            int dl_id = sc.nextInt();
                            sc.nextLine();
                            con.deleteRecord("DELETE FROM users WHERE user_id = ?", dl_id);
                            System.out.println("✅ User deleted.");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 3: 
                    System.out.println("1. View Appointments");
                    System.out.println("2. Delete Appointment");
                    System.out.print("Enter choice: ");
                    int app_choice = sc.nextInt();
                    sc.nextLine();
                    switch (app_choice) {
                        case 1:
                            viewAppointments();
                            break;
                        case 2:
                            System.out.print("Appointment ID to delete: ");
                            int app_id = sc.nextInt();
                            sc.nextLine();
                            con.deleteRecord("DELETE FROM appointments WHERE appointment_id = ?", app_id);
                            System.out.println("✅ Appointment deleted.");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 4: 
                    System.out.println("1. Approve Services");
                    System.out.println("2. View Services");
                    System.out.println("3. Delete Service");
                    System.out.print("Enter choice: ");
                    int srv_choice = sc.nextInt();
                    sc.nextLine();
                    switch (srv_choice) {
                        case 1:
                    System.out.print("Enter ID to approve: ");
                    int service_id = sc.nextInt();
                    sc.nextLine();
                    String service_qry = "UPDATE services SET u_status = ? WHERE services_id = ?";
                    con.updateRecord(service_qry, "Approved", service_id);
                    System.out.println("Service Approved Approved.");
                            break;
                        case 2:
                            viewServices();
                            break;
                        case 3:
                            System.out.print("Service ID to delete: ");
                            int srv_id = sc.nextInt();
                            sc.nextLine();
                            con.deleteRecord("DELETE FROM services WHERE services_id = ?", srv_id);
                            System.out.println("✅ Service deleted.");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 0: 
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
