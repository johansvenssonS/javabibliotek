package form;

import service.BookService;
import service.LoanService;

import javax.swing.*;
import java.awt.*;

public class AddBokForm implements InputForm{
    private BookService bookService;
    private JTextField bookTitle;
    private JTextField bookIsbn;
    private JTextField yearPublished;
    private JTextField totalCopies;
    private JTextField availableCopies;

    public AddBokForm(BookService bookService){
        this.bookService = bookService;
    }

    @Override
    public String getTitle(){ return "Lägg till ny bok";}

    @Override
    public JPanel buildForm(){
        JPanel panel = new JPanel(new GridLayout(0,2));
        bookTitle = new JTextField();
        bookIsbn = new JTextField();
        yearPublished = new JTextField();
        totalCopies = new JTextField();
        availableCopies = new JTextField();
        panel.add(new JLabel("Ange Bok titel")); panel.add(bookTitle);
        panel.add(new JLabel("Ange Bok isbn")); panel.add(bookIsbn);
        panel.add(new JLabel("Ange Utgivningsår")); panel.add(yearPublished);
        panel.add(new JLabel("Ange antal kopior")); panel.add(totalCopies);
        panel.add(new JLabel("Ange antal tillgängliga kopior")); panel.add(availableCopies);
        return panel;
    }
    @Override
    public boolean sendForm(){
        try{
            String title = bookTitle.getText();
            String isbn = bookIsbn.getText();
            int yB = Integer.parseInt(yearPublished.getText());
            int tC = Integer.parseInt(totalCopies.getText());
            int aC = Integer.parseInt(availableCopies.getText());

            bookService.createNewBook(title,isbn,yB,tC,aC);
            JOptionPane.showMessageDialog(null, "Bok med titel: "+ title + "tillagd i bibliotek");
            return true;
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Fel på input, Bok lades ej till.");
            return false;
        }
    }
}
