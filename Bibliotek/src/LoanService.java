import java.util.ArrayList;
import java.util.Date;

public class LoanService {
    LoanRepository loanRepository = new LoanRepository();

    public ArrayList <Loan> getAllLoans(){
        ArrayList <Loan> loans = loanRepository.getAllLoans();
        return loans;
    }
    public ArrayList <Loan> getAllActiveLoans(){
        ArrayList <Loan> loans = loanRepository.getAllActiveLoans();
        return loans;
    }
    public int createNewLoan(int bookId, int memberId, String status){
        return loanRepository.createNewLoan(bookId, memberId, status);
    }
    public void returnBook(int loanId){
        loanRepository.returnBook(loanId);
    }
    public boolean isActiveLoan(int loanId){
        return loanRepository.isActive(loanId);
    }
}
