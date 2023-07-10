import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
public class MembershipManagment {
    final private Scanner reader = new Scanner(System.in);

    private int getIntInput() {
        int choice = 0;
        while (choice == 0) {
            try {
                choice = reader.nextInt();
                if (choice == 0) throw new InputMismatchException();
                reader.nextLine();
            } catch (InputMismatchException e) {
                reader.nextLine();
                System.out.println("\nERROR: INVALID INPUT. Please try again: ");
            }
        }
        return choice;
    }

    private void printClubOptions() {
        System.out.println("\n1. Club Mercury");
        System.out.println("\n2. Club Neptune");
        System.out.println("\n3. Club Jupiter");
        System.out.println("\n4. Multi Clubs");
    }

    public int getChoice() {
        int choice;

        System.out.println("\nWelcome!");
        System.out.println("==========");
        System.out.println("1. Add member");
        System.out.println("2. Remove member");
        System.out.println("3. Display member information");
        System.out.println("Please select an option (or Enter -1 to quit): ");
        choice = getIntInput();
        return choice;
    }

    public String addMember(LinkedList<Member> m) {
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal;

        System.out.println("Please enter the member's name: ");
        name = reader.nextLine();
        printClubOptions();

        System.out.println("\nPlease enter the member's clubID: ");
        club = getIntInput();

        while (club < 1 || club > 4) {
            System.out.println("\nInvalid Club ID. Please try again: ");
            club = getIntInput();
        }

        if (m.size() > 0) {
            memberID = m.getLast().getMemberID() + 1;
        } else memberID = 1;

        if (club != 4) {
            cal = (n) -> {
                switch(n) {
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    default:
                        return -1;
                }
            };
            fees = cal.calculateFees(club);
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            m.add(mbr);
            mem = mbr.toString();

            System.out.println("\nSTATUS: Single club member added\n");
        }
        else {
            cal = (n) -> {
                if (n == 4) return 1200;
                else return -1;
            };
            fees = cal.calculateFees(club);
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            m.add(mbr);
            mem = mbr.toString();

            System.out.println("\nSTATUS: Multi club member added");
        }
        return mem;
    }

    public void removeMember(LinkedList<Member> m) {
        int memberID;

        System.out.println("\nEnter Member ID to remove: ");
        memberID = getIntInput();

        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getMemberID() == memberID) {
                m.remove(i);
                System.out.println("\nMember removed\n");
                return;
            }
        }
        System.out.println("\nMemer ID not found\n");
    }

    public void printMemberInfo(LinkedList<Member> m) {
        int memberID;

        System.out.println("\nEnter Member ID to display information: ");
        memberID = getIntInput();

        for(int i = 0; i < m.size(); i++) {
            if (m.get(i).getMemberID() == memberID) {
                String[] memberInfo = m.get(i).toString().split(", ");
                System.out.println("\n\nMember type = " + memberInfo[0]);
                System.out.println("\n\nMember ID = " + memberInfo[1]);
                System.out.println("\n\nMember name = " + memberInfo[2]);
                System.out.println("\n\nMember fees = " + memberInfo[3]);

                if (memberInfo[0].equals("S")) {
                    System.out.println("Club ID = " + memberInfo[4]);
                } else {
                    System.out.println("Membership points = " + memberInfo[4]);
                }
                return;
            }
        }
        System.out.println("\nMember ID not found\n");
    }
}
