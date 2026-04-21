package repository;//databas interaktion

import model.Book;
import model.BookDTO;

import java.sql.*;
import java.util.ArrayList;
/// Klass för hantera db gällande bok entiteten.
/// ärver ifrån repository där vi sätter env variabler.
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
    /// Metod för att hitta tillgängliga böcker.
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
    /// Metod för söka bok
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
    public int getBookIdByTitle(String title){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT b.id  FROM books b\n" +
                     "WHERE b.title LIKE ?")){;
            // här ersätter vi första förekomsten av ? med LIKE operator ""%" +searchTerm +"%"
            //med % framför o efter så tar den alla med den förekomesten i titeln
            // searchTerm% början på termen, %searchTerm slutet på termen.
            stmt.setString(1, title +"%");
            //Kör den ersatta strängen som queery
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("id");

                return bookId;
            }
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return 0;
    }

    public BookDTO getBook(int id){
        //Skapa prepered statment för ?
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT b.title,b.isbn, b.year_published,b.total_copies, b.available_copies FROM books b\n" +
                "WHERE b.id = ?")){;
            // här ersätter vi första förekomsten av ? med LIKE operator ""%" +searchTerm +"%"
            //med % framför o efter så tar den alla med den förekomesten i titeln
            // searchTerm% början på termen, %searchTerm slutet på termen.
            stmt.setInt(1, id);
            //Kör den ersatta strängen som queery
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String isbn = rs.getString("isbn");
                int yearPublished = rs.getInt("year_published");
                int total_copies = rs.getInt("total_copies");
                int availableCopies = rs.getInt("available_copies");
                return new BookDTO(title,id,isbn,yearPublished,total_copies,availableCopies);
            }
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return null;

    }
    /// metod för att söka bok per kategori
    public  ArrayList<Book> getBooksByCategory(String category){
        ArrayList<Book> books = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             /// optimera för för detta ? dto? många onödiga fält
             PreparedStatement stmt = conn.prepareStatement("""
            SELECT b.title, b.year_published, b.available_copies, bd.summary, bd.language, bd.page_count, a.first_name, a.last_name , c.name FROM books b
            JOIN book_descriptions bd ON b.id=bd.book_id
            JOIN book_authors ba ON b.id=ba.book_id
            JOIN authors a ON a.id=ba.author_id
            JOIN book_categories bc ON b.id = bc.book_id
            JOIN categories c ON bc.category_id = c.id
            WHERE c.name = ?
            """)){
            // här kommer input ifrån knappen man tryck på.
            stmt.setString(1, category);
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
    public boolean createNewBook(String title, String isbn, int year_published, int total_copies, int available_copies){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO books (title, isbn, year_published, total_copies, available_copies) \n"+
                             "VALUES(?,?,?,?,?)",
                     Statement.RETURN_GENERATED_KEYS)) {;

///ACTIVE till actives
            stmt.setString(1, title);
            stmt.setString(2, isbn);
            stmt.setInt(3, year_published);
            stmt.setInt(4, total_copies);
            stmt.setInt(5, available_copies);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()){
                int newBookID = generatedKeys.getInt(1);
                System.out.println("Bok med"+title + " Har blivit tillagd och fått id: "+ newBookID );
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return false;
    }
    public void updateBook(int book_id,String title, String isbn, int year_published, int total_copies, int available_copies){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
            "UPDATE  books SET title=?, isbn=?, year_published=?, total_copies=?, available_copies=? \n"+
             "WHERE id = ? ")){

            stmt.setString(1, title);
            stmt.setString(2, isbn);
            stmt.setInt(3, year_published);
            stmt.setInt(4, total_copies);
            stmt.setInt(5, available_copies);
            stmt.setInt(6, book_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }
    public void removeBook(int id){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM books WHERE id= ?")){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }
    public void createNewAuthor(String firstName, String lastName, String nationality, Date birthDate){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO authors (first_name, last_name, nationality, birth_date) \n"+
                             "VALUES(?,?,?,?)",
                     Statement.RETURN_GENERATED_KEYS)) {;

///ACTIVE till actives
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, nationality);
            stmt.setDate(4, birthDate);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()){
                int newAuthorID = generatedKeys.getInt(1);
                System.out.println("Författare: " + firstName + "," + lastName + "tillagd och fått id: " +newAuthorID );
            }

        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }

}
