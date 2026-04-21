package form;

import service.BookService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.sql.Date;

public class AddAuthorForm implements InputForm{
    private BookService bookService;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField nationality;
    private JTextField birthDate;

    public AddAuthorForm(BookService bookService){
        this.bookService = bookService;
    }

    @Override
    public String getTitle(){ return "Lägg till ny författare";}

    @Override
    public JPanel buildForm(){
        JPanel panel = new JPanel(new GridLayout(0,2));
        firstName = new JTextField();
        lastName = new JTextField();
        nationality = new JTextField();
        birthDate = new JTextField("ÅÅÅÅ-MM-DD");
        panel.add(new JLabel("Ange förnamn")); panel.add(firstName);
        panel.add(new JLabel("Ange efternamn")); panel.add(lastName);
        panel.add(new JLabel("Ange ursprung")); panel.add(nationality);
        panel.add(new JLabel("Ange födelsedatum")); panel.add(birthDate);
        return panel;
    }
    @Override
    public boolean sendForm(){
        try{
            String first_name = firstName.getText();
            String last_name = lastName.getText();
            String nation = nationality.getText();
            LocalDate localDate = LocalDate.parse(birthDate.getText());
            java.sql.Date birth = Date.valueOf(localDate);

            bookService.createNewAuthor(first_name,last_name,nation,birth);
            JOptionPane.showMessageDialog(null, "Författare: "+ first_name+", "+ last_name + "tillagd");
            return true;
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Fel på input, Bok lades ej till.");
            return false;
        }
    }
}
