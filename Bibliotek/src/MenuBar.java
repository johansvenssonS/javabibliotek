import javax.swing.*;
import java.awt.*;
/// Menybar som ska återanvändas i book,loan,member osv
public abstract class MenuBar {
    //Panel som menybaren ska va i.
    protected JPanel panel;
    //Konstruktor för menybar.
    public MenuBar(){
        panel = new JPanel();

    }
    //Abstrakt klass som book,loan,member måste implementera.
    protected abstract void buildMenu();
    //metod för att skapa knappar med metod. samt lägga till knapp till panel.
    protected  JButton addButton(String btn_name, Runnable metod){
        JButton btn = new JButton(btn_name);
        btn.addActionListener(e -> metod.run());
        panel.add(btn);
        // kan i framtiden vara bra att returnera om vi ska komma åt knappen.
        return btn;
    }
    //hjälp metod för att hämta panelen
    public JPanel getPanel(){
        return panel;
    }
}
