package ui;

import form.NewLoanForm;
import form.ReturnLoanForm;
import service.LoanService;

import java.awt.*;

public class LoanMenu extends MenuBar {
    private LoanService loanService;
    private Userinterface userinterface;

    public LoanMenu(LoanService loanService, Userinterface userinterface){
        this.loanService = loanService;//sparar db koppling kopplat till instansen.
        this.userinterface = userinterface;//sparar referensen till ui och metoder blir tillgängligt.
        panel.setBackground(new Color(25,118,210));
        buildMenu();//bygger upp knapparna och deras metoder.
    }

    @Override
    protected void buildMenu(){
        //Bygger upp knappar med deras namn och vad som ska hända om man klickar.
        addButton("Alla lån", () -> userinterface.createTable(this.loanService.getAllLoans()));
        addButton("Aktiva lån", () -> userinterface.createTable(loanService.getAllActiveLoans()));
        addButton("Skapa lån", () -> userinterface.createInputWindow(new NewLoanForm(loanService)));
        addButton("Lämna tillbaka bok", () -> userinterface.createInputWindow(new ReturnLoanForm(loanService)));
        addButton("Förläng valt lån", () -> userinterface.createInputWindow(new ReturnLoanForm(loanService)));

    }
}
