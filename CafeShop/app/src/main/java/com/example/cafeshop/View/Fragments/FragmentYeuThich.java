package com.example.cafeshop.View.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cafeshop.R;

public class FragmentYeuThich  extends Fragment {
    //Khởi tạo giao diện cho Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_yeuthich,container, false); //attachToRoot: có gắn vô vột cái ViewGroup nào không?
        return view;
    }
}