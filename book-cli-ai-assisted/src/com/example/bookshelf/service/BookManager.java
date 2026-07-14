package com.example.bookshelf.service;

import com.example.bookshelf.model.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Owns the in-memory list of books and all the operations on it.
 * Kept separate from the console/UI code so the two concerns
 * (data handling vs. user interaction) don't mix.
 */
public class BookManager {

    private final List<Book> books = new ArrayList<>();

    public void addBook(String title, String author, double hoursSpent, int rating) {
        books.add(new Book(title, author, hoursSpent, rating));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public boolean isEmpty() {
        return books.isEmpty();
    }

    public int count() {
        return books.size();
    }

    public List<Book> searchByTitle(String query) {
        String lowerQuery = query.toLowerCase();
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(lowerQuery)) {
                results.add(book);
            }
        }
        return results;
    }

    /** Returns the book at the given 0-based index, or null if the index is out of range. */
    public Book getBookAt(int index) {
        if (index < 0 || index >= books.size()) {
            return null;
        }
        return books.get(index);
    }

    /** Removes the book at the given 0-based index. Returns true if a book was removed. */
    public boolean removeBookAt(int index) {
        if (index < 0 || index >= books.size()) {
            return false;
        }
        books.remove(index);
        return true;
    }

    public List<Book> filterByMinRating(int minRating) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getRating() >= minRating) {
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> filterByMinHours(double minHours) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getHoursSpent() >= minHours) {
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> sortedByRatingDescending() {
        List<Book> sorted = new ArrayList<>(books);
        sorted.sort(Comparator.comparingInt(Book::getRating).reversed());
        return sorted;
    }

    public List<Book> sortedByHoursDescending() {
        List<Book> sorted = new ArrayList<>(books);
        sorted.sort(Comparator.comparingDouble(Book::getHoursSpent).reversed());
        return sorted;
    }

    public double getAverageRating() {
        if (books.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (Book book : books) {
            total += book.getRating();
        }
        return total / books.size();
    }

    public double getTotalHoursRead() {
        double total = 0;
        for (Book book : books) {
            total += book.getHoursSpent();
        }
        return total;
    }
}
