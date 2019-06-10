package com.example.cafeshop.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThanhVienModel {
    private String hoten;
    private String sdt;
    private String hinhanh;
    private String email;
    private String diachi;

    public ThanhVienModel(String hoten, String sdt, String hinhanh, String email, String diachi, String username, long diem) {
        this.hoten = hoten;
        this.sdt = sdt;
        this.hinhanh = hinhanh;
        this.email = email;
        this.diachi = diachi;
        this.username = username;
        this.diem = diem;
    }

    private String username;


    long diem;
    private DatabaseReference dataNodeThanhVien;

    public ThanhVienModel(){
    }
    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
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

    public long getDiem() {
        return diem;
    }

    public void setDiem(long diem) {
        this.diem = diem;
    }

    public  void ThemThongTinThanhVien(ThanhVienModel thanhVienModel,String uid){
        dataNodeThanhVien = FirebaseDatabase.getInstance().getReference().child("thanhvien");//tạo reference đến node thành viên
        dataNodeThanhVien.child(uid).setValue(thanhVienModel);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
