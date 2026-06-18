public class SecurityCompany {
    private String name;
    private double costPerGuard;

    SecurityCompany(String name, double costPerGuard) {
        this.name = name;
        this.costPerGuard = costPerGuard;
    }

    public double getCostPerGuard() {
        return costPerGuard;
    }
}
