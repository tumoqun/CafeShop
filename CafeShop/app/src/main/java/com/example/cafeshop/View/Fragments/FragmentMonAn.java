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

import com.example.cafeshop.Controller.ControllerMonAn;
import com.example.cafeshop.FontManager;
import com.example.cafeshop.R;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FragmentMonAn extends Fragment {
    ControllerMonAn controllerMonAn;
    RecyclerView recyclerMonAn;
    ProgressBar pbMonAn;
    NestedScrollView nestedScrollViewMonAn;

    Typeface iconFontItemMonAn;

    //Khởi tạo giao diện cho Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_monan,container, false); //attachToRoot: có gắn vô ai không
        recyclerMonAn=(RecyclerView) view.findViewById(R.id.recyclerMonAn);
        pbMonAn=view.findViewById(R.id.pbMonAn);
        nestedScrollViewMonAn = view.findViewById(R.id.nestedScrollViewMonAn);

        iconFontItemMonAn = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(view.findViewById(R.id.recyclerMonAn), iconFontItemMonAn);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        monAnModel = new MonAnModel();
////        List<MonAnModel>monAnModelList= monAnModel.getDanhSachMonAn();
////        Log.d("KTList",monAnModelList.size() + " ");
//        monAnModel.getDanhSachMonAn();
        controllerMonAn=new ControllerMonAn(getContext());
        controllerMonAn.getDanhSachMonAnController(nestedScrollViewMonAn, recyclerMonAn,pbMonAn);
    }
}
