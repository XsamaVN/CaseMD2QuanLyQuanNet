package models;

import services.PCManager;

public class PC {
    private int idPC;
    private double pricePC;
    private boolean checkStatus;
    User user =null;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (this.user!= null){
            this.checkStatus=true;
        }
         else{
             this.checkStatus=false;
        }

    }

    public boolean getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Override
    public String toString() {
        return "PC{" +
                " Id = " + idPC +
                ", Price = " + pricePC +
                ", Status = " + checkStatus+
                '}';
    }
}
