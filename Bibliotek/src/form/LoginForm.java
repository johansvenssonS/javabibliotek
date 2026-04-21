package form;

import service.MemberService;

import javax.swing.*;
import java.awt.*;

public class LoginForm {
    private MemberService memberservice;
    private JTextField memberEmail;

    public LoginForm(MemberService memberservice){
        this.memberservice = memberservice;
    }


    public String getTitle(){ return "Logga in";}


    public JPanel buildForm(){
        JPanel panel = new JPanel(new GridLayout(0,1));
        memberEmail = new JTextField("aiden.smith43@email.com");
        panel.add(new JLabel("EmailAdress")); panel.add(memberEmail);
        return panel;
    }

    public int sendForm(){
        try{
            String member_email = memberEmail.getText();
             return memberservice.memberLogin(member_email);
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Kunde inte hitta användarnamnet i databasen!.");
            return 0;
        }
    }
}
