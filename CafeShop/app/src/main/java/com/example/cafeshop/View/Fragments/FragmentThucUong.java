package com.example.cafeshop.View.Fragments;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cafeshop.Controller.ControllerThucUong;
import com.example.cafeshop.Model.ThucUongModel;
import com.example.cafeshop.R;

import java.util.List;

public class FragmentThucUong extends Fragment {
//    ThucUongModel thucUongModel;
    ControllerThucUong controllerThucUong;
    RecyclerView recyclerThucUong;

    //Khởi tạo giao diện cho Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_thucuong,container, false); //attachToRoot: có gắn vô ai không
        recyclerThucUong=(RecyclerView) view.findViewById(R.id.recyclerThucUong);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        thucUongModel = new ThucUongModel();
////        List<ThucUongModel>thucUongModelList= thucUongModel.getDanhSachThucUong();
////        Log.d("KTList",thucUongModelList.size() + " ");
//        thucUongModel.getDanhSachThucUong();
        controllerThucUong=new ControllerThucUong(getContext());
        controllerThucUong.getDanhSachThucUongController(recyclerThucUong);
    }
}
