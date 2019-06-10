package com.example.cafeshop.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cafeshop.View.Fragments.FragmentCuaHang;
import com.example.cafeshop.View.Fragments.FragmentMonAn;
import com.example.cafeshop.View.Fragments.FragmentThucUong;
import com.example.cafeshop.View.Fragments.FragmentYeuThich;

public class AdapterViewPagerCuaHang extends FragmentStatePagerAdapter {
    FragmentCuaHang fmCuaHang;
    FragmentThucUong fmThucUong;

    public AdapterViewPagerCuaHang (FragmentManager fm) {
        super(fm);
        fmCuaHang=new FragmentCuaHang();
        fmThucUong = new FragmentThucUong();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return fmCuaHang;
            case 1:
                return  fmThucUong;

            default: return null ;
        }

    }

    @Override
    public int getCount() {
        return 2; // đếm có 3 item: fmMonAn,fmYeuThich va fmThucUong, khi chạy vào sẽ chạy getCount trước để đếm có bao nhiêu vị trí
    }
}
