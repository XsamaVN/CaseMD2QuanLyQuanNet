package services;

import models.PC;
import models.Product;
import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManager implements IQLQN<Product> {
    List<Product> productList;
    public static int idProductIncrement = 1;

    public ProductManager() throws IOException {
        productList = readFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/product.csv");
    }

    @Override
    public void addNew(Product product) throws IOException {
        productList.add(product);
        product.setIdProduct(idProductIncrement);
        idProductIncrement++;
        saveProductFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/product.csv", productList);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public void edit(int id) throws IOException {
        Scanner word1 = new Scanner(System.in);
        Scanner number1 = new Scanner(System.in);
        int index = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getIdProduct() == id) {
                index = i;
            }
        }
        if (index != -1) {
            System.out.println("┌———————————————————————————————————┐");
            System.out.println("⎟    Sửa thông tin mặt hàng id: " + id + "  ⎟");
            System.out.println("⎟———————————————————————————————————⎟");
            System.out.println("⎟1. Đổi tên.                        ⎟");
            System.out.println("⎟2. Đổi giá.                        ⎟");
            System.out.println("⎟3. Đổi số lượng.                   ⎟");
            System.out.println("⎟4. Thêm số lượng.                  ⎟");
            System.out.println("⎟0. Quay lại.                       ⎟");
            System.out.println("└———————————————————————————————————┘");
            System.out.println("Nhập lựa chọn :");
            String choice1 = "-1";
            while (!choice1.equals("0")) {
                choice1 = word1.nextLine();
                if (isInteger(choice1)) {
                    switch (choice1) {
                        case "1":
                            System.out.println("Nhập vào tên mới: ");
                            String newName = word1.nextLine();
                            productList.get(index).setNameProduct(newName);
                            saveProductFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/product.csv", productList);
                            break;
                        case "2":
                            System.out.println("Nhập vào giá mới: ");
                            double newPrice = number1.nextDouble();
                            productList.get(index).setPriceProduct(newPrice);
                            saveProductFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/product.csv", productList);
                            break;
                        case "3":
                            System.out.println("Nhập số lượng mới: ");
                            int newQuantity = number1.nextInt();
                            productList.get(index).setQuantity(newQuantity);
                            saveProductFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/product.csv", productList);
                            break;
                        case "4":
                            System.out.println("Nhập số lượng muốn thêm: ");
                            int addQuantity = number1.nextInt();
                            productList.get(index).setQuantity(addQuantity + productList.get(index).getQuantity());
                            saveProductFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/product.csv", productList);
                        case "0":
                            return;
                        default:
                            System.out.println("Nhập sai. Mời nhập lại");

                    }

                }
            }
        } else {
            System.out.println("Không tìm thấy mặt hàng có id " + id);
        }
    }


    @Override
    public void delete(int id) throws IOException {
        int index = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getIdProduct() == id) {
                index = i;
            }
        }
        if (index != -1) {
            productList.remove(index);
            System.out.println("Xóa thành công id: " + id);
            saveProductFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/product.csv", productList);

        } else {
            System.out.println("Không tìm thấy thông tin mặt hàng id: " + id);
        }
    }

    @Override
    public void showAll() throws IOException {
        System.out.println("Danh sách các mặt hàng: ");

        for (Product p : productList) {
            System.out.println(p);
        }
    }

    @Override
    public void findById(int t) {
        for (Product u : productList) {
            if (u.getIdProduct() == t) {
                System.out.println(u);
            }
        }
    }
    public String getNameProductById(int id){
        String name = null;
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getIdProduct() == id){
                name = productList.get(i).getNameProduct();

            }
        }return name;
    }


    public static void saveProductFile(String path, List<Product> productList) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String str = "id, tên hàng hóa, số lượng còn lại, giá\n";
        for (Product p : productList) {
            str += p.getIdProduct() + ", " + p.getNameProduct() + ", " + p.getQuantity() + ", " + p.getPriceProduct() + "\n";
        }
        bufferedWriter.write(str);
        bufferedWriter.close();
        fileWriter.close();
    }

    public List<Product> readFile(String path) throws IOException {
        List<Product> list = new ArrayList<>();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            String[] value = line.split(", ");
            int id = Integer.parseInt(value[0]);
            String nameProduct = value[1];
            int quantity = Integer.parseInt(value[2]);
            double money = Double.parseDouble(value[3]);
            Product product = new Product(nameProduct, quantity, money);
            list.add(product);
            product.setIdProduct(idProductIncrement);
            idProductIncrement++;

        }
        bufferedReader.close();
        fileReader.close();
        return list;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Nhập sai, mời nhập lại!!!");
            return false;
        }
    }

}
