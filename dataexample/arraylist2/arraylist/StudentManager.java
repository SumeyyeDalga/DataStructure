import java.util.ArrayList;
import java.util.List;
class Student {
    private String name;
    private double grade;

    // Constructor
    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    // Öğrencinin bilgilerini döndürme
    @Override
    public String toString() {
        return name + " -> " + grade;
    }
}

public class StudentManager {
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    // Öğrenci ekleme
    public void addStudent(String name, double grade) {
        students.add(new Student(name, grade));
    }

    // Öğrenci silme
    public boolean removeStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                students.remove(student);
                return true;
            }
        }
        System.err.println("Student not found.");
        return false;
    }

    // Öğrenci arama
    public double findGrade(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student.getGrade();
            }
        }
        System.err.println("Student not found.");
        return -1;
    }

    // Öğrenci listesini yazdırma
    public void tableOfStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // En yüksek notu bulma
    public Student findHighestGrade() {
        if (students.isEmpty()) {
            System.err.println("No students in the list.");
            return null;
        }
        
        Student highest = students.get(0);
        for (Student student : students) {
            if (student.getGrade() > highest.getGrade()) {
                highest = student;
            }
        }
        return highest;
    }
}