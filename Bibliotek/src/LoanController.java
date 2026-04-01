import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class LoanController {
    LoanService loanService = new LoanService();

    Scanner scanner = new Scanner(System.in);


    public void showLoanMenu() {

        boolean active = true;

        while (active) {
            System.out.println("----Loan menu----");
            System.out.println("1. Show all Loans");
            System.out.println("2. Show active Loans");
            System.out.println("3. Create a Loan");
            System.out.println("4. Return a book");
            System.out.println("5. See if loan isActive");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    ArrayList<Loan> loans = loanService.getAllLoans();
                    for (Loan l : loans) {
                        System.out.println(l.toString());
                    }
                    break;
                }

                case 2: {
                    ArrayList<Loan> loans = loanService.getAllActiveLoans();
                    for (Loan l : loans) {
                        System.out.println(l.toString());
                    }
                    break;
                    }

                case 3:{
                    scanner.nextLine();
                    System.out.println("Ange bookid");
                    int bookId = scanner.nextInt();
                    System.out.println("Ange medlemsId:");
                    int memberId = scanner.nextInt();
                    String status = "active";
                    /// Funkar bara med rätt värde på rätt plats i konstruktorn
                    /// Hämta standard enum värdena och sätt dom
                    int nyttId = loanService.createNewLoan(
                            bookId,
                            memberId,
                            status
                    );
                    System.out.println("Nytt Lån skapades med ID:"+ nyttId);
                    break;
                }


                case 4: {
                    scanner.nextLine();
                    System.out.println("Ange LånId");
                    int loanId = scanner.nextInt();
                    loanService.returnBook(loanId);

                }

                case 5: {
                    scanner.nextLine();
                    System.out.println("Ange LånId kolla om de är aktivt!");
                    int loanId = scanner.nextInt();
                    if (loanService.isActiveLoan(loanId)){
                        System.out.println("LånId:"+loanId+" är aktivt!");
                    }else {
                        System.out.println("LånId:"+loanId+" är inte aktivt!");
                    }
                }
                case 6:

                case 0:
                    active = false;
                    break;
            }
        }
    }
}

