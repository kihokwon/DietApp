package com.example.dietapp.login;

// 사용자 계정 정보 모델 class
public class UserAccount {
    private  String email_id;
    private String pwd;
    private String idToken;


    public UserAccount(){} // firebase RealTime database를 사용할때는 default class를 반드시 작성해야함.
    public String getEmail_id(){return email_id;}
    public String getPwd(){return pwd;}
    public String getIdToken(){return idToken;}
    public void setEmail_id(String email_id){this.email_id = email_id;}
    public void setPwd(String pwd){this.pwd = pwd;}
    public void setIdToken(String idToken){this.idToken = idToken;}
}