import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Student {
    String name;
    int rollNumber;
    String course;
    int attendance; // 1 for present, 0 for absent
    String attendanceDate; // Store attendance date and time

    // Constructor
    public Student() {
        this.name = "";
        this.rollNumber = 0;
        this.course = "";
        this.attendance = 0;
        this.attendanceDate = "";
    }
}

public class StudentManagementSystem {
    public static void addStudent(Student[] students, int numStudents) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the student name: ");
        students[numStudents].name = scanner.next();
        System.out.print("Enter the student roll number: ");
        students[numStudents].rollNumber = scanner.nextInt();
        System.out.print("Enter the student course: ");
        students[numStudents].course = scanner.next();
        students[numStudents].attendance = 0; // 0 for absent by default
        students[numStudents].attendanceDate = ""; // Initialize attendanceDate

        // Increment the number of students.
        numStudents++;
    }

    public static void removeStudent(Student[] students, int numStudents, int rollNumber) {
        // Find the student with the given roll number.
        int i;
        for (i = 0; i < numStudents; i++) {
            if (students[i].rollNumber == rollNumber) {
                break;
            }
        }

        // If the student is found, remove it from the array.
        if (i < numStudents) {
            for (; i < numStudents - 1; i++) {
                students[i] = students[i + 1];
            }
            numStudents--;
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    public static void markAttendance(Student[] students, int numStudents, int rollNumber, int isPresent) {
        // Find the student with the given roll number.
        int i;
        for (i = 0; i < numStudents; i++) {
            if (students[i].rollNumber == rollNumber) {
                break;
            }
        }

        // If the student is found, mark their attendance and set the date.
        if (i < numStudents) {
            students[i].attendance = isPresent;

            // Get the current date and time.
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Store the date in the student's record.
            students[i].attendanceDate = currentDateTime.format(formatter);
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    public static void printReport(Student[] students, int numStudents) {
        // Print the attendance report in a table format.
        System.out.printf("| %-20s | %-12s | %-20s | %-10s | %-20s |\n", "Name", "Roll Number", "Course", "Attendance", "Attendance Date");
        System.out.println("+----------------------+--------------+----------------------+------------+----------------------+");

        for (int i = 0; i < numStudents; i++) {
            System.out.printf("| %-20s | %-12d | %-20s | %-10d | %-20s |\n",
                    students[i].name, students[i].rollNumber, students[i].course, students[i].attendance, students[i].attendanceDate);
        }
        System.out.println("+----------------------+--------------+----------------------+------------+----------------------+");
    }

    public static void main(String[] args) {
        // Initialize the student array and the number of students.
        Student[] students = new Student[100];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }
        int numStudents = 0;

        // The main menu.
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            while (true) {
                System.out.println("1. Add student");
                System.out.println("2. Remove student");
                System.out.println("3. Mark attendance");
                System.out.println("4. Print report");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addStudent(students, numStudents);
                        break;
                    case 2:
                        System.out.print("Enter the student roll number to remove: ");
                        int rollNumber = scanner.nextInt();
                        removeStudent(students, numStudents, rollNumber);
                        break;
                    case 3:
                        System.out.print("Enter the student roll number to mark attendance: ");
                        int rollNum = scanner.nextInt();
                        System.out.print("Is the student present (1 for yes, 0 for no)? ");
                        int isPresent = scanner.nextInt();
                        markAttendance(students, numStudents, rollNum, isPresent);
                        break;
                    case 4:
                        printReport(students, numStudents);
                        break;
                    case 5:
                        // Exit the program.
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }
}
