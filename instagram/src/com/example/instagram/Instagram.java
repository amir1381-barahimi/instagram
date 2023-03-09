package com.example.instagram;

import com.example.instagram.Exeption.Invalid_user;

import java.util.*;

public class Instagram {
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args){

        L1:

        while(true) {
            try {
                System.out.println("1) NEW USER\n" + "2) DELETE USER\n" + "3) LOGIN USER\n" + "4) SEARCH USER WITH ID\n" + "5) SEARCH USER WITH MOBILE NUMBER\n" + "6) EXIT");
                int s = sc.nextInt();
                switch (s) {
                    case 1: {
                        try {
                            System.out.println("please enter the name");
                            String name = sc.next();
                            System.out.println("please enter the family");
                            String family = sc.next();
                            System.out.println("please enter the ID");
                            String ID = sc.next();
                            System.out.println("please enter the mobile number");
                            String mobile_number = sc.next();

                            new_User(name, family, ID, mobile_number);
                        } catch (RuntimeException RE) {
                            System.out.println(RE.getMessage());
                        }
                        break;
                    }
                    case 2: {
                        try {
                            User user_temp = get_search_user(users);
                            delete_user(user_temp);
                        } catch (RuntimeException RE) {
                            System.out.println(RE.getMessage());
                        }
                        break;
                    }
                    case 3: {
                        try {
                            L2:
                            while (true) {
                                System.out.println("1) SHOW USER LIST");              //لاگین یک کابر با نشان دادن لیست کاربران
                                System.out.println("2) LOGIN WITH ID");             //لاگین کاربر با وارد کردن آیدی
                                System.out.println("3) BACK");
                                switch (sc.nextInt()) {
                                    case 1: {
                                        try {
                                            User user_select = get_search_user(users);
                                            L3:
                                            while (true) {
                                                System.out.println("1) SEE ALL POST");
                                                System.out.println("2) NEW POST");
                                                System.out.println("3) SEE USER POST");        //دیدن پست های یک کاربر
                                                System.out.println("4) DELETE MY POST");       //حذف یکی از پست های خودمان
                                                System.out.println("5) FALLOW");
                                                System.out.println("6) UNFOLLOW");
                                                System.out.println("7) DELETE FOLLOWERS");
                                                System.out.println("8) BACK");
                                                switch (sc.nextInt()) {
                                                    case 1: {
                                                        try {
                                                            L4:
                                                            for (Post p : Post.all_post) {
                                                                p.print_info();
                                                                Post.seen_post(p, Post.all_post);
                                                                System.out.println("1) LIKE");
                                                                System.out.println("2) COMMENT");
                                                                System.out.println("3) NEXT POST");
                                                                System.out.println("4) BACK");
                                                                switch (sc.nextInt()) {
                                                                    case 1: {
                                                                        try {
                                                                            p.like(user_select);
                                                                        } catch (RuntimeException RE) {
                                                                            System.out.println(RE.getMessage());
                                                                        }
                                                                        break;
                                                                    }
                                                                    case 2: {
                                                                        try {
                                                                            System.out.println("please enter a comment for this post");
                                                                            String text = sc.next();
                                                                            p.comment(text, user_select);
                                                                        } catch (RuntimeException RE) {
                                                                            System.out.println(RE.getMessage());
                                                                        }
                                                                        break;
                                                                    }
                                                                    case 3: {
                                                                        try {
                                                                            continue L4;
                                                                        } catch (RuntimeException RE) {
                                                                            System.out.println(RE.getMessage());
                                                                        }

                                                                    }
                                                                    case 4: {
                                                                        break L4;
                                                                    }
                                                                }

                                                            }
                                                        } catch (RuntimeException RE) {
                                                            System.out.println(RE.getMessage());
                                                        }
                                                        break;
                                                    }
                                                    case 2: {
                                                        try {
                                                            System.out.println("please enter a text for post");
                                                            String text = sc.next();
                                                            user_select.new_post(new Post(text, user_select));
                                                        } catch (RuntimeException RE) {
                                                            System.out.println(RE.getMessage());

                                                        }
                                                        break;
                                                    }
                                                    case 3: {
                                                        try {
                                                            User user = get_search_user(users);
                                                            if (user.posts.isEmpty()) {
                                                                System.out.println("no post");
                                                            }
                                                            L5:
                                                            for (Post p : user.posts) {
                                                                p.print_info();
                                                                System.out.println("1) LIKE");
                                                                System.out.println("2) COMMENT");
                                                                System.out.println("3) NEXT POST");
                                                                System.out.println("4) BACK");
                                                                S3:
                                                                switch (sc.nextInt()) {
                                                                    case 1: {
                                                                        try {
                                                                            p.like(user_select);
                                                                        } catch (RuntimeException RE) {
                                                                            System.out.println(RE.getMessage());
                                                                        }
                                                                        break S3;
                                                                    }
                                                                    case 2: {
                                                                        try {
                                                                            System.out.println("please enter a comment for this post");
                                                                            String text = sc.next();
                                                                            p.comment(text, user_select);
                                                                        } catch (RuntimeException RE) {
                                                                            System.out.println(RE.getMessage());
                                                                        }
                                                                        break S3;
                                                                    }
                                                                    case 3: {
                                                                        try {
                                                                            continue L5;
                                                                        } catch (RuntimeException RE) {
                                                                            System.out.println(RE.getMessage());
                                                                        }

                                                                    }
                                                                    case 4: {
                                                                        break L5;
                                                                    }
                                                                }

                                                            }
                                                        } catch (RuntimeException RE) {
                                                            System.out.println(RE.getMessage());
                                                        }
                                                        break;
                                                    }
                                                    case 4: {
                                                        try {
                                                            Object post_temp = Post.get_search_post(user_select.posts);
                                                            user_select.delete_post((Post) post_temp);
                                                        } catch (RuntimeException RE) {
                                                            System.out.println(RE.getMessage());
                                                        }
                                                        break;
                                                    }
                                                    case 5: {
                                                        try {
                                                            User follow_user = get_search_user(users);
                                                            user_select.follow(follow_user);
                                                        } catch (RuntimeException RE) {
                                                            System.out.println(RE.getMessage());
                                                        }
                                                        break;
                                                    }
                                                    case 6: {
                                                        try {
                                                            User unFollow = get_search_user(users);
                                                            user_select.unFollow(unFollow);
                                                        } catch (RuntimeException RE) {
                                                            System.out.println(RE.getMessage());
                                                        }
                                                        break;
                                                    }
                                                    case 7: {
                                                        try {
                                                            User delete_user = get_search_user(users);
                                                            user_select.delete_followers(user_select);
                                                        } catch (RuntimeException RE) {
                                                            System.out.println(RE.getMessage());
                                                        }
                                                        break;
                                                    }
                                                    case 8: {
                                                        break L3;
                                                    }
                                                }

                                            }
                                        } catch (RuntimeException RE) {
                                            System.out.println(RE.getMessage());

                                        }
                                        break;
                                    }
                                    case 2: {
                                        User user_select = get_search_user_key(sc.next(), users);
                                        L3:
                                        while (true) {
                                            System.out.println("1) SEE ALL POST");
                                            System.out.println("2) NEW POST");
                                            System.out.println("3) SEE USER POST");         //دیدن پست های یک کابر
                                            System.out.println("4) DELETE MY POST");
                                            System.out.println("5) FALLOW");
                                            System.out.println("6) UNFOLLOW");
                                            System.out.println("7) DELETE FOLLOWERS");
                                            System.out.println("8) BACK");
                                            switch (sc.nextInt()) {
                                                case 1: {
                                                    L4:
                                                    for (Post p : Post.all_post) {
                                                        p.print_info();
                                                        System.out.println("1) LIKE");
                                                        System.out.println("2) COMMENT");
                                                        System.out.println("3) NEXT POST");
                                                        System.out.println("4) BACK");
                                                        S1:
                                                        switch (sc.nextInt()) {
                                                            case 1: {
                                                                try {
                                                                    p.like(user_select);
                                                                } catch (RuntimeException RE) {
                                                                    System.out.println(RE.getMessage());
                                                                }
                                                                break S1;
                                                            }
                                                            case 2: {
                                                                try {
                                                                    System.out.println("please enter a comment for this post");
                                                                    String text = sc.next();
                                                                    p.comment(text, user_select);
                                                                } catch (RuntimeException RE) {
                                                                    System.out.println(RE.getMessage());
                                                                }
                                                                break S1;
                                                            }
                                                            case 3: {
                                                                try {
                                                                    continue L4;
                                                                } catch (RuntimeException RE) {
                                                                    System.out.println(RE.getMessage());
                                                                }

                                                            }
                                                            case 4: {
                                                                break L4;
                                                            }
                                                        }

                                                    }
                                                    break;
                                                }
                                                case 2: {
                                                    System.out.println("please enter a text for post");
                                                    String text = sc.next();
                                                    user_select.new_post(new Post(text, user_select));
                                                    break;
                                                }
                                                case 3: {
                                                    User user = get_search_user(users);
                                                    L5:
                                                    for (Post p : user.posts) {
                                                        p.print_info();
                                                        System.out.println("1) LIKE");
                                                        System.out.println("2) COMMENT");
                                                        System.out.println("3) NEXT POST");
                                                        System.out.println("4) BACK");
                                                        S2:
                                                        switch (sc.nextInt()) {
                                                            case 1: {
                                                                try {
                                                                    p.like(user_select);
                                                                } catch (RuntimeException RE) {
                                                                    System.out.println(RE.getMessage());
                                                                }
                                                                break S2;
                                                            }
                                                            case 2: {
                                                                try {
                                                                    System.out.println("please enter a comment for this post");
                                                                    String text = sc.next();
                                                                    p.comment(text, user_select);
                                                                } catch (RuntimeException RE) {
                                                                    System.out.println(RE.getMessage());
                                                                }
                                                                break S2;
                                                            }
                                                            case 3: {
                                                                try {
                                                                    continue L5;

                                                                } catch (RuntimeException RE) {
                                                                    System.out.println(RE.getMessage());
                                                                }
                                                            }
                                                            case 4: {
                                                                break L5;
                                                            }
                                                        }

                                                    }
                                                    break;
                                                }
                                                case 4: {
                                                    Object post_temp = Post.get_search_post(user_select.posts);
                                                    user_select.delete_post((Post) post_temp);
                                                    break;
                                                }
                                                case 5:{
                                                    try {
                                                        User follow_user = get_search_user(users);
                                                        user_select.follow(follow_user);
                                                    } catch (RuntimeException RE) {
                                                        System.out.println(RE.getMessage());
                                                    }
                                                    break ;
                                                }
                                                case 6:{
                                                    try {
                                                        User unFollow = get_search_user(users);
                                                        user_select.unFollow(unFollow);
                                                    } catch (RuntimeException RE) {
                                                        System.out.println(RE.getMessage());
                                                    }
                                                    break ;
                                                }
                                                case 7:{
                                                    try {
                                                        User delete_user = get_search_user(users);
                                                        user_select.delete_followers(user_select);
                                                    } catch (RuntimeException RE) {
                                                        System.out.println(RE.getMessage());
                                                    }
                                                    break ;
                                                }
                                                case 8: {
                                                    break L3;
                                                }
                                            }

                                        }
                                        break;
                                    }
                                    case 3: {
                                        break L2;
                                    }
                                }

                            }
                        } catch (RuntimeException RE) {
                            System.out.println(RE.getMessage());
                        }
                        break;
                    }
                    case 4: {
                        try {
                            System.out.println("please enter the ID: ");
                            get_search_user_key(sc.next(), users).print_info_user();

                        } catch (RuntimeException RE) {

                            System.out.println(RE.getMessage());
                        }
                        break;
                    }
                    case 5: {
                        try {
                            System.out.println("please enter the mobile number");
                            search_ID(sc.next(), users_ID);
                        } catch (RuntimeException RE) {
                            System.out.println(RE.getMessage());
                        }
                        break;
                    }

                    case 6: {
                        break L1;
                    }
                }
            }catch (InputMismatchException IE){
                System.out.println("you must input number");
            }
        }

    }
    public static void search_ID(String mobile_number,Map<String,List<String>> mobile_ID){
        if(mobile_ID.containsKey(mobile_number)) {
            List<String> temp = mobile_ID.get(mobile_number);
            System.out.println(temp.size()+" ID exist with this mobile number");
            for (String s:temp) {
                System.out.println(s);
            }
        }else{
            throw new Invalid_user("ID with this mobile number not exist");
        }
    }

    public static User get_search_user(ArrayList <User> user_list){          //لیست کابران را نشان داده و بعد از انتخاب یکی رو بر میگرداند
        Collections.sort(user_list);
        for(int i=0;i<user_list.size();i++){
            System.out.println((i+1)+") "+user_list.get(i).info());
        }
        System.out.println("PLEASE ENTER THE NUMBER OF USER");
        int num=sc.nextInt();
        return user_list.get(num-1);
    }
    public static User get_search_user_key(String ID,ArrayList<User> users){      //ایدی رو گرفته و کابر متناظر را برمیگرداند
        for(User u:users){
            if(u.getID().equals(ID)){
                return u;
            }
        }
        throw new Invalid_user("user with this ID not found");
    }
    public static void new_User(String name, String family, String ID, String mobile_number){        //تعریف کابر جدید

        if(userID_exist(ID,users)){
            if(users_ID.containsKey(mobile_number)){
                users_ID.get(mobile_number).add(ID);
            }else{
                List<String> user_ID=new ArrayList<>();
                users_ID.put(mobile_number,user_ID);
                users_ID.get(mobile_number).add(ID);
            }
            Instagram.users.add(new User(name,family,ID,mobile_number));
            System.out.println("user create successfully");
        }else{
            throw new Invalid_user("this user ID created before");
        }
    }
    public static boolean userID_exist(String ID,ArrayList<User> userArrayList){            //چک میکند آیا این آیدی وجود دارد(قبلا ساخته شده یا ن)
        for(User u:userArrayList){
            if(u.getID().equals(ID)){
                return false;
            }
        }
        return true;
    }
    public static void delete_user(User user){              //دیلیت اکانت یک کابر
        users.remove(user);
        for(User u: users){
            if(u.followings.contains(user)){
                u.followings.remove(user);
            }

            if(Post.all_post.containsAll(u.posts)){
                Post.all_post.removeAll(u.posts);
            }
            if(u.followers.contains(user)){
                u.followers.remove(user);
            }
        }
        for(int i=0;i<users_ID.size();i++){
            if(users_ID.containsKey(user)){
                users_ID.remove(user).remove(user.getID());
            }
        }
        for(Post p:Post.all_post){
            if(p.like.contains(user)){
                p.like.remove(user);
            }
            if(p.comment.containsKey(user)){
                p.comment.remove(user);
            }
            if(p.writer_post.equals(user)){
                Post.all_post.removeAll(user.posts);
            }
        }
        System.out.println("user delete successfully");
    }
    public static ArrayList<User> users=new ArrayList<>();               //کل کابران اینستا
    public static HashMap<String , List<String>> users_ID=new HashMap<>();        // برای وجود داشتتن چند اکانت(آیدی) با یک شماره تلفن
}

