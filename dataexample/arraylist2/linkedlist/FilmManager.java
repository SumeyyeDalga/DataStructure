class FilmNode {
  String name;
  int year;
  String type;
  FilmNode next;

  FilmNode(String name, int year, String type) {
      this.name = name;
      this.year = year;
      this.type = type;
      this.next = null;
  }
}

public class FilmManager {
  FilmNode head;

  FilmManager() {
      head = null;
  }

  public void addFilm(String name, int year, String type) {
      FilmNode newFilm = new FilmNode(name, year, type);

      if (head == null) {
          head = newFilm;
          return;
      }

      FilmNode temp = head;
      while (temp.next != null) {
          temp = temp.next;
      }
      temp.next = newFilm;
  }

  public void removeFilm(String name_1) {
      if (head == null) {
          System.out.println("List is empty.");
          return;
      }

      if (head.name.equals(name_1)) {
          head = head.next;
          return;
      }

      FilmNode temp = head;
      while (temp.next != null) {
          if (temp.next.name.equals(name_1)) {
              temp.next = temp.next.next;
              return;
          }
          temp = temp.next;
      }
      System.out.println("Film not found.");
  }

  public FilmNode findFilm(String name_1) {
      FilmNode temp = head;
      while (temp != null) {
          if (temp.name.equals(name_1)) {
              System.out.println("Movie name: " + temp.name + " movie year: " + temp.year + " movie type: " + temp.type);
              return temp;
          }
          temp = temp.next;
      }
      System.out.println("Film not found.");
      return null;
  }

  public void allFilms() {
      if (head == null) {
          System.out.println("No films in the list.");
          return;
      }

      FilmNode temp = head;
      while (temp != null) {
          System.out.println("Movie name: " + temp.name + " movie year: " + temp.year + " movie type: " + temp.type);
          temp = temp.next;
      }
  }

  public void oldestFilm() {
      if (head == null) {
          System.out.println("No films in the list.");
          return;
      }

      int oldestYear = head.year;
      FilmNode temp = head;

      while (temp != null) {
          if (temp.year < oldestYear) {
              oldestYear = temp.year;
          }
          temp = temp.next;
      }

      temp = head;
      while (temp != null) {
          if (temp.year == oldestYear) {
              System.out.println("Oldest Movie: " + temp.name + " movie year: " + temp.year + " movie type: " + temp.type);
          }
          temp = temp.next;
      }
  }

  public void update_film(String film_name, int film_year, String film_type) {
    FilmNode temp = head;
    while (temp != null) {
        if (temp.name.equals(film_name)) {
            temp.year = film_year;
            temp.type = film_type;
            return;
        }
        temp = temp.next;
    }
    System.out.println("Film not found.");
}

}
