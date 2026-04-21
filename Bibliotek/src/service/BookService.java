package service;

import model.Book;
import model.BookDTO;
import repository.BookRepository;

import java.util.ArrayList;
import java.sql.Date;

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
    public ArrayList<Book> getBooksByCategory(String category){
        ArrayList<Book> books = bookRepository.getBooksByCategory(category);
        return books;
    }
    public boolean createNewBook(String title, String isbn, int year_published, int
            total_copies, int available_copies){
        return bookRepository.createNewBook(title,isbn,year_published,total_copies, available_copies);
    }
    public BookDTO getBook(int id){
        return bookRepository.getBook(id);
    }
    public int getBookIdByTitle(String title){
        return bookRepository.getBookIdByTitle(title);
    }

    public void updateBook(String title, String isnb, int yP, int tC, int aC, int bookID){
        bookRepository.updateBook(bookID,title,isnb,yP,tC,aC);
    }
    public void removeBook(int id){
        bookRepository.removeBook(id);
    }
    public void createNewAuthor(String firstName, String lastName, String nationality, Date birthDate){
        bookRepository.createNewAuthor(firstName,lastName,nationality,birthDate);
    }
}
