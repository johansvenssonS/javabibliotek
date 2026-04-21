package ui;

import form.*;
import service.BookService;

import javax.swing.*;
import java.awt.*;

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
        addButton("Lägg till bok", () -> userinterface.createInputWindow(new AddBokForm(bookService)));
        addButton("Redigera bok", () -> userinterface.createInputWindow(new UpdateBookForm(bookService, userinterface)));
        addButton("Radera bok", () -> userinterface.createInputWindow(new RemoveBookForm(bookService, userinterface)));
        textField = new JTextField("Sök bok...",20);
        //appenchild till Jpanel panel som är ett attribut kopplat till ui.MenuBar
        panel.add(textField);
        addButton("Sök bok", () -> userinterface.createTable(bookService.searchBooks(textField.getText())));
        addButton("Filtrera Böker",() -> userinterface.createInputWindow(new FilterForm(bookService, userinterface)));
        addButton("X", () -> userinterface.clearTable());
        addButton("Alla författare", () -> userinterface.createTable(bookService.getAllAuthors()));
        addButton("Lägg till författare", () -> userinterface.createInputWindow(new AddAuthorForm(bookService)));
        addButton("Redigera författare", () -> userinterface.createInputWindow(new UpdateAuthorForm(bookService, userinterface)));

    }
}
