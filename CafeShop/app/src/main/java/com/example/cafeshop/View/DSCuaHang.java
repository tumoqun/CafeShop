package com.example.cafeshop.View;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafeshop.Adapters.AdapterViewPagerCuaHang;
//import com.example.cafeshop.Adapters.AdapterViewPagerTinTuc;
import com.example.cafeshop.FontManager;
import com.example.cafeshop.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class DSCuaHang  extends AppCompatActivity implements  ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    RadioButton rbtatca;
    RadioButton rbgantoi;
    RadioGroup  rgtatcagantoi;
    ViewPager viewpagerdscuahang;

    Typeface iconFont;
    RadioGroup rgbottombardscuahang;
    RadioButton rbtintuc;
    RadioButton rbthucdon;
    RadioButton rbcuahang;
    RadioButton rbtaikhoan;
    LinearLayout layoutdscuahang;

    public  void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachcuahang);
        //recv=getIntent();

        //sử dụng font awesome:
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.layoutdanhsachcuahang), iconFont);

        rgbottombardscuahang=findViewById(R.id.rgBottomBarDSCuaHang);
        layoutdscuahang=findViewById(R.id.layoutdanhsachcuahang);
        rbtintuc=findViewById(R.id.rbTinTucDSCuaHang);
        rbthucdon=findViewById(R.id.rbThucDonDSCuaHang);
        rbcuahang=findViewById(R.id.rbCuaHangDSCuaHang);
        rbtaikhoan=findViewById(R.id.rbTaiKhoanDSCuaHang);

        rbtatca = findViewById(R.id.rbTatCa);
        rbgantoi = findViewById(R.id.rbGanToi);
        rgtatcagantoi = findViewById(R.id.rgTatCaGanToi);


        //Tìm viewpagerTinTuc ở trang chủ gán vào viewPagerTinTuc
        viewpagerdscuahang=(ViewPager)findViewById(R.id.viewpagerDSCuaHang);
        //Khởi tạo adapter hiển thị fragment cho viewpager
        AdapterViewPagerCuaHang adapterViewPagerCuaHang = new AdapterViewPagerCuaHang((getSupportFragmentManager()));
        //Gắn adapter vào viewPagerTinTuc vừa khai báo.
        viewpagerdscuahang.setAdapter(adapterViewPagerCuaHang);

        //Lắng nghe sự kiện thay đổi page cho ViewPager
        viewpagerdscuahang.addOnPageChangeListener(this);

        //Lắng nghe sự kiện click cho Radio group top
        rgtatcagantoi.setOnCheckedChangeListener( this);

        //Lắng nghe sự kiện click cho bottom bar
        rgbottombardscuahang.setOnCheckedChangeListener(this);



//        //Lắng nghe sự kiện click BackButton
//        Button btnbackdscuahang = findViewById(R.id.btnBackDSCuaHang);
//        btnbackdscuahang.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

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
            case 0: rbtatca.setChecked(true);
                rbgantoi.setChecked(false);
                break;
            case 1: rbgantoi.setChecked(true);
                rbtatca.setChecked(false);
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
            case R.id.rbTatCa:
                viewpagerdscuahang.setCurrentItem(0);
                break;
            case  R.id.rbGanToi:
                viewpagerdscuahang.setCurrentItem(1);
                break;

            case  R.id.rbTinTucDSCuaHang:
//                Toast.makeText(getApplicationContext(), "Đã chọn tin tức", Toast.LENGTH_SHORT).show();
                Intent iTinTuc = new Intent(this, TinTuc.class);
                startActivity(iTinTuc);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;

            case  R.id.rbThucDonDSCuaHang:
//                Toast.makeText(getApplicationContext(), "Đã chọn thực đơn", Toast.LENGTH_SHORT).show();
                Intent iThucDon=new Intent(this, ThucDon.class);
                startActivity(iThucDon);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
            case  R.id.rbCuaHangDSCuaHang:
                rbcuahang.setChecked(true);
//                Toast.makeText(getApplicationContext(), "Đã chọn cửa hàng", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.rbTaiKhoanDSCuaHang:
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
        onCheckedChanged(rgbottombardscuahang,R.id.rbCuaHangDSCuaHang);
    }


}
