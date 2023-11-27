package models;
public class User {
    private int id;
    private String userName;
    private String userPassword;
    private double moneyCharge;

    public User( String userName, String userPassword, double moneyCharge) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.moneyCharge = moneyCharge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public double getMoneyCharge() {
        return moneyCharge;
    }

    public void setMoneyCharge(double moneyCharge) {
        this.moneyCharge = moneyCharge;
    }

    @Override
    public String toString() {
        return "User{" +
                "id = " + id +
                ", User Name= " + userName +
                ", User Password=" + userPassword +
                ", Money Charge=" + moneyCharge +
                '}';
    }
}
