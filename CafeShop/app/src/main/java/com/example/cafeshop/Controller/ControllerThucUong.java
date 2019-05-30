package com.example.cafeshop.Controller;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.cafeshop.Adapters.AdapterRecyclerThucUong;
import com.example.cafeshop.Controller.Interface.ThucUongInterface;
import com.example.cafeshop.Model.ThucUongModel;
import com.example.cafeshop.R;

import java.util.ArrayList;
import java.util.List;

public class ControllerThucUong {
    Context context;
    ThucUongModel thucUongModel;
    AdapterRecyclerThucUong adapterRecyclerThucUong;

    public  ControllerThucUong(Context context){
        this.context=context;
        thucUongModel = new ThucUongModel();
    }

    //controller nhận recycler view của fragment truyền qua
    public  void getDanhSachThucUongController(RecyclerView recyclerViewThucUong){

       final List<ThucUongModel> thucUongModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewThucUong.setLayoutManager(layoutManager);
        adapterRecyclerThucUong = new AdapterRecyclerThucUong(thucUongModelList, R.layout.layout_recyclerview_thucuong);
        adapterRecyclerThucUong.getItemId(1);
        recyclerViewThucUong.setAdapter(adapterRecyclerThucUong);
        //new một interface để ThucUongModel sẽ nhận vô ThucUongInterface
        ThucUongInterface thucUongInterface = new ThucUongInterface() {
            @Override
            public void getDanhSachThucUongModel(ThucUongModel thucUongModel) {
                //Log.d("ktNodeThucUong",thucUongModel.getTenthucuong() +"");
                thucUongModelList.add(thucUongModel);
                adapterRecyclerThucUong.notifyDataSetChanged();
            }
        };
        thucUongModel.getDanhSachThucUong(thucUongInterface);
    }
}
