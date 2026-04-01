import javax.swing.*;
import java.awt.*;

public class ReturnLoanForm implements InputForm {
        private LoanService loanService;
        private JTextField loanId;

        public ReturnLoanForm(LoanService loanService){
            this.loanService = loanService;
        }

        @Override
        public String getTitle(){ return "Lämna tillbaka Bok";}

        @Override
        public JPanel buildForm(){
            JPanel panel = new JPanel(new GridLayout(0,2));
            loanId = new JTextField();
            panel.add(new JLabel("Ange Lån-ID")); panel.add(loanId);
            return panel;
        }
        @Override
        public void sendForm(){
            try{
                int loan_id = Integer.parseInt(loanId.getText());
                loanService.returnBook(loan_id);
            } catch (NumberFormatException e ){
                JOptionPane.showMessageDialog(null, "Fel på input, id måste vara ett nummer.");
            }
        }
    }


