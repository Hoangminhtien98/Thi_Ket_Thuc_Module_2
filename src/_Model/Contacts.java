package _Model;

import java.time.LocalDate;

public class Contacts {
    private String phoneNumber;
    private String group;
    private String name;
    private String gender;
    private String address;
    private LocalDate dateOfBirth;
    private String email;

    public Contacts() {
    }

    public Contacts(String phoneNumber, String group, String name, String gender, String address, LocalDate dateOfBirth, String email) {
        this.phoneNumber = phoneNumber;
        this.group = group;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "số điện thoại:'" + phoneNumber + '\'' +
                ", nhóm: '" + group + '\'' +
                ", tên: '" + name + '\'' +
                ", giới tính:'" + gender + '\'' +
                ", địa chỉ:'" + address + '\'' +
                ", ngày sinh:" + dateOfBirth +
                ", email:'" + email + '\'' +
                '}';
    }
}
