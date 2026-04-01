import javax.swing.*;
import java.awt.*;

public class NewLoanForm implements InputForm{
    private LoanService loanService;
    private JTextField bookId;
    private JTextField memberId;
    private JTextField status;

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
        status = new JTextField();
        panel.add(new JLabel("Ange Bok-ID")); panel.add(bookId);
        panel.add(new JLabel("Ange Member-ID")); panel.add(memberId);
        panel.add(new JLabel("Ange Status")); panel.add(status);
        return panel;
    }
    @Override
    public void sendForm(){
        try{
            int book_id = Integer.parseInt(bookId.getText());
            int member_id = Integer.parseInt(memberId.getText());
            String status_now = status.getText();
            loanService.createNewLoan(book_id,member_id,status_now);
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Fel på input, id måste vara ett nummer.");
        }
    }
}
