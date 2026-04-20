package form;

import model.Loan;
import model.Member;
import model.TableRow;
import service.LoanService;
import service.MemberService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UserPage {
    private LoanService loanService;
    private MemberService memberService;
    final int userId;
    private Member member ;
    private ArrayList <Loan> loans;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane tabelParent;
    private JPanel mainPanel;


    public UserPage(LoanService loanService, MemberService memberService, int userId){
        this.loanService = loanService;
        this.memberService = memberService;
        this.userId = userId;
        this.member = memberService.getMemberById(userId);
        this.loans = loanService.getMembersLoans(userId);

    }


    public String getTitle(){
        return "Mina sidor";
    }


    public JPanel buildForm(){
        mainPanel = new JPanel(new BorderLayout());
        JPanel content = new JPanel();
        //stapla i y-led
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JPanel header = new JPanel(new GridLayout(2,1));

        JLabel welcomeMsg = new JLabel("Välkommen "+ member.getFirstname()+" "+  member.getLastName());
        welcomeMsg.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel loanRubrik = new JLabel("Dina lån");
        loanRubrik.setFont(new Font("Arial", Font.BOLD, 12));


        //html formatering fungerar tydligen i swing?
        JLabel profileInfo = new JLabel(
                "<html>" +
                "Namn: "+ member.getFirstname() + " " + member.getLastName() + "<br>"+
                "Email "+ member.getEmail() + "<br>"+
                "Du blev medlem :"+ member.getMembership_date() + "<br>" +
                "Medlemstyp :" + member.getMembership_type() + "<br>"+
                " Status :"+ member.getStatus()+
                "</html>"
                );


        header.add(welcomeMsg);
        header.add(loanRubrik);
        mainPanel.add(header, BorderLayout.NORTH);

        ///System.out.println(loans);
        JScrollPane table = createTable(loans);
        // kontrollera hur stor tabellen får vara
        table.setMaximumSize(new Dimension(Integer.MAX_VALUE, loans.size() * 30 + 50));
        content.add(table);
        content.add(profileInfo);
        mainPanel.add(content, BorderLayout.CENTER);
        JPanel btnSection = createButtons();
        mainPanel.add(btnSection, BorderLayout.SOUTH);

        return mainPanel;
    }

    public JScrollPane createTable(ArrayList<?extends TableRow> rows){
        String[] columnNames = rows.get(0).getColumnNamn();
        //bygger ny tabell default model, med satta rubriker på kolumner
        tableModel = new DefaultTableModel(columnNames, 0);
        //lägger till en rad i tabellen
        for(TableRow row : rows){
            tableModel.addRow(row.toRow());
        }
        table = new JTable(tableModel);
        table.setBackground(Color.GRAY);
        tabelParent = new JScrollPane(table);
        return tabelParent;

    }

    public JPanel createButtons(){
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));

        JButton firstNameBtn = new JButton("Redigera Förnamn");
        JButton lastNameBtn = new JButton("Redigera Efternamn");
        JButton emailBtn = new JButton("Redigera Email");
        firstNameBtn.addActionListener(e-> createInputField("Förnamn"));
        lastNameBtn.addActionListener(e-> createInputField("Efternamn"));
        emailBtn.addActionListener(e-> createInputField("Email"));
        btnPanel.add(firstNameBtn);
        btnPanel.add(lastNameBtn);
        btnPanel.add(emailBtn);
        // switch för enum värdena
        switch (member.getStatus()){
            //om suspended eller exipired skapa knapp
            case SUSPENDED, EXPIRED ->{
                JButton activateBtn = new JButton("Aktivera medlemskap");
                activateBtn.addActionListener(e-> createInputField("Medlemskap"));
                btnPanel.add(activateBtn);
            }
        }
        return btnPanel;
    }

    public void createInputField(String reason){
        //stökigt när frame inte är med, här hämtar vi fönstret mainpanel och använder de för att skapa en modal/popup.
        Window window = SwingUtilities.getWindowAncestor(mainPanel);
        JDialog popUp = new JDialog(window, "Redigera "+reason, Dialog.ModalityType.APPLICATION_MODAL);
        popUp.setSize(400, 400);
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JTextField userInput = new JTextField("Nytt " +reason);
        content.add(userInput);

        JButton submitBtn = new JButton("Uppdatera "+reason);
        submitBtn.addActionListener(e -> {
            switch (reason){
                case "Förnamn":
                    String firstName = userInput.getText();
                    memberService.updateFirstName(firstName,userId );
                    popUp.dispose();
                    break;
                case "Efternamn":
                    String lastName = userInput.getText();
                    memberService.updateLastName(lastName,userId);
                    popUp.dispose();
                    break;
                case "Email":
                    String email = userInput.getText();
                    memberService.updateEmail(email,userId);
                    popUp.dispose();
                    break;
                case "Medlemskap":
                    memberService.updateMembership(userId);
                    popUp.dispose();
                    break;



            }
            popUp.dispose();
        });
        content.add(submitBtn);
        popUp.add(content);
        popUp.setVisible(true);
    }
}
