public class VipAttendee extends Attendee implements Constants {
    private long cardNumber;


    public VipAttendee(String name, String DNI, String creditCardNumber, boolean isFrequentAssistant, long cardNumber) {
        super(name, DNI, creditCardNumber, isFrequentAssistant);

        this.cardNumber = cardNumber;
    }

    @Override
    public double getDiscount() {
        return VIP_DISCOUNT;
    }
}
