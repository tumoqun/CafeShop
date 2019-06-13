package com.example.cafeshop.Model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cafeshop.Controller.Interface.TinTucInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TinTucModel {
    String  tieude,noidung,matintuc;
    //Firebase không có kiểu int và mảng array
    List<String> hinhanhtintuc;
    DatabaseReference nodeRoot; //tạo reference đến node lớn nhất trong DB
    List<Bitmap> bitmapList;

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getMatintuc() {
        return matintuc;
    }

    public void setMatintuc(String matintuc) {
        this.matintuc = matintuc;
    }

    public List<String> getHinhanhtintuc() {
        return hinhanhtintuc;
    }

    public void setHinhanhtintuc(List<String> hinhanhtintuc) {
        this.hinhanhtintuc = hinhanhtintuc;
    }

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }


    public  TinTucModel(){
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }


    private  DataSnapshot dataRoot;
    //Cần lấy nhiều dữ liệu của nhiều bảng => lắng nghe node cha lớn nhất (root)
    public  void getDanhSachTinTuc(final TinTucInterface tinTucInterface, final int itemtieptheo, final int itemdaload){
//        tạo interface:
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataRoot=dataSnapshot;
                layDanhSachTinTuc(dataSnapshot,tinTucInterface,itemtieptheo,itemdaload);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        if(dataRoot!=null){
            layDanhSachTinTuc(dataRoot,tinTucInterface,itemtieptheo,itemdaload);
        }else {
            nodeRoot.addListenerForSingleValueEvent((valueEventListener));
        }

    }

    private  void   layDanhSachTinTuc(DataSnapshot dataSnapshot, TinTucInterface tinTucInterface, int itemtieptheo, int itemdaload)
    //item tiếp theo = số item trước đó + 1 sl item nhỏ  (5) để download thêm
    //item đã load = số item trạng thái đã load bnhiêu cái
    //dataSnapshot = dataRoot
    {
        Log.d("ktdataRoot",dataSnapshot +"");
        //muốn đi vào bảng nào thì dataSnapshot.child("tên bảng");
        //Log.d("ktdataRoot",dataSnapshot.child("tintuc") +"");
        DataSnapshot dataSnapshotTinTuc=dataSnapshot.child("tintuc");
        //đụng key động => duyệt key: Lấy danh sách thức uống:
        int i = 0; //đếm xem load đến sp thứ mấy
        for (DataSnapshot valueTinTuc:dataSnapshotTinTuc.getChildren()){

            if(i==itemtieptheo){
                break;
            }
            if(i<itemdaload){
                i++;
                continue;
            }
            i++;

            TinTucModel tinTucModel = valueTinTuc.getValue(TinTucModel.class); //lấy 1 món ăn
            tinTucModel.setMatintuc(valueTinTuc.getKey());

            DataSnapshot dataSnapshotHinhTinTuc = dataSnapshot.child("hinhtintuc").child(valueTinTuc.getKey()); //getket lấy mã thức uống
            List<String> hinhanhList = new ArrayList<>(); //mỗi quán ăn có 1 list hình ảnh, duyệt for lấy hình ảnh lưu vào list của mỗi thức uống
            //Duyệt key hình ảnh món ăn:
            for(DataSnapshot valueHinhTinTuc :dataSnapshotHinhTinTuc.getChildren()){
                hinhanhList.add(valueHinhTinTuc.getValue(String.class));
            }
            tinTucModel.setHinhanhtintuc(hinhanhList);
            tinTucInterface.getDanhSachTinTucModel(tinTucModel);
        }
    }
}
