package com.example.bookshelf.ui;

import com.example.bookshelf.model.Book;
import com.example.bookshelf.service.BookManager;

import java.util.List;
import java.util.Scanner;

/**
 * Handles all console input/output. Kept separate from BookManager so the
 * data logic doesn't depend on how the user interacts with it.
 */
public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);
    private final BookManager bookManager = new BookManager();

    public void run() {
        System.out.println("=== Personal Bookshelf Manager (AI-assisted CLI) ===");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readIntInRange("Choose an option: ", 1, 10);

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewAllBooks();
                case 3 -> viewBookDetails();
                case 4 -> removeBook();
                case 5 -> searchByTitle();
                case 6 -> filterBooks();
                case 7 -> viewSortedByRating();
                case 8 -> viewSortedByHours();
                case 9 -> viewStatistics();
                case 10 -> running = false;
            }
        }

        System.out.println("Goodbye! Happy reading.");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Add a book");
        System.out.println("2. View all books");
        System.out.println("3. View details of a book");
        System.out.println("4. Remove a book");
        System.out.println("5. Search by title");
        System.out.println("6. Filter books");
        System.out.println("7. View books sorted by rating");
        System.out.println("8. View books sorted by hours spent reading");
        System.out.println("9. View statistics");
        System.out.println("10. Exit");
    }

    private void addBook() {
        String title = readNonEmptyLine("Enter book title: ");
        String author = readNonEmptyLine("Enter author: ");
        double hours = readNonNegativeDouble("Enter hours spent reading: ");
        int rating = readIntInRange("Enter rating (1-5): ", 1, 5);

        bookManager.addBook(title, author, hours, rating);
        System.out.println("\"" + title + "\" added to your shelf.");
    }

    private void viewAllBooks() {
        if (bookManager.isEmpty()) {
            System.out.println("No books added yet.");
            return;
        }
        printTable(bookManager.getAllBooks());
    }

    private void viewBookDetails() {
        if (bookManager.isEmpty()) {
            System.out.println("No books added yet.");
            return;
        }
        printIndexedTable(bookManager.getAllBooks());
        int index = readIntInRange("Enter the number of the book to view: ", 1, bookManager.count());
        Book book = bookManager.getBookAt(index - 1);

        System.out.println();
        System.out.println("Title    : " + book.getTitle());
        System.out.println("Author   : " + book.getAuthor());
        System.out.printf("Hours    : %.1f%n", book.getHoursSpent());
        System.out.println("Rating   : " + book.getRatingStars() + " (" + book.getRating() + "/5)");
    }

    private void removeBook() {
        if (bookManager.isEmpty()) {
            System.out.println("No books added yet.");
            return;
        }
        printIndexedTable(bookManager.getAllBooks());
        int index = readIntInRange("Enter the number of the book to remove: ", 1, bookManager.count());
        Book book = bookManager.getBookAt(index - 1);

        String confirm = readNonEmptyLine("Remove \"" + book.getTitle() + "\"? (y/n): ");
        if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
            bookManager.removeBookAt(index - 1);
            System.out.println("Removed.");
        } else {
            System.out.println("Cancelled - nothing was removed.");
        }
    }

    private void filterBooks() {
        if (bookManager.isEmpty()) {
            System.out.println("No books added yet.");
            return;
        }
        System.out.println("Filter by:");
        System.out.println("1. Minimum rating");
        System.out.println("2. Minimum hours spent reading");
        int filterChoice = readIntInRange("Choose an option: ", 1, 2);

        List<Book> results;
        if (filterChoice == 1) {
            int minRating = readIntInRange("Show books rated at least (1-5): ", 1, 5);
            results = bookManager.filterByMinRating(minRating);
        } else {
            double minHours = readNonNegativeDouble("Show books with at least this many hours read: ");
            results = bookManager.filterByMinHours(minHours);
        }

        if (results.isEmpty()) {
            System.out.println("No books matched that filter.");
        } else {
            printTable(results);
        }
    }

    private void searchByTitle() {
        String query = readNonEmptyLine("Enter a title to search for: ");
        List<Book> results = bookManager.searchByTitle(query);
        if (results.isEmpty()) {
            System.out.println("No books matched \"" + query + "\".");
            return;
        }
        printTable(results);
    }

    private void viewSortedByRating() {
        if (bookManager.isEmpty()) {
            System.out.println("No books added yet.");
            return;
        }
        printTable(bookManager.sortedByRatingDescending());
    }

    private void viewSortedByHours() {
        if (bookManager.isEmpty()) {
            System.out.println("No books added yet.");
            return;
        }
        printTable(bookManager.sortedByHoursDescending());
    }

    private void viewStatistics() {
        if (bookManager.isEmpty()) {
            System.out.println("No books added yet, so there are no statistics to show.");
            return;
        }
        System.out.println();
        System.out.println("Total books on shelf : " + bookManager.count());
        System.out.printf("Average rating        : %.1f / 5%n", bookManager.getAverageRating());
        System.out.printf("Total hours read       : %.1f%n", bookManager.getTotalHoursRead());
    }

    private void printIndexedTable(List<Book> books) {
        String format = "%-4s %-25s %-20s %-10s %-8s%n";
        System.out.println();
        System.out.printf(format, "No.", "Title", "Author", "Hours", "Rating");
        System.out.println("-".repeat(70));
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.printf(format,
                    (i + 1) + ".",
                    truncate(book.getTitle(), 25),
                    truncate(book.getAuthor(), 20),
                    book.getHoursSpent(),
                    book.getRatingStars());
        }
    }

    private void printTable(List<Book> books) {
        String format = "%-25s %-20s %-10s %-8s%n";
        System.out.println();
        System.out.printf(format, "Title", "Author", "Hours", "Rating");
        System.out.println("-".repeat(65));
        for (Book book : books) {
            System.out.printf(format,
                    truncate(book.getTitle(), 25),
                    truncate(book.getAuthor(), 20),
                    book.getHoursSpent(),
                    book.getRatingStars());
        }
    }

    private String truncate(String value, int maxLength) {
        if (value.length() <= maxLength) {
            return value;
        }
        return value.substring(0, maxLength - 3) + "...";
    }

    // ---------- Input validation helpers ----------

    private String readNonEmptyLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This can't be blank - please try again.");
        }
    }

    private double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Please enter a number that isn't negative.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number - please try again (e.g. 3.5).");
            }
        }
    }

    private int readIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.println("Please enter a whole number between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid whole number - please try again.");
            }
        }
    }
}
