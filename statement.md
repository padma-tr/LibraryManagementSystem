# Project Statement

## Problem Statement

As a college student, I've faced these attendance-related problems:

**The 75% Rule is Scary:**
We need 75% attendance to be eligible for exams. Miss a few classes, and suddenly you're in the detention zone. The problem? You don't realize you're below 75% until it's too late because manual calculation is tedious.

**Manual Tracking is Impossible:**
With 6-7 subjects, marking attendance in a notebook and calculating percentages every week is too much work. Most students (including me) just guess "I think I'm around 80%" which is often wrong.

**No Early Warnings:**
By the time you realize one subject is at 70%, you need to attend like 10 classes continuously to recover. Would be better to get alerts when it drops to 80% so you can fix it early.

**Grade Tracking is Messy:**
We have quizzes, assignments, midterms, finals... keeping track of all marks and calculating SGPA manually with credit weightage is confusing. Excel sheets get complicated.

**No Way to Plan:**
Can't answer questions like "If I miss tomorrow's class, will I drop below 75%?" or "How many more classes do I need to attend to be safe?"

I've almost been detained twice because of this. That's when I decided to build an automated system.

## Scope of the Project

### What I Built:

**Student Management:**
- Register students with ID, name, roll number, branch, semester
- Store all info persistently
- Handle multiple students

**Subject Management:**
- Add subjects with code, name, credits, faculty
- Track credits for SGPA calculation
- Store subject details

**Attendance Tracking:**
- Mark daily attendance (present/absent) with date
- Calculate attendance percentage per subject
- Calculate overall attendance across all subjects
- Check 75% eligibility automatically
- Identify subjects with low attendance
- Calculate how many classes needed to reach 75%

**Grade Management:**
- Record different assessment types (Quiz, Assignment, Midterm, Final)
- Track max marks and obtained marks
- Calculate percentage for each assessment
- Convert percentage to grade points (10-point scale)
- Assign letter grades (A+, A, B+, B, C, D, F)
- Calculate SGPA using weighted average based on credits

**Reporting:**
- Detailed attendance report with warnings
- Grade report with SGPA
- Student list
- Subject list

**Data Persistence:**
- Save to CSV files (students, subjects, attendance, grades)
- Load automatically on startup
- Never lose data

### What I Didn't Include:

**GUI:**
Just a console program with text menus. No windows or buttons. Kept it simple to focus on logic.

**Multi-User Features:**
No login system or user authentication. Single-user focused on personal use.

**Advanced Analytics:**
No graphs, trends, predictions. Just straightforward data.

**Notifications:**
No email/SMS alerts (would need external services).

**Cloud/Online:**
Runs locally on your computer only.

### Why These Limitations?

Honestly, had to finish this in 3 weeks while managing other courses! Started with core features that solve the main problem. Other stuff can be added later if needed.

## Target Users

**Primary User: Me (and students like me)!**

Who needs this:
- Students who need to track attendance across multiple subjects
- Anyone struggling with the 75% rule
- Students who want to calculate SGPA easily
- People who prefer automation over manual tracking

Who it's NOT for:
- Teachers/faculty (they have separate systems)
- People wanting professional attendance software
- Those needing mobile apps or cloud sync

Basically made it for students in my situation - need to track their own attendance and grades without complicated setup.

## High-Level Features

### Feature 1: Smart Attendance Tracking

**What it does:**
- Mark each day's attendance in seconds
- Shows real-time percentage after every entry
- Alerts when below 75% (⚠️ WARNING)
- Calculates exactly how many classes to attend to recover

**Why it's cool:**
No more "I think I'm around 80%" guessing. You know EXACTLY where you stand, every single day.

**Example:**
Current: 72% attendance in Data Structures
Alert: ⚠️ Below 75%!
Action needed: Attend 3 more classes continuously
Result: Will reach 75.5%

### Feature 2: Eligibility Checker

**What it does:**
Instantly tells you if you're eligible for exams based on overall attendance.

**Why it matters:**
Prevents last-minute panic. You know weeks in advance if you're in danger.

**Display:**
Overall Attendance: 77.89%
Exam Eligibility: ✓ Eligible

Low attendance subjects:

Database (73.33%) ⚠️


### Feature 3: Grade & SGPA Calculator

**What it does:**
- Store marks from all assessments
- Automatically calculate percentages
- Convert to grade points
- Calculate SGPA with credit weightage

**Why it's useful:**
Know your SGPA after every test. No waiting for official results to get a rough idea.

**Calculation:**
Data Structures (4 credits): GP 8.0
Operating Systems (3 credits): GP 9.0
Database (4 credits): GP 7.0

SGPA = (8×4 + 9×3 + 7×4) / (4+3+4)
= 91 / 11
= 8.27

### Feature 4: Historical Tracking

**What it does:**
Maintains complete history of:
- Every class attended/missed with dates
- Every assessment score
- Attendance trends over time

**Why it's important:**
Can review patterns like "I miss a lot of Friday classes" or "My midterm scores are always lower than quizzes".

### Feature 5: Multiple Student Support

**What it does:**
Can track data for multiple students in the same file.

**Use case:**
Help your roommate track their attendance too, or use for group study planning.

## How It Actually Works

**Daily Workflow:**

*Morning (after classes):*
1. Open program (double-click run.bat)
2. Option 3: Mark Attendance
3. Enter subject code
4. Mark present/absent
5. See updated percentage
6. Close program (data auto-saved)

Takes literally 30 seconds!

*Weekly Check (every Sunday):*
1. Option 5: Attendance Report
2. Review all subjects
3. See which ones need attention
4. Plan next week accordingly

*After Tests:*
1. Option 4: Add Grade
2. Enter test marks
3. Check updated SGPA

**Data Storage:**

Everything saves to simple CSV files you can open in Excel:
- students.csv - Your details
- subjects.csv - All your subjects
- attendance.csv - Every class record
- grades.csv - All test scores

If you mess something up, just edit the CSV file directly!

## Technical Implementation

**OOP Concepts Used:**

*Classes:*
- Student (main entity)
- Subject (course details)
- AttendanceRecord (single attendance entry)
- Grade (single assessment score)

*Services:*
- AttendanceManager (handles all operations)
- GradeCalculator (percentage → grade point conversion)
- FileHandler (CSV read/write)

**Data Structures:**
- HashMap<String, ArrayList<AttendanceRecord>> for subject-wise attendance
- HashMap<String, ArrayList<Grade>> for subject-wise grades
- ArrayList for main collections

**Key Algorithms:**

*Attendance Percentage:*
Percentage = (Classes Attended / Total Classes) × 100

*Classes Needed for 75%:*
Let current = attended/total
Need: (attended + x) / (total + x) = 0.75
Solving: x = (0.75 × total - attended) / 0.25

*SGPA Calculation:*
SGPA = Σ(GradePoint × Credits) / Σ(Credits)

## Success Metrics

After using this for 3 weeks:

✓ Check attendance daily: < 1 minute (previously: never checked)
✓ Never below 75%: Caught drops early (previously: close calls)
✓ SGPA estimation: Updated after every test (previously: waited for semester end)
✓ Data accuracy: 100% (previously: Excel errors)
✓ Time saved: ~30 minutes/week

## Challenges Faced

**Challenge 1: HashMap Structure**
Deciding how to store subject-wise data for each student. Tried ArrayList first, but HashMap made lookups faster.

**Challenge 2: Weighted SGPA**
Initially forgot credits! All subjects had equal weight. Had to refactor to include credit-based calculation.

**Challenge 3: "Classes Needed" Calculation**
The math for "how many classes to attend" was tricky. Derived the formula myself (felt like solving a DSA problem!).

## Future Improvements

*If I had more time:*
- Add attendance trends graph (even ASCII art would help)
- Semester-wise CGPA tracking
- Export report as PDF
- Prediction: "At current rate, you'll end at X%"
- Comparison with last semester

*Dream features:*
- Mobile app
- Sync across devices
- Notifications via Telegram bot
- Connect with college ERP to auto-fetch attendance

But for now, solves my immediate problem perfectly!

---

**Made by:** [Your Name]  
**Roll Number:** [Your Roll Number]  
**College:** VIT Bhopal  
**Semester:** 3 (2nd Year)  
**Date:** November 2025

**Motivation:** Almost got detained. Never again! 😅
