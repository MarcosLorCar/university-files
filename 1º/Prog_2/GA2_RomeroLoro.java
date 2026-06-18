import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GA2_RomeroLoro {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Artist[] artists = null;
        Attendee[] attendees = null;

        try {
            artists = readArtistsData("Artistas.txt");
            attendees = readAttendeeData("Asistentes.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Missing files, exiting.");
        }

        // Continue if any of the files is missing. (exit else)
        if (artists != null && attendees != null) {
            Festival festival = new Festival(
                    "Increible festival",
                    "París",
                    artists,
                    attendees
            );
            SecurityCompany sc = new SecurityCompany("Evento Seguro S. L.", 250.0);
            boolean exit;

            // Main loop
            do {
                exit = menu(festival, sc);
            } while (!exit);
        }

        scanner.close();
    }

    private static boolean menu(Festival festival, SecurityCompany securityCompany) {
        boolean exit = false;

        // Show all options
        System.out.print("""
                Choose an option:
                1-Show the information of all the artists scheduled to participate in the festival.
                2-Show estimation on how much the festival's security service would cost.
                3-Check the cost of a ticket to a specific concert.
                4-Estimate the total cost to attend all headliners and buy a t-shirt at each show.
                5-Purchase a ticket to a specific concert.
                6-Show all the information of the tickets that a specific attendee has purchased.
                7-Show concerts with merchandise stands where a VIP attendee has bought tickets.
                8-Exit the program.
                >""");

        // Ask for a valid option
        boolean valid = false;
        int option = -1;
        do {
            try {
                option = scanner.nextInt();

                // The input was numeric, but not valid
                if (option < 1 || option > 8) {
                    System.out.println("Invalid option, try again.");
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                // The input wasn't numeric
                System.out.println("Invalid input, try again.");
                scanner.nextLine();
            }
        } while (!valid);

        switch (option) {
            case 1:
                showArtistInfo(festival);
                break;
            case 2:
                showSecurityCost(festival, securityCompany);
                break;
            case 3:
                checkTicketPrice(festival);
                break;
            case 4:
                checkEstimatedPrice(festival);
                break;
            case 5:
                purchaseTicketForAttendee(festival);
                break;
            case 6:
                showAllTicketInfo(festival);
                break;
            case 7:
                showAllVIPInfo(festival);
                break;
            case 8:
                exit = true;
                break;
            default:
                break;
        }

        return exit;
    }

    private static void showAllVIPInfo(Festival festival) {
        System.out.println("Enter the DNI of the vip attendee: ");
        String dni = scanner.next();
        String result = festival.getVipInfo(dni);
        System.out.println(result);
    }

    private static void showAllTicketInfo(Festival festival) {
        System.out.println("Enter the DNI of the attendee whose ticket info you want to view: ");
        String dni = scanner.next();
        try {
            String info = festival.getAttendeePurchasedInfo(dni);
            System.out.println(info);
        } catch (NullPointerException e) {
            System.out.println("The attendee is not registered in the festival.");
        }
    }

    private static void purchaseTicketForAttendee(Festival festival) {
        System.out.println("Enter the dni of the attendee who wants to buy: ");
        String dni = scanner.next();
        System.out.println("Enter the name of the artist: ");
        String artistName = scanner.next();
        boolean success;
        try {
            // purchaseTicket() might throw IndexOutOfBoundsException or MaxTicketsException
            success = festival.purchaseTicket(dni, artistName);

            if (!success) {
                System.out.println("Attendee not found, Enter its data:\n");
                Attendee attendee = askForAttendeeData();
                festival.addAttendee(attendee);
                festival.purchaseTicket(dni, artistName);
            }
            System.out.println("Ticket purchased successfully.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The artist is not registered in the festival.");
        } catch (MaxTicketsException e) {
            System.out.println("The attendee has already purchased the maximum number of tickets.");
        }
    }

    private static Attendee askForAttendeeData() {
        System.out.print("Name: ");
        String name = scanner.next();

        System.out.print("DNI: ");
        String dni = scanner.next();

        System.out.print("Is frequent assistant? (true/false): ");
        boolean isFrequentAssistant = scanner.nextBoolean();

        System.out.print("Credit card number: ");
        String creditCardNumber = scanner.next();

        return new Attendee(name, dni, creditCardNumber, isFrequentAssistant);
    }

    private static void checkEstimatedPrice(Festival festival) {
        System.out.print("Enter the dni of the attendee to check for: ");
        String dni = scanner.next();

        double price = festival.estimatePrice(dni);
        if (price == -1) {
            System.out.println("The attendee is not registered in the festival.");
        } else {
            System.out.println("The estimated price for the attendee is " + price + "€.");
        }
    }

    private static void checkTicketPrice(Festival festival) {
        System.out.print("Enter the dni of the attendee to check for: ");
        String attendeeDNI = scanner.next();

        System.out.print("Enter the name of the artist: ");
        String artistName = scanner.next();

        double[] discountAndPrice = null;
        try {
            discountAndPrice = festival.checkTicketPrice(attendeeDNI, artistName);
        } catch (NullPointerException e) {
            System.out.println("The attendee is not registered in the festival.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The artist is not registered in the festival.");
        }

        if (discountAndPrice != null) {
            System.out.printf("Before discount: %.2f\nAfter discount: %.2f\nDiscount applied: %.2f%% \n",
                    discountAndPrice[1],
                    discountAndPrice[1] * (1 - discountAndPrice[0]),
                    discountAndPrice[0]*100);
        }
    }

    private static void showSecurityCost(Festival festival, SecurityCompany securityCompany) {
        double cost = festival.securityCost(securityCompany);
        System.out.println(cost + " €");
    }

    private static void showArtistInfo(Festival festival) {
        String info = festival.getAllArtistsInfo();
        System.out.println(info);
    }

    private static Artist[] readArtistsData(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        int lineCount = countLines(f);
        Scanner sc = new Scanner(f);
        Artist[] artists = new Artist[lineCount];

        // Common data
        char type;
        String name, genre;
        boolean headliner, isConfirmed;
        double price;
        int duration, maxAudience;

        // Soloists
        boolean requireRoom;
        long managerPhoneNumber;

        // Groups
        int numMembers;
        boolean sellMerch;

        for (int i = 0; i < lineCount; i++) {
            type = sc.next().charAt(0);
            name = sc.next();
            genre = sc.next();
            headliner = sc.nextBoolean();
            price = sc.nextDouble();
            duration = sc.nextInt();
            maxAudience = sc.nextInt();
            isConfirmed = sc.nextBoolean();

            if (type == 'g') {
                numMembers = sc.nextInt();
                sellMerch = sc.nextBoolean();
                artists[i] = new Group(headliner, maxAudience, duration, genre, name, price, isConfirmed, numMembers, sellMerch);
            } else if (type == 's') {
                requireRoom = sc.nextBoolean();
                managerPhoneNumber = sc.nextLong();
                artists[i] = new Soloist(headliner, maxAudience, duration, genre, name, price, isConfirmed, requireRoom, managerPhoneNumber);
            }
        }
        sc.close();

        return artists;
    }

    private static Attendee[] readAttendeeData(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        int lineCount = countLines(f);
        Scanner nombre_f = new Scanner(f);
        Attendee[] attendees = new Attendee[lineCount];

        String nombre, dni, tarjeta;
        boolean frecuente, vip;
        int tarjetaVIP;

        for (int i = 0; i < lineCount; i++) {
            nombre = nombre_f.next();
            dni = nombre_f.next();
            tarjeta = nombre_f.next();
            frecuente = nombre_f.nextBoolean();
            vip = nombre_f.nextBoolean();

            if (vip) {
                tarjetaVIP = nombre_f.nextInt();
                attendees[i] = new VipAttendee(nombre, dni, tarjeta, frecuente, tarjetaVIP);
            } else {
                attendees[i] = new Attendee(nombre, dni, tarjeta, frecuente);
            }
        }

        nombre_f.close();
        return attendees;
    }

    private static int countLines(File file) throws FileNotFoundException {
        // Open a dedicated scanner just to count how many times it can nextLine()
        Scanner sc = new Scanner(file);
        int count = 0;
        while (sc.hasNextLine()) {
            sc.nextLine();
            count++;
        }
        sc.close();
        return count;
    }
}
