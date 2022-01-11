package _Manager;

import _Model.Contacts;
import _Validate.Validate;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {
    private ArrayList<Contacts> contactList;
    private final Scanner scanner = new Scanner(System.in);
    private final Validate validate = new Validate();
    public static final String PATH_NAME = "src/File/contacts.csv";

    public ContactManager() {
        if (new File(PATH_NAME).length() == 0) {
            this.contactList = new ArrayList<>();
        } else {
            this.contactList = readFile(PATH_NAME);
        }
    }
    public ArrayList<Contacts> getContact() {
        return contactList;
    }
    public String getGender(int choice) {
        String gender = "";
        switch (choice) {
            case 1:
                gender = "Male";
                break;
            case 2:
                gender = "Female";
                break;
        }
        return gender;
    }
    public String getPhoneNumber() {
        String phoneNumber;
        while (true) {
            System.out.println("Nhập số điện thoại");
            String phone = scanner.nextLine();
            if (!validate.validatePhone(phone)) {
                System.out.println("Số điện thoại không hợp lệ !!!");
                System.out.println("[Chú ý]: Số điện thoại phả có định dạng 9 hoặc 10 số định dạng :(+84)-0961579256");
            } else {
                phoneNumber = phone;
                break;
            }
        }
        return phoneNumber;
    }
    public String getEmail() {
        String email;
        while (true) {
            System.out.println("Nhập email: ");
            String inputEmail = scanner.nextLine();
            if (!validate.validateEmail(inputEmail)) {
                System.out.println("Email không hợp lệ");
                System.out.println("Email phải có định dạng là: abc.zxc@gmail.com");
            } else {
                email = inputEmail;
                break;
            }
        }
        return email;
    }
    public void add() {
        System.out.println("Mời bạn nhập thông tin:");
        String phoneNumber = getPhoneNumber();
        System.out.println("Nhập tên nhóm:");
        String group = scanner.nextLine();
        System.out.println("Nhập Họ và Tên:");
        String name = scanner.nextLine();
        System.out.println("Nhập giới tính:");
        System.out.println("1. Male");
        System.out.println("2. Female");
        int gender = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        System.out.println("Nhập ngày sinh(dd-mm-yy):");
        String date = scanner.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-LL-yy"));
        String email = getEmail();
        if (getGender(gender).equals("")) {
            System.out.println("Nhập sai lựa chọn, mời nhập lại!!");
            return;
        }
        for (Contacts c : contactList) {
            if (c.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("Số điện thoại bị trùng, Mời nhập lại số khác!");
                return;
            }
        }
        Contacts contact = new Contacts(phoneNumber, group, name, getGender(gender), address, dateOfBirth, email);
        contactList.add(contact);
        writeFile(contactList, PATH_NAME);
        System.out.println("Thêm " + phoneNumber + " vào danh bạ thành công !");
        System.out.println("--------------------");
    }
    public void update(String phoneNumber) {
        Contacts editContacts = null;
        for (Contacts c : contactList) {
            if (c.getPhoneNumber().equals(phoneNumber)) {
                editContacts = c;
            }
        }
        if (editContacts != null) {
            int index = contactList.indexOf(editContacts);
            System.out.println("Nhập tên nhóm mới");
            editContacts.setGroup(scanner.nextLine());
            System.out.println("Nhập Họ và tên mới:");
            editContacts.setName(scanner.nextLine());
            System.out.println("▹ Nhập giới tính mới:");
            System.out.println("▹ 1. Male");
            System.out.println("▹ 2. Female");
            int gender = Integer.parseInt(scanner.nextLine());
            editContacts.setGender(getGender(gender));
            System.out.println("▹ Nhập địa chỉ mới:");
            editContacts.setAddress(scanner.nextLine());
            System.out.println("▹ Nhập ngày sinh mới(dd-mm-yyyy):");
            String date = scanner.nextLine();
            LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
            editContacts.setDateOfBirth(dateOfBirth);
            editContacts.setEmail(getEmail());
            contactList.set(index, editContacts);
            writeFile(contactList, PATH_NAME);
            System.out.println("Sửa" + phoneNumber + "Thành công!");
        } else {
            System.out.println("không tìm đc danh bạ với số điện thoại trên !");
        }
    }

    public void deleteContact(String phoneNumber) {
        Contacts deleteContact = null;
        for (Contacts c : contactList) {
            if (c.getPhoneNumber().equals(phoneNumber)) {
                deleteContact = c;
            }
        }
        if (deleteContact != null) {
            System.out.println("▹ Nhập xác nhận:");
            System.out.println("▹ Y: Xóa");
            System.out.println("▹ Ký tự khác: Thoát");
            String confirm = scanner.next();
            if (confirm.equalsIgnoreCase("y")) {
                contactList.remove(deleteContact);
                writeFile(contactList, PATH_NAME);
                System.out.println("Xóa " + phoneNumber + " thành công !");
                System.out.println("--------------------");
            }
        } else {
            System.out.println(" Không tìm thấy danh bạ với số điện thoại trên !");
            System.out.println("--------------------");
        }

    }
    public void writeFile(ArrayList<Contacts> contactList, String path) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for (Contacts contact : contactList) {
                bufferedWriter.write(contact.getPhoneNumber() + "," + contact.getGroup() + "," + contact.getName() + "," + contact.getGender() +
                        "," + contact.getAddress() + "," + contact.getDateOfBirth() + "," + contact.getEmail() + "\n");
            }
            bufferedWriter.close();
            System.out.println("Ghi File Thành Công!!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void searchContactByNameOrPhone(String keyword) {
        ArrayList<Contacts> contacts = new ArrayList<>();
        for (Contacts c : contactList) {
            if (validate.validatePhoneOrName(keyword, c.getPhoneNumber()) || validate.validatePhoneOrName(keyword, c.getName())) {
                contacts.add(c);
            }
        }
        if (contacts.isEmpty()) {
            System.out.println("⛔ Không tìm thấy danh bạ với từ khóa trên !");
            System.out.println("--------------------");
        } else {
            System.out.println("Danh bạ cần tìm:");
            contacts.forEach(System.out::println);
            System.out.println("--------------------");
        }
    }

    public void displayAll() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("| %-15s| %-10s| %-15s| %-10s| %-10s|\n", "Số điện thoại", "Nhóm", "Họ tên", "Giới tính", "Địa chỉ");
        System.out.println("-----------------------------------------------------------------------");
        for (Contacts c : contactList) {
            System.out.printf("| %-15s| %-10s| %-15s| %-10s| %-10s|\n", c.getPhoneNumber(), c.getGroup(), c.getName(), c.getGender(), c.getAddress());
            System.out.println("-----------------------------------------------------------------------");
        }
    }
    public ArrayList<Contacts> readFile(String path) {
        ArrayList<Contacts> contacts = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split(",");
                contacts.add(new Contacts(str[0], str[1], str[2], str[3], str[4], LocalDate.parse(str[5], DateTimeFormatter.ISO_LOCAL_DATE), str[6]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return contacts;
    }
}
