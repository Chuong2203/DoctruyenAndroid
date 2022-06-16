package com.example.truyentranhonline.model;

//model này có các trường trùng với table trong mysql
public class model_loaitruyen {
    private int id;
    private String tentruyen;
    private int loaitruyen;

    //constructor giúp truyền param khi khởi tạo model
    public model_loaitruyen(int id, String tentruyen, int loaitruyen) {
        this.id = id;
        this.tentruyen = tentruyen;
        this.loaitruyen = loaitruyen;
    }

    //getter and setter(lấy và set dữ liệu)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public int getLoaitruyen() {
        return loaitruyen;
    }

    public void setLoaitruyen(int loaitruyen) {
        this.loaitruyen = loaitruyen;
    }
}
