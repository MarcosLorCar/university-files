public class MaxTicketsException extends RuntimeException {
  public MaxTicketsException() {
    super("Tried to purchase with max tickets.");
  }
}
