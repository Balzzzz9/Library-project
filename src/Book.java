//Book.java
public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private String ISBN;
    private boolean availability;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.availability = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailablilty(boolean availability) {
        this.availability = availability;
    }

    public void checkout() {
        if (availability) {
            availability = false; // Mark as checked out
        }
    }

    public void returnBook() {
        availability = true; // Mark as available
    }

    @Override
    public int compareTo(Book other) {
        return this.ISBN.compareTo(other.ISBN); // Compare based on ISBN for uniqueness
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + ISBN + ", Available: " + availability;
    }
}