import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void bookTicket(Passenger p) {
        TicketBooker booker = new TicketBooker();

        if(TicketBooker.availablewaitingList==0) {
            System.out.println("No Tickets Available");
            return;
        }
        if((p.berthPreference.equals("L") && TicketBooker.availableLowerBerths > 0) ||
           (p.berthPreference.equals("M") && TicketBooker.availableMiddleBerths > 0) ||
           (p.berthPreference.equals("U") && TicketBooker.availableUpperBerths > 0)) {

            System.out.println("Preferred Berth Available");
            if (p.berthPreference.equals("L")) {
                System.out.println("Lower Berth Given");
                booker.bookTicket(p, (TicketBooker.lowerBerthsPositions.get(0)), "L");
                TicketBooker.lowerBerthsPositions.remove(0);
                TicketBooker.availableLowerBerths--;


            } else if (p.berthPreference.equals("M")) {
                System.out.println("Middle Berth Given");
                booker.bookTicket(p, (TicketBooker.middleBerthsPositions.get(0)), "M");
                TicketBooker.middleBerthsPositions.remove(0);
                TicketBooker.availableMiddleBerths--;


            } else if (p.berthPreference.equals("U")) {
                System.out.println("Upper Berth Given");
                booker.bookTicket(p, (TicketBooker.upperBerthsPositions.get(0)), "U");
                TicketBooker.upperBerthsPositions.remove(0);
                TicketBooker.availableUpperBerths--;

            }
        }

            // Preferences Not Available
            else if (TicketBooker.availableLowerBerths > 0) {
                System.out.println("Lower Berth Given");
                booker.bookTicket(p,(TicketBooker.lowerBerthsPositions.get(0)),"L");
                TicketBooker.lowerBerthsPositions.remove(0);
                TicketBooker.availableLowerBerths--;
            }
            else if (TicketBooker.availableMiddleBerths > 0) {
                System.out.println("Middle Berth Given");
                booker.bookTicket(p,(TicketBooker.middleBerthsPositions.get(0)),"M");
                TicketBooker.middleBerthsPositions.remove(0);
                TicketBooker.availableMiddleBerths--;
            }
            else if (TicketBooker.availableUpperBerths > 0) {
                System.out.println("Upper Berth Given");
                booker.bookTicket(p,(TicketBooker.upperBerthsPositions.get(0)),"U");
                TicketBooker.upperBerthsPositions.remove(0);
                TicketBooker.availableUpperBerths--;
            }
            else if (TicketBooker.availableRacTickets > 0) {
                System.out.println("RAC available");
                booker.bookTicket(p,(TicketBooker.racPositions.get(0)),"RAC");
            }
            else if (TicketBooker.availablewaitingList > 0) {
                System.out.println("Added to Waiting List");
                booker.bookTicket(p,(TicketBooker.waitingListPositions.get(0)),"WL");
            }
    }

    public static void cancelTicket(int id) {
        TicketBooker booker = new TicketBooker();

        if(!booker.passengers.containsKey(id)) {
            System.out.println("Passenger Detail Unknown");
        }
        else {
            booker.cancelTicket(id);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while(loop) {
            System.out.println("1.Book Ticket \n 2.Cancel Ticket \n 3.AvailableTickets \n 4.Booked Tickets \n 5.Exit");
            int choice = sc.nextInt();
            switch(choice) {
                case 1: {
                    System.out.println("Enter Passenger Name,Age,BerthPreference (L,M or U)");
                    String name = sc.next();
                    int age = sc.nextInt();
                    String berthPreference = sc.next();
                    Passenger p = new Passenger(name, age, berthPreference);

                    bookTicket(p);
                }
                break;
                case 2: {
                    System.out.println("Enter PassengerId to Cancel");
                    int id =sc.nextInt();

                    cancelTicket(id);

                }
                break;
                case 3: {
                    TicketBooker booker = new TicketBooker();
                    booker.printAvailable();

                }
                break;
                case 4: {
                    TicketBooker booker = new TicketBooker();
                    booker.printPassengers();

                }
                break;
                case 5: {
                    loop = false;
                }
                break;
                default:
                break;
            }
        }
    }
}