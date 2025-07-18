import java.util.LinkedList;
import java.util.Queue;

class SpaceShip {
    String name;
    int time_mission;

    // Constructor ekliyoruz
    public SpaceShip(String name, int time_mission) {
        this.name = name;
        this.time_mission = time_mission;
    }

    // SpaceShip bilgilerini yazdırmak için toString metodu
    @Override
    public String toString() {
        return "SpaceShip [Name=" + name + ", Mission Time=" + time_mission + " seconds]";
    }
}

public class Space {
    Queue<SpaceShip> space = new LinkedList<>();

    // Uzay gemisini kuyruğa ekliyoruz
    public void add_spaceship(SpaceShip spaceShip_1) {
        space.offer(spaceShip_1);
    }

    // Kuyruğun başındaki gemiyi çıkarıyoruz
    public void remove_spaceship() {
        SpaceShip removed = space.poll();
        if (removed != null) {
            System.out.println(removed.name + " has started its mission and has been removed from the queue.");
        } else {
            System.out.println("No spaceship available to remove.");
        }
    }

    // Kuyruğun başındaki gemiyi gösteriyoruz
    public SpaceShip front_spaceShip() {
        return space.peek();
    }

    public static void main(String[] args) {
        Space space = new Space();
        
        // Uzay gemilerini oluşturuyoruz
        SpaceShip spaceship1 = new SpaceShip("Apollo", 10);
        SpaceShip spaceship2 = new SpaceShip("Voyager", 5);
        SpaceShip spaceship3 = new SpaceShip("Explorer", 7);
        
        // Kuyruğa gemileri ekliyoruz
        space.add_spaceship(spaceship1);
        space.add_spaceship(spaceship2);
        space.add_spaceship(spaceship3);
        
        // Kuyruğun başındaki gemiyi gösteriyoruz
        System.out.println("The spaceship at the front of the queue: " + space.front_spaceShip());
        
        // Kuyruğun başındaki gemiyi çıkarıyoruz
        space.remove_spaceship();
        
        // Kuyruğun başındaki yeni gemiyi gösteriyoruz
        System.out.println("The spaceship at the front of the queue: " + space.front_spaceShip());
    }
}
