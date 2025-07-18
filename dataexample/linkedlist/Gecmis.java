package linkedlist;

import java.util.Scanner;
import java.util.Stack;

public class Gecmis {
    public static void main(String[] args) {
        Stack<String> gecmis = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- TARAYICI GEÇMİŞİ ---");
            System.out.println("1. Siteye Git");
            System.out.println("2. Geri Git");
            System.out.println("3. Mevcut Sayfayı Görüntüle");
            System.out.println("4. Tüm Geçmişi Gör");
            System.out.println("5. Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine(); // dummy read

            switch (secim) {
                case 1:
                    System.out.print("Gitmek istediğiniz site (URL): ");
                    String site = scanner.nextLine();
                    gecmis.push(site);
                    System.out.println(site + " sayfasına gidildi.");
                    break;

                case 2:
                    if (gecmis.isEmpty()) {
                        System.out.println("Geri gidilecek sayfa yok.");
                    } else {
                        String geri = gecmis.pop();
                        System.out.println("Geri gidildi, çıkılan sayfa: " + geri);
                        if (!gecmis.isEmpty()) {
                            System.out.println("Şu anki sayfa: " + gecmis.peek());
                        } else {
                            System.out.println("Artık hiçbir sayfa açık değil.");
                        }
                    }
                    break;

                case 3:
                    if (gecmis.isEmpty()) {
                        System.out.println("Hiçbir sayfa açık değil.");
                    } else {
                        System.out.println("Şu anki sayfa: " + gecmis.peek());
                    }
                    break;

                case 4:
                    if (gecmis.isEmpty()) {
                        System.out.println("Geçmiş boş.");
                    } else {
                        System.out.println("Geçmiş:");
                        for (int i = gecmis.size() - 1; i >= 0; i--) {
                            System.out.println("- " + gecmis.get(i));
                        }
                    }
                    break;

                case 5:
                    System.out.println("Uygulamadan çıkılıyor...");
                    return;

                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }
}
