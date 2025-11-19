package models;

public class Grade {
    private String studentId;
    private String subjectCode;
    private String assessmentType; // Quiz, Assignment, Midterm, Final
    private double maxMarks;
    private double obtainedMarks;
    
    public Grade(String studentId, String subjectCode, String assessmentType, 
                 double maxMarks, double obtainedMarks) {
        this.studentId = studentId;
        this.subjectCode = subjectCode;
        this.assessmentType = assessmentType;
        this.maxMarks = maxMarks;
        this.obtainedMarks = obtainedMarks;
    }
    
    // Calculate percentage for this assessment
    public double getPercentage() {
        return (obtainedMarks / maxMarks) * 100.0;
    }
    
    public void displayInfo() {
        System.out.println(assessmentType + ": " + obtainedMarks + "/" + maxMarks + 
                          " (" + String.format("%.2f", getPercentage()) + "%)");
    }
    
    // Getters
    public String getStudentId() {
        return studentId;
    }
    
    public String getSubjectCode() {
        return subjectCode;
    }
    
    public String getAssessmentType() {
        return assessmentType;
    }
    
    public double getMaxMarks() {
        return maxMarks;
    }
    
    public double getObtainedMarks() {
        return obtainedMarks;
    }
    
    // CSV conversion
    public String toCSV() {
        return studentId + "," + subjectCode + "," + assessmentType + "," + 
               maxMarks + "," + obtainedMarks;
    }
    
    public static Grade fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Grade(parts[0], parts[1], parts[2], 
                        Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
    }
}
