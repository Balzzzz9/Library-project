// BalancedBinarySearchTree
import java.util.ArrayList;
import java.util.List;

public class BalancedBinarySearchTree {
    private TreeNode root;

    public BalancedBinarySearchTree() {
        this.root = null;
    }

    public void insert(Book book) {
        root = insertRecursively(root, book);
    }

    private TreeNode insertRecursively(TreeNode node, Book book) {
        if (node == null) {
            return new TreeNode(book);
        }
        if (book.getISBN().compareTo(node.book.getISBN()) < 0) {
            node.left = insertRecursively(node.left, book);
        } else if (book.getISBN().compareTo(node.book.getISBN()) > 0) {
            node.right = insertRecursively(node.right, book);
        }
        return node;
    }

    public List<Book> inOrderTraversal() {
        return inOrderRecursively(root);
    }

    private List<Book> inOrderRecursively(TreeNode node) {
        List<Book> books = new ArrayList<>();
        if (node != null) {
            books.addAll(inOrderRecursively(node.left));
            books.add(node.book);
            books.addAll(inOrderRecursively(node.right));
        }
        return books;
    }

    public boolean checkoutBook(String isbn) {
        Book bookToCheckout = new Book("", "", isbn); // Create a dummy book to search by ISBN
        TreeNode node = findBook(root, bookToCheckout); // Find the book in the tree
    
        if (node != null && node.book.isAvailable()) {
            node.book.checkout(); // Mark it as checked out
            return true; // Checkout successful
        }
        
        return false; // Checkout unsuccessful (book not found or not available)
    }
    
    private TreeNode findBook(TreeNode node, Book book) {
        if (node == null) {
            return null; // Book not found
        }
    
        int cmp = book.getISBN().compareTo(node.book.getISBN());
    
        if (cmp < 0) {
            return findBook(node.left, book); // Search in left subtree
        } else if (cmp > 0) {
            return findBook(node.right, book); // Search in right subtree
        } else {
            return node; // Book found
        }
    }

    public void remove(Book book) {
        root = removeRecursively(root, book);
    }
    
    private TreeNode removeRecursively(TreeNode node, Book book) {
        if (node == null) {
            return node; // Base case: book not found
        }
        
        // Compare based on ISBN
        if (book.getISBN().compareTo(node.book.getISBN()) < 0) {
            node.left = removeRecursively(node.left, book);
        } else if (book.getISBN().compareTo(node.book.getISBN()) > 0) {
            node.right = removeRecursively(node.right, book);
        } else { // Node to be deleted found
            if (node.left == null) return node.right; // Node with only right child or no child
            else if (node.right == null) return node.left; // Node with only left child
    
            // Node with two children: Get the inorder successor (smallest in the right subtree)
            TreeNode successor = minValueNode(node.right);
            node.book = successor.book; // Copy the inorder successor's content to this node
            node.right = removeRecursively(node.right, successor.book); // Remove the inorder successor
        }
        return node;
    }
    
    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left; // Find the leftmost leaf in the right subtree
        }
        return current;
    }
}