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
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(44,62,80));
        panel.setBorder(BorderFactory.createEmptyBorder(40,50,40,50));

        JLabel rubrik = new JLabel("Bibliotek");
        rubrik.setFont(new Font("Segoe UI", Font.BOLD,24));
        rubrik.setForeground(Color.WHITE);
        rubrik.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel underrubrik = new JLabel("Logga in!");
        underrubrik.setFont(new Font("Segoe UI", Font.PLAIN,13));
        underrubrik.setForeground(new Color(189,195,199));
        underrubrik.setAlignmentX(Component.CENTER_ALIGNMENT);



        memberEmail = new JTextField("aiden.smith43@email.com");
        memberEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        memberEmail.setFont(new Font("Segoe UI", Font.PLAIN,13));
        memberEmail.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 152,219)),
                BorderFactory.createEmptyBorder(5,10,5,10)
        ));
        panel.add(rubrik);
        panel.add(Box.createVerticalStrut(5));
        panel.add(underrubrik);
        panel.add(Box.createVerticalStrut(30));
        panel.add(new JLabel("Email"){{
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 12));

        }});
        panel.add(Box.createVerticalStrut(5));
        panel.add(memberEmail);
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
