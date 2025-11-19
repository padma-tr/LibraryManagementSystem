package services;

public class GradeCalculator {
    
    // Convert percentage to grade point (10-point scale)
    public static double percentageToGradePoint(double percentage) {
        if (percentage >= 90) return 10.0;
        else if (percentage >= 80) return 9.0;
        else if (percentage >= 70) return 8.0;
        else if (percentage >= 60) return 7.0;
        else if (percentage >= 50) return 6.0;
        else if (percentage >= 40) return 5.0;
        else return 0.0;
    }
    
    // Convert grade point to letter grade
    public static String gradePointToLetter(double gradePoint) {
        if (gradePoint >= 9.5) return "A+";
        else if (gradePoint >= 9.0) return "A";
        else if (gradePoint >= 8.0) return "B+";
        else if (gradePoint >= 7.0) return "B";
        else if (gradePoint >= 6.0) return "C+";
        else if (gradePoint >= 5.0) return "C";
        else if (gradePoint >= 4.0) return "D";
        else return "F";
    }
    
    // Calculate SGPA from grade points and credits
    public static double calculateSGPA(double[] gradePoints, int[] credits) {
        double totalWeightedScore = 0.0;
        int totalCredits = 0;
        
        for (int i = 0; i < gradePoints.length; i++) {
            totalWeightedScore += gradePoints[i] * credits[i];
            totalCredits += credits[i];
        }
        
        return totalCredits > 0 ? totalWeightedScore / totalCredits : 0.0;
    }
    
    // Calculate CGPA from multiple SGPAs
    public static double calculateCGPA(double[] sgpas) {
        double sum = 0.0;
        for (double sgpa : sgpas) {
            sum += sgpa;
        }
        return sgpas.length > 0 ? sum / sgpas.length : 0.0;
    }
}
