package form;

import service.BookService;

import javax.swing.*;
import java.awt.*;

public class FilterForm implements InputForm{
    private BookService bookService;
    private JButton categories;
    private JButton language;
    private JButton published;
    private JButton page_count;
    private JButton title;



    public FilterForm(BookService bookService){this.bookService = bookService;}

    @Override
    public String getTitle() {
        return "Filtrera Böcker";
    }

    @Override
    public JPanel buildForm() {
        JPanel panel = new JPanel(new GridLayout(0,1));
        JButton categories = new JButton("Kategorier");
        JButton language = new JButton("Språk");
        JButton published = new JButton("År");
        JButton page_count = new JButton("Antal sidor");
        JButton title = new JButton("Titel");

        panel.add(categories);
        panel.add(language);
        panel.add(published);
        panel.add(page_count);
        panel.add(title);
        return panel;
    }

    @Override
    public boolean sendForm() {
        System.out.println("inne i sendform i filterform.");
        return false;
    }
}
