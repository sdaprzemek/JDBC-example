package jdbc.examples;

import domain.Book;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcStatementExamples {

    private Connection connection;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_NUMBER_OF_PAGES = "number_of_pages";
    private static final String COLUMN_HAS_HARD_COVER = "has_hard_cover";
    private static final String COLUMN_RELEASE_DATE = "release_date";

    private static final String FIND_BY_ID = "SELECT * FROM books WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM books";
    private static final String FIND_BY_PAGE_COUNT_GREATER_OR_EQUAL = "SELECT * FROM books WHERE number_of_pages >= ?";
    private static final String INSERT = "INSERT INTO books (title, number_of_pages, has_hard_cover, release_date) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE books SET title = ?, number_of_pages = ?, has_hard_cover = ?, release_date = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM books WHERE id = ?";

    public JdbcStatementExamples(Connection connection) {
        this.connection = connection;
    }

    public Book findBookById(Long id) {
        Book result = null;
        try (
                PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = getBookFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Book> findAllBooks() {
        List<Book> result = new ArrayList<>();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(FIND_ALL)
        ) {
            while (resultSet.next()) {
                result.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public List<Book> findBooksByParametrizedPageCount(Integer pageCount) {
        List<Book> result = new ArrayList<>();
        try (
                PreparedStatement statement = connection.prepareStatement(FIND_BY_PAGE_COUNT_GREATER_OR_EQUAL)
        ) {
            statement.setInt(1, pageCount);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(getBookFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Book createNewBook() {
        Book book = new Book();
        book.setTitle("New book");
        book.setHasHardCover(false);
        book.setNumberOfPages(15);
        book.setReleaseDate(LocalDate.of(2020, 1, 1));

        try (
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getNumberOfPages());
            statement.setBoolean(3, book.getHasHardCover());
            statement.setDate(4, Date.valueOf(book.getReleaseDate()));
            statement.executeUpdate();
            ResultSet generatedIds = statement.getGeneratedKeys();
            if (generatedIds.next()) {
                book.setId(generatedIds.getLong(1));
            } else {
                throw new RuntimeException("Nie wygenerowano ID dla nowo stworzonej książki");
            }
            generatedIds.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public Book updateExistingBook(Book book) {
        book.setReleaseDate(LocalDate.now());

        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE)
        ) {
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getNumberOfPages());
            statement.setBoolean(3, book.getHasHardCover());
            statement.setDate(4, Date.valueOf(book.getReleaseDate()));
            statement.setLong(5, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public void deleteBook(Long bookId) {
        try (
                PreparedStatement statement = connection.prepareStatement(DELETE)
        ) {
            statement.setLong(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong(COLUMN_ID));
        book.setTitle(resultSet.getString(COLUMN_TITLE));
        book.setHasHardCover(resultSet.getBoolean(COLUMN_HAS_HARD_COVER));
        book.setNumberOfPages(resultSet.getInt(COLUMN_NUMBER_OF_PAGES));
        book.setReleaseDate(resultSet.getDate(COLUMN_RELEASE_DATE).toLocalDate());
        return book;
    }

}
