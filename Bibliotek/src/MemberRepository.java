import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class MemberRepository extends Repository {
        public ArrayList<Member> getAllMembers(){

            ArrayList<Member> members = new ArrayList<>();

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {

                ResultSet rs = stmt.executeQuery("SELECT * FROM members");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");
                    String email = rs.getString("email");
                    Date membership_date = rs.getDate("membership_date");
                    /// fastställa enum värdet med valueof
                    ///  toUppercase för att enumvärden är tydlgein bestpractice i versaler.
                    Member.MembershipType membershipType = Member.MembershipType.valueOf(rs.getString("membership_type").toUpperCase()
                    );
                    Member.MemberStatus memberStatus = Member.MemberStatus.valueOf(rs.getString("status").toUpperCase()
                    );
                    Member member = new Member(id,first_name,last_name,email,membership_date,membershipType,memberStatus);
                    members.add(member);

                }
            } catch (SQLException e) {
                System.out.println("Fel: " + e.getMessage());
            }
            return members;
        }
        public Member searchMember(String searchTerm){
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM members m \n"+
                         "WHERE m.email = ?")) {;

                stmt.setString(1, searchTerm);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");
                    String email = rs.getString("email");
                    Date membership_date = rs.getDate("membership_date");
                    /// fastställa enum värdet med valueof
                    ///  toUppercase för att enumvärden är tydlgein bestpractice i versaler.
                    Member.MembershipType membershipType = Member.MembershipType.valueOf(rs.getString("membership_type").toUpperCase()
                    );
                    Member.MemberStatus memberStatus = Member.MemberStatus.valueOf(rs.getString("status").toUpperCase()
                    );
                    return new Member(id,first_name,last_name,email,membership_date,membershipType,memberStatus);


                }
            } catch (SQLException e) {
                System.out.println("Fel: " + e.getMessage());
            }
            return null;
        }
        public int createMember(String firstName, String lastName, Member.MemberStatus status, Member.MembershipType membership_type, Date membership_date, String email){
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO members (first_name, last_name, status, membership_type, membership_date, email) \n"+
                         "VALUES(?,?,?,?,?,?)",
                         Statement.RETURN_GENERATED_KEYS)) {;

///ACTIVE till actives
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, status.name().toLowerCase() );
                stmt.setString(4, membership_type.name().toLowerCase());
                stmt.setDate(5, new java.sql.Date(membership_date.getTime()));
                stmt.setString(6, email);
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
}
