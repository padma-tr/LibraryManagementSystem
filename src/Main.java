import services.AttendanceManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AttendanceManager manager = new AttendanceManager();
        
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║  STUDENT ATTENDANCE & GRADE MANAGEMENT SYSTEM     ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        
        while (true) {
            displayMenu();
            System.out.print("\nEnter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        manager.addStudent();
                        break;
                    case 2:
                        manager.addSubject();
                        break;
                    case 3:
                        manager.markAttendance();
                        break;
                    case 4:
                        manager.addGrade();
                        break;
                    case 5:
                        manager.viewAttendanceReport();
                        break;
                    case 6:
                        manager.viewGradeReport();
                        break;
                    case 7:
                        manager.viewAllStudents();
                        break;
                    case 8:
                        manager.viewAllSubjects();
                        break;
                    case 0:
                        System.out.println("\n✓ Thank you for using Attendance Management System!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("✗ Invalid choice! Please try again.");
                }
                
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            } catch (Exception e) {
                System.out.println("✗ An error occurred: " + e.getMessage());
            }
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║               MAIN MENU                           ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  1. Register New Student                          ║");
        System.out.println("║  2. Add New Subject                               ║");
        System.out.println("║  3. Mark Attendance                               ║");
        System.out.println("║  4. Add Grade                                     ║");
        System.out.println("║  5. View Attendance Report                        ║");
        System.out.println("║  6. View Grade Report & SGPA                      ║");
        System.out.println("║  7. View All Students                             ║");
        System.out.println("║  8. View All Subjects                             ║");
        System.out.println("║  0. Exit                                          ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}
