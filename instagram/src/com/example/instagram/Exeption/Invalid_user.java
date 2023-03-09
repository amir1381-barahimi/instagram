package com.example.instagram.Exeption;

public class Invalid_user extends RuntimeException{     //خطاهای مربوط به کابر
    public Invalid_user(String msg){
        super(msg);
    }
    public Invalid_user(){
        super("Error : Invalid user");
    }
}
