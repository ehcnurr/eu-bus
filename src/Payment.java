public class Payment {
    private String type = "";
    private double amount = 0;

    public void setType (String type) {
        this.type = type;
    }

    public String getType () {
        return type;
    }

    public void setAmount (String type) {
        if (type.equals("guest")) {
            amount = 12.00;
        } else if (type.equals("student/staff")) {
            amount = 10.00;
        }
    }

    public double getAmount() {
        return amount;
    }
}
