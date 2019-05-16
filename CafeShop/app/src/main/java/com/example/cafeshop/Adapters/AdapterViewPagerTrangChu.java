package com.example.cafeshop.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cafeshop.View.Fragments.FragmentMonAn;
import com.example.cafeshop.View.Fragments.FragmentThucUong;

public class AdapterViewPagerTrangChu extends FragmentStatePagerAdapter {
    FragmentMonAn fmMonAn;
    FragmentThucUong fmThucUong;

    public AdapterViewPagerTrangChu(FragmentManager fm) {
        super(fm);
        fmMonAn=new FragmentMonAn();
        fmThucUong = new FragmentThucUong();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return fmThucUong;
            case 1:
                return  fmMonAn;

            default: return null ;
        }

    }

    @Override
    public int getCount() {
        return 2; // đếm có 2 item: fmMonAn va fmThucUong, khi chạy vào sẽ chạy getCount trước để đếm có bao nhiêu vị trí
    }
}
