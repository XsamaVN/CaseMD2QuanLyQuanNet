package models;

import services.PCManager;

public class PC {
    private int idPC;
    private double pricePC;
    private boolean checkStatus;
    String userNamePlay = null;

    public PC(double pricePC) {
        this.pricePC = pricePC;
        this.checkStatus = false;
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


    public boolean getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getUserNamePlay() {
        return userNamePlay;
    }

    public void setUserNamePlay(String userNamePlay) {
        this.userNamePlay = userNamePlay;
    }

    @Override
    public String toString() {
        return "PC{" +
                " Id = " + idPC +
                ", Price = " + pricePC +
                ", Status = " + checkStatus+
                ", User = " + userNamePlay+
                '}';
    }
}
