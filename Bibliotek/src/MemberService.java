import java.util.ArrayList;
import java.util.Date;

public class MemberService {
    MemberRepository memberRepository = new MemberRepository();
    public ArrayList <Member> getAllMembers(){
        ArrayList<Member> members = memberRepository.getAllMembers();
        return members;
    }
    public Member searchMember(String searchTerm){
        Member member = memberRepository.searchMember(searchTerm);
        return member;
    }
    public int createMember(String firstName, String lastName, Member.MemberStatus status, Member.MembershipType membership_type, Date membership_date, String email){
        return memberRepository.createMember(firstName, lastName, status, membership_type, membership_date, email);

    }
}
