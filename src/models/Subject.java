package models;

public class Subject {
    private String subjectCode;
    private String subjectName;
    private int credits;
    private String faculty;
    
    public Subject(String subjectCode, String subjectName, int credits, String faculty) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.faculty = faculty;
    }
    
    public void displayInfo() {
        System.out.println("\n--- Subject Information ---");
        System.out.println("Subject Code: " + subjectCode);
        System.out.println("Subject Name: " + subjectName);
        System.out.println("Credits: " + credits);
        System.out.println("Faculty: " + faculty);
    }
    
    // Getters
    public String getSubjectCode() {
        return subjectCode;
    }
    
    public String getSubjectName() {
        return subjectName;
    }
    
    public int getCredits() {
        return credits;
    }
    
    public String getFaculty() {
        return faculty;
    }
    
    // CSV conversion
    public String toCSV() {
        return subjectCode + "," + subjectName + "," + credits + "," + faculty;
    }
    
    public static Subject fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Subject(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
    }
}
