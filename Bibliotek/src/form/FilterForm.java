package form;

import service.BookService;
import ui.Userinterface;

import javax.swing.*;
import java.awt.*;

public class FilterForm implements InputForm{
    private Userinterface userinterface;
    private BookService bookService;


    public FilterForm(BookService bookService, Userinterface userinterface){
        this.bookService = bookService;
        this.userinterface = userinterface;
    }

    @Override
    public String getTitle() {
        return "Filtrera Böcker";
    }

    @Override
    public JPanel buildForm() {
        JPanel panel = new JPanel(new GridLayout(0,1));
        JButton fiction = new JButton("Fiktion");
        fiction.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Fiction")));

        JButton real = new JButton("Verklighetsbaserat");
        real.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Non-Fiction")));


        JButton scifi = new JButton("Sci-Fi");
        scifi.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Science Fiction")));


        JButton fantasty = new JButton("Fantasty");
        fantasty.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Fantasy")));


        JButton mystik = new JButton("Mystik");
        mystik.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Mystery")));


        JButton thriller = new JButton("Thriller");
        thriller.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Thriller")));


        JButton romantik = new JButton("Romantik");
        romantik.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Romance")));


        JButton historia = new JButton("Historia");
        historia.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Historical Fiction")));


        JButton biografi = new JButton("Biografi");
        biografi.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Biography")));


        JButton självhjälp = new JButton("Självhjälp");
        självhjälp.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Self-Help")));


        JButton skräck = new JButton("Skräck");
        skräck.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Horror")));

        JButton äventyr = new JButton("Äventyr");
        äventyr.addActionListener(e -> userinterface.createTable(bookService.getBooksByCategory("Adventure")));





        panel.add(fiction);
        panel.add(real);
        panel.add(scifi);
        panel.add(fantasty);
        panel.add(mystik);
        panel.add(thriller);
        panel.add(romantik);
        panel.add(historia);
        panel.add(biografi);
        panel.add(självhjälp);
        panel.add(skräck);
        panel.add(äventyr);
        return panel;
    }

    @Override
    public boolean sendForm() {
        System.out.println("inne i sendform i filterform.");
        return false;
    }
}
