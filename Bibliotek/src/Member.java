import java.util.Date;

public class Member implements TableRow {
    /// Enum
    public enum MembershipType {
        STANDARD,
        PREMIUM

    }
    /// Enum
    public enum MemberStatus {
        ACTIVE,
        SUSPENDED,
        EXPIRED

    }
    /// memberegenskaper
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Date membership_date;
    private MembershipType membership_type;
    private MemberStatus status;

    /// Konstruktor för allt förutom id, (VID INSERTS, Id är AUTO incremental i db)
    public Member(String firstName, String lastName, MemberStatus status, MembershipType membership_type, Date membership_date, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.membership_type = membership_type;
        this.membership_date = membership_date;
        this.email = email;
    }
    ///  Konstruktor för allt, när man vill ha all info ifrån db
    public Member(int id, String firstName, String lastName, String email, Date membership_date, MembershipType membership_type, MemberStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.membership_date = membership_date;
        this.membership_type = membership_type;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public MemberStatus getStatus() {
        return status;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public Date getMembership_date() {
        return membership_date;
    }

    public MembershipType getMembership_type() {
        return membership_type;
    }

    public String getLastName() {
        return lastName;
    }
    public void setMembership_type(MembershipType membership_type) {
        this.membership_type = membership_type;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                '}';
    }
    @Override
    public  String[] getColumnNamn(){
        return new String[]{"ID", "Förnamn", "Efternamn",
                 "Email","Medlemsdatum", "Medlemstyp", "Status"};
    }
    @Override
    public Object[] toRow() {
        return new Object[]{
                getId(), getFirstname(), getLastName(),getEmail(), getMembership_date(),
                getMembership_type(), getStatus()
        };
    }
}
