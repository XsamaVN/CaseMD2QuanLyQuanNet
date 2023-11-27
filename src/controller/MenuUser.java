package controller;

import models.Invoice;

import models.User;
import services.InvoiceManager;
import services.PCManager;
import services.ProductManager;
import services.UserManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.time.Duration;

import java.time.LocalTime;
import java.util.Scanner;

public class MenuUser {
    public static void main(String[] args) throws IOException {

        UserManager userManager = new UserManager();
        ProductManager productManager = new ProductManager();
        PCManager pcManager = new PCManager();
        InvoiceManager invoiceManager = new InvoiceManager();
        Scanner word = new Scanner(System.in);
        Scanner number = new Scanner(System.in);
        while (true) {

            System.out.println("┌———————————————————————————————————┐");
            System.out.println("⎟             CHỌN MÁY              ⎟");
            System.out.println("└———————————————————————————————————┘");
            pcManager.showAll();
            System.out.println("Chọn 0 để tắt máy!!!");
            System.out.print("Chọn Id:  ");
            String choiceIdPC = word.nextLine();
            if(Integer.parseInt(choiceIdPC) == 0){
                break;
            }
            if (ProductManager.isInteger(choiceIdPC)) {
                if (pcManager.getPcList().size() > Integer.parseInt(choiceIdPC) && Integer.parseInt(choiceIdPC) >= 1) {
                    while (true) {
                        if (ProductManager.isInteger(choiceIdPC) && pcManager.checkUsed(Integer.parseInt(choiceIdPC))) {
                            System.out.println("┌———————————————————————————————————┐");
                            System.out.println("⎟            ĐĂNG NHẬP              ⎟");
                            System.out.println("└———————————————————————————————————┘");
                            System.out.print("    Tài khoản:  ");
                            String user = word.nextLine();
                            System.out.print("    Mật khẩu:  ");
                            String password = word.nextLine();
                            User userCheck = new User(user, password, userManager.getMoneyByName(user));
                            if (userManager.checkLogin(userCheck, pcManager.getPcList().get(Integer.parseInt(choiceIdPC)-1).getPricePC())) {
                                if (userManager.getMoneyByName(user) > 0) {
                                    String choice = "-1";
                                    if (ProductManager.isInteger(choice)) {

                                        LocalTime startTime = LocalTime.now();
                                        Invoice invoice = new Invoice();
                                        PCManager.setUsed(userCheck);
                                        invoice.setUser(userCheck);
                                        invoice.setPcId(Integer.parseInt(choiceIdPC));
                                        invoice.setPcPrice(pcManager.getPcList().get(Integer.parseInt(choiceIdPC) - 1).getPricePC());

                                        while (!choice.equals("0")) {
                                            System.out.println("┌———————————————————————————————————┐");
                                            System.out.println("         MENU NGƯỜI DÙNG " + user + "       ");
                                            System.out.println("⎟———————————————————————————————————⎟");
                                            System.out.println("⎟1. Mua gì đó.                      ⎟");
                                            System.out.println("⎟2. Chat với anh Long.              ⎟");
                                            System.out.println("⎟0. Tắt máy.                        ⎟");
                                            System.out.println("└———————————————————————————————————┘");
                                            System.out.println("Nhập lựa chọn :");
                                            choice = word.nextLine();
                                            switch (choice) {
                                                case "1":
                                                    productManager.showAll();
                                                    System.out.println("Nhập vào id sản phẩm bạn muốn mua.");
                                                    int id = number.nextInt();
                                                    int index = -1;
                                                    for (int i = 0; i < productManager.getProductList().size(); i++) {
                                                        if (productManager.getProductList().get(i).getIdProduct() == id) {
                                                            index = i;
                                                        }
                                                    }
                                                    int buyQuantity;
                                                    if (index != -1) {
                                                        System.out.println("Nhập vào số lượng bạn muốn mua");
                                                        buyQuantity = number.nextInt();

                                                        if (buyQuantity <= productManager.getProductList().get(index).getQuantity() && productManager.getProductList().get(index).getQuantity() > 0) {
                                                            if (buyQuantity * productManager.getProductList().get(index).getPriceProduct() < invoice.getUser().getMoneyCharge()) {
                                                                productManager.getProductList().get(index).setQuantity(productManager.getProductList().get(index).getQuantity() - buyQuantity);
                                                                System.out.println("Mua thành công " + buyQuantity + " " + productManager.getProductList().get(index).getNameProduct());
                                                                invoice.setBuyName(productManager.getProductList().get(index).getNameProduct());
                                                                invoice.setBuyQuantity(buyQuantity);
                                                                invoice.setBuyPrice(productManager.getProductList().get(index).getPriceProduct());
                                                                InvoiceManager.saveInvoicetFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/invoice.csv", invoiceManager.getInvoiceList());
                                                                ProductManager.saveProductFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/product.csv", productManager.getProductList());
                                                            }else {
                                                                System.out.println("Số tiền bạn có không đủ để thanh toán");
                                                            }
                                                        } else {
                                                            System.out.println("Số lượng sản phẩm còn lại không đủ");
                                                        }
                                                    } else {
                                                        System.out.println("Không tìm thấy id sản phẩm");
                                                    }

                                                    break;
                                                case "2":
                                                    try {
                                                        Socket socket = new Socket("localhost", 3333);
                                                        System.out.println("'Chat với anh Long'\n" +
                                                                "(gõ 'bye' để thoát.)");
                                                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                                                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                                                        Scanner chatClient = new Scanner(System.in);
                                                        while (true) {

                                                            String str1 = chatClient.nextLine();
                                                            dataOutputStream.writeUTF(str1);
                                                            dataOutputStream.flush();
                                                            if (str1.equals("bye")) {
                                                                break;
                                                            }
                                                            String strHost = dataInputStream.readUTF();
                                                            System.out.println("Anh Long says: " + strHost);
                                                        }
                                                        dataInputStream.close();
                                                        dataOutputStream.close();
                                                        socket.close();
                                                    } catch (Exception e) {
                                                        System.out.println("Sever đang bận. Thử lại sau nhé");
                                                    }
                                                    break;
                                                case "0":
                                                    LocalTime endTime = LocalTime.now();
                                                    Duration duration = Duration.between(startTime, endTime);
                                                    int timeUsed = (int) duration.getSeconds();
                                                    invoice.setPlayTime(timeUsed);
                                                    System.out.println("┌———————————————————————————————————┐\n" +
                                                            "⎟             HÓA ĐƠN               ⎟\n" +
                                                            "└———————————————————————————————————┘");
                                                    System.out.println("Thời gian sử dụng " + timeUsed + " giây");
                                                    double servicePrice = invoice.getBuyPrice() * invoice.getBuyQuantity();
                                                    System.out.println("Sản phẩm đã mua: " + invoice.getBuyName() + ", số lượng: " + invoice.getBuyQuantity() + ", giá: " + invoice.getBuyPrice() + ", thành tiền: " + servicePrice);
                                                    System.out.println("Số tiền cần trả: " + (timeUsed * invoice.getPcPrice() + servicePrice));
                                                    if ((timeUsed * invoice.getPcPrice() + servicePrice) < invoice.getUser().getMoneyCharge()) {
                                                        for (User u: userManager.getUserList()) {
                                                            if(u.getUserName().equals(invoice.getUser().getUserName())){
                                                                u.setMoneyCharge(invoice.getUser().getMoneyCharge()-(timeUsed * invoice.getPcPrice() + servicePrice));
                                                            }
                                                        }
                                                        invoiceManager.addNew(invoice);
                                                        InvoiceManager.saveInvoicetFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/invoice.csv", invoiceManager.getInvoiceList());
                                                        ProductManager.saveProductFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/product.csv", productManager.getProductList());
                                                        UserManager.saveUserFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/user.csv", userManager.getUserList());
                                                        PCManager.setUsed(null);
                                                        break;
                                                    } else {
                                                        System.out.println("Liên hệ quản lý để nạp thêm tiền!!!");
                                                    }

                                                default:
                                                    System.out.println("Nhập sai mời nhập lại");
                                            }
                                        }
                                    }

                                } else {
                                    System.out.println("Bạn đã hết tiền trong tài khoản!!!");
                                }
                            } else {
                                System.out.println("Liên hệ quản lý để tạo tài khoản mới !!!");
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("nhập sai mời nhập lại!!!");
                }
            }
        }
    }
}