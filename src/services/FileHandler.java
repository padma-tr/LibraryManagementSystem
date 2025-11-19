package services;

import models.*;
import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private static final String STUDENTS_FILE = "data/students.csv";
    private static final String SUBJECTS_FILE = "data/subjects.csv";
    private static final String ATTENDANCE_FILE = "data/attendance.csv";
    private static final String GRADES_FILE = "data/grades.csv";
    
    // Save students
    public static void saveStudents(ArrayList<Student> students) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE));
            writer.write("StudentID,Name,RollNumber,Branch,Semester\n");
            
            for (Student student : students) {
                writer.write(student.toCSV() + "\n");
            }
            
            writer.close();
            System.out.println("Students saved successfully!");
            
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
    
    // Load students
    public static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        
        try {
            File file = new File(STUDENTS_FILE);
            if (!file.exists()) {
                System.out.println("No existing student data. Starting fresh.");
                return students;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE));
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Student student = Student.fromCSV(line);
                    students.add(student);
                }
            }
            
            reader.close();
            System.out.println("Loaded " + students.size() + " students.");
            
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        
        return students;
    }
    
    // Save subjects
    public static void saveSubjects(ArrayList<Subject> subjects) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(SUBJECTS_FILE));
            writer.write("SubjectCode,SubjectName,Credits,Faculty\n");
            
            for (Subject subject : subjects) {
                writer.write(subject.toCSV() + "\n");
            }
            
            writer.close();
            System.out.println("Subjects saved successfully!");
            
        } catch (IOException e) {
            System.out.println("Error saving subjects: " + e.getMessage());
        }
    }
    
    // Load subjects
    public static ArrayList<Subject> loadSubjects() {
        ArrayList<Subject> subjects = new ArrayList<>();
        
        try {
            File file = new File(SUBJECTS_FILE);
            if (!file.exists()) {
                System.out.println("No existing subject data. Starting fresh.");
                return subjects;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(SUBJECTS_FILE));
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Subject subject = Subject.fromCSV(line);
                    subjects.add(subject);
                }
            }
            
            reader.close();
            System.out.println("Loaded " + subjects.size() + " subjects.");
            
        } catch (IOException e) {
            System.out.println("Error loading subjects: " + e.getMessage());
        }
        
        return subjects;
    }
    
    // Save all attendance records
    public static void saveAllAttendance(ArrayList<Student> students) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(ATTENDANCE_FILE));
            writer.write("StudentID,SubjectCode,Date,Present\n");
            
            for (Student student : students) {
                for (ArrayList<AttendanceRecord> records : student.getAttendanceMap().values()) {
                    for (AttendanceRecord record : records) {
                        writer.write(record.toCSV() + "\n");
                    }
                }
            }
            
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Error saving attendance: " + e.getMessage());
        }
    }
    
    // Load attendance records
    public static ArrayList<AttendanceRecord> loadAttendance() {
        ArrayList<AttendanceRecord> records = new ArrayList<>();
        
        try {
            File file = new File(ATTENDANCE_FILE);
            if (!file.exists()) {
                return records;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(ATTENDANCE_FILE));
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    AttendanceRecord record = AttendanceRecord.fromCSV(line);
                    records.add(record);
                }
            }
            
            reader.close();
            System.out.println("Loaded " + records.size() + " attendance records.");
            
        } catch (IOException e) {
            System.out.println("Error loading attendance: " + e.getMessage());
        }
        
        return records;
    }
    
    // Save all grades
    public static void saveAllGrades(ArrayList<Student> students) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(GRADES_FILE));
            writer.write("StudentID,SubjectCode,AssessmentType,MaxMarks,ObtainedMarks\n");
            
            for (Student student : students) {
                for (ArrayList<Grade> grades : student.getGradesMap().values()) {
                    for (Grade grade : grades) {
                        writer.write(grade.toCSV() + "\n");
                    }
                }
            }
            
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Error saving grades: " + e.getMessage());
        }
    }
    
    // Load grades
    public static ArrayList<Grade> loadGrades() {
        ArrayList<Grade> grades = new ArrayList<>();
        
        try {
            File file = new File(GRADES_FILE);
            if (!file.exists()) {
                return grades;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(GRADES_FILE));
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Grade grade = Grade.fromCSV(line);
                    grades.add(grade);
                }
            }
            
            reader.close();
            System.out.println("Loaded " + grades.size() + " grade records.");
            
        } catch (IOException e) {
            System.out.println("Error loading grades: " + e.getMessage());
        }
        
        return grades;
    }
}
