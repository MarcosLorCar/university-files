public class Attendee implements Constants {
    protected String name, DNI, creditCardNumber;

    protected boolean isFrequentAssistant;

    protected Ticket[] tickets;
    protected int ticketCount;
    public Attendee(String name, String DNI, String creditCardNumber, boolean isFrequentAssistant) {
        this.name = name;
        this.DNI = DNI;
        this.creditCardNumber = creditCardNumber;
        this.isFrequentAssistant = isFrequentAssistant;
        this.tickets = new Ticket[MAX_TICKETS];
        ticketCount = 0;
    }

    public boolean purchaseTicket(Artist artist) {
        boolean canPurchase = ticketCount < MAX_TICKETS;

        if (canPurchase) {
            // Generate a random number to use as ticket ID
            int number = (int) (Math.random() * Integer.MAX_VALUE);
            tickets[ticketCount] = new Ticket(number, artist);
            ticketCount++;
        }

        return canPurchase;
    }

    public double getDiscount() {
        double discount;
        if (isFrequentAssistant) {
            discount =  FREQUENT_DISCOUNT;
        } else {
            discount = 0;
        }
        return discount;
    }

    // If called with requireStand = true, will only concatenate artists who sell merchandise

    public String getAllPurchased(boolean requireStand) {
        String result = "";

        if (ticketCount == 0) {
            result = "No tickets purchased";
        } else {
            for (int i = 0; i < ticketCount; i++) {
                if (tickets[i] != null) {
                    if (!requireStand || tickets[i].getArtist().getSellsMerchandise()) {
                        result += tickets[i].getArtist().toString();
                        result += "\n";
                    }
                }
            }
        }

        return result;
    }

    public boolean isFrequentAssistant() {
        return isFrequentAssistant;
    }

    public String getName() {
        return name;
    }

    public String getDNI() {
        return DNI;
    }
}
