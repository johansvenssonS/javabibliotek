import java.util.ArrayList;

public class BookService {
    BookRepository bookRepository = new BookRepository();
    public ArrayList <Book> getAllBooks(){
        ArrayList<Book> books = bookRepository.getAllBooks();
        return books;
    }
    public ArrayList <Book> getAvailableBooks(){
        ArrayList<Book> books = bookRepository.getAvailableBooks();
        return books;
    }
    public ArrayList <Book> searchBooks(String searchTerm){
        ArrayList<Book> books = bookRepository.searchBooks(searchTerm);
        return books;
    }
}
