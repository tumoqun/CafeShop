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

import com.example.cafeshop.Adapters.AdapterRecyclerMonAn;
import com.example.cafeshop.Controller.Interface.MonAnInterface;
import com.example.cafeshop.Model.MonAnModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ControllerMonAn {
    Context context;
    MonAnModel monAnModel;
    AdapterRecyclerMonAn adapterRecyclerMonAn;
    int itemdaload=8;



    public  ControllerMonAn(Context context){
        this.context=context;
        monAnModel = new MonAnModel();
    }

    //controller nhận recycler view của fragment truyền qua
    public  void getDanhSachMonAnController(NestedScrollView nestedScrollViewMonAn, RecyclerView recyclerViewMonAn, final ProgressBar progressBar){

        final List<MonAnModel> monAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewMonAn.setLayoutManager(layoutManager);
        adapterRecyclerMonAn = new AdapterRecyclerMonAn(monAnModelList, R.layout.layout_recyclerview_monan);

        recyclerViewMonAn.setAdapter(adapterRecyclerMonAn);
        recyclerViewMonAn.setLayoutManager(new GridLayoutManager(context,2));
        //new một interface để MonAnModel sẽ nhận vô MonAnInterface

        ////Kiểm tra nestedScrollView có tới tận cùng của item đã load chưa

        progressBar.setVisibility(View.VISIBLE);

        final MonAnInterface monAnInterface = new MonAnInterface() {
            @Override
            public void getDanhSachMonAnModel(final MonAnModel monAnModel) {
                final List<Bitmap> bitmaps= new ArrayList<>();
                for(String linkhinh: monAnModel.getHinhanhmonan()){
                    StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);//Lấy hình ảnh vị trí 0 (get(0)) trong list hình ảnh của 1 thức uống
                    //Thực hiện download
                    long ONE_MEGABYTE = 1024 * 1024;
                    storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            //chuyển kiểu byte về bitmap:
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            bitmaps.add(bitmap);
                            monAnModel.setBitmapList(bitmaps);

                            if(monAnModel.getBitmapList().size() == monAnModel.getHinhanhmonan().size()){
                                monAnModelList.add(monAnModel);
                                adapterRecyclerMonAn.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }


            }
        };

        //lắng nghe sự kiện scroll của nested scrollview
        nestedScrollViewMonAn.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nsv, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (nsv.getChildAt(nsv.getChildCount() -1) !=null) //kiểm tra vị trí 0 có khác null k, nếu có con:
                {
                    //kiểm tra scrollY có >= chiều cao của tất cả những con đang nằm trong nó lkhông
                    if(scrollY >= (nsv.getChildAt(nsv.getChildCount()-1)).getMeasuredHeight()- nsv.getMeasuredHeight()){
                        itemdaload +=8;
                        monAnModel.getDanhSachMonAn(monAnInterface,itemdaload,itemdaload-8);
                    }
                }
            }
        });


        monAnModel.getDanhSachMonAn(monAnInterface,itemdaload,0);
    }
}
