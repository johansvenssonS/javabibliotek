import java.util.Date;

public class Loan implements TableRow {
    private int id;
    private int bookId;
    private int memberId;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private String status;

    public Loan(int id, int bookId, Date returnDate, String status, Date dueDate, Date loanDate, int memberId) {
        this.id = id;
        this.bookId = bookId;
        this.returnDate = returnDate;
        this.status = status;
        this.dueDate = dueDate;
        this.loanDate = loanDate;
        this.memberId = memberId;
    }

    public Loan(int bookId, int memberId, Date loanDate, Date dueDate, String status) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", memberId=" + memberId +
                ", status='" + status + '\'' +
                '}';
    }
    @Override
    public  String[] getColumnNamn(){
        return new String[]{"Lån-id", "Bok-id", "Member-id",
                "Utlåningsdatum", "Inlämingsdatum", "Inlämnatdatum", "Status"};
    }
    @Override
    public Object[] toRow(){
        return new Object[]{
                getId(), getBookId(),getMemberId(),getLoanDate(),
                getDueDate(),getReturnDate(),getStatus()
        };
    }
}
