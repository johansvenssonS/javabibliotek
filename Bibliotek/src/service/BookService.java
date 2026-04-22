package service;

import model.Author;
import model.Book;
import model.BookDTO;
import model.CreateBookDTO;
import repository.BookRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class BookService {
    BookRepository bookRepository = new BookRepository();
    public ArrayList <Book> getAllBooks(){
        ArrayList<Book> books = bookRepository.getAllBooks();
        return books;
    }
    public ArrayList <Author> getAllAuthors(){
        ArrayList<Author> authors = bookRepository.getAllAuthors();
        return authors;
    }
    public Author getAuthor(String firstName, String lastName, Date birthDate){
        return bookRepository.getAuthor(firstName, lastName, birthDate);
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
    public void createNewBook(CreateBookDTO cbDTO){
        bookRepository.createFullBook(cbDTO);
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
    public void updateAuthor(int id ,String firstName, String lastName, String nationality, Date birthDate){
        bookRepository.updateAuthor( id,firstName,lastName,nationality,birthDate);
    }


    public void removeBook(int id){
        bookRepository.removeBook(id);
    }
    public void createNewAuthor(String firstName, String lastName, String nationality, Date birthDate){
        bookRepository.createNewAuthor(firstName,lastName,nationality,birthDate);
    }
}
