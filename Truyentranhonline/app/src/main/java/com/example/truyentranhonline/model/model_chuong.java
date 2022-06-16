package com.example.truyentranhonline.model;

//model này có các trường trùng với table trong mysql
public class model_chuong {
    private int idtruyen;
    private String chuong,tenchuong,noidung;
    private int idloaitruyen;

    //constructor giúp truyền param khi khởi tạo model
    public model_chuong(int idtruyen, String chuong, String tenchuong, String noidung, int idloaitruyen) {
        this.idtruyen = idtruyen;
        this.chuong = chuong;
        this.tenchuong = tenchuong;
        this.noidung = noidung;
        this.idloaitruyen = idloaitruyen;
    }

    //getter and setter(lấy và set dữ liệu)
    public int getIdtruyen() {
        return idtruyen;
    }

    public void setIdtruyen(int idtruyen) {
        this.idtruyen = idtruyen;
    }

    public String getChuong() {
        return chuong;
    }

    public void setChuong(String chuong) {
        this.chuong = chuong;
    }

    public String getTenchuong() {
        return tenchuong;
    }

    public void setTenchuong(String tenchuong) {
        this.tenchuong = tenchuong;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getIdloaitruyen() {
        return idloaitruyen;
    }

    public void setIdloaitruyen(int idloaitruyen) {
        this.idloaitruyen = idloaitruyen;
    }
}
