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
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel header = new JPanel(new GridLayout(2,1));
        JLabel welcomeMsg = new JLabel("Välkommen "+ member.getFirstname()+" "+  member.getLastName());
        welcomeMsg.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel loanRubrik = new JLabel("Dina lån");
        loanRubrik.setFont(new Font("Arial", Font.BOLD, 12));

        header.add(welcomeMsg);
        header.add(loanRubrik);
        mainPanel.add(header, BorderLayout.NORTH);
        ///System.out.println(loans);
        JScrollPane table = createTable(loans);
        mainPanel.add(table, BorderLayout.CENTER);
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

    public boolean sendForm(){
        return true;
    }



}
