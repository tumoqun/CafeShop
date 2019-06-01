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

import com.example.cafeshop.Controller.ControllerThucUong;
import com.example.cafeshop.FontManager;
import com.example.cafeshop.Model.ThucUongModel;
import com.example.cafeshop.R;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FragmentMonAn extends Fragment {
    //    ThucUongModel thucUongModel;
    ControllerThucUong controllerThucUong;
    RecyclerView recyclerThucUong;
    ProgressBar pbThucUong;
    NestedScrollView nestedScrollViewThucUong;

    Typeface iconFontItemThucUong;

    //Khởi tạo giao diện cho Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_thucuong,container, false); //attachToRoot: có gắn vô ai không
        recyclerThucUong=(RecyclerView) view.findViewById(R.id.recyclerThucUong);
        pbThucUong=view.findViewById(R.id.pbThucUong);
        nestedScrollViewThucUong = view.findViewById(R.id.nestedScrollViewThucUong);

        iconFontItemThucUong = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(view.findViewById(R.id.recyclerThucUong), iconFontItemThucUong);

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
        controllerThucUong.getDanhSachThucUongController(nestedScrollViewThucUong, recyclerThucUong,pbThucUong);
    }
}
