package com.example.cafeshop.Model;

public class DonHangModel {
    String madonhang;
    String thoigian;
    String nguoidat;
    String diachi;
    String chitietdonhang;
    String tonggia;
    String SDT;
    String IdUser;

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public DonHangModel(){

    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public DonHangModel(String madonhang, String thoigian, String nguoidat, String diachi, String chitietdonhang, String tonggia, String SDT, String idUser) {
        this.madonhang = madonhang;
        this.thoigian = thoigian;
        this.nguoidat = nguoidat;
        this.diachi = diachi;
        this.chitietdonhang = chitietdonhang;
        this.tonggia = tonggia;
        this.SDT = SDT;
        IdUser = idUser;
    }

    public String getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(String madonhang) {
        this.madonhang = madonhang;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getNguoidat() {
        return nguoidat;
    }

    public void setNguoidat(String nguoidat) {
        this.nguoidat = nguoidat;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getChitietdonhang() {
        return chitietdonhang;
    }

    public void setChitietdonhang(String chitietdonhang) {
        this.chitietdonhang = chitietdonhang;
    }

    public String getTonggia() {
        return tonggia;
    }

    public void setTonggia(String tonggia) {
        this.tonggia = tonggia;
    }
}
