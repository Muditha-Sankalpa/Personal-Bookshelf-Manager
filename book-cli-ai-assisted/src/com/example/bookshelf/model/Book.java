package com.example.bookshelf.model;

/**
 * Represents a single book entry in the shelf.
 * Immutable-ish: fields are set once via constructor and only exposed through getters.
 */
public class Book {

    private final String title;
    private final String author;
    private final double hoursSpent;
    private final int rating;

    public Book(String title, String author, double hoursSpent, int rating) {
        this.title = title;
        this.author = author;
        this.hoursSpent = hoursSpent;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getHoursSpent() {
        return hoursSpent;
    }

    public int getRating() {
        return rating;
    }

    /** Renders the rating as filled/empty stars, e.g. "***--" for a 3-star rating. */
    public String getRatingStars() {
        StringBuilder stars = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            stars.append(i <= rating ? "*" : "-");
        }
        return stars.toString();
    }
}
