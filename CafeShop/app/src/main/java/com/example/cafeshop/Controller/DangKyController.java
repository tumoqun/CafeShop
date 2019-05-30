package com.example.cafeshop.Controller;

import com.example.cafeshop.Model.ThanhVienModel;

public class DangKyController {
    ThanhVienModel thanhVienModel;
    public DangKyController(){
        thanhVienModel=new ThanhVienModel();
    }
    public void ThemtThongtinThanhVienController(ThanhVienModel thanhVienModel,String uid){
        thanhVienModel.ThemThongTinThanhVien(thanhVienModel,uid);
    }
}
