package services;

import models.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AttendanceManager {
    private ArrayList<Student> students;
    private ArrayList<Subject> subjects;
    private Scanner scanner;
    
    public AttendanceManager() {
        this.students = FileHandler.loadStudents();
        this.subjects = FileHandler.loadSubjects();
        this.scanner = new Scanner(System.in);
        
        // Load attendance records for each student
        loadAttendanceRecords();
        // Load grades for each student
        loadGradeRecords();
    }
    
    private void loadAttendanceRecords() {
        ArrayList<AttendanceRecord> records = FileHandler.loadAttendance();
        for (AttendanceRecord record : records) {
            Student student = findStudentById(record.getStudentId());
            if (student != null) {
                student.addAttendance(record.getSubjectCode(), record);
            }
        }
    }
    
    private void loadGradeRecords() {
        ArrayList<Grade> grades = FileHandler.loadGrades();
        for (Grade grade : grades) {
            Student student = findStudentById(grade.getStudentId());
            if (student != null) {
                student.addGrade(grade.getSubjectCode(), grade);
            }
        }
    }
    
    // Add new student
    public void addStudent() {
        System.out.println("\n=== Add New Student ===");
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        if (findStudentById(studentId) != null) {
            System.out.println("Error: Student with this ID already exists!");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();
        
        System.out.print("Enter Branch (e.g., CSE, ECE): ");
        String branch = scanner.nextLine();
        
        System.out.print("Enter Semester: ");
        int semester = Integer.parseInt(scanner.nextLine());
        
        Student student = new Student(studentId, name, rollNumber, branch, semester);
        students.add(student);
        
        FileHandler.saveStudents(students);
        
        System.out.println("\n✓ Student added successfully!");
        student.displayInfo();
    }
    
    // Add new subject
    public void addSubject() {
        System.out.println("\n=== Add New Subject ===");
        
        System.out.print("Enter Subject Code: ");
        String code = scanner.nextLine();
        
        if (findSubjectByCode(code) != null) {
            System.out.println("Error: Subject with this code already exists!");
            return;
        }
        
        System.out.print("Enter Subject Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Credits: ");
        int credits = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Faculty Name: ");
        String faculty = scanner.nextLine();
        
        Subject subject = new Subject(code, name, credits, faculty);
        subjects.add(subject);
        
        FileHandler.saveSubjects(subjects);
        
        System.out.println("\n✓ Subject added successfully!");
        subject.displayInfo();
    }
    
    // Mark attendance
    public void markAttendance() {
        System.out.println("\n=== Mark Attendance ===");
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        System.out.print("Enter Subject Code: ");
        String subjectCode = scanner.nextLine();
        
        Subject subject = findSubjectByCode(subjectCode);
        if (subject == null) {
            System.out.println("Error: Subject not found!");
            return;
        }
        
        System.out.print("Enter Date (YYYY-MM-DD) or press Enter for today: ");
        String dateStr = scanner.nextLine();
        LocalDate date = dateStr.isEmpty() ? LocalDate.now() : LocalDate.parse(dateStr);
        
        System.out.print("Present? (yes/no): ");
        String presentStr = scanner.nextLine().toLowerCase();
        boolean present = presentStr.equals("yes") || presentStr.equals("y");
        
        AttendanceRecord record = new AttendanceRecord(studentId, subjectCode, date, present);
        student.addAttendance(subjectCode, record);
        
        FileHandler.saveAllAttendance(students);
        
        System.out.println("\n✓ Attendance marked successfully!");
        System.out.println("Date: " + date);
        System.out.println("Subject: " + subject.getSubjectName());
        System.out.println("Status: " + (present ? "Present ✓" : "Absent ✗"));
        System.out.println("\nCurrent Attendance for " + subject.getSubjectName() + ": " + 
                          String.format("%.2f", student.getAttendancePercentage(subjectCode)) + "%");
    }
    
    // Add grade
    public void addGrade() {
        System.out.println("\n=== Add Grade ===");
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        System.out.print("Enter Subject Code: ");
        String subjectCode = scanner.nextLine();
        
        Subject subject = findSubjectByCode(subjectCode);
        if (subject == null) {
            System.out.println("Error: Subject not found!");
            return;
        }
        
        System.out.print("Assessment Type (Quiz/Assignment/Midterm/Final): ");
        String assessmentType = scanner.nextLine();
        
        System.out.print("Maximum Marks: ");
        double maxMarks = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Obtained Marks: ");
        double obtainedMarks = Double.parseDouble(scanner.nextLine());
        
        if (obtainedMarks > maxMarks) {
            System.out.println("Error: Obtained marks cannot exceed maximum marks!");
            return;
        }
        
        Grade grade = new Grade(studentId, subjectCode, assessmentType, maxMarks, obtainedMarks);
        student.addGrade(subjectCode, grade);
        
        FileHandler.saveAllGrades(students);
        
        System.out.println("\n✓ Grade added successfully!");
        System.out.println("Subject: " + subject.getSubjectName());
        grade.displayInfo();
    }
    
    // View student attendance report
    public void viewAttendanceReport() {
        System.out.println("\n=== Attendance Report ===");
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        student.displayInfo();
        
        System.out.println("\n--- Subject-wise Attendance ---");
        for (String subjectCode : student.getAttendanceMap().keySet()) {
            Subject subject = findSubjectByCode(subjectCode);
            String subjectName = subject != null ? subject.getSubjectName() : subjectCode;
            
            double percentage = student.getAttendancePercentage(subjectCode);
            int totalClasses = student.getAttendanceMap().get(subjectCode).size();
            int attended = (int) Math.round((percentage / 100.0) * totalClasses);
            
            System.out.println("\n" + subjectName + " (" + subjectCode + "):");
            System.out.println("  Classes Attended: " + attended + "/" + totalClasses);
            System.out.println("  Percentage: " + String.format("%.2f", percentage) + "%");
            
            if (percentage < 75.0) {
                System.out.println("  ⚠️  WARNING: Below 75% - Need to improve!");
                int classesNeeded = (int) Math.ceil((75.0 * totalClasses - attended * 100) / 25.0);
                System.out.println("  Need to attend " + classesNeeded + " more classes continuously to reach 75%");
            } else {
                System.out.println("  ✓ Good Attendance");
            }
        }
        
        // Check low attendance subjects
        ArrayList<String> lowSubjects = student.getLowAttendanceSubjects();
        if (!lowSubjects.isEmpty()) {
            System.out.println("\n⚠️  ALERT: Low Attendance Subjects:");
            for (String code : lowSubjects) {
                Subject subject = findSubjectByCode(code);
                System.out.println("  - " + (subject != null ? subject.getSubjectName() : code) + 
                                  " (" + String.format("%.2f", student.getAttendancePercentage(code)) + "%)");
            }
        }
    }
    
    // View grade report
    public void viewGradeReport() {
        System.out.println("\n=== Grade Report ===");
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        student.displayInfo();
        
        System.out.println("\n--- Subject-wise Grades ---");
        double totalWeightedScore = 0.0;
        int totalCredits = 0;
        
        for (String subjectCode : student.getGradesMap().keySet()) {
            Subject subject = findSubjectByCode(subjectCode);
            String subjectName = subject != null ? subject.getSubjectName() : subjectCode;
            int credits = subject != null ? subject.getCredits() : 0;
            
            System.out.println("\n" + subjectName + " (" + subjectCode + ") - " + credits + " credits:");
            
            ArrayList<Grade> grades = student.getGradesMap().get(subjectCode);
            double totalMarks = 0.0;
            double obtainedMarks = 0.0;
            
            for (Grade grade : grades) {
                grade.displayInfo();
                totalMarks += grade.getMaxMarks();
                obtainedMarks += grade.getObtainedMarks();
            }
            
            if (totalMarks > 0) {
                double percentage = (obtainedMarks / totalMarks) * 100.0;
                double gradePoint = GradeCalculator.percentageToGradePoint(percentage);
                String letterGrade = GradeCalculator.gradePointToLetter(gradePoint);
                
                System.out.println("Overall: " + String.format("%.2f", obtainedMarks) + "/" + 
                                  String.format("%.2f", totalMarks) + " (" + 
                                  String.format("%.2f", percentage) + "%)");
                System.out.println("Grade: " + letterGrade + " (GP: " + String.format("%.2f", gradePoint) + ")");
                
                totalWeightedScore += gradePoint * credits;
                totalCredits += credits;
            }
        }
        
        if (totalCredits > 0) {
            double sgpa = totalWeightedScore / totalCredits;
            System.out.println("\n" + "=".repeat(40));
            System.out.println("SGPA: " + String.format("%.2f", sgpa) + " / 10.0");
            System.out.println("=".repeat(40));
        }
    }
    
    // View all students
    public void viewAllStudents() {
        System.out.println("\n=== All Students ===");
        
        if (students.isEmpty()) {
            System.out.println("No students registered yet!");
            return;
        }
        
        System.out.println("\nTotal Students: " + students.size());
        for (Student student : students) {
            System.out.println("\n" + "-".repeat(50));
            student.displayInfo();
        }
    }
    
    // View all subjects
    public void viewAllSubjects() {
        System.out.println("\n=== All Subjects ===");
        
        if (subjects.isEmpty()) {
            System.out.println("No subjects added yet!");
            return;
        }
        
        System.out.println("\nTotal Subjects: " + subjects.size());
        for (Subject subject : subjects) {
            subject.displayInfo();
        }
    }
    
    // Helper methods
    private Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
    
    private Subject findSubjectByCode(String code) {
        for (Subject subject : subjects) {
            if (subject.getSubjectCode().equals(code)) {
                return subject;
            }
        }
        return null;
    }
}
