package com.example.instagram;

import com.example.instagram.Exeption.Invalid_user;
import com.example.instagram.Exeption.PostException;

import java.util.*;

public class Post {
    String text;
    User writer_post;
    HashSet<User> like = new HashSet<>();         //تا هر نفر یکبار بیشتر نتواند لایک کند
    public static ArrayDeque<Post> all_post = new ArrayDeque<>();    //توانایی خواندن از آخرین پست اضافه شده و درج در ابتدا
    public LinkedHashMap<User,String> comment=new LinkedHashMap<>();     //تناظر بین نویسنده کامنت و خود کامنت

    public Post(String text,User writer_post) {       //سازنده
        this.text = text;
        this.writer_post=writer_post;
    }
    public static void new_post(Post post){           //پست جدید
        if(all_post.contains(post)){
            throw new PostException("this post is create before");
        }else {
            all_post.add(post);
        }
    }
    public static void delete_post(Post post){          //حذف پست
        if(all_post.contains(post)){
            all_post.remove(post);
        }else{
            throw new PostException("this post not exist");
        }
    }
    public void print_info(){                     //نشان دادن اطلاعات یک پست
        System.out.println("__________ "+this.writer_post.getID()+" __________");
        System.out.println("POST : "+text);
        System.out.println("LIKE : "+like.size());
        System.out.println("COMMENT : ");
        show_comment();
    }
    public void show_comment(){              //سرچ روی تمام کابران در صورت وجود در نویسندگان کامنت برای این پست آن را همرا با نویسنده چاپ میکند
        for (User u:Instagram.users){
            if(this.comment.containsKey(u)){
                for(int i=0;i<this.comment.size();i++){
                    System.out.println("writer : "+u.getID()+"\t"+comment.get(u));
                }
            }
        }

    }
    public void like(User user){
        if(!this.like.contains(user))
            this.like.add(user);
        else{
            throw new PostException("you like this before");
        }
    }
    public void comment(String text,User writer){
        this.comment.put(writer,text);
    }

    public static void seen_post(Post post,ArrayDeque<Post> posts){     //اضافه کردن پست دیده شده به ابتدای صف
        if(posts.contains(post)){
            posts.addLast(posts.removeFirst());
        }else {
            throw new PostException("this post not found");
        }
    }
    public static Object get_search_post(ArrayDeque<Post> posts){       //انتخاب یک پست از لیست تمام پست ها و برگرداندن ان
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<posts.size();i++){
            System.out.println((i+1)+")");
            posts.getLast().print_info();
        }
        System.out.println("please enter the number of the post");
        Object[] postArrayList=posts.toArray();
        return postArrayList[sc.nextInt()-1];
    }
}
