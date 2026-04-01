import javax.swing.*;
import java.awt.*;
import java.util.Date;

//String firstName, String lastName, Member.MemberStatus status, Member.MembershipType membership_type, Date membership_date, String email
public class NewMemberForm implements InputForm {
    private MemberService memberService;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField memberStatus;
    private JTextField membershipType;
    private JTextField membershipDate;
    private JTextField email;

    public NewMemberForm(MemberService memberService){this.memberService = memberService;}


    @Override
    public String getTitle() {return  "Skapa ny medlem";}

    @Override
    public JPanel buildForm(){
        JPanel panel = new JPanel(new GridLayout(0,2));
        firstName = new JTextField();
        lastName = new JTextField();
        email = new JTextField();
        panel.add(new JLabel("Ange Förnamn")); panel.add(firstName);
        panel.add(new JLabel("Ange Efternamn")); panel.add(lastName);
        panel.add(new JLabel("Ange email")); panel.add(email);
        return panel;
    }
    @Override
    public void sendForm(){
        try{
            String first_name = firstName.getText();
            String last_name = lastName.getText();
            String m_email = email.getText();
            //Sätt defualt när man skapar ny medlem till active och standard
            Member.MemberStatus m_status = Member.MemberStatus.ACTIVE;
            Member.MembershipType m_type = Member.MembershipType.STANDARD;
            //Hämta ett nytt datum objekt och tid just nu
            Date m_date = new Date(new java.util.Date().getTime());
            memberService.createMember(first_name, last_name, m_status,m_type,m_date,m_email);
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Fel på input, id måste vara ett nummer.");
        }
    }

}
