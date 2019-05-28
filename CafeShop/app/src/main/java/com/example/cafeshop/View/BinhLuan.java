package com.example.cafeshop.View;

import android.net.Uri;

public class BinhLuan{
    String tieude;
    String binhluan;
    String thoigian;
    String hinh;

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(String binhluan) {
        this.binhluan = binhluan;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public BinhLuan(String tieude, String binhluan, String thoigian, String hinh) {
        this.tieude = tieude;
        this.binhluan = binhluan;
        this.thoigian = thoigian;
        this.hinh = hinh;
    }

    public BinhLuan(){
    }
}
