//package com.example.cafeshop.View.Fragments;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.cafeshop.R;
//
//public class FragmentMonAn extends Fragment {
//    //Khởi tạo giao diện cho Fragment
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view=inflater.inflate(R.layout.layout_fragment_monan,container, false); //attachToRoot: có gắn vô vột cái ViewGroup nào không?
//        return view;
//    }
//}

package com.example.cafeshop.View.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.cafeshop.Controller.ControllerCuaHang;
import com.example.cafeshop.FontManager;
import com.example.cafeshop.R;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FragmentCuaHang extends Fragment {
    ControllerCuaHang controllerCuaHang;
    RecyclerView recyclerCuaHang;
    ProgressBar pbCuaHang;
    NestedScrollView nestedScrollViewCuaHang;

    Typeface iconFontItemCuaHang;

    //Khởi tạo giao diện cho Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_cuahang,container, false); //attachToRoot: có gắn vô ai không
        recyclerCuaHang=(RecyclerView) view.findViewById(R.id.recyclerCuaHang);
        pbCuaHang=view.findViewById(R.id.pbCuaHang);
        nestedScrollViewCuaHang = view.findViewById(R.id.nestedScrollViewCuaHang);

        iconFontItemCuaHang = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(view.findViewById(R.id.recyclerCuaHang), iconFontItemCuaHang);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        cuaHangModel = new CuaHangModel();
////        List<CuaHangModel>cuaHangModelList= cuaHangModel.getDanhSachCuaHang();
////        Log.d("KTList",cuaHangModelList.size() + " ");
//        cuaHangModel.getDanhSachCuaHang();
        controllerCuaHang=new ControllerCuaHang(getContext());
        controllerCuaHang.getDanhSachCuaHangController(nestedScrollViewCuaHang, recyclerCuaHang,pbCuaHang);
    }
}
