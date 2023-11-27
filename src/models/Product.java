package models;

public class Product {
    private int idProduct;
    private String nameProduct;
    private int quantity;
    private double priceProduct;

    public Product(String nameProduct, int quantity, double priceProduct) {
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.priceProduct = priceProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    @Override
    public String toString() {
        return "FoodAndDrink{" +
                "id= " + idProduct +
                ", Name Product = " + nameProduct +
                ", Quantity = " + quantity +
                ", Price Product = " + priceProduct +
                '}';
    }
}
