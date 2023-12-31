package services;

import models.PC;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PCManager implements IQLQN<PC> {
    List<PC> pcList = new ArrayList<>();
    public int idPCIncrement = 1;

    public PCManager() throws IOException {
        pcList = readFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/pc.csv");

    }

    public List<PC> getPcList() {
        return pcList;
    }

    @Override
    public void addNew(PC pc) throws IOException {
        pcList.add(pc);
        pc.setIdPC(idPCIncrement);
        idPCIncrement++;
        savePCFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/pc.csv", pcList);
    }

    @Override
    public void edit(int id) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int index = -1;
        for (int i = 0; i < pcList.size(); i++) {
            if (pcList.get(i).getIdPC() == id) {
                index = i;
            }
        }
        if (index != -1) {
            System.out.println("Sửa giá tiền\n" +
                    "Nhập giá tiền mới:");
            double newPrice = scanner.nextDouble();
            pcList.get(index).setPricePC(newPrice);
            savePCFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/pc.csv", pcList);
        } else {
            System.out.println("Không tìm thấy thông tin máy id" + id);
        }
    }

    @Override
    public void delete(int id) throws IOException {
        int index = -1;
        for (int i = 0; i < pcList.size(); i++) {
            if (pcList.get(i).getIdPC() == id) {
                index = i;
            }
        }
        if (index != -1) {
            pcList.remove(index);
            System.out.println("Xóa thành công");
            savePCFile("/Users/chiuchiuleuleu/Desktop/Project/MD2/QuanLyQuanNetBoDoi/src/data/pc.csv", pcList);
        } else {
            System.out.println("Không tìm thấy thông tin máy id" + id);
        }
    }

    public boolean checkUsed(int id) {
        int index = -1;
        for (int i = 0; i < pcList.size(); i++) {
            if (pcList.get(i).getIdPC() == id) {
                index = i;
            }
        }
        if (index != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkStatus(int id) throws IOException {
        int index = -1;
        for (int i = 0; i < pcList.size(); i++) {
            if (pcList.get(i).getIdPC() == id) {
                index = i;
            }
        }
        if (index != -1) {
            if (pcList.get(index).getCheckStatus()) {
                System.out.println("máy đã có người sử dụng!!!");
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void showAll() {
        System.out.println("Danh sách máy: ");
        for (PC pc : pcList) {
            if (pc.getCheckStatus()) {
                System.out.println("id: " + pc.getIdPC() + ", price: " + pc.getPricePC() + " Trạng thái: Có người sử dụng.");
            } else {
                System.out.println("id: " + pc.getIdPC() + ", price: " + pc.getPricePC() + " Trạng thái: Còn trống, có thể sử dụng.");
            }
        }
    }

    public void showAllAdmin() {
        System.out.println("Danh sách máy: ");
        for (PC pc : pcList) {
            if (pc.getCheckStatus()) {
                System.out.println("id: " + pc.getIdPC() + ", price: " + pc.getPricePC() + " Trạng thái: Có người sử dụng. UserName: " + pc.getUserNamePlay());
            } else {
                System.out.println("id: " + pc.getIdPC() + ", price: " + pc.getPricePC() + " Trạng thái: Còn trống, có thể sử dụng.");
            }
        }
    }

    public boolean checkDuplicate(String user) {
        boolean check = false;
        int index = -1;
        for (int i = 0; i < pcList.size(); i++)
            if (pcList.get(i).getUserNamePlay().equals(user)) {
                index = i;
            }
        if(index!=-1){
            System.out.println("Tài khoản đã có người sử dụng!!!");
            check = true;
        }
        else {
            check = false;
        }
        return check;
    }

    @Override
    public void findById(int id) {
        for (PC p : pcList) {
            if (p.getIdPC() == id) {
                System.out.println(p);
            }
        }
    }

    public static void savePCFile(String path, List<PC> pcList) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String str = "Id, Price, Status, Username\n";
        for (PC p : pcList) {
            str += p.getIdPC() + ", " + p.getPricePC() + ", " + p.getCheckStatus() + ", " + p.getUserNamePlay() + "\n";
        }
        bufferedWriter.write(str);
        bufferedWriter.close();
        fileWriter.close();
    }

    public List<PC> readFile(String path) throws IOException {
        List<PC> list = new ArrayList<>();
        UserManager userManager = new UserManager();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            String[] value = line.split(", ");
            int id = Integer.parseInt(value[0]);
            double price = Double.parseDouble(value[1]);
            boolean checkstatus = Boolean.parseBoolean(value[2]);
            String userName = value[3];
            PC pc = new PC(price);
            list.add(pc);
            pc.setCheckStatus(checkstatus);
            pc.setUserNamePlay(userName);
            pc.setIdPC(idPCIncrement);
            idPCIncrement++;
        }
        bufferedReader.close();
        fileReader.close();
        return list;
    }
}
