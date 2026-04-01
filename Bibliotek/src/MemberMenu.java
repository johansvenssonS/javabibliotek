import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MemberMenu extends MenuBar {
    private JTextField textField;
    private MemberService memberService;
    private Userinterface userinterface;

    public MemberMenu(MemberService memberService, Userinterface userinterface){
        this.memberService = memberService;//sparar db koppling kopplat till instansen.
        this.userinterface = userinterface;//sparar referensen till ui och metoder blir tillgängligt.
        panel.setBackground(new Color(2,136,209));
        buildMenu();//bygger upp knapparna och deras metoder.
    }

    @Override
    protected void buildMenu(){
        addButton("Alla medlemar", () -> userinterface.createTable(memberService.getAllMembers()));
        //text fält för att söka efter medlem
        textField = new JTextField("Sök medlem...",20);
        //appenchild till Jpanel panel som är ett attribut kopplat till MenuBar
        panel.add(textField);
        //createtable i ui tar bara emot arraylist
        //searchmember i repo returnar endast 1 member objekt(email-unique)
        addButton("Sök medlem", () -> {
            Member member = memberService.searchMember(textField.getText());
            //bygga ny arraylista och lägga till matching medlem
            ArrayList <Member> userArray = new ArrayList<>();
            if(member != null ){
                userArray.add(member);
            }//ful lösning
            userinterface.createTable(userArray);
        });
        addButton("Skapa ny medlem", () -> userinterface.createInputWindow(new NewMemberForm(memberService)));

        //Bygger upp knappar med deras namn och vad som ska hända om man klickar.
        //addButton("Alla böcker", () -> userinterface.createTable(bookService.getAllBooks()));
        //addButton("X", () -> userinterface.clearTable());
    }
}
