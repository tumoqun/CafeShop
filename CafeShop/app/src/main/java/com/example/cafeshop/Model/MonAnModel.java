package com.example.cafeshop.Model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cafeshop.Controller.Interface.MonAnInterface;
import com.example.cafeshop.Controller.Interface.ThucUongInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MonAnModel {
    String  tenmonan,mamonant;
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
        return mamonant;
    }

    public void setMamonant(String mamonant) {
        this.mamonant = mamonant;
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
//                ThucUongModel thucUongModel=dataSnapshot1ThucUong.getValue(ThucUongModel.class);
//                //Log.d("ktduyetmonan",monAnModel.getTenmonan());//Lỗi vì chưa có setter cho mã thức uống do cấp mã động
//                monAnModelList.add(thucUongModel);
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

            ThucUongModel thucUongModel = valueMonAn.getValue(ThucUongModel.class); //lấy 1 quán ăn
            //Log.d("ktduyetthucuong",thucUongModel.getTenthucuong());
            thucUongModel.setMathucuong(valueThucUong.getKey());

            DataSnapshot dataSnapshotHinhThucUong = dataSnapshot.child("hinhthucuong").child(valueThucUong.getKey()); //getket lấy mã thức uống
            List<String> hinhanhList = new ArrayList<>(); //mỗi quán ăn có 1 list hình ảnh, duyệt for lấy hình ảnh lưu vào list của mỗi thức uống
//                    Log.d("ktHinhThucUong",dataSnapshotHinhThucUong + "");
            //Duyệt key hình ảnh thức uống:
            for(DataSnapshot valueHinhThucUong :dataSnapshotHinhThucUong.getChildren()){
//                            Log.d("ktHinhThucUong",valueHinhThucUong + "");
                hinhanhList.add(valueHinhThucUong.getValue(String.class));
            }
            thucUongModel.setHinhanh(hinhanhList);
            thucUongInterface.getDanhSachThucUongModel(thucUongModel);
        }
    }




}
