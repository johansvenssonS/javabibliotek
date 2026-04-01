import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class LoanRepository extends Repository {
    public ArrayList<Loan> getAllLoans(){

        ArrayList<Loan> loans = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM loans");

            while (rs.next()) {
                int id = rs.getInt("id");
                int book_id = rs.getInt("book_id");
                int member_id = rs.getInt("member_id");
                Date loan_date = rs.getDate("loan_date");
                Date due_date = rs.getDate("due_date");
                Date returnDate = rs.getDate("return_date");
                String status = rs.getString("status");

                Loan loan = new Loan(id, book_id, returnDate, status, due_date, loan_date, member_id);
                loans.add(loan);

            }
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return loans;
        }
    public ArrayList<Loan> getAllActiveLoans(){

        ArrayList<Loan> loans = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM loans WHERE status = 'active'");

            while (rs.next()) {
                int id = rs.getInt("id");
                int book_id = rs.getInt("book_id");
                int member_id = rs.getInt("member_id");
                Date loan_date = rs.getDate("loan_date");
                Date due_date = rs.getDate("due_date");
                Date returnDate = rs.getDate("return_date");
                String status = rs.getString("status");

                Loan loan = new Loan(id, book_id, returnDate, status, due_date, loan_date, member_id);
                loans.add(loan);

            }
        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return loans;
        }
    public int createNewLoan(int bookId, int memberId,String status){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO loans (book_id, member_id, loan_date, due_date, status) \n"+
                             "VALUES(?,?,CURDATE(),DATE_ADD(CURDATE(), INTERVAL  14 DAY),?)",
                     Statement.RETURN_GENERATED_KEYS)) {;

///ACTIVE till actives
            stmt.setInt(1, bookId);
            stmt.setInt(2, memberId);
            stmt.setString(3, status);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()){
                return generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return -1;
    }
    public void returnBook(int loanId){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE loans set return_date = CURDATE(), status= 'returned'" +
                     " WHERE id = ?")) {;

            stmt.setInt(1,loanId);
            stmt.executeUpdate();
            System.out.println("Bok för LånId:"+ loanId + "Är återlämnad!");

        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }
    public boolean isActive(int loanId){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT loans.status FROM loans" +
                     "  WHERE id = ?")) {;

            stmt.setInt(1,loanId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return rs.getString("status").equals("active");
            }


        } catch (SQLException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    return false;
    }
}
