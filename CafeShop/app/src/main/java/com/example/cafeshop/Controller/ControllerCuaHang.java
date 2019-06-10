package com.example.cafeshop.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cafeshop.Adapters.AdapterRecyclerCuaHang;
import com.example.cafeshop.Controller.Interface.CuaHangInterface;
import com.example.cafeshop.FontManager;
import com.example.cafeshop.Model.CuaHangModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ControllerCuaHang {
    Context context;
    CuaHangModel cuaHangModel;
    AdapterRecyclerCuaHang adapterRecyclerCuaHang;
    int itemdaload=8;



    public  ControllerCuaHang(Context context){
        this.context=context;
        cuaHangModel = new CuaHangModel();
    }

    //controller nhận recycler view của fragment truyền qua
    public  void getDanhSachCuaHangController(NestedScrollView nestedScrollViewCuaHang, RecyclerView recyclerViewCuaHang, final ProgressBar progressBar){

        final List<CuaHangModel> cuaHangModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewCuaHang.setLayoutManager(layoutManager);
        adapterRecyclerCuaHang = new AdapterRecyclerCuaHang(cuaHangModelList, R.layout.layout_recyclerview_cuahang);
        recyclerViewCuaHang.setAdapter(adapterRecyclerCuaHang);


//        recyclerViewCuaHang.setLayoutManager(new GridLayoutManager(context,2));
        //new một interface để CuaHangModel sẽ nhận vô CuaHangInterface

        ////Kiểm tra nestedScrollView có tới tận cùng của item đã load chưa

        progressBar.setVisibility(View.VISIBLE);

        final CuaHangInterface cuaHangInterface = new CuaHangInterface() {
            @Override
            public void getDanhSachCuaHangModel(final CuaHangModel cuaHangModel) {
                final List<Bitmap> bitmaps= new ArrayList<>();
                for(String linkhinh: cuaHangModel.getHinhanhcuahang()){
                    StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);//Lấy hình ảnh vị trí 0 (get(0)) trong list hình ảnh của 1 thức uống
                    //Thực hiện download
                    long ONE_MEGABYTE = 1024 * 1024;
                    storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            //chuyển kiểu byte về bitmap:
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            bitmaps.add(bitmap);
                            cuaHangModel.setBitmapList(bitmaps);

                            if(cuaHangModel.getBitmapList().size() == cuaHangModel.getHinhanhcuahang().size()){
                                cuaHangModelList.add(cuaHangModel);
                                adapterRecyclerCuaHang.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }


            }
        };

        //lắng nghe sự kiện scroll của nested scrollview
        nestedScrollViewCuaHang.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nsv, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (nsv.getChildAt(nsv.getChildCount() -1) !=null) //kiểm tra vị trí 0 có khác null k, nếu có con:
                {
                    //kiểm tra scrollY có >= chiều cao của tất cả những con đang nằm trong nó lkhông
                    if(scrollY >= (nsv.getChildAt(nsv.getChildCount()-1)).getMeasuredHeight()- nsv.getMeasuredHeight()){
                        itemdaload +=8;
                        cuaHangModel.getDanhSachCuaHang(cuaHangInterface,itemdaload,itemdaload-8);
                    }
                }
            }
        });


        cuaHangModel.getDanhSachCuaHang(cuaHangInterface,itemdaload,0);
    }
}
