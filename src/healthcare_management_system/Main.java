package healthcare_management_system;

import Config.config;
import java.util.InputMismatchException;
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
            int choice = -1;
            
            // Add input validation for main menu choice
            while (true) {
                try {
                    choice = sc.nextInt();
                    sc.nextLine();  // Consume newline
                    if (choice == 1 || choice == 2 || choice == 0) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter 1, 2, or 0.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.nextLine();  // Consume the invalid input
                }
            }

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
                    // This case won't occur now since we've validated input
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

        // Check for existing email in the database
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

        // Add input validation for role selection
        int role = 0;
        while (role < 1 || role > 3) {
            try {
                System.out.print("Role (1 - Patient / 2 - Doctor / 3 - Admin): ");
                role = sc.nextInt();
                sc.nextLine();  // Consume newline
                if (role < 1 || role > 3) {
                    System.out.println("Invalid role! Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();  // Consume invalid input
            }
        }

        String type;
        switch (role) {
            case 1:
                type = "Patient";
                break;
            case 2:
                type = "Doctor";
                break;
            default:
                type = "Admin";
                break;
        }

        // Insert the new user into the database
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

    // Query to check for user credentials
    String qry = "SELECT * FROM users WHERE email = ? AND password = ?";
    java.util.List<java.util.Map<String, Object>> result = con.fetchRecords(qry, email, password);

    if (result.isEmpty()) {
        System.out.println("❌ Invalid credentials.");
        return;
    }

    java.util.Map<String, Object> user = result.get(0);
    String status = user.get("status").toString();
    String type = user.get("type").toString();

    // If the user is an admin, skip the pending status check
    if (type.equalsIgnoreCase("Admin")) {
        // Admin login is successful and skips pending check
        System.out.println("✅ Admin login successful!");
        Admin admin = new Admin();
        admin.Admin();
        return;
    }

    // For non-admin users, check if their account is pending
    if (status.equalsIgnoreCase("Pending")) {
        System.out.println(" Account is pending. Wait for admin approval.");
        return;
    }

    // If the user is approved (not pending), login successful
    System.out.println("✅ Login successful!");

    // Proceed to respective user dashboards based on the role
    if (type.equalsIgnoreCase("Doctor")) {
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