package healthcare_management_system;

import Config.config;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static config con = new config();

    public static void main(String[] args) {
        System.out.println("--- Healthcare Management Schedule System ---");
        String choiceContinue;

        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    Register();
                    break;
                case 2:
                    Login();
                    break;
                case 0:
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.print("\nDo you want to continue? (yes/no): ");
            choiceContinue = sc.nextLine().trim().toLowerCase();

        } while (choiceContinue.equals("yes"));

        System.out.println("Thank you for using the Healthcare Management System!");
    }

 
    private static void Register() {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        
        while (true) {
            String qry = "SELECT * FROM users WHERE email = ?";
            java.util.List<java.util.Map<String, Object>> result = con.fetchRecords(qry, email);
            if (result.isEmpty()) {
                break;
            } else {
                System.out.print("Email already exists, enter another email: ");
                email = sc.nextLine().trim();
            }
        }

        System.out.print("Phone: ");
        String phone = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine().trim();

        System.out.print("Registration Date (YYYY-MM-DD): ");
        String date = sc.nextLine().trim();

        System.out.print("Role (1 - Patient / 2 - Doctor / 3 - Admin): ");
        int role = sc.nextInt();
        sc.nextLine(); 

        String type;
        if (role == 1) type = "Patient";
        else if (role == 2) type = "Doctor";
        else type = "Admin";

        con.addRecord(
            "INSERT INTO users (name, email, phone, password, date, type, status) VALUES (?, ?, ?, ?, ?, ?, ?)",
            name, email, phone, password, date, type, "Pending"
        );

        System.out.println("✅ Registration successful! Waiting for admin approval.");
    }


    private static void Login() {
        System.out.println("=== Login User ===");
        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = sc.nextLine().trim();

        String qry = "SELECT * FROM users WHERE email = ? AND password = ?";
        java.util.List<java.util.Map<String, Object>> result = con.fetchRecords(qry, email, password);

        if (result.isEmpty()) {
            System.out.println("❌ Invalid credentials.");
            return;
        }

        java.util.Map<String, Object> user = result.get(0);
        String status = user.get("status").toString();
        String type = user.get("type").toString();

        if (status.equalsIgnoreCase("Pending")) {
            System.out.println(" Account is pending. Wait for admin approval.");
            return;
        }

        System.out.println(" Login successful!");

        if (type.equalsIgnoreCase("Admin")) {
            Admin admin = new Admin();
            admin.Admin();
        } else if (type.equalsIgnoreCase("Doctor")) {
            Doctor doctor = new Doctor();
            doctor.Doctor();
        } else if (type.equalsIgnoreCase("Patient")) {
            Patient patient = new Patient();
            patient.Patient();
        } else {
            System.out.println("Access denied.");
        }
    }
}
