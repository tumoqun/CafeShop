package com.example.cafeshop.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cafeshop.Adapters.AdapterRecyclerDonHang;
import com.example.cafeshop.Controller.Interface.DonHangInterface;
import com.example.cafeshop.Controller.Interface.TinTucInterface;
import com.example.cafeshop.Model.DonHangModel;
import com.example.cafeshop.Model.TinTucModel;
import com.example.cafeshop.R;
import com.example.cafeshop.View.DonHang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ControllerDonHang {
    Context context;
    DonHangModel donHangModel;
    AdapterRecyclerDonHang adapterRecyclerDonHang;
    int itemdaload=8;



    public  ControllerDonHang(Context context){
        this.context=context;
        donHangModel = new DonHangModel();
    }

    //controller nhận recycler view của fragment truyền qua
    public  void getDonHangController(NestedScrollView nestedScrollViewDonHang, RecyclerView recyclerViewDonHang, final ProgressBar progressBar){

        final List<DonHangModel> donHangModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewDonHang.setLayoutManager(layoutManager);
        adapterRecyclerDonHang = new AdapterRecyclerDonHang(donHangModelList, R.layout.layout_recyclerview_lichsudathang);
        recyclerViewDonHang.setAdapter(adapterRecyclerDonHang);//set adapter cho recycler

        //Chia 2 column
//        recyclerViewDonHang.setLayoutManager(new GridLayoutManager(context,2));


        ////Kiểm tra nestedScrollView có tới tận cùng của item đã load chưa

        progressBar.setVisibility(View.VISIBLE);
        //new một interface để DonHangModel sẽ nhận vô DonHangInterface
        final DonHangInterface donHangInterface = new DonHangInterface() {
            @Override
            public void getDanhSachDonHangModel(final DonHangModel donHangModel) {
                final List<DonHangModel> donHangModels= new ArrayList<>();
                for(String linkhinh: donHangModel.getHinhanhtintuc()){
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
