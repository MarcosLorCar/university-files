public class Soloist extends Artist {
    private boolean needsRoom;
    private long managerNumber;

    public Soloist(boolean headliner, int maxCapacity, int duration, String genre, String name, double ticketPrice, boolean confirmation, boolean needsRoom, long managerNumber) {
        super(headliner, maxCapacity, duration, genre, name, ticketPrice, confirmation);
        this.needsRoom = needsRoom;
        this.managerNumber = managerNumber;
    }

    @Override
    public String toString() {
        return "Soloist: " +
                "name= '" + name + "'" +
                ", headliner= " + headliner +
                ", genre= " + genre +
                ", confirmation= " + confirmation +
                ", maxCapacity= " + maxCapacity +
                ", duration= " + duration +
                ", ticketPrice= " + ticketPrice +
                ", needsRoom= " + needsRoom +
                ", managerNumber= " + managerNumber;
    }

    public boolean needsRoom() {
        return needsRoom;
    }

    @Override
    public boolean getSellsMerchandise() {
        return false;
    }
}
