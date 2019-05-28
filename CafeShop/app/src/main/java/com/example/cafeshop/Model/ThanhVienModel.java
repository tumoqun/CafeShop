package com.example.cafeshop.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThanhVienModel {
    String hoten, sdt,hinhanh,email,diachi;
    long diem;
    DatabaseReference dataNodeThanhVien;

    ThanhVienModel(){
        dataNodeThanhVien = FirebaseDatabase.getInstance().getReference().child("thanhvien");//tạo reference đến node thành viên
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

    public  void ThemThongTinThanhVien(ThanhVienModel thanhVienModel){
        dataNodeThanhVien.setValue(thanhVienModel);

    }


}
