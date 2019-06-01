package com.example.cafeshop.View;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafeshop.Adapters.AdapterViewPagerTrangChu;
import com.example.cafeshop.FontManager;
import com.example.cafeshop.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class TrangChu  extends AppCompatActivity implements  ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    RadioButton rbthucuong;
    RadioButton rbmonan;
    RadioButton rbyeuthich;
    RadioGroup  rgmonanthucuong;
    ViewPager viewPagerTrangChu;

    Typeface iconFont;
    RadioGroup rgbottombartrangchu;
    RadioButton rbtintuc;
    RadioButton rbthucdon;
    RadioButton rbcuahang;
    RadioButton rbtaikhoan;
    LinearLayout layouttrangchu;

    public  void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);
        //recv=getIntent();

        //sử dụng font awesome:
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.layouttrangchu), iconFont);

        rgbottombartrangchu=findViewById(R.id.rgBottomBarTrangChu);
        layouttrangchu=findViewById(R.id.layouttrangchu);
        rbtintuc=findViewById(R.id.rbTinTucTrangChu);
        rbthucdon=findViewById(R.id.rbThucDonTrangChu);
        rbcuahang=findViewById(R.id.rbCuaHangTrangChu);
        rbtaikhoan=findViewById(R.id.rbTaiKhoanTrangChu);

        rbthucuong = findViewById(R.id.rbThucUong);
        rbmonan = findViewById(R.id.rbMonAn);
        rbyeuthich=findViewById(R.id.rbYeuThich);
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

        //Lắng nghe sự kiện click cho Radio group top
        rgmonanthucuong.setOnCheckedChangeListener( this);

        //Lắng nghe sự kiện click cho bottom bar
        rgbottombartrangchu.setOnCheckedChangeListener(this);



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
                    rbyeuthich.setChecked(false);
                    break;
            case 1: rbmonan.setChecked(true);
                    rbthucuong.setChecked(false);
                    rbyeuthich.setChecked(false);
                    break;
            case 2: rbyeuthich.setChecked(true);
                    rbthucuong.setChecked(false);
                    rbmonan.setChecked(false);
                    break;
//            case 3: rbtintuc.setChecked(true);
//                    rbthucdon.setChecked(false);
//                    rbcuahang.setChecked(false);
//                    rbtaikhoan.setChecked(false);
//                break;
//            case 4: rbthucdon.setChecked(true);
//                    rbtintuc.setChecked(false);
//                    rbcuahang.setChecked(false);
//                    rbtaikhoan.setChecked(false);
//                break;
//            case 5: rbcuahang.setChecked(true);
//                    rbthucdon.setChecked(false);
//                    rbtintuc.setChecked(false);
//                    rbtaikhoan.setChecked(false);
//                break;
//            case 6: rbtaikhoan.setChecked(false);
//                    rbcuahang.setChecked(false);
//                    rbthucdon.setChecked(false);
//                    rbtintuc.setChecked(false);
//                break;
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
            case R.id.rbYeuThich:
                viewPagerTrangChu.setCurrentItem(2);

                break;
            case  R.id.rbTinTucTrangChu:
                Toast.makeText(getApplicationContext(), "Đã chọn tin tức", Toast.LENGTH_SHORT).show();
                rbtintuc.setChecked(true);
                break;

            case  R.id.rbThucDonTrangChu:
                Toast.makeText(getApplicationContext(), "Đã chọn thực đơn", Toast.LENGTH_SHORT).show();
                Intent iThucDon=new Intent(this, ThucDon.class);
                startActivity(iThucDon);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
            case  R.id.rbCuaHangTrangChu:
                Toast.makeText(getApplicationContext(), "Đã chọn cửa hàng", Toast.LENGTH_SHORT).show();
                Intent iCuaHang = new Intent(this, DSCuaHang.class);
                startActivity(iCuaHang);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
            case  R.id.rbTaiKhoanTrangChu:
                Toast.makeText(getApplicationContext(), "Đã chọn tài khoản", Toast.LENGTH_SHORT).show();
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
        onCheckedChanged(rgbottombartrangchu,R.id.rbTinTucTrangChu);
    }


}
