package com.example.truyentranhonline.model;

//model này có các trường trùng với table trong mysql
public class model_getdatauser {
    private int iduser;
    private String username,password,sodienthoai,email,diachi;
    private String luuchuong;

    //constructor giúp truyền param khi khởi tạo model
    public model_getdatauser(int iduser, String username, String password, String sodienthoai, String email, String diachi, String luuchuong) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.sodienthoai = sodienthoai;
        this.email = email;
        this.diachi = diachi;
        this.luuchuong = luuchuong;
    }

    //getter and setter(lấy và set dữ liệu)
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getLuuchuong() {
        return luuchuong;
    }

    public void setLuuchuong(String luuchuong) {
        this.luuchuong = luuchuong;
    }

}
