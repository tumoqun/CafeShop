package com.example.cafeshop.View.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.cafeshop.Controller.ControllerTinTuc;
import com.example.cafeshop.R;

public class FragmentLSDonHang extends Fragment {
    ControllerTinTuc controllerTinTuc;
    RecyclerView recyclerTinTuc;
    ProgressBar pbTinTuc;
    NestedScrollView nestedScrollViewTinTuc;


    //Khởi tạo giao diện cho Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_tintuc,container, false); //attachToRoot: có gắn vô ai không
        recyclerTinTuc=(RecyclerView) view.findViewById(R.id.recyclerTinTuc);
        pbTinTuc=view.findViewById(R.id.pbTinTuc);
        nestedScrollViewTinTuc = view.findViewById(R.id.nestedScrollViewTinTuc);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        controllerTinTuc=new ControllerTinTuc(getContext());
        controllerTinTuc.getDanhSachTinTucController(nestedScrollViewTinTuc, recyclerTinTuc,pbTinTuc);
    }
}
