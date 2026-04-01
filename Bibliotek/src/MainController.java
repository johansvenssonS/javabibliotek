import java.util.ArrayList;
import java.util.Scanner;

public class MainController {

        Scanner scanner = new Scanner(System.in);


        public void showMainMenu(){

            boolean active = true;

            while(active){
                System.out.println("----BiblioteksSystem740----");
                System.out.println("1. BookSystem");
                System.out.println("2. MemberSystem");
                System.out.println("3. LoanSystem");
                System.out.println("0. Exit");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        BookController bookController = new BookController();
                        bookController.showBookMenu();
                        break;

                    case 2:
                        MemberController memberController = new MemberController();
                        memberController.showMemberMenu();
                        break;

                    case 3:
                        LoanController LoanController = new LoanController();
                        LoanController.showLoanMenu();
                        break;
                    case 4:
                    case 5:
                    case 6:

                    case 0:
                        active = false;
                        break;
                }
            }



        }
    }

