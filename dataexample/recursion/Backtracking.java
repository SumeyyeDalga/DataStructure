import java.util.ArrayList;

class Block {
    int x_coordinate;
    int y_coordinate;
    static ArrayList<ArrayList<Integer>> grid;

    Block(int x, int y, int row, int column) {
        this.x_coordinate = x;
        this.y_coordinate = y;

        if (grid == null) {
            grid = new ArrayList<>();
            // Grid oluşturuluyor, her bir satırda `column` kadar eleman olacak
            for (int i = 0; i < row; i++) {
                grid.add(new ArrayList<Integer>(column));
                // Her bir satırda column kadar hücreyi başlatıyoruz
                for (int j = 0; j < column; j++) {
                    grid.get(i).add(0); // Başlangıçta bütün hücreler 0 olacak
                }
            }
        }

        // Verilen x, y koordinatındaki hücreyi 1 olarak işaretliyoruz
        grid.get(x_coordinate).set(y_coordinate, 1);
    }

    public static int getRowStatic() {
        return grid.size(); // Satır sayısını döndürür
    }

    public static int getColumnStatic() {
        return grid.get(0).size(); // İlk satırdaki sütun sayısını döndürür
    }
}
public class Backtracking {

    static ArrayList<String> path = new ArrayList<>();

    public static boolean findMazePath(int x, int y) {
        System.out.println("Visiting: (" + x + ", " + y + ")");
        
        // Eğer (x, y) koordinatı dışarıda ya da engel varsa
        if (x < 0 || y < 0 || x >= Block.getRowStatic() || y >= Block.getColumnStatic() || Block.grid.get(x).get(y) == 1) {
            return false; // Geri dön, geçerli bir yol yok
        }
    
        // Eğer çıkış noktasına ulaşıldıysa
        if (x == Block.getRowStatic() - 1 && y == Block.getColumnStatic() - 1) {
            path.add("(" + x + "," + y + ")");
            return true; // Çıkışa ulaşıldı, yolu kaydettik
        }
    
        // Bu hücreyi geçici olarak işaretle (yolu bulurken)
        Block.grid.get(x).set(y, 1); // İşaretliyoruz
    
        // Yolu kaydediyoruz
        path.add("(" + x + "," + y + ")");
    
        // Bütün komşu hücrelere gidiyoruz (yukarı, aşağı, sağa, sola)
        // Eğer bir yol bulunursa true döner
        if (findMazePath(x + 1, y) || findMazePath(x + 1, y) || findMazePath(x, y - 1) || findMazePath(x, y + 1)) {
            return true;
        }
    
        // Eğer yukarıdaki yollarla çıkış bulunamazsa, geri dönüyoruz (backtracking)
        path.remove(path.size() - 1); // Bu hücreyi yol listesinden çıkar
        Block.grid.get(x).set(y, 0);  // Bu yolu geri al
        return false; // Geçerli bir yol bulunmadı
    }
    

    public static void main(String[] args) {
        // 4x4'lük bir grid oluşturuyoruz, başlangıçta sadece 0'lar olacak
        // Engelleri ekleyelim
        new Block(1, 1, 4, 4); // 1, 1 hücresini engel olarak işaretliyoruz
        new Block(2, 1, 4, 4); // 2, 1 hücresini engel olarak işaretliyoruz

        // Yolu bulmaya çalışalım
        if (findMazePath(0, 0)) {
            System.out.println("Path found!");
            System.out.println("Path: " + path); // Yolu yazdırıyoruz
        } else {
            System.out.println("No path found.");
        }
    }
}
