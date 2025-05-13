import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentRecordSystem {
    static class Student {
        private String name, rollNumber;
        private double marks;

        public Student(String name, String rollNumber, double marks) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.marks = marks;
        }

        public String getName() { return name; }
        public String getRollNumber() { return rollNumber; }
        public double getMarks() { return marks; }

        public void setName(String name) { this.name = name; }
        public void setMarks(double marks) { this.marks = marks; }

        public void display() {
            System.out.println("Name: " + name + ", Roll Number: " + rollNumber + ", Marks: " + marks);
        }
    }

    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;
        do {
            System.out.println("\n1. Add Student | 2. View All | 3. Update | 4. Delete | 5. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAll(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine();

        for (Student s : students) {
            if (s.getRollNumber().equals(rollNumber)) {
                System.out.println("Roll number already exists. Student not added.");
                return;
            }
        }

        System.out.print("Enter marks: ");
        try {
            double marks = Double.parseDouble(scanner.nextLine());
            students.add(new Student(name, rollNumber, marks));
            System.out.println("Student added!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid marks. Student not added.");
        }
    }

    private static void viewAll() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                s.display();
            }
        }
    }

    private static void updateStudent() {
        System.out.print("Enter roll number to update: ");
        String rollNumber = scanner.nextLine();
        for (Student s : students) {
            if (s.getRollNumber().equals(rollNumber)) {
                System.out.print("Enter new name (or leave empty to keep current): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) s.setName(name);

                System.out.print("Enter new marks (or leave empty to keep current): ");
                String marksInput = scanner.nextLine();
                if (!marksInput.isEmpty()) {
                    try {
                        s.setMarks(Double.parseDouble(marksInput));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid marks input. Keeping previous marks.");
                    }
                }

                System.out.println("Student updated!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void deleteStudent() {
        System.out.print("Enter roll number to delete: ");
        String rollNumber = scanner.nextLine();
        boolean removed = students.removeIf(s -> s.getRollNumber().equals(rollNumber));
        System.out.println(removed ? "Student deleted." : "Student not found.");
    }
}
