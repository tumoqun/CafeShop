package com.example.cafeshop.Model;

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
    String hinhanh, tenthucuong, thanhphan,mathucuong;
    //Firebase không có kiểu int và mảng array
    long luotthich;
    double danhgia;
    DatabaseReference nodeRoot; //node lớn nhất trong DB

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
    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
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



    //Cần lấy nhiều dữ liệu của nhiều bảng => lắng nghe node cha lớn nhất (root)
    public  void getDanhSachThucUong(final ThucUongInterface thucUongInterface){

//        tạo interface:
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.d("ktdataRoot",dataSnapshot +"");
                //muốn đi vào bảng nào thì dataSnapshot.child("tên bảng");
                //Log.d("ktdataRoot",dataSnapshot.child("thucuong") +"");
                DataSnapshot dataSnapshotThucUong=dataSnapshot.child("thucuong");
//                ThucUongModel thucUongModel=dataSnapshot1ThucUong.getValue(ThucUongModel.class);
//                //Log.d("ktduyetthucuong",thucUongModel.getTenthucuong());//Lỗi vì chưa có setter cho mã thức uống do cấp mã động
//                thucUongModelList.add(thucUongModel);
                //đụng key động => duyệt key:
                for (DataSnapshot valueThucUong:dataSnapshotThucUong.getChildren()){
                    ThucUongModel thucUongModel = valueThucUong.getValue(ThucUongModel.class);
                    //Log.d("ktduyetthucuong",thucUongModel.getTenthucuong());

                    thucUongInterface.getDanhSachThucUongModel(thucUongModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        nodeRoot.addListenerForSingleValueEvent((valueEventListener));
    }


//Nháp phân tích lỗi thường gặp
//    //duyệt node quán ăn
//    //add thư viện realtime database vô
//    //khai bao bien lấy dữ liệu node thucuong
//    DatabaseReference dataThucUong;
//
//
//    public ThucUongModel(){
//        //Khởi tạo đường dẫn tới node thucuong
//        dataThucUong = FirebaseDatabase.getInstance().getReference().child("thucuong");
//
//    }
//    //Tạo list danh sách Thức uống
//    List <ThucUongModel> thucUongModelList;
//
//    public List<ThucUongModel> getDanhSachThucUong(){
//        thucUongModelList = new ArrayList<>();
//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //Log.d("ktdata",dataSnapshot + "");
//                //duyệt child (các mã thức uống phát sinh động)
//                for (DataSnapshot dataValue : dataSnapshot.getChildren()) {
//                    //Log.d("ktdata",dataValue + "");
//                    ThucUongModel  thucUongModel = dataValue.getValue(ThucUongModel.class);
//                    thucUongModelList.add(thucUongModel);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        //chỉ lắng nghe một lần duy nhất
//        //khi gọi addListenerForSingleValueEvent sẽ bắt sự kiện của ValueEventListênr và chạy vào trong hàm ValueEventListener
//        dataThucUong.addListenerForSingleValueEvent(valueEventListener);
//        return thucUongModelList;
//    }



}
