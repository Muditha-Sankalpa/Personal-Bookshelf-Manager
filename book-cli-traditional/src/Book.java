// Simple data holder for a book. No encapsulation beyond basic getters -
// kept deliberately plain for the traditional (non-AI-assisted) version.
public class Book {

    String title;
    String author;
    double hoursSpent;
    int rating;

    public Book(String title, String author, double hoursSpent, int rating) {
        this.title = title;
        this.author = author;
        this.hoursSpent = hoursSpent;
        this.rating = rating;
    }
}
