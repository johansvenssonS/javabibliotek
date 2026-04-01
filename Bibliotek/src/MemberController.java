import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MemberController {

        MemberService memberService = new MemberService();

        Scanner scanner = new Scanner(System.in);


        public void showMemberMenu(){

            boolean active = true;

            while(active){
                System.out.println("----Member menu----");
                System.out.println("1. Show all Members");
                System.out.println("2. Search by Email");
                System.out.println("3. Create new Member");
                System.out.println("0. Exit");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        ArrayList<Member> members = memberService.getAllMembers();
                        for(Member m : members){
                            System.out.println(m.toString());
                        }
                        break;

                    case 2:
                        /// Töm scanner?
                        scanner.nextLine();
                        System.out.println("Sök efter användare med mail..");
                        String searchTerm = scanner.nextLine();
                        Member member = memberService.searchMember(searchTerm);
                        System.out.println(member.toString());
                        break;

                    case 3:
                        scanner.nextLine();
                        System.out.println("First name:");
                        String firstName = scanner.nextLine();
                        System.out.println("Last name:");
                        String lastName = scanner.nextLine();
                        System.out.println("Email:");
                        String email = scanner.nextLine();
                        Date membershipDate = new Date();
                        /// Funkar bara med rätt värde på rätt plats i konstruktorn
                        /// Hämta standard enum värdena och sätt dom
                        int nyttId = memberService.createMember(
                            firstName,
                            lastName,
                            Member.MemberStatus.ACTIVE,
                            Member.MembershipType.STANDARD,
                            membershipDate,
                            email
                        );
                        System.out.println("Ny medlem skapades med ID:"+ nyttId);
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

