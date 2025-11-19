package models;

import java.time.LocalDate;

public class AttendanceRecord {
    private String studentId;
    private String subjectCode;
    private LocalDate date;
    private boolean present;
    
    public AttendanceRecord(String studentId, String subjectCode, LocalDate date, boolean present) {
        this.studentId = studentId;
        this.subjectCode = subjectCode;
        this.date = date;
        this.present = present;
    }
    
    public void displayInfo() {
        System.out.println("Date: " + date + " | Subject: " + subjectCode + " | Status: " + 
                          (present ? "Present ✓" : "Absent ✗"));
    }
    
    // Getters
    public String getStudentId() {
        return studentId;
    }
    
    public String getSubjectCode() {
        return subjectCode;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public boolean isPresent() {
        return present;
    }
    
    // CSV conversion
    public String toCSV() {
        return studentId + "," + subjectCode + "," + date + "," + present;
    }
    
    public static AttendanceRecord fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new AttendanceRecord(parts[0], parts[1], LocalDate.parse(parts[2]), 
                                   Boolean.parseBoolean(parts[3]));
    }
}
