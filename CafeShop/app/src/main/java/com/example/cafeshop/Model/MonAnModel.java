package com.example.cafeshop.Model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cafeshop.Controller.Interface.MonAnInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MonAnModel {
    String  tenmonan,mamonan;
    //Firebase không có kiểu int và mảng array
    long luotthich,giatien;
    double danhgia;
    List<String> hinhanhmonan;
    DatabaseReference nodeRoot; //tạo reference đến node lớn nhất trong DB
    List<Bitmap> bitmapList;

    public String getTenmonan() {
        return tenmonan;
    }

    public void setTenmonan(String tenmonan) {
        this.tenmonan = tenmonan;
    }

    public String getMamonant() {
        return mamonan;
    }

    public void setMamonan(String mamonan) {
        this.mamonan = mamonan;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public long getGiatien() {
        return giatien;
    }

    public void setGiatien(long giatien) {
        this.giatien = giatien;
    }

    public double getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(double danhgia) {
        this.danhgia = danhgia;
    }

    public List<String> getHinhanhmonan() {
        return hinhanhmonan;
    }

    public void setHinhanhmonan(List<String> hinhanhmonan) {
        this.hinhanhmonan = hinhanhmonan;
    }

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }


    public  MonAnModel(){
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }


    private  DataSnapshot dataRoot;
    //Cần lấy nhiều dữ liệu của nhiều bảng => lắng nghe node cha lớn nhất (root)
    public  void getDanhSachMonAn(final MonAnInterface monAnInterface, final int itemtieptheo, final int itemdaload){
//        tạo interface:
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataRoot=dataSnapshot;
                layDanhSachMonAn(dataSnapshot,monAnInterface,itemtieptheo,itemdaload);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        if(dataRoot!=null){
            layDanhSachMonAn(dataRoot,monAnInterface,itemtieptheo,itemdaload);
        }else {
            nodeRoot.addListenerForSingleValueEvent((valueEventListener));
        }

    }

    private  void   layDanhSachMonAn(DataSnapshot dataSnapshot, MonAnInterface monAnInterface, int itemtieptheo, int itemdaload)
    //item tiếp theo = số item trước đó + 1 sl item nhỏ  (5) để download thêm
    //item đã load = số item trạng thái đã load bnhiêu cái
    //dataSnapshot = dataRoot
    {
        Log.d("ktdataRoot",dataSnapshot +"");
        //muốn đi vào bảng nào thì dataSnapshot.child("tên bảng");
        //Log.d("ktdataRoot",dataSnapshot.child("monan") +"");
        DataSnapshot dataSnapshotMonAn=dataSnapshot.child("monan");
        //đụng key động => duyệt key: Lấy danh sách thức uống:
        int i = 0; //đếm xem load đến sp thứ mấy
        for (DataSnapshot valueMonAn:dataSnapshotMonAn.getChildren()){

            if(i==itemtieptheo){
                break;
            }
            if(i<itemdaload){
                i++;
                continue;
            }
            i++;

            MonAnModel monAnModel = valueMonAn.getValue(MonAnModel.class); //lấy 1 món ăn
            monAnModel.setMamonan(valueMonAn.getKey());

            DataSnapshot dataSnapshotHinhMonAn = dataSnapshot.child("hinhmonan").child(valueMonAn.getKey()); //getket lấy mã thức uống
            List<String> hinhanhList = new ArrayList<>(); //mỗi quán ăn có 1 list hình ảnh, duyệt for lấy hình ảnh lưu vào list của mỗi thức uống
            //Duyệt key hình ảnh món ăn:
            for(DataSnapshot valueHinhMonAn :dataSnapshotHinhMonAn.getChildren()){
                hinhanhList.add(valueHinhMonAn.getValue(String.class));
            }
            monAnModel.setHinhanhmonan(hinhanhList);
            monAnInterface.getDanhSachMonAnModel(monAnModel);
        }
    }




}
