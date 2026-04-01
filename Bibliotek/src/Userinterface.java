
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
//klass för att skapa ett enkelt ui i java
public class Userinterface {
    private JFrame frame;
    private JPanel topPanel;
    private JScrollPane tabelParent;
    private JTable table;
    private BookService bookService;
    private LoanService loanService;
    private MemberService memberService;
    private DefaultTableModel tableModel;


    public Userinterface(){
        //Skapa instans av service lager, db-koppling
        bookService = new BookService();
        loanService = new LoanService();
        memberService = new MemberService();
        //skapa programfönster med titel.
        frame = new JFrame("Biblioteksystem");
        //sätt dimensioner på programfönster.
        frame.setSize(500, 500);
        //delas in i zooner tydligen, norr,center ,south osv.
        frame.setLayout(new BorderLayout()); // layout för swing
        //Bygger topmenyn med knapparna.
        createTopBody();
        //appenda till programfönster(appendchild?)
        //NORTh lägger elementet högst upp ^
        frame.add(topPanel, BorderLayout.NORTH);
        //createTable(frame);

        frame.setVisible(true);

    }
    public void createTopBody(){
        //Skapa en instans av bookmenu som ärver av menubar.
        //Skicka med this, alltså instansen av Userinterface.
        BookMenu bookMenu = new BookMenu(bookService, this);
        LoanMenu loanMenu = new LoanMenu(loanService, this);
        MemberMenu memberMenu = new MemberMenu(memberService, this);
        topPanel = new JPanel();
        //Boxlayout som stapplar vertikalt i y-led
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(bookMenu.getPanel());
        topPanel.add((loanMenu.getPanel()));
        topPanel.add((memberMenu.getPanel()));
    }

    public void rerender(){
        //säger till layouten att räkna om dimensioner antar jag
        frame.revalidate();
        // ritar om det visuella refreshar?
        frame.repaint();
    }
    public void clearTable(){
        if (tableModel != null){
            tableModel.setRowCount(0);
        }

    }
    //Generell metod för att skapa tabell
    //tar emot arraylist ifrån med ett interface som styr vad som efterfrågas.
    public void createTable(ArrayList<?extends TableRow> rows){
        //Hämta specifika kolumnnamn ifrån books,members,loans
        String[] columnNames = rows.get(0).getColumnNamn();
        //bygger ny tabell default model, med satta rubriker på kolumner
        tableModel = new DefaultTableModel(columnNames, 0);
        //lägger till en rad i tabellen
        for(TableRow row : rows){
            tableModel.addRow(row.toRow());
        }
        if(tabelParent != null){
            frame.remove(tabelParent);
        }

        table = new JTable(tableModel);
        table.setBackground(Color.GRAY);
        tabelParent = new JScrollPane(table);
        frame.add(tabelParent, BorderLayout.CENTER);
        rerender();

    }
    //generell metod för att skapa input fönster
    public void createInputWindow(InputForm form){
        JDialog popUp = new JDialog(frame, form.getTitle(), true);
        popUp.setSize(400, 300);
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(form.buildForm());

        JButton submitBtn = new JButton("Skicka");
        submitBtn.addActionListener(e -> {
            form.sendForm();
            popUp.dispose();
        });
        content.add(submitBtn);
        popUp.add(content);
        popUp.setVisible(true);

    }

}
