import domain.Book;
import jdbc.JdbcHelper;
import jdbc.examples.JdbcStatementExamples;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Book book;
        List<Book> books;

        try (
                Connection connection = JdbcHelper.getConnection()
        ) {
            JdbcStatementExamples examples = new JdbcStatementExamples(connection);

            book = examples.findBookById(1L);
            displayResult("Book of id 1", book);

            books = examples.findAllBooks();
            displayResult("All books", books);

            books = examples.findBooksByParametrizedPageCount(150);
            displayResult("Books with more than 100 pages", books);

            book = examples.createNewBook();
            displayResult("Newly created book", book);

            books = examples.findAllBooks();
            displayResult("All books", books);

            book = examples.updateExistingBook(book);
            displayResult("Updated book", book);

            books = examples.findAllBooks();
            displayResult("All books", books);

            examples.deleteBook(book.getId());
            System.out.println("Deleting book " + book.getId());

            books = examples.findAllBooks();
            displayResult("All books", books);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void displayResult(String label, Book book) {
        System.out.println(label);
        System.out.println(book);
        System.out.println("\n");
    }

    private static void displayResult(String label, List<Book> books) {
        System.out.println(label);
        books.forEach(System.out::println);
        System.out.println("\n");
    }

}
