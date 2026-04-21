package form;


import model.Book;
import model.BookDTO;
import service.BookService;
import ui.Userinterface;

import javax.swing.*;
import java.awt.*;

public class UpdateBookForm implements InputForm{
    private BookService bookService;
    private Userinterface userinterface;
    private BookDTO book;
    private JTextField bookTitle;
    private JTextField yearPublished;
    private JTextField availableCopies;
    private JTextField bookIsbn;
    private JTextField totalCopies;

    public UpdateBookForm(BookService bookService, Userinterface userinterface){
        this.bookService = bookService;
        this.userinterface = userinterface;
        this.book = userinterface.selectedBook();
    }

    @Override
    public String getTitle(){ return "Uppdatera bok";}

    @Override
    public JPanel buildForm(){
        JPanel panel = new JPanel(new GridLayout(0,2));
        bookTitle = new JTextField(book.getTitle());
        bookIsbn = new JTextField(book.getIsbn());
        yearPublished = new JTextField(String.valueOf(book.getYear_published()));
        totalCopies = new JTextField(String.valueOf(book.getTotal_copies()));
        availableCopies = new JTextField(String.valueOf(book.getAvailable_copies()));

        panel.add(new JLabel("Ange Bok titel")); panel.add(bookTitle);
        panel.add(new JLabel("Ange isbn")); panel.add(bookIsbn);
        panel.add(new JLabel("Ange Utgivningsår")); panel.add(yearPublished);
        panel.add(new JLabel("Ange totalt antal kopior")); panel.add(totalCopies);
        panel.add(new JLabel("Ange tillgängliga kopior")); panel.add(availableCopies);



        return panel;
    }
    @Override
    public boolean sendForm(){
        try{
            String title = bookTitle.getText();
            String isbn = bookIsbn.getText();
            int yP = Integer.parseInt(yearPublished.getText());
            int tC = Integer.parseInt(totalCopies.getText());
            int aC = Integer.parseInt(availableCopies.getText());

            bookService.updateBook(title,isbn,yP,tC,aC,book.getBookId());
            System.out.println("hej");
            JOptionPane.showMessageDialog(null, "Bok med titel: "+ title + "är uppdaterad");
            return true;
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Fel på input, Bok uppdaterades inte.");
            return false;
        }
    }
}
