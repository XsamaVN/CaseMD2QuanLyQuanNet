package models;

public class PC {
    private int idPC;
    private double pricePC;
    User user = null;

    public PC(double pricePC) {
        this.pricePC = pricePC;
    }


    public int getIdPC() {
        return idPC;
    }

    public void setIdPC(int idPC) {
        this.idPC = idPC;
    }

    public double getPricePC() {
        return pricePC;
    }

    public void setPricePC(double pricePC) {
        this.pricePC = pricePC;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PC{" +
                " Id = " + idPC +
                ", Price = " + pricePC +
                ", User = " + user+
                '}';
    }
}
