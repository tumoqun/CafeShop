package com.example.cafeshop.Model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cafeshop.Controller.Interface.ThucUongInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ThucUongModel {
    String  tenthucuong, thanhphan,mathucuong;
    //Firebase không có kiểu int và mảng array
    long luotthich;
    double danhgia;
    List<String> hinhanh;
    DatabaseReference nodeRoot; //tạo reference đến node lớn nhất trong DB
    List<Bitmap> bitmapList;
    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }

    public List<String> getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(List<String> hinhanh) {
        this.hinhanh = hinhanh;
    }

    public  ThucUongModel(){
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public double getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(double danhgia) {
        this.danhgia = danhgia;
    }

    public String getTenthucuong() {
        return tenthucuong;
    }

    public void setTenthucuong(String tenthucuong) {
        this.tenthucuong = tenthucuong;
    }

    public String getThanhphan() {
        return thanhphan;
    }

    public void setThanhphan(String thanhphan) {
        this.thanhphan = thanhphan;
    }

    public String getMathucuong() {
        return mathucuong;
    }

    public void setMathucuong(String mathucuong) {
        this.mathucuong = mathucuong;
    }


    private  DataSnapshot dataRoot;
    //Cần lấy nhiều dữ liệu của nhiều bảng => lắng nghe node cha lớn nhất (root)
    public  void getDanhSachThucUong(final ThucUongInterface thucUongInterface, final int itemtieptheo, final int itemdaload){
//        tạo interface:
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataRoot=dataSnapshot;
                layDanhSachThucUong(dataSnapshot,thucUongInterface,itemtieptheo,itemdaload);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        if(dataRoot!=null){
            layDanhSachThucUong(dataRoot,thucUongInterface,itemtieptheo,itemdaload);
        }else {
            nodeRoot.addListenerForSingleValueEvent((valueEventListener));
        }

    }

    private  void   layDanhSachThucUong(DataSnapshot dataSnapshot, ThucUongInterface thucUongInterface, int itemtieptheo, int itemdaload)
            //item tiếp theo = số item trước đó + 1 sl item nhỏ  (5) để download thêm
            //item đã load = số item trạng thái đã load bnhiêu cái
            //dataSnapshot = dataRoot
    {
        Log.d("ktdataRoot",dataSnapshot +"");
        //muốn đi vào bảng nào thì dataSnapshot.child("tên bảng");
        //Log.d("ktdataRoot",dataSnapshot.child("thucuong") +"");
        DataSnapshot dataSnapshotThucUong=dataSnapshot.child("thucuong");
//                ThucUongModel thucUongModel=dataSnapshot1ThucUong.getValue(ThucUongModel.class);
//                //Log.d("ktduyetthucuong",thucUongModel.getTenthucuong());//Lỗi vì chưa có setter cho mã thức uống do cấp mã động
//                thucUongModelList.add(thucUongModel);
        //đụng key động => duyệt key: Lấy danh sách thức uống:
        int i = 0; //đếm xem load đến sp thứ mấy
        for (DataSnapshot valueThucUong:dataSnapshotThucUong.getChildren()){

            if(i==itemtieptheo){
                break;
            }
            if(i<itemdaload){
                i++;
                continue;
            }
            i++;

            ThucUongModel thucUongModel = valueThucUong.getValue(ThucUongModel.class); //lấy 1 quán ăn
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
