package services;

import models.Invoice;
import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceManager implements IQLQN<Invoice> {
    List<Invoice> invoiceList = new ArrayList<>();
    public int idInvoiceIncrement = 1;

    public InvoiceManager() throws IOException {
        invoiceList = readFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/invoice.csv");
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }


    @Override
    public void addNew(Invoice invoice) throws IOException {
        invoiceList.add(invoice);
        invoice.setId(idInvoiceIncrement);
        idInvoiceIncrement++;
        saveInvoicetFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/invoice.csv", invoiceList);
    }

    @Override
    public void edit(int t) throws IOException {

    }

    @Override
    public void delete(int idInvoice) throws IOException {
        int index = -1;
        for (int i = 0; i < invoiceList.size(); i++) {
            if(invoiceList.get(i).getId() ==idInvoice){
                index = i;
            }
        }
        if (index!= -1){
            invoiceList.remove(index);
            System.out.println("Xóa thành công hóa đơn id: "+ idInvoice);
            saveInvoicetFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/invoice.csv", invoiceList);
        }
        else {
            System.out.println("Không tìm thấy thông tin hóa đơn id: " + idInvoice);
        }
    }

    @Override
    public void showAll() throws IOException {
        for (Invoice i : invoiceList) {
            System.out.println(i);
        }
    }

    @Override
    public void findById(int id) throws IOException {
        int index = -1;
        for (int i = 0; i < invoiceList.size(); i++) {
            if (invoiceList.get(i).getId() == id) {
                index = i;
            }
        }
        if (index != -1) {
            System.out.println(invoiceList.get(index));
            saveInvoicetFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/invoice.csv", invoiceList);

        } else {
            System.out.println("Không tìm thấy thông tin!!!");
        }
    }

    public double totalRevenue() {
        double totalRevenue = 0;
        for (Invoice i : invoiceList) {
            totalRevenue += i.getTotal();
        }
        return totalRevenue;
    }

    public static void saveInvoicetFile(String path, List<Invoice> invoiceList) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String str = "id, giờ chơi, tên hàng hóa , số lượng, giá bán, ID máy chơi, giá máy, User name, Tổng hóa đơn\n";
        for (Invoice i : invoiceList) {
            str +=  i.getId()
                    + ", " + i.getPlayTime()
                    + ", " + i.getBuyName()
                    + ", " + i.getBuyQuantity()
                    + ", " + i.getBuyPrice()
                    + ", " + i.getPcId()
                    + ", " + i.getPcPrice()
                    + ", " + i.getUser().getUserName()
                    + ", " + i.getTotal() + "\n";
        }
        bufferedWriter.write(str);
        bufferedWriter.close();
        fileWriter.close();
    }

    public List<Invoice> readFile(String path) throws IOException {
        UserManager userManager = new UserManager();
        List<Invoice> list = new ArrayList<>();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            String[] value = line.split(", ");
            int id = Integer.parseInt(value[0]);
            int time = Integer.parseInt(value[1]);
            String buyName = value[2];
            int buyQuantity = Integer.parseInt(value[3]);
            double buyPrice = Double.parseDouble(value[4]);
            int idPC = Integer.parseInt(value[5]);
            double pcPrice = Double.parseDouble(value[6]);
            String userName = value[7];
            String password = userManager.getPassByName(userName);
            double money = userManager.getMoneyByName(userName);
            User user = new User(userName, password, money);
            Invoice invoice = new Invoice(time, buyName, buyQuantity, buyPrice, idPC, pcPrice, user);
            list.add(invoice);
            invoice.setId(idInvoiceIncrement);
            idInvoiceIncrement++;

        }
        bufferedReader.close();
        fileReader.close();
        return list;
    }

}
