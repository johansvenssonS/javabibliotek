package form;

import service.MemberService;

import javax.swing.*;
import java.awt.*;

public class LoginForm implements InputForm {
    private MemberService memberservice;
    private JTextField memberEmail;

    public LoginForm(MemberService memberservice){
        this.memberservice = memberservice;
    }

    @Override
    public String getTitle(){ return "Logga in";}

    @Override
    public JPanel buildForm(){
        JPanel panel = new JPanel(new GridLayout(0,1));
        memberEmail = new JTextField();
        panel.add(new JLabel("EmailAdress")); panel.add(memberEmail);
        return panel;
    }
    @Override
    public void sendForm(){
        try{
            String member_email = memberEmail.getText();
            memberservice.memberLogin(member_email);
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Kunde inte hitta användarnamnet i databasen!.");
        }
    }
}
