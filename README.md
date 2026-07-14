# Personal Bookshelf Manager

A command-line Java application for tracking books you've read - title, author, hours spent reading, and a 1-5 star rating. Built twice as part of an assignment on AI-assisted programming: once by hand (traditional approach) and once with AI assistance, so the two can be compared directly.

## Assignment context

This repository was built for the assignment **"Analyzing the Role of AI-Assisted Programming in Modern Software Development"** (Advanced Programming Concepts module). It contains two independent implementations of the same problem, used to compare traditional vs. AI-assisted development in terms of development time, code quality, error handling, and maintainability.

## Project structure

```
Personal-Bookshelf-Manager/
  book-cli-traditional/     - Version A: written without AI assistance
  book-cli-ai-assisted/     - Version B: built with AI assistance
  README.md                 - this file
```

Each subfolder has its own README with detailed setup and run instructions.

## Version A - Traditional

A single-class console application: one `Book` data class, one `BookShelfApp` class handling the menu, input, storage, and display together. No input validation, no framework, in-memory storage only.

**Run it:**
```
cd book-cli-traditional/src
javac Book.java BookShelfApp.java
java BookShelfApp
```

See [`book-cli-traditional/README.md`](book-cli-traditional/README.md) for full details.

## Version B - AI-assisted

A layered console application split into `model`, `service`, and `ui` packages. Includes input validation with retry loops, search by title, sorting by rating or hours read, a statistics view, and star-style rating display.

**Run it:**
```
cd book-cli-ai-assisted/src
javac com/example/bookshelf/model/Book.java com/example/bookshelf/service/BookManager.java com/example/bookshelf/ui/ConsoleUI.java com/example/bookshelf/Main.java
java com.example.bookshelf.Main
```

See [`book-cli-ai-assisted/README.md`](book-cli-ai-assisted/README.md) for full details.

## Requirements

- Java JDK 17 or newer for both versions
- No external dependencies or build tools required - plain `javac`/`java`, or open either folder directly in an IDE such as IntelliJ

## Comparing the two versions

| Aspect | Version A (traditional) | Version B (AI-assisted) |
|---|---|---|
| Structure | One class, no separation of concerns | Layered: model / service / ui |
| Input validation | None - invalid input crashes the program | Retry-until-valid loops on every field |
| Features | Add book, view all books | Add, view, search by title, sort by rating/hours, statistics |
| Output | Raw pipe-separated line | Formatted fixed-width table with star ratings |
| Storage | In-memory only (lost on exit) | In-memory only (lost on exit) - same trade-off |

## Author

Muditha Sankalpa
