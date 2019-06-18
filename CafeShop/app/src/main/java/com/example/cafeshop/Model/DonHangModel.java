package com.example.cafeshop.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cafeshop.Controller.Interface.DonHangInterface;
import com.example.cafeshop.Controller.Interface.TinTucInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DonHangModel {
    String madonhang;
    String thoigian;
    String nguoidat;
    String diachi;
    String chitietdonhang;
    String tonggia;
    String SDT;
    String IdUser;

    DatabaseReference nodeRoot; //tạo reference đến node lớn nhất trong DB

    public DonHangModel(){
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }
>>>>>>> 1512325_1

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public DonHangModel(String madonhang, String thoigian, String nguoidat, String diachi, String chitietdonhang, String tonggia, String SDT) {
        this.madonhang = madonhang;
        this.thoigian = thoigian;
        this.nguoidat = nguoidat;
        this.diachi = diachi;
        this.chitietdonhang = chitietdonhang;
        this.tonggia = tonggia;
        this.SDT = SDT;
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

    //Minh
    private DataSnapshot dataRoot;

    //Cần lấy nhiều dữ liệu của nhiều bảng => lắng nghe node cha lớn nhất (root)
    public  void getDanhSachDonHang(final DonHangInterface donHangInterface){
//        tạo interface:
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataRoot=dataSnapshot;
                layDanhSachDonHang(dataSnapshot,donHangInterface);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        if(dataRoot!=null){
            layDanhSachDonHang(dataRoot,donHangInterface);
        }else {
            nodeRoot.addListenerForSingleValueEvent((valueEventListener));
        }

    }

    private  void   layDanhSachDonHang(DataSnapshot dataSnapshot, DonHangInterface donHangInterface)
    //item tiếp theo = số item trước đó + 1 sl item nhỏ  (5) để download thêm
    //item đã load = số item trạng thái đã load bnhiêu cái
    //dataSnapshot = dataRoot
    {
        Log.d("ktdataRoot",dataSnapshot +"");
        //muốn đi vào bảng nào thì dataSnapshot.child("tên bảng");
        DataSnapshot dataSnapshotDonHang=dataSnapshot.child("donhang");
        //đụng key động => duyệt key: Lấy danh sách thức uống:

            DonHangModel donHangModel = valueDonHang.getValue(DonHangModel.class); //lấy 1 món ăn
            donHangModel.setMadonhang(valueDonHang.getKey());

            DataSnapshot dataSnapshotHinhTinTuc = dataSnapshot.child("hinhtintuc").child(valueDonHang.getKey()); //getket lấy mã thức uống
            List<String> hinhanhList = new ArrayList<>(); //mỗi quán ăn có 1 list hình ảnh, duyệt for lấy hình ảnh lưu vào list của mỗi thức uống
            //Duyệt key hình ảnh món ăn:
            for(DataSnapshot valueHinhDonHang :dataSnapshotHinhTinTuc.getChildren()){
                hinhanhList.add(valueHinhTinTuc.getValue(String.class));
            }
            tinTucModel.setHinhanhtintuc(hinhanhList);
            donHangInterface.getDanhSachDonHangModel(donHangModel);
        }
    }
}

