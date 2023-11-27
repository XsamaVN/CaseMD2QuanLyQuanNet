package models;

public class Invoice {
    private int id;
    private int playTime;
    private String buyName;
    private int buyQuantity;
    private double buyPrice;
    private int pcId;
    private double pcPrice;
    User user = null;

    public Invoice() {
    }

    public Invoice(int playTime, String buyName, int buyQuantity, double buyPrice, int pcId, double pcPrice, User user) {
        this.playTime = playTime;
        this.buyName = buyName;
        this.buyQuantity = buyQuantity;
        this.buyPrice = buyPrice;
        this.pcId = pcId;
        this.pcPrice = pcPrice;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getPcId() {
        return pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    public double getPcPrice() {
        return pcPrice;
    }

    public void setPcPrice(double pcPrice) {
        this.pcPrice = pcPrice;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", Giờ chơi= " + playTime +
                ", Tên hàng=' " + buyName + '\'' +
                ", Số lượng= " + buyQuantity +
                ", Giá hàng= " + buyPrice +
                ", id máy= " + pcId +
                ", Giá máy= " + pcPrice +
                ", user= " + user.getUserName() +
                ", Tổng= " + (buyQuantity*buyPrice + playTime*pcPrice) +
                '}';
    }
}

