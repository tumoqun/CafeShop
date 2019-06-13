package com.example.cafeshop.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cafeshop.View.Fragments.FragmentMonAn;
import com.example.cafeshop.View.Fragments.FragmentThucUong;
import com.example.cafeshop.View.Fragments.FragmentYeuThich;

public class AdapterViewPagerThucDon extends FragmentStatePagerAdapter {
    FragmentMonAn fmMonAn;
    FragmentThucUong fmThucUong;
    FragmentYeuThich fmYeuThich;

    public AdapterViewPagerThucDon(FragmentManager fm) {
        super(fm);
        fmMonAn=new FragmentMonAn();
        fmThucUong = new FragmentThucUong();
        fmYeuThich = new FragmentYeuThich();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return fmThucUong;
            case 1:
                return  fmMonAn;
            case 2:
                return  fmYeuThich;

            default: return null ;
        }

    }

    @Override
    public int getCount() {
        return 3; // đếm có 3 item: fmMonAn,fmYeuThich va fmThucUong, khi chạy vào sẽ chạy getCount trước để đếm có bao nhiêu vị trí
    }
}
