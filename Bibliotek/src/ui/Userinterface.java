package ui;

import form.InputForm;
import form.LoginForm;
import form.NewLoanForm;
import form.UserPage;
import model.*;
import service.BookService;
import service.LoanService;
import service.MemberService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

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
    private InputForm LoginForm;
    private boolean isLoggedIn ;
    private int userId;

    public Userinterface() {
        //Skapa instans av service lager, db-koppling
        isLoggedIn = false;
        bookService = new BookService();
        loanService = new LoanService();
        memberService = new MemberService();
        init();

    }

    private void init(){
        // Skapa login form,om formuläret returnerar true, skapa program.
        if(createLoginWindow()){
            //skapa programfönster med titel.
            frame = new JFrame("Biblioteksystem");
            //sätt dimensioner på programfönster.
            frame.setSize(1000, 1000);
            //delas in i zooner tydligen, norr,center ,south osv.
            frame.setLayout(new BorderLayout()); // layout för swing
            //Bygger topmenyn med knapparna.
            createTopBody();
            //appenda till programfönster(appendchild?)
            //NORTh lägger elementet högst upp ^
            frame.add(topPanel, BorderLayout.NORTH);
            frame.setVisible(true);
        }

    }
    public void createTopBody(){
        //Skapa en instans av bookmenu som ärver av menubar.
        //Skicka med this, alltså instansen av ui.Userinterface.
        BookMenu bookMenu = new BookMenu(bookService, this);
        LoanMenu loanMenu = new LoanMenu(loanService, this);
        MemberMenu memberMenu = new MemberMenu(memberService, this);
        topPanel = new JPanel();
        //Boxlayout som stapplar vertikalt i y-led
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JButton logoutBtn = new JButton("Logga ut");
        logoutBtn.addActionListener(e ->{
            userId = 0;
            isLoggedIn = false;
            frame.dispose();
            init();
        });
        topPanel.add(logoutBtn);
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
        popUp.setSize(800, 800);
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
    public boolean createLoginWindow(){
        LoginForm loginform = new LoginForm(memberService);

        JDialog popUp = new JDialog((Frame) null , loginform.getTitle(), true);
        popUp.setSize(800, 800);
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(loginform.buildForm());

        JButton submitBtn = new JButton("Logga in");
        submitBtn.addActionListener(e -> {
            int result = loginform.sendForm();
            if (result != 0){
                isLoggedIn = true;
                userId = result;
                popUp.dispose();
                System.out.println("Inloggning lyckades!med result som blev:"+ result);
            } else {
                JOptionPane.showMessageDialog(popUp,"Lyckades inte logga in result blev:"+ result);
                isLoggedIn = false;
            }

        });
        content.add(submitBtn);
        popUp.add(content);
        popUp.setVisible(true);
        return isLoggedIn ;

    }
    public boolean createUserWindow(){
        UserPage userPage = new UserPage(loanService, memberService, userId);
        JDialog popUp = new JDialog((Frame) null , userPage.getTitle(), true);
        popUp.setSize(800, 800);
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(userPage.buildForm());
        popUp.add(content);
        popUp.setVisible(true);
        return true;



    }
    public int selectedTableId(){
        int selectedRow = table.getSelectedRow();
        //System.out.println(selectedRow);
        try{
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Markera ett fält först!" );

            }
            /// skum syntax, men getvalueat vill ha object, så antar man overridear de med (int)
            int selectedId = (int) tableModel.getValueAt(selectedRow, 0);

            Loan loan = loanService.getLoanById(selectedId);

            JDialog popUp = new JDialog((Frame) null , "Lån Hantering", true);
            popUp.setSize(400, 400);
            JPanel content = new JPanel();
            JButton confirmExtension = new JButton("Förläng valt Lån");
            confirmExtension.addActionListener(e ->{
                if (loanService.extendLoan(selectedId)){
                    JOptionPane.showMessageDialog(frame,"Lån med LånId:"+ selectedId +" Förlängt med 14 dagar!");
                    popUp.dispose();
                /// onödigt egentligen eftersom man markerar något som ska finnas
                }else {
                    JOptionPane.showMessageDialog(frame,"Något blev fel när LånId:"+ selectedId +" Skulle bli förlängt!");
                }
            });
            content.add(new JLabel(loan.toString()));
            content.add(confirmExtension);
            popUp.add(content);
            popUp.setVisible(true);
            ///JOptionPane.showMessageDialog(frame, loan.toString(), "Låndetaljer", JOptionPane.QUESTION_MESSAGE);;
            /// test med lånid 18 inlämingsdatum -2024-03-09 borde bli nytt till 2024-03-23
            /// Funkar

            return selectedId;
        } catch ( Exception e){
            JOptionPane.showMessageDialog(frame, "Du har inte markerat ett lån " + e);
            return 0;
        }

    }
    public BookDTO selectedBook() {
        int selectedRow = table.getSelectedRow();
        //System.out.println(selectedRow);
        try {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Markera ett fält först!");
                return null;
            }
            String selectedBook = (String) tableModel.getValueAt(selectedRow, 0);
            int bookId = bookService.getBookIdByTitle(selectedBook);
            System.out.println(bookId);
            return bookService.getBook(bookId);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Du har inte markerat ett lån " + e);
        }
        return null;
    }
    public Author selectedAuthor() {
        int selectedRow = table.getSelectedRow();
        //System.out.println(selectedRow);
        try {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Markera ett fält först!");
                return null;
            }
            String selectedFirstName = (String) tableModel.getValueAt(selectedRow, 0);
            String selectedLastName = (String) tableModel.getValueAt(selectedRow, 1);
            java.sql.Date selectedBirthDate = (java.sql.Date) tableModel.getValueAt(selectedRow, 3);


            System.out.println(selectedFirstName);
            System.out.println(selectedLastName);
            System.out.println(selectedBirthDate);

            return bookService.getAuthor(selectedFirstName,selectedLastName, selectedBirthDate);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Du har inte markerat ett lån " + e);
        }
        return null;
    }

}
