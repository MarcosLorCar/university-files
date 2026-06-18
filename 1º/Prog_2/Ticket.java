public class Ticket {
    private int identifier;
    private Artist artist;

    Ticket(int identifier, Artist artist) {
        this.identifier = identifier;
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }
}
