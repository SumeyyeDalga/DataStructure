package linkedlist;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Banka{
    public static void main(String[] args) {
        Queue<String> kuyruk = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- BANKA KUYRUĞU ---");
            System.out.println("1. Kuyruğa Kişi Ekle");
            System.out.println("2. İşlem Yap (Kuyruktan Çıkar)");
            System.out.println("3. Kuyruktaki Kişileri Görüntüle");
            System.out.println("4. Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine(); // dummy read

            switch (secim) {
                case 1:
                    System.out.print("Kişi adı: ");
                    String ad = scanner.nextLine();
                    kuyruk.offer(ad);
                    System.out.println(ad + " kuyruğa eklendi.");
                    break;

                case 2:
                    if (kuyruk.isEmpty()) {
                        System.out.println("Kuyruk boş, işlem yapılacak kişi yok.");
                    } else {
                        String islemyapilan = kuyruk.poll();
                        System.out.println("İşlem yapılan kişi: " + islemyapilan);
                    }
                    break;

                case 3:
                    if (kuyruk.isEmpty()) {
                        System.out.println("Kuyrukta kimse yok.");
                    } else {
                        System.out.println("Kuyruktaki kişiler:");
                        for (String kisi : kuyruk) {
                            System.out.println("- " + kisi);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Programdan çıkılıyor...");
                    return;

                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }
}
