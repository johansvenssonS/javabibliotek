//databas interaktion

import java.sql.*;
import java.util.ArrayList;

public class BookRepository extends Repository {
    public ArrayList<Book> getAllBooks(){

        ArrayList<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT b.title, b.year_published, b.available_copies, bd.summary, bd.language, bd.page_count, a.first_name, a.last_name FROM books b\n" +
                    " JOIN book_descriptions bd ON b.id=bd.book_id\n" +
                    " JOIN book_authors ba ON b.id=ba.book_id\n" +
                    " JOIN authors a ON a.id=ba.author_id");

            while (rs.next()) {
                 String title = rs.getString("title");
                 int yearPublished = rs.getInt("year_published");
                 int availableCopies = rs.getInt("available_copies");
                 String summary = rs.getString("summary");
                 String language = rs.getString("language");
                 int pageCount = rs.getInt("page_count");
                 String author = rs.getString("last_name")+ ","+ rs.getString("first_name");

                 Book book = new Book(title,yearPublished,availableCopies,summary,language,pageCount,author);
                 books.add(book);

            }
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return books;
    }

    public  ArrayList<Book> getAvailableBooks(){
        ArrayList<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT b.title, b.year_published, b.available_copies, bd.summary, bd.language, bd.page_count, a.first_name, a.last_name FROM books b\n" +
                    " JOIN book_descriptions bd ON b.id=bd.book_id\n" +
                    " JOIN book_authors ba ON b.id=ba.book_id\n" +
                    " JOIN authors a ON a.id=ba.author_id\n" +
                    "WHERE available_copies > 0");

            while (rs.next()) {
                String title = rs.getString("title");
                int yearPublished = rs.getInt("year_published");
                int availableCopies = rs.getInt("available_copies");
                String summary = rs.getString("summary");
                String language = rs.getString("language");
                int pageCount = rs.getInt("page_count");
                String author = rs.getString("last_name")+ ","+ rs.getString("first_name");

                Book book = new Book(title,yearPublished,availableCopies,summary,language,pageCount,author);
                books.add(book);

            }
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return books;

    }
    public  ArrayList<Book> searchBooks(String searchTerm){
        ArrayList<Book> books = new ArrayList<>();
        //Skapa prepered statment för ?
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT b.title, b.year_published, b.available_copies, bd.summary, bd.language, bd.page_count, a.first_name, a.last_name FROM books b\n" +
                " JOIN book_descriptions bd ON b.id=bd.book_id\n" +
                " JOIN book_authors ba ON b.id=ba.book_id\n" +
                " JOIN authors a ON a.id=ba.author_id\n" +
                "WHERE b.title LIKE ?")){;
            // här ersätter vi första förekomsten av ? med LIKE operator ""%" +searchTerm +"%"
            //med % framför o efter så tar den alla med den förekomesten i titeln
            // searchTerm% början på termen, %searchTerm slutet på termen.
            stmt.setString(1, searchTerm +"%");
            //Kör den ersatta strängen som queery
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                int yearPublished = rs.getInt("year_published");
                int availableCopies = rs.getInt("available_copies");
                String summary = rs.getString("summary");
                String language = rs.getString("language");
                int pageCount = rs.getInt("page_count");
                String author = rs.getString("last_name")+ ","+ rs.getString("first_name");

                Book book = new Book(title,yearPublished,availableCopies,summary,language,pageCount,author);
                books.add(book);

            }
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return books;

    }
}
