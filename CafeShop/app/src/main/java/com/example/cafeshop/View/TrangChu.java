package com.example.cafeshop.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.cafeshop.Adapters.AdapterViewPagerTrangChu;
import com.example.cafeshop.R;

public class TrangChu  extends AppCompatActivity {
  //  Intent recv;
    ViewPager viewPagerTrangChu;
    public  void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);
  //      recv=getIntent();

        //Tìm viewpagerTrangChu ở trang chủ gán vào viewPagerTrangChu
        viewPagerTrangChu=(ViewPager)findViewById(R.id.viewpager_TrangChu);
        //Khởi tạo adapter cho viewpager
//        AdapterViewPagerTrangChu adapterViewPagerTrangChu= new AdapterViewPagerTrangChu(getSupportFragmentManager());
        AdapterViewPagerTrangChu adapterViewPagerTrangChu = new AdapterViewPagerTrangChu((getSupportFragmentManager()));
        //Gắn adapter vào viewPagerTrangChu vừa khai báo.
        viewPagerTrangChu.setAdapter(adapterViewPagerTrangChu);
    }

}
