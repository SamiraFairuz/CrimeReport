package com.example.On_The_Go_Crime_Report.Model;

public class User {
    private String Password;
    private String Address;
    private String Email;
    private String NID;
    private String Phone;
    private String UserType;

    public User(String p, String a, String e, String nid1, String phone1,String userType1){
        this.Password =p;
        this.Address= a;
        this.Email= e;
        this.NID= nid1;
        this.Phone= phone1;
        this.UserType= userType1;
    }
    public User(){

    }
    public  String getAddress(){
        return Address;
    }
    public  String getEmail(){
        return Email;
    }
    public  String getNID(){
        return NID;
    }
    public  String getPhone(){
        return Phone;
    }

    public String getPassword(){
        return Password;
    }
    public String getUserType(){
        return UserType;
    }



}
