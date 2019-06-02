package com.example.cafeshop.View;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.cafeshop.FontManager;
import com.example.cafeshop.R;
import com.example.cafeshop.View.Fragments.FragmentTinTuc;

public class TinTuc  extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    Typeface iconFont;
    RadioGroup rgbottombartintuc;
    RadioButton rbtintuc;
    RadioButton rbthucdon;
    RadioButton rbcuahang;
    RadioButton rbtaikhoan;
    LinearLayout layouttintuc;
    FrameLayout frametintuc;


    public  void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);
        //recv=getIntent();

        //sử dụng font awesome:
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.layouttintuc), iconFont);

        rgbottombartintuc=findViewById(R.id.rgBottomBarTinTuc);
        layouttintuc=findViewById(R.id.layouttintuc);
        rbtintuc=findViewById(R.id.rbTinTucTinTuc);
        rbthucdon=findViewById(R.id.rbThucDonTinTuc);
        rbcuahang=findViewById(R.id.rbCuaHangTinTuc);
        rbtaikhoan=findViewById(R.id.rbTaiKhoanTinTuc);
        frametintuc=findViewById(R.id.frameTinTuc);

        rgbottombartintuc.setOnCheckedChangeListener(this);

        FragmentTinTuc fragmentTinTuc=new FragmentTinTuc();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameTinTuc, fragmentTinTuc);
        fragmentTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case  R.id.rbTinTucTinTuc:
//                Toast.makeText(getApplicationContext(), "Đã chọn tin tức", Toast.LENGTH_SHORT).show();
                rbtintuc.setChecked(true);
                break;

            case  R.id.rbThucDonTinTuc:
//                Toast.makeText(getApplicationContext(), "Đã chọn thực đơn", Toast.LENGTH_SHORT).show();
                Intent iThucDon=new Intent(this, ThucDon.class);
                startActivity(iThucDon);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
            case  R.id.rbCuaHangTinTuc:
//                Toast.makeText(getApplicationContext(), "Đã chọn cửa hàng", Toast.LENGTH_SHORT).show();
                Intent iCuaHang = new Intent(this, DSCuaHang.class);
                startActivity(iCuaHang);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
            case  R.id.rbTaiKhoanTinTuc:
//                Toast.makeText(getApplicationContext(), "Đã chọn tài khoản", Toast.LENGTH_SHORT).show();
                Intent iTaiKhoan=new Intent(this, TaiKhoan.class);
                startActivity(iTaiKhoan);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
        }
    }
    ///End Các phương thức default phải Override của implements  ViewPager.OnPageChangeListener:
    @Override
    protected void onResume() {
        super.onResume();
        onCheckedChanged(rgbottombartintuc,R.id.rbTinTucTinTuc);
    }
}
