
//Library.java
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<String, Book> booksTable;
    private List<Patron> patrons;
    private BalancedBinarySearchTree bookCollection;

    public Library() {
        booksTable = new HashMap<>();
        patrons = new ArrayList<>();
        bookCollection = new BalancedBinarySearchTree();
    }

    public void addBook(Book book) {
        if (!booksTable.containsKey(book.getISBN())) {
            booksTable.put(book.getISBN(), book);
            bookCollection.insert(book);
        }
    }

    public void removeBook(String ISBN) {
        if (booksTable.containsKey(ISBN)) {
            Book book = booksTable.get(ISBN);
            bookCollection.remove(book);
            booksTable.remove(ISBN);
        }
    }

    public void addPatron(String name, String patronId) {
        Patron patron = new Patron(name, patronId);
        patrons.add(patron);
        System.out.println("Patron added: " + patron);
    }

    public void checkoutBook(String patronId, String ISBN) {
        Patron patron = findPatronById(patronId);
        if (patron == null) {
            System.out.println("Patron not found.");
            return;
        }

        if (!booksTable.containsKey(ISBN)) {
            System.out.println("Book not found.");
            return;
        }

        Book book = booksTable.get(ISBN);
        if (!book.isAvailable()) {
            System.out.println("Book is currently checked out.");
            return;
        }

        patron.checkoutBook(book);
        System.out.println("Book checked out successfully.");
    }

    public void returnBook(String patronId, String ISBN) {
        if (booksTable.containsKey(ISBN)) {
            Book book = booksTable.get(ISBN);
            Patron patron = findPatronById(patronId);
            if (patron != null) {
                patron.returnBook(book);
            }
        }
    }

    public boolean lookupBook(String ISBN) {
        return booksTable.containsKey(ISBN);
    }

    public List<Book> getBooksSorted() {
        return bookCollection.inOrderTraversal();
    }

    private Patron findPatronById(String patronId) {
        for (Patron patron : patrons) {
            if (patron.patronId.equals(patronId)) {
                return patron;
            }
        }
        return null;
    }

    // Added a search method for Final Project
    public List<Book> searchBooks(String query) {
        List<Book> results = new ArrayList<>();
        
        
        for (Book book : booksTable.values()) {
            // Check if the title, author, or ISBN matches
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) || 
                book.getAuthor().toLowerCase().contains(query.toLowerCase()) || 
                book.getISBN().toLowerCase().contains(query.toLowerCase())) {
                results.add(book);
            }
        }
        
        return results; 
    }

    // Added statistics method for final project
    public String getStatistics() {
        int numberOfBooks = booksTable.size();
        int numberOfPatrons = patrons.size();
        
        return "Number of books: " + numberOfBooks + "\n" +
               "Number of patrons: " + numberOfPatrons;
    }
}
