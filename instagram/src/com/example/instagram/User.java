package com.example.instagram;

import com.example.instagram.Exeption.Invalid_user;
import com.example.instagram.Exeption.PostException;

import java.util.*;

public class User implements Comparable<User> {
    private String name;
    private String family;
    private String ID;
    private String mobile_number;
    HashSet<User> followings = new HashSet<>();   //نداشتن فالوینگ تکراری
    HashSet<User> followers = new HashSet<>();    //نداشتن فالور تکراری
    ArrayDeque<Post> posts=new ArrayDeque<>();      // توانایی خواندن از اخر و درج در اول

    //_________________________setter_________________________//

    public void setName(String name) {
        this.name = name;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    //_________________________getter_________________________//
    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getID() {
        return ID;
    }

    public String getMobile_number() {
        return mobile_number;
    }


    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

//_______________________________________constructor________________________________//
    public User(String name, String family, String ID,String mobile_number) {            //constructor
        this.ID = ID;
        this.family = family;
        this.name = name;
        this.mobile_number=mobile_number;
    }
//______________________________________methods_______________________________________//
    public void follow(User user) {             // این تابع یک کاربر میگیرد و ان را به لیست فالوینگ های ما اضافه میکند
        if(this.equals(user)){
            throw new Invalid_user("you can not follow yourself");
        }
        if(this.followings.add(user))
            user.followers.add(this);
        else
            throw new Invalid_user("this user you fallow it before");
    }

    public void unFollow(User user) {           //این تابع یک کابر میگیرد و آن را از لیست فالور ما حذف میکند و ما را از لیست فالوینگ او حذف میکند
        if(this.followings.remove(user))
            user.followers.remove(this);
        else{
            throw new Invalid_user("you do not follow this user");
        }
    }

    public void delete_followers(User user) {             //این تابع یک کابر میگیرد و آن را از لیست فالوینگ ما حذف میکند و ما را از لیست فالور او حذف میکند
        if(this.followers.remove(user))
            user.followings.remove(this);
        else{
            throw new Invalid_user("this user do not follow you");
        }
    }
    public void new_post(Post post){             //اضفه کردن یک پست جدید
        if(posts.contains(post)){
            throw new PostException("this post create before by you");
        }else {
            this.posts.add(post);
            Post.all_post.add(post);
        }
    }

    public void print_info_user() {                        //این تابع اطلاعات کابر را چاپ میکند
        System.out.println("POST : "+this.posts.size()+"\tFOLLOWINGS : "+this.followings.size()+"\tFOLLOWERS : "+this.followers.size());
        System.out.println("USER ID :"+this.ID);
        System.out.println(this.name+" "+this.family);
    }

    public String info() {                                 //این تابع اطلاعات کابر را بر میگرداند
        return "name : " + this.name + "\tfamily : " + this.family + "\tID : " + this.ID + "\tfollowers : "+this.followers.size() +"\tfollowings : "+this.followings.size();
    }
    public void delete_post(Post post){            //حذف یکی از پست های خود کابر
        if(posts.contains(post)){
            posts.remove(post);
            Post.all_post.remove(post);
        }else{
            throw new PostException("this post not found");
        }
    }
    @Override
    public int compareTo(User user2){                   //پیاده سازی واسط کامپیربل برای داشتن توانایی سورت کردن
        double value1;
        double value2;
        try {
            value1=this.followers.size()*100/this.followings.size();
        }catch (ArithmeticException AE){
            value1=this.followers.size();
        }
        try {
            value2=user2.followers.size()*100/user2.followings.size();
        }catch (ArithmeticException AE){
            value2=user2.followers.size();
        }
        if(value1>value2){
            return 1;
        }else if(value1<value2){
            return -1;
        }
        else{
            return 0;
        }
    }
}
