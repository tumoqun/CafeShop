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

import com.example.cafeshop.Adapters.AdapterRecyclerTinTuc;
import com.example.cafeshop.Controller.Interface.TinTucInterface;
import com.example.cafeshop.Model.TinTucModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ControllerTinTuc {
    Context context;
    TinTucModel tinTucModel;
    AdapterRecyclerTinTuc adapterRecyclerTinTuc;
    int itemdaload=8;



    public  ControllerTinTuc(Context context){
        this.context=context;
        tinTucModel = new TinTucModel();
    }

    //controller nhận recycler view của fragment truyền qua
    public  void getDanhSachTinTucController(NestedScrollView nestedScrollViewTinTuc, RecyclerView recyclerViewTinTuc, final ProgressBar progressBar){

        final List<TinTucModel> tinTucModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewTinTuc.setLayoutManager(layoutManager);
        adapterRecyclerTinTuc = new AdapterRecyclerTinTuc(tinTucModelList, R.layout.layout_recyclerview_tintuc);
        recyclerViewTinTuc.setAdapter(adapterRecyclerTinTuc);//set adapter cho recycler

        //Chia 2 column
//        recyclerViewTinTuc.setLayoutManager(new GridLayoutManager(context,2));


        ////Kiểm tra nestedScrollView có tới tận cùng của item đã load chưa

        progressBar.setVisibility(View.VISIBLE);
        //new một interface để TinTucModel sẽ nhận vô TinTucInterface
        final TinTucInterface tinTucInterface = new TinTucInterface() {
            @Override
            public void getDanhSachTinTucModel(final TinTucModel tinTucModel) {
                final List<Bitmap> bitmaps= new ArrayList<>();
                for(String linkhinh: tinTucModel.getHinhanhtintuc()){
                    StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);//Lấy hình ảnh vị trí 0 (get(0)) trong list hình ảnh của 1 thức uống
                    //Thực hiện download
                    long ONE_MEGABYTE = 1024 * 1024;
                    storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            //chuyển kiểu byte về bitmap:
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            bitmaps.add(bitmap);
                            tinTucModel.setBitmapList(bitmaps);

                            if(tinTucModel.getBitmapList().size() == tinTucModel.getHinhanhtintuc().size()){
                                tinTucModelList.add(tinTucModel);
                                adapterRecyclerTinTuc.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }


            }
        };

        //lắng nghe sự kiện scroll của nested scrollview
        nestedScrollViewTinTuc.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nsv, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (nsv.getChildAt(nsv.getChildCount() -1) !=null) //kiểm tra vị trí 0 có khác null k, nếu có con:
                {
                    //kiểm tra scrollY có >= chiều cao của tất cả những con đang nằm trong nó lkhông
                    if(scrollY >= (nsv.getChildAt(nsv.getChildCount()-1)).getMeasuredHeight()- nsv.getMeasuredHeight()){
                        itemdaload +=8;
                        tinTucModel.getDanhSachTinTuc(tinTucInterface,itemdaload,itemdaload-8);
                    }
                }
            }
        });


        tinTucModel.getDanhSachTinTuc(tinTucInterface,itemdaload,0);
    }
}
