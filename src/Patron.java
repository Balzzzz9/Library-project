// File: Patron.java
import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String name;
    public String patronId;
    private List<Book> checkedOutBooks;

    public Patron(String name, String patronId) {
        this.name = name;
        this.patronId = patronId;
        this.checkedOutBooks = new ArrayList<>();
    }

    public void checkoutBook(Book book) {
        checkedOutBooks.add(book);
        book.setAvailablilty(false);
    }

    public void returnBook(Book book) {
        checkedOutBooks.remove(book);
        book.setAvailablilty(true);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID " + patronId + ", Checked Out Books: " + checkedOutBooks;
    }
}