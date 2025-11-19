package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String studentId;
    private String name;
    private String rollNumber;
    private String branch;
    private int semester;
    private Map<String, ArrayList<AttendanceRecord>> attendanceMap; // Subject -> List of records
    private Map<String, ArrayList<Grade>> gradesMap; // Subject -> List of grades
    
    public Student(String studentId, String name, String rollNumber, String branch, int semester) {
        this.studentId = studentId;
        this.name = name;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.semester = semester;
        this.attendanceMap = new HashMap<>();
        this.gradesMap = new HashMap<>();
    }
    
    // Add subject to student
    public void addSubject(String subjectCode) {
        if (!attendanceMap.containsKey(subjectCode)) {
            attendanceMap.put(subjectCode, new ArrayList<>());
            gradesMap.put(subjectCode, new ArrayList<>());
        }
    }
    
    // Add attendance record
    public void addAttendance(String subjectCode, AttendanceRecord record) {
        if (!attendanceMap.containsKey(subjectCode)) {
            addSubject(subjectCode);
        }
        attendanceMap.get(subjectCode).add(record);
    }
    
    // Add grade
    public void addGrade(String subjectCode, Grade grade) {
        if (!gradesMap.containsKey(subjectCode)) {
            addSubject(subjectCode);
        }
        gradesMap.get(subjectCode).add(grade);
    }
    
    // Calculate attendance percentage for a subject
    public double getAttendancePercentage(String subjectCode) {
        ArrayList<AttendanceRecord> records = attendanceMap.get(subjectCode);
        if (records == null || records.isEmpty()) {
            return 0.0;
        }
        
        int totalClasses = records.size();
        int classesAttended = 0;
        
        for (AttendanceRecord record : records) {
            if (record.isPresent()) {
                classesAttended++;
            }
        }
        
        return (classesAttended * 100.0) / totalClasses;
    }
    
    // Calculate overall attendance percentage
    public double getOverallAttendance() {
        if (attendanceMap.isEmpty()) {
            return 0.0;
        }
        
        int totalClasses = 0;
        int totalPresent = 0;
        
        for (ArrayList<AttendanceRecord> records : attendanceMap.values()) {
            totalClasses += records.size();
            for (AttendanceRecord record : records) {
                if (record.isPresent()) {
                    totalPresent++;
                }
            }
        }
        
        return totalClasses > 0 ? (totalPresent * 100.0) / totalClasses : 0.0;
    }
    
    // Check if eligible for exams (75% attendance required)
    public boolean isEligibleForExams() {
        return getOverallAttendance() >= 75.0;
    }
    
    // Get subjects below 75% attendance
    public ArrayList<String> getLowAttendanceSubjects() {
        ArrayList<String> lowSubjects = new ArrayList<>();
        for (String subject : attendanceMap.keySet()) {
            if (getAttendancePercentage(subject) < 75.0) {
                lowSubjects.add(subject);
            }
        }
        return lowSubjects;
    }
    
    // Display student info
    public void displayInfo() {
        System.out.println("\n--- Student Information ---");
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Branch: " + branch);
        System.out.println("Semester: " + semester);
        System.out.println("Overall Attendance: " + String.format("%.2f", getOverallAttendance()) + "%");
        System.out.println("Exam Eligibility: " + (isEligibleForExams() ? "✓ Eligible" : "✗ Not Eligible"));
    }
    
    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getRollNumber() {
        return rollNumber;
    }
    
    public String getBranch() {
        return branch;
    }
    
    public int getSemester() {
        return semester;
    }
    
    public Map<String, ArrayList<AttendanceRecord>> getAttendanceMap() {
        return attendanceMap;
    }
    
    public Map<String, ArrayList<Grade>> getGradesMap() {
        return gradesMap;
    }
    
    // CSV conversion
    public String toCSV() {
        return studentId + "," + name + "," + rollNumber + "," + branch + "," + semester;
    }
    
    public static Student fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Student(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
    }
}
