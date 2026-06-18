public abstract class Artist {
    protected boolean headliner, confirmation;

    protected int maxCapacity, duration;

    protected String name, genre;
    protected double ticketPrice;
    public Artist(boolean headliner, int maxCapacity, int duration, String genre, String name, double ticketPrice, boolean confirmation) {
        this.headliner = headliner;
        this.maxCapacity = maxCapacity;
        this.duration = duration;
        this.genre = genre;
        this.name = name;
        this.ticketPrice = ticketPrice;
        this.confirmation = confirmation;
    }

    public boolean isHeadliner() {
        return headliner;
    }

    public boolean isConfirmed() {
        return confirmation;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getName() {
        return name;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public abstract boolean getSellsMerchandise();
}
