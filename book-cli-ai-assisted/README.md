# Personal Bookshelf Manager - CLI (Version B - AI-assisted)

A command-line Java program for tracking books you've read: title,
author, hours spent reading, and a 1-5 rating. Layered into model,
service, and UI classes, with input validation, search, filtering,
sorting, single-book detail view, removal, and basic statistics.

## Requirements

- Java JDK 17 or newer (java -version and javac -version to check)

## Files

```
book-cli-ai-assisted/
  src/com/example/bookshelf/
    Main.java              - entry point
    model/Book.java        - Book data class (title, author, hours, rating)
    service/BookManager.java - storage, search, filter, sort, and statistics logic
    ui/ConsoleUI.java       - menu, input validation, formatted table output
```

## How to run

### Option 1: IntelliJ (recommended)
1. Open IntelliJ -> Open -> select the book-cli-ai-assisted folder
2. If prompted, let it configure a Java SDK
3. Open Main.java, click the green run arrow next to main

### Option 2: Command line
```
cd book-cli-ai-assisted/src
javac com/example/bookshelf/model/Book.java com/example/bookshelf/service/BookManager.java com/example/bookshelf/ui/ConsoleUI.java com/example/bookshelf/Main.java
java com.example.bookshelf.Main
```

## Using it

```
=== Personal Bookshelf Manager (AI-assisted CLI) ===

1. Add a book
2. View all books
3. View details of a book
4. Remove a book
5. Search by title
6. Filter books
7. View books sorted by rating
8. View books sorted by hours spent reading
9. View statistics
10. Exit
```

- Adding a book re-prompts you if you type something invalid (text where
  a number is expected, a rating outside 1-5, or a blank title) instead
  of crashing.
- Viewing details or removing a book shows a numbered list first, so you
  pick a book by its number - both are bounds-checked, so an out-of-range
  number just re-prompts instead of crashing.
- Removing a book asks for a y/n confirmation before deleting anything.
- Filtering lets you choose "minimum rating" or "minimum hours read" and
  shows only the books that qualify.
- Ratings display as stars, e.g. **** - for a 4-star book.
- Statistics shows total books, average rating, and total hours read.

## What's different from Version A (for the report)

- Split into model / service / ui packages instead of one class.
- Every input is validated with a retry loop instead of crashing on bad input.
- Extra features: single-book detail view, filter by rating or hours,
  search by title, two sort views, delete confirmation, and a statistics screen.
- Formatted table output (fixed-width columns, with row numbers where relevant)
  instead of a raw pipe-separated line.
- Removing a book is bounds-checked and confirmed, rather than able to crash
  the whole program on a bad index.
- Books are still in-memory only (lost on exit) - same intentional trade-off as Version A.
