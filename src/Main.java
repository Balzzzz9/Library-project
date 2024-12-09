
//Main.java
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Options:\n"
                    + "1. Add Book\n"
                    + "2. Remove Book\n"
                    + "3. Checkout Book\n"
                    + "4. Return Book\n"
                    + "5. List All Books (Sorted)\n"
                    + "6. Add Patron\n"
                    + "7. Search for a Book\n"
                    + "8. Show Statistics\n"
                    + "9. Exit ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1: // Add book
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    Book book = new Book(title, author, isbn);
                    library.addBook(book);
                    break;
                case 2: // remove book
                    System.out.print("Enter ISBN to remove:");
                    String isbnToRemove = scanner.nextLine();
                    library.removeBook(isbnToRemove);
                    break;
                case 3: // checkout book
                    System.out.print("Enter patron ID number: ");
                    String patronId = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbnToCheckout = scanner.nextLine();
                    library.checkoutBook(patronId, isbnToCheckout);
                    break;
                case 4: // Return book
                    System.out.print("Enter Patron ID: ");
                    String returnPatronId = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbnToReturn = scanner.nextLine();
                    library.returnBook(returnPatronId, isbnToReturn);
                    break;
                case 5: // List All Books (Sorted)
                    List<Book> sortedBooks = library.getBooksSorted();
                    System.out.println("Sorted Book List");
                    for (Book b : sortedBooks) {
                        System.out.println(b);
                    }
                    break;
                case 6: // Add Patron
                    System.out.print("Enter patron name: ");
                    String patronName = scanner.nextLine();
                    System.out.print("Enter patron ID: ");
                    String newPatronId = scanner.nextLine(); 
                    library.addPatron(patronName, newPatronId);
                    break;
                case 7: // Search for a Book
                    System.out.print("Enter a title, author, or ISBN to search: ");
                    String searchQuery = scanner.nextLine();
                    List<Book> foundBooks = library.searchBooks(searchQuery);
                    if (foundBooks.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        System.out.println("Found Books:");
                        for (Book b : foundBooks) {
                            System.out.println(b);
                        }
                    }
                    break;
                case 8: // Show Statistics
                    String statistics = library.getStatistics();
                    System.out.println(statistics);
                    break;
                case 9: // Exit
                    System.out.println("Exiting");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");

            }
        }
    }
}
