public class Main {
    public static void main(String[] args) {
        FilmManager list = new FilmManager();

        // Add some films
        list.addFilm("The Shawshank Redemption", 1994, "Drama");
        list.addFilm("The Matrix", 1999, "Action");
        list.addFilm("Inception", 2010, "Action");

        // Display the list
        list.allFilms();

        // Update a film
        list.update_film("The Matrix",1999,"Sci-Fi");

        // Delete a film
        list.removeFilm("Inception");

        // Display the updated list
        list.allFilms();

        // Find the oldest film
        list.oldestFilm();
    }
}