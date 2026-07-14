import java.util.ArrayList;
import java.util.Scanner;

// Everything lives in one class here on purpose - no separate service class,
// no input validation loops, no formatting helpers. This is the traditional
// (non-AI-assisted) version, written by hand, so it is simpler and less
// robust than Version B.
public class BookShelfApp {

    static ArrayList<Book> books = new ArrayList<Book>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = 0;

        while (choice != 4) {

            System.out.println("");
            System.out.println("=== My Bookshelf ===");
            System.out.println("1. Add a book");
            System.out.println("2. View all books");
            System.out.println("3. Remove a book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                addBook();
            } else if (choice == 2) {
                viewBooks();
            } else if (choice == 3) {
                removeBook();
            } else if (choice == 4) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
    }

    static void removeBook() {
        viewBooks();
        System.out.print("Enter the number of the book to remove: ");
        int index = Integer.parseInt(sc.nextLine());
        books.remove(index - 1);
        System.out.println("Removed.");
    }

    static void addBook() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();

        System.out.print("Enter author: ");
        String author = sc.nextLine();

        System.out.print("Enter hours spent reading: ");
        double hours = Double.parseDouble(sc.nextLine());

        System.out.print("Enter rating (1-5): ");
        int rating = Integer.parseInt(sc.nextLine());

        Book book = new Book(title, author, hours, rating);
        books.add(book);

        System.out.println("Book added!");
    }

    static void viewBooks() {
        if (books.size() == 0) {
            System.out.println("No books added yet.");
            return;
        }

        System.out.println("");
        System.out.println("No. | Title | Author | Hours | Rating");
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            System.out.println((i + 1) + " | " + b.title + " | " + b.author + " | " + b.hoursSpent + " | " + b.rating);
        }
    }
}
