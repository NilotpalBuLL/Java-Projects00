import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";  // Wamp default user
    private static final String PASSWORD = "";  // Wamp default has empty password unless you set one

    private Connection conn;

    public EmployeeApp() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to Database!");
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed! Check if MySQL is running and DB exists.");
            e.printStackTrace();
        }
    }

    // Add Employee
    public void addEmployee(String name, String position, double salary) {
        String sql = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.executeUpdate();
            System.out.println("✅ Employee Added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Employees
    public void viewEmployees() {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n📋 Employee List:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name") +
                                   " | " + rs.getString("position") + " | " + rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Employee
    public void updateEmployee(int id, String position, double salary) {
        String sql = "UPDATE employees SET position = ?, salary = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, position);
            stmt.setDouble(2, salary);
            stmt.setInt(3, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee Updated!");
            } else {
                System.out.println("⚠ No employee found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Employee
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee Deleted!");
            } else {
                System.out.println("⚠ No employee found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menu
    public void runApp() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Employee\n2. View Employees\n3. Update Employee\n4. Delete Employee\n5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline after number input

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Position: ");
                    String pos = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double sal = sc.nextDouble();
                    sc.nextLine(); // consume leftover newline
                    addEmployee(name, pos, sal);
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Position: ");
                    String newPos = sc.nextLine();
                    System.out.print("Enter New Salary: ");
                    double newSal = sc.nextDouble();
                    sc.nextLine();
                    updateEmployee(id, newPos, newSal);
                    break;

                case 4:
                    System.out.print("Enter Employee ID to Delete: ");
                    int delId = sc.nextInt();
                    sc.nextLine();
                    deleteEmployee(delId);
                    break;

                case 5:
                    System.out.println("👋 Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("⚠ Invalid choice, try again.");
            }
        }
    }

    public static void main(String[] args) {
        EmployeeApp app = new EmployeeApp();
        app.runApp();
    }
}