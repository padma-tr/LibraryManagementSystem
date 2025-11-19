# Student Attendance & Grade Management System

Hey! This is my attendance tracking project for college. Got tired of manually calculating if I have enough attendance for exams, so I built this system using Java.

## About Me

**Name:** PADMA TRIPATHI 
**Roll No:** 24BSA10137 
**Branch:** BTech CSE - Cloud Computing (2nd Year)  
**College:** VIT Bhopal  


## Why I Made This

So here's the thing - I've been almost detained twice because I didn't realize my attendance was below 75% until it was too late. We need 75% to be eligible for exams, and manually calculating this for 6-7 subjects is a pain.

Also noticed these problems:
- No easy way to track daily attendance across multiple subjects
- Calculating percentages manually is tedious and error-prone
- Don't know how many more classes I need to attend to reach 75%
- Tracking grades and calculating SGPA takes forever
- No alerts when attendance is dropping

So I thought, why not automate all this? That's how this project started!

## What This Does

**Attendance Stuff:**
- Mark attendance for each subject (present/absent)
- Automatically calculates attendance percentage
- Alerts you when any subject is below 75%
- Shows how many classes you need to attend to reach 75%
- Checks if you're eligible for exams
- Tracks attendance over time

**Grade Management:**
- Record marks for quizzes, assignments, midterms, finals
- Calculates percentage for each subject
- Converts percentage to grade points (10-point scale)
- Gives letter grades (A+, A, B+, B, C, etc.)
- Calculates SGPA based on credits
- Shows subject-wise performance

**Smart Features:**
- Saves everything to CSV files (data never lost!)
- Loads previous data when you restart
- Works for multiple students
- Handles multiple subjects per student
- Shows warnings for low attendance
- All calculations done automatically

## Tech I Used

- Java 25 (the version on my laptop)
- OOP concepts we learned in class
- ArrayList and HashMap for storing data
- File I/O for CSV files
- LocalDate for handling dates
- Console-based (that black command window)

## Project Structure

AttendanceManagementSystem/
├── src/
│ ├── models/ (Entity classes)
│ │ ├── Student.java
│ │ ├── Subject.java
│ │ ├── AttendanceRecord.java
│ │ └── Grade.java
│ │
│ ├── services/ (Business logic)
│ │ ├── AttendanceManager.java
│ │ ├── GradeCalculator.java
│ │ └── FileHandler.java
│ │
│ └── Main.java (Entry point)
│
├── data/ (CSV files)
├── screenshots/ (Project screenshots)
└── run.bat (Quick run script)

text

## How to Run

**Easy Way (Windows):**
Just double-click `run.bat` - it compiles and runs everything!

**Command Line Way:**
Compile
javac -d bin src/models/.java src/services/.java src/Main.java

Run
java -cp bin Main

## How to Use

**First Time Setup:**
1. Register yourself as a student (Option 1)
2. Add your subjects (Option 2)

**Daily Use:**
1. Mark today's attendance (Option 3)
   - Select student ID
   - Select subject
   - Mark present/absent

2. Check attendance status (Option 5)
   - Shows percentage per subject
   - Alerts if below 75%
   - Tells you how many classes to attend

3. Add grades when you get test results (Option 4)
   - Quiz, Assignment, Midterm, Final
   - Enter max marks and obtained marks

4. Check SGPA (Option 6)
   - Shows all grades
   - Calculates overall SGPA

**Menu Options:**
- 1: Register student
- 2: Add subject
- 3: Mark attendance (do this daily!)
- 4: Add grades
- 5: Attendance report (CHECK THIS REGULARLY!)
- 6: Grade report & SGPA
- 7: View all students
- 8: View all subjects
- 0: Exit

## Real Example

Let me show you how I use it:

**Morning after class:**
Option 3 (Mark Attendance)
Student ID: S001
Subject: CSE301
Date: (press Enter for today)
Present? yes

Output: Current attendance: 85%

**When checking eligibility:**
Option 5 (Attendance Report)
Student ID: S001

Output shows:

Data Structures: 72% ⚠️ WARNING

Need 4 more classes to reach 75%

OS: 88% ✓ Good

DBMS: 91% ✓ Good
Overall: 83.67% ✓ Eligible

**After getting test results:**
Option 4 (Add Grade)
Subject: CSE301
Type: Midterm
Max: 50
Got: 42

Shows: 84% (Grade: B+, GP: 8.0)

## Testing I Did

Tested a bunch of scenarios:
- Added 3 students with different branches ✓
- Marked 50+ attendance records ✓
- Tested with 70% attendance - showed warning correctly ✓
- Tested with 80% - showed eligible ✓
- Added grades, calculated SGPA - matched manual calculation ✓
- Closed program, reopened - all data still there ✓
- Tried invalid student ID - showed error properly ✓
- Tried marks > max marks - rejected with error ✓

## Problems I Faced

**Problem 1: Attendance Calculation**
Initially, I was calculating percentage wrong when some subjects had different number of classes. Fixed by calculating per-subject first, then averaging.

**Problem 2: SGPA Calculation**
Forgot to weight by credits! A 4-credit subject should count more than 3-credit. Fixed by implementing proper weighted average.

**Problem 3: Date Handling**
First time using LocalDate. Took me a while to figure out how to parse dates from CSV. Stack Overflow helped!

## What I Learned

**Technical Skills:**
- HashMap is perfect for subject-wise tracking
- File handling is actually not that hard once you get it
- Date calculations are easy with java.time package
- Weighted averages for SGPA calculation

**Practical Skills:**
- Breaking a big problem into smaller pieces
- Testing with real data (my own attendance!)
- Error handling is super important
- Comments help when you come back after a week

**Life Lesson:**
Building something you actually use is way more motivating than theoretical assignments!

## Future Plans

If I get time (and if this gets good grades 😅), I'd love to add:
- GUI with proper windows and buttons
- Graphs showing attendance trends
- Email alerts when attendance drops below 80%
- Export reports as PDF
- Mobile app version
- Comparison with classmates (anonymous)

But honestly, even this simple version solves my problem!

## What's Not Included

To keep it simple, I didn't add:
- No fancy GUI (console only)
- No multi-user login system
- No database (CSV files work fine)
- No online/cloud features
- No notifications

Maybe next semester project! 

## References

- Our Java textbook
- Stack Overflow for date parsing
- GeeksforGeeks for HashMap examples
- java.time documentation
- My own attendance nightmares as motivation 

## Final Thoughts

This is my first project that I actually use daily! I check my attendance every weekend using this. Already caught myself at 74% in one subject and attended extra classes to get back to 75%.

If you're working on something similar, good luck! Feel free to use any ideas from here.

---

**Made with ☕ and the fear of being detained**  
**VIT Bhopal | November 2025**

**P.S.** - Currently maintaining 78% overall attendance thanks to this tool! 

