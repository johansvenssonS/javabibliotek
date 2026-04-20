package service;

import model.Member;
import repository.MemberRepository;

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
    public int memberLogin(String member_email){
        return memberRepository.login(member_email);
    }
    public Member getMemberById(int userId){
        return memberRepository.getMemberById(userId);
    }
    public void updateFirstName(String firstName, int id){
        memberRepository.updateFirstName(firstName,id);
    }
    public void updateLastName(String lastName, int id){
        memberRepository.updateLastName(lastName, id);
    }
    public void updateEmail(String email, int id){
        memberRepository.updateEmail(email, id);
    }
    public void updateMembership(int id){
        memberRepository.updateMembership(id);
    }
}
