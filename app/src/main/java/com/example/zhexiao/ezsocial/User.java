package com.example.zhexiao.ezsocial;

/**
 * Created by zhexiao on 11/27/16.
 */

public class User {

    Integer id;
    String f_name;
    String l_name;
    String password;
    String email;


    public  User() { }


    public void setFName (String first_name){
        this.f_name = first_name;
    }

    public String getFName (){
        return f_name;
    }

    public void setLName (String last_name){
        this.l_name = last_name;
    }

    public String getLName (){
        return l_name;
    }

    public void setId (Integer i){
        this.id = i;
    }

    public Integer getId (){
        return id;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public String getEmail (){
        return email;
    }

    public void setPassword (String pw){
        this.password = pw;
    }

    public String getPassword (){
        return password;
    }
}
