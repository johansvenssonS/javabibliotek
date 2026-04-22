package repository;//databas interaktion

import model.Author;
import model.Book;
import model.BookDTO;
import model.CreateBookDTO;

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
    public ArrayList<Author> getAllAuthors(){

        ArrayList<Author> authors = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(
                    "SELECT a.id , a.first_name, a.last_name, a.nationality, a.birth_date FROM authors a");

            while (rs.next()) {
                int authorId = rs.getInt("id");
                 String firstName = rs.getString("first_name");
                 String lastName = rs.getString("last_name");
                 String natioanlity = rs.getString("nationality");
                 Date birthDate = rs.getDate("birth_date");
                 Author author = new Author(authorId,firstName,birthDate,natioanlity,lastName);
                 authors.add(author);

            }
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return authors;
    }
    public Author getAuthor(String firstName, String lastName, Date birthDate){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
            "SELECT a.id, a.first_name, a.last_name, a.nationality, a.birth_date \n" +
                " FROM authors a \n"+
                "WHERE a.first_name = ? AND a.last_name = ? AND a.birth_date = ?")){

            stmt.setString(1,firstName);
            stmt.setString(2,lastName);
            stmt.setDate(3,birthDate);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int authorId = rs.getInt("id");
                String natioanlity = rs.getString("nationality");
                return new Author(authorId,firstName,birthDate,natioanlity,lastName);


            }
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return null;

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
            stmt.setInt(1, id);
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
    public void updateAuthor(int id,String firstName, String lastName, String nationality, java.sql.Date birthDate){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE  authors SET first_name=?, last_name=?, nationality=?, birth_date=?\n"+
                             "WHERE id = ? ")){

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, nationality);
            stmt.setDate(4, birthDate);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }

    public void createFullBook(CreateBookDTO dto){
        try(Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)){
            conn.setAutoCommit(false);
            try{
                int bookId = insertBook(conn,dto);
                insertDescription(conn, dto, bookId);
                int authorId = getAuthorId(conn,dto);
                insertBookAuthor(conn,bookId, authorId);
                insertBookCategory(conn,bookId,dto);
                conn.commit();
            } catch (SQLException e){
                conn.rollback();
                System.out.println("Något gick fel i stegen, rullar tbx" + e.getMessage());
            }
        }catch (SQLException e ){
            System.out.println("Fel: " + e.getMessage());
        }
    }



    //private metod för att insert i book, använder transaction connectionen.
    private int insertBook(Connection conn, CreateBookDTO dto){
        try{
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO books (title, isbn, year_published, total_copies, available_copies) \n"+
                             "VALUES(?,?,?,?,?)",
                     Statement.RETURN_GENERATED_KEYS);

///ACTIVE till actives
            stmt.setString(1, dto.getTitle());
            stmt.setString(2, dto.getIsbn());
            stmt.setInt(3, dto.getYearPublished());
            stmt.setInt(4, dto.getTotalCopies());
            stmt.setInt(5, dto.getAvailableCopies());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()){
                int newBookID = generatedKeys.getInt(1);
                System.out.println("Boksteg klart");
                return newBookID;
            }

        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return 0;
    }
    private void insertDescription(Connection conn, CreateBookDTO dto, int bookId){
        try{
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO book_descriptions (book_id, summary, language, page_count) \n"+
                             "VALUES(?,?,?,?)");

            stmt.setInt(1, bookId);
            stmt.setString(2, dto.getSummary());
            stmt.setString(3, dto.getLanguage());
            stmt.setInt(4, dto.getPageCount());
            stmt.executeUpdate();
            System.out.println("insertDescription klart");
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }

    private int getAuthorId(Connection conn, CreateBookDTO dto){
        try{
            PreparedStatement stmt = conn.prepareStatement(" SELECT id FROM authors a \n"+
                    "WHERE (a.first_name = ? AND a.last_name = ? AND a.birth_date =?)");

            stmt.setString(1, dto.getAuthorFirstName());
            stmt.setString(2, dto.getAuthorLastName());
            stmt.setDate(3, dto.getAuthorBirthDate());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                int id = rs.getInt("id");
                System.out.println("getAuthor klart");
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return 0;
    }

    private void insertBookAuthor(Connection conn, int bookId, int authorId){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO book_authors (book_id, author_id) \n"+
                    "VALUES(?,?)");

            stmt.setInt(1, bookId);
            stmt.setInt(2, authorId);//inte 1 :)
            stmt.executeUpdate();
            System.out.println("insertBookauthor klart");

        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }
    private void insertBookCategory(Connection conn, int bookId, CreateBookDTO dto){
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM categories WHERE name = ?");

            stmt.setString(1, dto.getBookCategory());
            ResultSet rs = stmt.executeQuery();

            if(!rs.next()){
                System.out.println("Kategori hittades inte:" + dto.getBookCategory());
                return;
            }
            int categoryId = rs.getInt("id");
            System.out.println("id i  insertbookcategory klart");


            PreparedStatement insert = conn.prepareStatement("INSERT INTO book_categories (book_id, category_id) \n"+
                    "VALUES (?,?)");
            insert.setInt(1,bookId);
            insert.setInt(2, categoryId);
            insert.executeUpdate();
            System.out.println("hela insertbookcategory klart");


        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }
}
