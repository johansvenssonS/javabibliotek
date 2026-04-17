package service;

import model.Loan;
import repository.LoanRepository;

import java.util.ArrayList;

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
    public Loan getLoanById(int loanId){
        return loanRepository.getLoanById(loanId);
    }
    public boolean extendLoan(int loanId){
        return loanRepository.extendLoan(loanId);
    }
    public ArrayList <Loan> getMembersLoans(int memberId){
        return loanRepository.getMembersLoans(memberId);
    }
}
