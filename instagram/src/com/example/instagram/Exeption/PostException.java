package com.example.instagram.Exeption;

public class PostException extends RuntimeException{     //خطا های مربوط به پست ها
    public PostException(String msg){
        super(msg);
    }
    public PostException(){
        super("Error : Invalid Post");
    }
}
