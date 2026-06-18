public class Group extends Artist {
    private int numMembers;
    private boolean sellsMerchandise;

    public Group(boolean headliner, int maxCapacity, int duration, String genre, String name, double ticketPrice, boolean confirmation, int numMembers, boolean sellsMerchandise) {
        super(headliner, maxCapacity, duration, genre, name, ticketPrice, confirmation);
        this.numMembers = numMembers;
        this.sellsMerchandise = sellsMerchandise;
    }

    public String toString() {
        return "Group: " +
                "name= '" + name + "'" +
                ", headliner= " + headliner +
                ", genre= " + genre +
                ", confirmation= " + confirmation +
                ", maxCapacity= " + maxCapacity +
                ", duration= " + duration +
                ", ticketPrice= " + ticketPrice +
                ", numMembers= " + numMembers +
                ", sellsMerchandise= " + sellsMerchandise;
    }

    @Override
    public boolean getSellsMerchandise() {
        return sellsMerchandise;
    }
}
