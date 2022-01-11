package _Main;

import _Manager.ContactManager;
import _Model.Contacts;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    ContactManager contactManager = new ContactManager();

    public Menu() {
    }

    public void menuContact() {
        try {
            do {
                System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ----");
                System.out.println("Chọn chức năng theo số (để tiếp tục):");
                System.out.println("1. Xem danh sách");
                System.out.println("2. Thêm mới");
                System.out.println("3. Cập nhật");
                System.out.println("4. Xóa");
                System.out.println("5. Tìm kiếm");
                System.out.println("6. Đọc từ file");
                System.out.println("7. Ghi vào file");
                System.out.println("8. Thoát");
                System.out.println("Chọn chức năng:");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 8) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                }
                switch (choice) {
                    case 1:
                        contactManager.displayAll();
                        break;
                    case 2:
                        contactManager.add();
                        break;
                    case 3:
                        System.out.println("▹ Nhập số điện thoại cần sửa (+84)-933334444:");
                        String phoneEdit = scanner.nextLine();
                        if (phoneEdit.equals("")) {
                            menuContact();
                        } else {
                            contactManager.update(phoneEdit);
                        }
                        break;
                    case 4:
                        System.out.println("▹ Nhập số điện thoại cần sửa (+84)-933334444:");
                        String phoneDelete = scanner.nextLine();
                        if (phoneDelete.equals("")) {
                            menuContact();
                        } else {
                            contactManager.deleteContact(phoneDelete);
                        }
                        break;
                    case 5:
                        System.out.println("▹ Nhập từ khóa:");
                        String keyword = scanner.nextLine();
                        contactManager.searchContactByNameOrPhone(keyword);
                        break;
                    case 6:
                        ArrayList<Contacts> contactArrayList = contactManager.readFile(ContactManager.PATH_NAME);
                        contactArrayList.forEach(System.out::println);
                        System.out.println("Read file successfully !");
                        System.out.println("--------------------");
                        break;
                    case 7:
                        contactManager.writeFile(contactManager.getContact(), ContactManager.PATH_NAME);
                        break;
                    case 8:
                        System.exit(8);
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("⛔ Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuContact();
        }
    }
}
