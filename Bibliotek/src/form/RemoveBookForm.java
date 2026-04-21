package form;


import model.Book;
import model.BookDTO;
import service.BookService;
import ui.Userinterface;

import javax.swing.*;
import java.awt.*;

public class RemoveBookForm implements InputForm{
    private BookService bookService;
    private Userinterface userinterface;
    private BookDTO book;
    private JLabel bookTitle;
    private JLabel yearPublished;
    private JLabel availableCopies;
    private JLabel bookIsbn;
    private JLabel totalCopies;

    public RemoveBookForm(BookService bookService, Userinterface userinterface){
        this.bookService = bookService;
        this.userinterface = userinterface;
        this.book = userinterface.selectedBook();
    }

    @Override
    public String getTitle(){ return "Radera bok";}

    @Override
    public JPanel buildForm(){
        JPanel panel = new JPanel(new GridLayout(0,1));
        JLabel rubrik = new JLabel("Radera bok med följande:");
        rubrik.setFont(new Font("Arial",Font.BOLD, 14));
        bookTitle = new JLabel("Titel: "+ book.getTitle());
        bookIsbn = new JLabel("Isbn: " + book.getIsbn());
        yearPublished = new JLabel(String.valueOf(book.getYear_published()));
        totalCopies = new JLabel(String.valueOf(book.getTotal_copies()));
        availableCopies = new JLabel(String.valueOf(book.getAvailable_copies()));

        panel.add(rubrik);
        panel.add(bookTitle);
        panel.add(bookIsbn);
        panel.add(yearPublished);
        panel.add(totalCopies);
        panel.add(availableCopies);



        return panel;
    }
    @Override
    public boolean sendForm(){
        try{
            String title = bookTitle.getText();
            bookService.removeBook(book.getBookId());
            System.out.println("hej ifrån removebookform");
            JOptionPane.showMessageDialog(null, "Bok med titel: "+ title + "är bortagen");
            return true;
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Fel på input, Bok togs inte bort.");
            return false;
        }
    }
}
