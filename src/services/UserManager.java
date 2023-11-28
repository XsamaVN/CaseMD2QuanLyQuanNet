package services;

import models.PC;
import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager implements IQLQN<User> {

    List<User> userList;
    public static int idUserIncrement = 1;
    public UserManager() throws IOException {
        userList = readFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/user.csv");

    }

    @Override
    public void addNew(User user) throws IOException {
        userList.add(user);
        user.setId(idUserIncrement++);
        UserManager.saveUserFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/user.csv", userList);
    }


    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public UserManager(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public void edit(int id) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int index = -1;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                index = i;
            }
        }
        if (index != -1) {
            String choice = "-1";
            if (isInteger(choice)) {
                while (true) {
                    System.out.println("┌———————————————————————————————————┐");
                    System.out.println("⎟      SỬA THÔNG TIN NGƯỜI DÙNG     ⎟");
                    System.out.println("⎟———————————————————————————————————⎟");
                    System.out.println("⎟1. Sửa tên đăng nhập.              ⎟");
                    System.out.println("⎟2. Sửa mật khẩu.                   ⎟");
                    System.out.println("⎟0. Quay lại                        ⎟");
                    System.out.println("└———————————————————————————————————┘");
                    System.out.println("Nhập lựa chọn :");
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.println("Nhập vào tên mới: ");
                            String newName = scanner.nextLine();
                            userList.get(index).setUserName(newName);
                            saveUserFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/user.csv", userList);
                            System.out.println("Sửa tên đăng nhập thành công!");
                            break;
                        case "2":
                            System.out.println("Nhập vào mật khẩu mới: ");
                            String newUserPassword = scanner.nextLine();
                            userList.get(index).setUserPassword(newUserPassword);
                            saveUserFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/user.csv", userList);
                            System.out.println("Sửa mật khẩu thành công!");
                            break;
                        case "0":
                            return;
                        default:
                            System.out.println("Nhập sai. Mời nhập lại");

                    }
                }
            }
        } else {
            System.out.println("Không tìm thấy thông tin id " + id);
        }
    }

    @Override
    public void delete(int id) throws IOException {
        int index = -1;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                index = i;
            }
        }
        if (index != -1) {
            userList.remove(index);
            System.out.println("Xóa thành công id: " + id);
            saveUserFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/user.csv", userList);
        } else {
            System.out.println("Không tìm thấy thông tin id: " + id);
        }

    }

    @Override
    public void showAll() {
        System.out.println("Danh sách tài khoản: ");
        for (User u : userList) {
            System.out.println(u);
        }
    }

    @Override
    public void findById(int id) {
        for (User u: userList) {
            if(u.getId() == id){
                System.out.println(u);
            }
        }
    }
    public double getMoneyByName(String name){
        for (User u:userList) {
            if(u.getUserName().equals(name)){
                return u.getMoneyCharge();
            }
        }
        return 0;
    }

    public void recharge(int id) throws IOException {
        int index = -1;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getId() == id){
                index = i;
            }
        }
        if(index != -1){
            System.out.println("Nhập vào số tiền muốn nạp: ");
            double charge = scanner.nextDouble();
            userList.get(index).setMoneyCharge(charge + userList.get(index).getMoneyCharge());
            System.out.println("Nạp tiền thành công!!!");
            saveUserFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/user.csv", userList);
        }
        else {
            System.out.println("Không tìm thây thông tin tài khoản id: "+ id);
        }
    }
    public boolean checkLogin(User user, double pcPrice){
        int index = -1;
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUserName().equals(user.getUserName()) && userList.get(i).getUserPassword().equals(user.getUserPassword())){
                index = i;
            }
        }
        if (index!= -1){
            if (user.getMoneyCharge() > pcPrice){
            System.out.println("Đăng nhập thành công!!!");
            return true;
            }
            else {
                System.out.println("Hết tiền rồi. Ra quầy nạp thêm nhé!!!!");
                return false;
            }
        }
        else {
            System.out.println("Đăng nhập thất bại!!!");
            return false;
        }
    }
    public String getPassByName(String name){
        String pass = null;
        for (User u: userList) {
            if(u.getUserName().equals(name)){
                pass = u.getUserPassword();
            }
        }
        return pass;
    }


    public static void saveUserFile(String path, List<User> userList) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String str = "id, user, password, số tiền\n";
        for (User u : userList) {
            str += u.getId() + ", " + u.getUserName() + ", " + u.getUserPassword() + ", " + u.getMoneyCharge() + "\n";
        }
        bufferedWriter.write(str);
        bufferedWriter.close();
        fileWriter.close();
    }

    public List<User> readFile(String path) throws IOException {
        List<User> list = new ArrayList<>();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            String[] value = line.split(", ");
            int id = Integer.parseInt(value[0]);
            String name = value[1];
            String password = value[2];
            double money = Double.parseDouble(value[3]);
            User user = new User(name, password, money);
            list.add(user);
            user.setId(idUserIncrement);
            idUserIncrement++;
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
            System.out.println("nhập sai");
            return false;
        }
    }
}
