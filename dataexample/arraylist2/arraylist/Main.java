public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        // Öğrencileri ekle
        manager.addStudent("aaa", 90);
        manager.addStudent("bbb", 95);
        manager.addStudent("ccc", 100);

        // Öğrenci listelerini yazdır
        System.out.println("Student List:");
        manager.tableOfStudents();

        // En yüksek notu bul
        Student highest = manager.findHighestGrade();
        if (highest != null) {
            System.out.println("\nHighest Grade: " + highest);
        }

        // Öğrenci arama
        System.out.println("\nGrade of 'bbb': " + manager.findGrade("bbb"));

        // Öğrenci silme
        System.out.println("\nRemoving 'bbb'");
        manager.removeStudent("bbb");

        // Güncel listeyi yazdır
        System.out.println("\nUpdated Student List:");
        manager.tableOfStudents();
    }
}
