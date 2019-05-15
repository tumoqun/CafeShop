package com.example.cafeshop.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cafeshop.Adapters.AdapterViewPagerTrangChu;
import com.example.cafeshop.R;

public class TrangChu  extends AppCompatActivity implements  ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    //Intent recv;
    RadioButton rbthucuong;
    RadioButton rbmonan;
    RadioGroup  rgmonanthucuong;

    ViewPager viewPagerTrangChu;
    public  void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);
        //recv=getIntent();

        rbthucuong = findViewById(R.id.rbThucUong);
        rbmonan = findViewById(R.id.rbMonAn);
        rgmonanthucuong = findViewById(R.id.rgMonAnThucUong);

        //Tìm viewpagerTrangChu ở trang chủ gán vào viewPagerTrangChu
        viewPagerTrangChu=(ViewPager)findViewById(R.id.viewpager_TrangChu);
        //Khởi tạo adapter cho viewpager
//        AdapterViewPagerTrangChu adapterViewPagerTrangChu= new AdapterViewPagerTrangChu(getSupportFragmentManager());
        AdapterViewPagerTrangChu adapterViewPagerTrangChu = new AdapterViewPagerTrangChu((getSupportFragmentManager()));
        //Gắn adapter vào viewPagerTrangChu vừa khai báo.
        viewPagerTrangChu.setAdapter(adapterViewPagerTrangChu);

        //Lắng nghe sự kiện thay đổi page cho ViewPager
        viewPagerTrangChu.addOnPageChangeListener(this);

        //Lắng nghe sự kiện click cho Radio group
        rgmonanthucuong.setOnCheckedChangeListener( this);

    }


    ///Các phương thức default phải Override của implements  ViewPager.OnPageChangeListener:
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    //onPageSelected trả vè position (i)
    @Override
    public void onPageSelected(int i) {
        Log.d("KiemTraPositionHienTai:",i + ""  );
        switch (i){
            case 0: rbthucuong.setChecked(true);
                    rbmonan.setChecked(false);
                    break;
            case 1: rbmonan.setChecked(true);
                    rbthucuong.setChecked(false);
                    break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.rbThucUong:
                viewPagerTrangChu.setCurrentItem(0);
                break;
            case  R.id.rbMonAn:
                viewPagerTrangChu.setCurrentItem(1);
                break;
        }
    }

    ///End Các phương thức default phải Override của implements  ViewPager.OnPageChangeListener:
}
