package com.example.cafeshop.Model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cafeshop.Controller.Interface.CuaHangInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CuaHangModel {

    String diachi;
    String sdt;
    String tencuahang;
    String macuahang;
    double kinhdo, vido;
    List<String> hinhanhcuahang;
    DatabaseReference nodeRoot; //tạo reference đến node lớn nhất trong DB
    List<Bitmap> bitmapList;


    public String getMacuahang() {
        return macuahang;
    }

    public void setMacuahang(String macuahang) {
        this.macuahang = macuahang;
    }

    public List<String> getHinhanhcuahang() {
        return hinhanhcuahang;
    }

    public void setHinhanhcuahang(List<String> hinhanhcuahang) {
        this.hinhanhcuahang = hinhanhcuahang;
    }

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTencuahang() {
        return tencuahang;
    }

    public void setTencuahang(String tencuahang) {
        this.tencuahang = tencuahang;
    }

    public double getKinhdo() {
        return kinhdo;
    }

    public void setKinhdo(double kinhdo) {
        this.kinhdo = kinhdo;
    }

    public double getVido() {
        return vido;
    }

    public void setVido(double vido) {
        this.vido = vido;
    }

    public CuaHangModel(){
        nodeRoot= FirebaseDatabase.getInstance().getReference();
    }
    private  DataSnapshot dataRoot;
    //Cần lấy nhiều dữ liệu của nhiều bảng => lắng nghe node cha lớn nhất (root)
    public  void getDanhSachCuaHang(final CuaHangInterface cuaHangInterface, final int itemtieptheo, final int itemdaload){
//        tạo interface:
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataRoot=dataSnapshot;
                layDanhSachCuaHang(dataSnapshot,cuaHangInterface,itemtieptheo,itemdaload);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        if(dataRoot!=null){
            layDanhSachCuaHang(dataRoot,cuaHangInterface,itemtieptheo,itemdaload);
        }else {
            nodeRoot.addListenerForSingleValueEvent((valueEventListener));
        }

    }

    private  void   layDanhSachCuaHang(DataSnapshot dataSnapshot, CuaHangInterface cuaHangInterface, int itemtieptheo, int itemdaload)
    //item tiếp theo = số item trước đó + 1 sl item nhỏ  (5) để download thêm
    //item đã load = số item trạng thái đã load bnhiêu cái
    //dataSnapshot = dataRoot
    {
        Log.d("ktdataRoot",dataSnapshot +"");
        //muốn đi vào bảng nào thì dataSnapshot.child("tên bảng");
        //Log.d("ktdataRoot",dataSnapshot.child("cuahang") +"");
        DataSnapshot dataSnapshotCuaHang=dataSnapshot.child("cuahang");
        //đụng key động => duyệt key: Lấy danh sách thức uống:
        int i = 0; //đếm xem load đến sp thứ mấy
        for (DataSnapshot valueCuaHang:dataSnapshotCuaHang.getChildren()){

            if(i==itemtieptheo){
                break;
            }
            if(i<itemdaload){
                i++;
                continue;
            }
            i++;

            CuaHangModel cuaHangModel = valueCuaHang.getValue(CuaHangModel.class); //lấy 1 món ăn
            cuaHangModel.setMacuahang(valueCuaHang.getKey());

            DataSnapshot dataSnapshotHinhCuaHang = dataSnapshot.child("hinhcuahang").child(valueCuaHang.getKey()); //getket lấy mã thức uống
            List<String> hinhanhList = new ArrayList<>(); //mỗi quán ăn có 1 list hình ảnh, duyệt for lấy hình ảnh lưu vào list của mỗi thức uống
            //Duyệt key hình ảnh món ăn:
            for(DataSnapshot valueHinhCuaHang :dataSnapshotHinhCuaHang.getChildren()){
                hinhanhList.add(valueHinhCuaHang.getValue(String.class));
            }
            cuaHangModel.setHinhanhcuahang(hinhanhList);
            cuaHangInterface.getDanhSachCuaHangModel(cuaHangModel);
        }
    }




}