package controller;

import models.PC;
import models.Product;
import models.User;
import services.*;

import java.io.IOException;
import java.util.Scanner;


public class MenuAdmin {
    public static void main(String[] args) throws IOException {
        PCManager pcManager = new PCManager();
        UserManager userManager = new UserManager();
        ProductManager productManager = new ProductManager();
        InvoiceManager invoiceManager = new InvoiceManager();
        Scanner word = new Scanner(System.in);
        Scanner number = new Scanner(System.in);
        String choice = "-1";
        if (ProductManager.isInteger(choice)) {
            while (!choice.equals("0")) {
                System.out.println("┌———————————————————————————————————┐");
                System.out.println("⎟           MENU ADMIN              ⎟");
                System.out.println("⎟———————————————————————————————————⎟");
                System.out.println("⎟1. Quản lý tài khoản người dùng.   ⎟");
                System.out.println("⎟2. Quản lý máy.                    ⎟");
                System.out.println("⎟3. Quản lý dịch vụ.                ⎟");
                System.out.println("⎟4. Quản lý hóa đơn.                ⎟");
                System.out.println("⎟5. Chat với khách hàng.            ⎟");
                System.out.println("⎟0. Thoát                           ⎟");
                System.out.println("└———————————————————————————————————┘");
                System.out.println("Nhập lựa chọn : ");
                choice = word.nextLine();
                switch (choice) {
                    case "1":
                        String choice1 = "-1";
                        if (ProductManager.isInteger(choice1)) {
                            while (!choice1.equals("0")) {
                                System.out.println("┌———————————————————————————————————┐");
                                System.out.println("⎟   QUẢN LÝ TÀI KHOẢN NGƯỜI DÙNG    ⎟");
                                System.out.println("⎟———————————————————————————————————⎟");
                                System.out.println("⎟1. Thêm người dùng.                ⎟");
                                System.out.println("⎟2. Sửa thông tin người dùng.       ⎟");
                                System.out.println("⎟3. Xóa người dùng.                 ⎟");
                                System.out.println("⎟4. Tìm thông tin người dùng        ⎟");
                                System.out.println("⎟5. Nạp tiền                        ⎟");
                                System.out.println("⎟0. Quay lại                        ⎟");
                                System.out.println("└———————————————————————————————————┘");
                                System.out.println("Nhập lựa chọn :");
                                System.out.print("chọn: ");
                                choice1 = word.nextLine();
                                switch (choice1) {
                                    case "1":
                                        System.out.println("Thêm thông tin người dùng mới");
                                        System.out.println("Nhập tài khoản người dùng");
                                        String userName = word.nextLine();
                                        System.out.println("Nhập mật khẩu người dùng:");
                                        String password = word.nextLine();
                                        System.out.println("Nhập số tiền nạp:");
                                        double moneyCharge = number.nextDouble();
                                        userManager.addNew(new User(userName, password, moneyCharge));
                                        break;
                                    case "2":
                                        userManager.showAll();
                                        System.out.println("Nhập vào id người dùng muốn sửa.");
                                        int idEdit = number.nextInt();
                                        userManager.edit(idEdit);
                                        break;
                                    case "3":
                                        userManager.showAll();
                                        System.out.println("Nhập vào id người dùng muốn xóa.");
                                        int idDelete = number.nextInt();
                                        userManager.delete(idDelete);
                                        break;
                                    case "4":
                                        System.out.println("Nhập vào id người dùng muốn tìm kiếm.");
                                        int idSearch = number.nextInt();
                                        userManager.findById(idSearch);
                                        break;
                                    case "5":
                                        userManager.showAll();
                                        System.out.println("Nhập id muốn nạp tiền: ");
                                        int idReCharge = number.nextInt();
                                        userManager.recharge(idReCharge);
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        System.out.println("Nhập sai mời nhập lại");
                                }
                            }
                        }
                        break;
                    case "2":
                        String choice2 = "-1";
                        if (ProductManager.isInteger(choice2)) {
                            while (!choice2.equals("0")) {
                                System.out.println("┌———————————————————————————————————┐");
                                System.out.println("⎟            QUẢN LÝ MÁY            ⎟");
                                System.out.println("⎟———————————————————————————————————⎟");
                                System.out.println("⎟1. Thêm Máy.                       ⎟");
                                System.out.println("⎟2. Sửa giá máy.                    ⎟");
                                System.out.println("⎟3. Xóa Máy.                        ⎟");
                                System.out.println("⎟4. Danh sách máy.                  ⎟");
                                System.out.println("⎟0. Quay lại.                       ⎟");
                                System.out.println("└———————————————————————————————————┘");
                                System.out.println("Nhập lựa chọn :");
                                choice2 = word.nextLine();
                                switch (choice2) {
                                    case "1":
                                        System.out.println("Thêm máy");
                                        System.out.println("Nhập giá cho máy mới:");
                                        int price = number.nextInt();
                                        pcManager.addNew(new PC(price));
                                        break;
                                    case "2":
                                        pcManager.showAllAdmin();
                                        System.out.println("Nhập vào id máy muốn sửa.");
                                        int idPcEdit = number.nextInt();
                                        pcManager.edit(idPcEdit);
                                        break;
                                    case "3":
                                        pcManager.showAllAdmin();
                                        System.out.println("Nhập vào id máy muốn xóa.");
                                        int idPcDelete = number.nextInt();
                                        pcManager.delete(idPcDelete);
                                        break;
                                    case "4":
                                        pcManager.showAllAdmin();
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        System.out.println("Nhập sai mời nhập lại");
                                }
                            }
                        }
                        break;
                    case "3":
                        String choice3 = "-1";
                        if (ProductManager.isInteger(choice3)) {
                            while (!choice3.equals("0")) {
                                System.out.println("┌———————————————————————————————————┐");
                                System.out.println("⎟         QUẢN LÝ DỊCH VỤ           ⎟");
                                System.out.println("⎟———————————————————————————————————⎟");
                                System.out.println("⎟1. Thêm sản phẩm.                  ⎟");
                                System.out.println("⎟2. Sửa sản phẩm.                   ⎟");
                                System.out.println("⎟3. Xóa sản phẩm.                   ⎟");
                                System.out.println("⎟4. Tìm kiếm sản phẩm.              ⎟");
                                System.out.println("⎟0. Quay lại.                       ⎟");
                                System.out.println("└———————————————————————————————————┘");
                                System.out.println("Nhập lựa chọn :");
                                choice3 = word.nextLine();
                                switch (choice3) {
                                    case "1":
                                        System.out.println("Thêm sảm phẩm mới: ");
                                        System.out.println("Nhập tên sản phẩm");
                                        String productName = word.nextLine();
                                        System.out.println("Nhập số số lượng");
                                        int productQuantity = number.nextInt();
                                        System.out.println("Nhập giá sản phẩm");
                                        double priceProduct = number.nextDouble();
                                        productManager.addNew(new Product(productName, productQuantity, priceProduct));
                                        System.out.println("Thêm thành công!!!");
                                        break;
                                    case "2":
                                        productManager.showAll();
                                        System.out.println("Nhập vào id sản phẩm muốn sửa.");
                                        int idProductEdit = number.nextInt();
                                        productManager.edit(idProductEdit);
                                        break;
                                    case "3":
                                        productManager.showAll();
                                        System.out.println("Nhập vào id máy muốn xóa.");
                                        int idProductDelete = number.nextInt();
                                        productManager.delete(idProductDelete);
                                        break;
                                    case "4":
                                        System.out.println("Nhập vào id muốn tìm kiếm.");
                                        int idProductFind = number.nextInt();
                                        productManager.findById(idProductFind);
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        System.out.println("Nhập sai mời nhập lại");
                                }
                            }
                        }
                        break;
                    case "4":
                        String choice4 = "-1";
                        if (ProductManager.isInteger(choice4)) {
                            while (!choice4.equals("0")) {
                                System.out.println("┌———————————————————————————————————┐");
                                System.out.println("⎟         QUẢN LÝ HÓA ĐƠN           ⎟");
                                System.out.println("⎟———————————————————————————————————⎟");
                                System.out.println("⎟1. Danh sách hóa đơn.              ⎟");
                                System.out.println("⎟2. Xóa hóa đơn.                    ⎟");
                                System.out.println("⎟3. Tìm kiếm hóa đơn.               ⎟");
                                System.out.println("⎟4. Tổng doanh thu.                 ⎟");
                                System.out.println("⎟0. Quay lại.                       ⎟");
                                System.out.println("└———————————————————————————————————┘");
                                System.out.println("Nhập lựa chọn :");
                                choice4 = word.nextLine();
                                switch (choice4) {
                                    case "1":
                                        invoiceManager.showAll();
                                        break;
                                    case "2":
                                        invoiceManager.showAll();
                                        System.out.println("Nhập vào id hóa đơn xóa.");
                                        int idInvoiceDelete = number.nextInt();
                                        invoiceManager.delete(idInvoiceDelete);
                                        break;
                                    case "3":
                                        System.out.println("Nhập vào id muốn tìm kiếm.");
                                        int idInvoiceFind = number.nextInt();
                                        invoiceManager.findById(idInvoiceFind);
                                        break;
                                    case "4":
                                        invoiceManager.showAll();
                                        System.out.println("Tổng doanh thu:  " + invoiceManager.totalRevenue());
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        System.out.println("Nhập sai mời nhập lại");
                                }
                            }
                        }
                        break;
                    case "5":
                        ChatServer.sever();
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println("Nhập sai, mời nhập lại!!!");
                        break;
                }
            }
        }
    }
}
