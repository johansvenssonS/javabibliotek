package form;

import service.LoanService;

import javax.swing.*;
import java.awt.*;

public class NewLoanForm implements InputForm {
    private LoanService loanService;
    private JTextField bookId;
    private JTextField memberId;

    public NewLoanForm(LoanService loanService){
        this.loanService = loanService;
    }

    @Override
    public String getTitle(){ return "Skapa nytt lån";}

    @Override
    public JPanel buildForm(){
        JPanel panel = new JPanel(new GridLayout(0,2));
        bookId = new JTextField();
        memberId = new JTextField();
        panel.add(new JLabel("Ange Bok-ID")); panel.add(bookId);
        panel.add(new JLabel("Ange Member-ID")); panel.add(memberId);
        return panel;
    }
    @Override
    public boolean sendForm(){
        try{
            int book_id = Integer.parseInt(bookId.getText());
            int member_id = Integer.parseInt(memberId.getText());
            ///hårdkodat till active för nytt lån status.getText()
            String status_now = "active";
            loanService.createNewLoan(book_id,member_id,status_now);
            return true;
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Fel på input, id måste vara ett nummer.");
            return false;
        }
    }
}
