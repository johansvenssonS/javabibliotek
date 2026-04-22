package form;

import model.CreateBookDTO;
import service.BookService;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;

public class AddBokForm implements InputForm{
    private BookService bookService;
    private JTextField bookTitle;
    private JTextField bookIsbn;
    private JTextField yearPublished;
    private JTextField totalCopies;
    private JTextField availableCopies;
    private JTextField summary;
    private JTextField language;
    private JTextField pageCount;
    private JTextField authorFirstName;
    private JTextField authorLastName;
    private JTextField authorBirthDate;
    private JTextField bookCategory;

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
        summary = new JTextField();
        language = new JTextField();
        pageCount = new JTextField();
        authorFirstName = new JTextField();
        authorLastName = new JTextField();
        authorBirthDate = new JTextField();
        bookCategory = new JTextField();
        JLabel bookDescr = new JLabel("Bok Information:");
        bookDescr.setFont(new Font("Arial",Font.BOLD, 14));
        JLabel authorDescr = new JLabel("Författare Information:");
        authorDescr.setFont(new Font("Arial",Font.BOLD, 14));
        JLabel category = new JLabel("Kategory Information:");
        category.setFont(new Font("Arial",Font.BOLD, 14));
        panel.add(new JLabel("Ange Bok titel")); panel.add(bookTitle);
        panel.add(new JLabel("Ange Bok isbn")); panel.add(bookIsbn);
        panel.add(new JLabel("Ange Utgivningsår")); panel.add(yearPublished);
        panel.add(new JLabel("Ange antal kopior")); panel.add(totalCopies);
        panel.add(new JLabel("Ange antal tillgängliga kopior")); panel.add(availableCopies);
        panel.add(bookDescr);
        panel.add(new JLabel("__________________________"));//bara tom för att ta upp plats i kolummnen.
        panel.add(new JLabel("Ange Sammanfattning")); panel.add(summary);
        panel.add(new JLabel("Ange Språk")); panel.add(language);
        panel.add(new JLabel("Ange antal sidor")); panel.add(pageCount);
        panel.add(authorDescr);
        panel.add(new JLabel("__________________________"));//bara tom för att ta upp plats i kolummnen.
        panel.add(new JLabel("Författare förnamn:")); panel.add(authorFirstName);
        panel.add(new JLabel("Författare efternamn")); panel.add(authorLastName);
        panel.add(new JLabel("Författare födelsedatum")); panel.add(authorBirthDate);
        panel.add(category);
        panel.add(new JLabel("__________________________"));//bara tom för att ta upp plats i kolummnen.
        panel.add(new JLabel("Bokens kategori")); panel.add(bookCategory);






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

            String sum = summary.getText();
            String lang = language.getText();
            int pageC = Integer.parseInt(pageCount.getText());

            String aFirst = authorFirstName.getText();
            String aLast = authorLastName.getText();
            LocalDate localDate = LocalDate.parse(authorBirthDate.getText());
            java.sql.Date birth = Date.valueOf(localDate);


            String bCategory = bookCategory.getText();

            CreateBookDTO createBookDTO = new CreateBookDTO(title,isbn,yB,tC,aC,sum,lang,pageC,aFirst,aLast,birth,bCategory);

            bookService.createNewBook(createBookDTO);

            JOptionPane.showMessageDialog(null, "Bok med titel: "+ title + "tillagd i bibliotek");
            return true;
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Fel på input, Bok lades ej till.");
            return false;
        }
    }
}
