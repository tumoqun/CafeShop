package com.example.cafeshop.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cafeshop.Adapters.AdapterRecyclerThucUong;
import com.example.cafeshop.Controller.Interface.ThucUongInterface;
import com.example.cafeshop.Model.ThucUongModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ControllerThucUong {
    Context context;
    ThucUongModel thucUongModel;
    AdapterRecyclerThucUong adapterRecyclerThucUong;
    int itemdaload=8;



    public  ControllerThucUong(Context context){
        this.context=context;
        thucUongModel = new ThucUongModel();
    }

    //controller nhận recycler view của fragment truyền qua
    public  void getDanhSachThucUongController(NestedScrollView nestedScrollViewThucUong, RecyclerView recyclerViewThucUong, final ProgressBar progressBar){

       final List<ThucUongModel> thucUongModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewThucUong.setLayoutManager(layoutManager);
        adapterRecyclerThucUong = new AdapterRecyclerThucUong(thucUongModelList, R.layout.layout_recyclerview_thucuong);

        //GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
       // gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        //recyclerViewThucUong.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

        recyclerViewThucUong.setAdapter(adapterRecyclerThucUong);
        recyclerViewThucUong.setLayoutManager(new GridLayoutManager(context,2));
        //new một interface để ThucUongModel sẽ nhận vô ThucUongInterface

        ////Kiểm tra nestedScrollView có tới tận cùng của item đã load chưa

        progressBar.setVisibility(View.VISIBLE);

        final ThucUongInterface thucUongInterface = new ThucUongInterface() {
            @Override
            public void getDanhSachThucUongModel(final ThucUongModel thucUongModel) {
                //Log.d("ktNodeThucUong",thucUongModel.getTenthucuong() +"");
                final List<Bitmap> bitmaps= new ArrayList<>();
                for(String linkhinh: thucUongModel.getHinhanh()){
                    StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);//Lấy hình ảnh vị trí 0 (get(0)) trong list hình ảnh của 1 thức uống
                    //Thực hiện download
                    long ONE_MEGABYTE = 1024 * 1024;
                    storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            //chuyển kiểu byte về bitmap:
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            bitmaps.add(bitmap);
                            thucUongModel.setBitmapList(bitmaps);

                            if(thucUongModel.getBitmapList().size() == thucUongModel.getHinhanh().size()){
                                thucUongModelList.add(thucUongModel);
                                adapterRecyclerThucUong.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }


            }
        };

        //lắng nghe sự kiện scroll của nested scrollview
        nestedScrollViewThucUong.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nsv, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (nsv.getChildAt(nsv.getChildCount() -1) !=null) //kiểm tra vị trí 0 có khác null k, nếu có con:
                {
                    //kiểm tra scrollY có >= chiều cao của tất cả những con đang nằm trong nó lkhông
                    if(scrollY >= (nsv.getChildAt(nsv.getChildCount()-1)).getMeasuredHeight()- nsv.getMeasuredHeight()){
                        itemdaload +=8;
                        thucUongModel.getDanhSachThucUong(thucUongInterface,itemdaload,itemdaload-8);
                    }
                }
            }
        });


        thucUongModel.getDanhSachThucUong(thucUongInterface,itemdaload,0);
    }
}
