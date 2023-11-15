package com.vz.foodydonatingapp.models;

public class CustomerInfo {
    private String name;
    private String mobile;
    private String address;
    private String role;
    private String profession;
    private String email;
    private String password;
   private String  profilepic;


    public CustomerInfo(){

    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public CustomerInfo(String name, String role, String email, String password) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public CustomerInfo(String mobile, String role) {
        this.mobile = mobile;
        this.role = role;
    }

//    public CustomerInfo(String name, String mobile, String address, String role, String profession,
//                        String email, String password) {
//        this.name = name;
//        this.mobile = mobile;
//        this.address = address;
//        this.role = role;
//        this.profession = profession;
//        this.email = email;
//        this.password = password;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
