import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BookMenu extends MenuBar {
    private JTextField textField;
    private BookService bookService;
    private Userinterface userinterface;

    public BookMenu(BookService bookService, Userinterface userinterface){
        this.bookService = bookService;//sparar db koppling kopplat till instansen.
        this.userinterface = userinterface;//sparar referensen till ui och metoder blir tillgängligt.
        panel.setBackground(new Color(21,101,192));
        buildMenu();//bygger upp knapparna och deras metoder.
    }

    @Override
    protected void buildMenu(){
        //text fält för att söka på bok

        //Bygger upp knappar med deras namn och vad som ska hända om man klickar.
        addButton("Tillgängliga böcker", () -> userinterface.createTable(bookService.getAvailableBooks()));
        addButton("Alla böcker", () -> userinterface.createTable(bookService.getAllBooks()));
        textField = new JTextField("Sök bok...",20);
        //appenchild till Jpanel panel som är ett attribut kopplat till MenuBar
        panel.add(textField);
        addButton("Sök bok", () -> userinterface.createTable(bookService.searchBooks(textField.getText())));

        addButton("X", () -> userinterface.clearTable());
    }
}
