package form;


import model.Author;
import service.BookService;
import ui.Userinterface;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;

public class UpdateAuthorForm implements InputForm{
    private BookService bookService;
    private Userinterface userinterface;
    private Author author;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField nationality;
    private JTextField birthDate;

    public UpdateAuthorForm(BookService bookService, Userinterface userinterface){
        this.bookService = bookService;
        this.userinterface = userinterface;
        this.author = userinterface.selectedAuthor();
        if(this.author ==null){
            throw new IllegalStateException("Ingen författare är markerad!");
        }
    }

    @Override
    public String getTitle(){ return "Uppdatera författare";}

    @Override
    public JPanel buildForm(){
        JPanel panel = new JPanel(new GridLayout(0,2));
        firstName = new JTextField(author.getFirstName());
        lastName = new JTextField(author.getLastName());
        nationality = new JTextField(author.getNationality());
        birthDate = new JTextField(String.valueOf(author.getBirthdDate()));

        panel.add(new JLabel("Ange Förnamn")); panel.add(firstName);
        panel.add(new JLabel("Ange Efternamn")); panel.add(lastName);
        panel.add(new JLabel("Ange Ursprung")); panel.add(nationality);
        panel.add(new JLabel("Ange Födelsedatum")); panel.add(birthDate);
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

            bookService.updateAuthor(author.getId(),first_name,last_name,nation,birth);
            System.out.println("hej");
            JOptionPane.showMessageDialog(null, "Bok med titel: "+ first_name + "är uppdaterad");
            return true;
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Fel på input, Bok uppdaterades inte.");
            return false;
        }
    }
}
