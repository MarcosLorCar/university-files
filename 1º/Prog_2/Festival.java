public class Festival implements Constants {
    private String festivalName, city;
    private Artist[] artists;
    private int artistCount = 0;
    private Attendee[] attendees;
    private int attendeeCount = 0;

    Festival(String festivalName, String city, Artist[] artists, Attendee[] attendees) {
        this.festivalName = festivalName;
        this.city = city;

        // Fill the first spaces of the array with the provided objects
        this.artists = new Artist[MAX_ARTISTS];
        for (int i = 0; i < artists.length; i++) {
            this.artists[i] = artists[i];
            artistCount++;
        }

        // And the same here
        this.attendees = new Attendee[MAX_ATTENDEES];
        for (int i = 0; i < attendees.length; i++) {
            this.attendees[i] = attendees[i];
            attendeeCount++;
        }
    }
    // This method returns a String containing all the information of all artists.
    public String getAllArtistsInfo() {
        String result = "";

        for (int i = 0; i < artistCount; i++) {
            result += artists[i].toString();
            result += "\n";
        }

        return result;
    }
    // This method returns a String containing all the information of the concerts with merchandise
    // a VIP attendee will attend.
    public String getVipInfo(String DNI) {
        String vipInfo;

        Attendee attendee = getAttendee(DNI);

        if (attendee == null) {
            vipInfo = "The attendee is not registered in the festival.";
        } else {
            if (attendee instanceof VipAttendee) {
                VipAttendee vipAttendee = (VipAttendee) attendee;
                vipInfo = vipAttendee.getAllPurchased(true);
            } else {
                vipInfo = "Not a vip attendee";
            }
        }

        return vipInfo;
    }

    // Returns [discount, oldPrice] as to calculate final price(s) in the Main
    public double[] checkTicketPrice(String AttendeeDNI, String artistName) throws IndexOutOfBoundsException, NullPointerException {
        int index = getArtistIndex(artistName);

        Artist artist = artists[index]; // Might throw IndexOutOfBoundsException if artistName is invalid.
        Attendee attendee = getAttendee(AttendeeDNI);

        double price = artist.getTicketPrice();
        double discount = attendee.getDiscount(); // Might throw NullPointerException if AttendeeDNI is invalid.

        return new double[] { discount, price };
    }
    // Support method that returns -1 if the artist name is not found, otherwise returns the index of the
    // array where the object is stored. Additionally, lowercase and uppercase are treated equally.
    private int getArtistIndex(String artistName) {
        int index = -1;

        for (int i = 0; i < artistCount; i++) {
            if (artists[i].getName().equalsIgnoreCase(artistName)) {
                index = i;
            }
        }

        return index;
    }
    // Support method that returns the Attendee object which has the DNI introduced as a parameter.
    // Again, lowercase and uppercase are treated equally.
    private Attendee getAttendee(String DNI) {
        Attendee attendee = null;
        for (int i = 0; i < attendeeCount; i++) {
            if (attendees[i].getDNI().equalsIgnoreCase(DNI)) {
                attendee = attendees[i];
            }
        }
        return attendee;
    }
    // This method allows to purchase a ticket and returns whether this purchase could be made. Moreover,
    // we control that no more than 7 tickets can be purchased.
    public boolean purchaseTicket(String DNI, String artistName) throws MaxTicketsException, IndexOutOfBoundsException {
        boolean couldPurchase;
        Attendee attendee = getAttendee(DNI);

        int index = getArtistIndex(artistName);

        if (attendee != null) {
            boolean success = attendee.purchaseTicket(artists[index]); // May throw IndexOutOfBounds if artistName was invalid
            if (success) {
                couldPurchase = true;
            } else {
                throw new MaxTicketsException();
            }
        } else {
            couldPurchase = false;
        }

        return couldPurchase;
    }

    public boolean addAttendee(Attendee attendee) {
        boolean success = false;

        if (attendeeCount < MAX_ATTENDEES) {
            attendees[attendeeCount] = attendee;
            attendeeCount++;
            success = true;
        }

        return success;
    }
    // This method returns in a String the information of all the concerts and Attendee wants to attend.
    public String getAttendeePurchasedInfo(String DNI) throws NullPointerException {
        Attendee attendee = getAttendee(DNI);
        return attendee.getAllPurchased(false);
    }
    // This method estimates the cost of an Attendee going to the confirmed headliner concerts that 
    // sell merchandise.
    public double estimatePrice(String DNI) {
        double price = 0;
        Attendee attendee = getAttendee(DNI);

        if (attendee == null) {
            price = -1;
        } else {
            for (int i = 0; i < artistCount; i++) {
                Artist artist = artists[i];
                // Checking that the conditions are met.
                if (artist.isConfirmed() && artist.isHeadliner()) {
                    if (artist.getSellsMerchandise())
                        price += AVERAGE_MERCH_PRICE;

                    price += artist.getTicketPrice();
                }
            }
            // Applying a discount if it is necessary.
            if (attendee.isFrequentAssistant()) {
                price -= price * FREQUENT_DISCOUNT_MERCH;
            }
        }

        return price;
    }
    // This method estimates the cost of hiring guards from a security company.
    public double securityCost(SecurityCompany securityCompany) {
        double costPerGuard = securityCompany.getCostPerGuard();
        double price = 0.0;

        // Accumulate max attendees
        int maxAttendees = 0;

        for (int i = 0; i < artistCount; i++) {
            Artist artist = artists[i];
            if (artist.getSellsMerchandise()) {
                price += costPerGuard * GUARDS_PER_STAND;
            }
            // More guards are need if the artist is a Soloist, since they need
            // a guard for their private room.
            if(artist instanceof Soloist) {
                Soloist soloist = (Soloist) artist;

                if (soloist.needsRoom()) {
                    price += costPerGuard;
                }
            }

            // Times a 1000 because it is stored as thousands of attendees.
            maxAttendees += artist.getMaxCapacity() * 1000;
        }

        // For every 500 attendees, 1 guard.
        price += (int) (maxAttendees / ATTENDEES_PER_GUARD) * costPerGuard;

        return price;
    }
}
