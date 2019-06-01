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

import com.example.cafeshop.Adapters.AdapterViewPagerDSCuaHang;
import com.example.cafeshop.Adapters.AdapterViewPagerTrangChu;
import com.example.cafeshop.FontManager;
import com.example.cafeshop.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class TaiKhoan  extends AppCompatActivity implements  RadioGroup.OnCheckedChangeListener {

    Typeface iconFont;
    RadioGroup rgbottombartaikhoan;
    RadioButton rbtintuc;
    RadioButton rbthucdon;
    RadioButton rbcuahang;
    RadioButton rbtaikhoan;
    LinearLayout layouttaikhoan;

    public  void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taikhoan);
        //recv=getIntent();

        //sử dụng font awesome:
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.layouttaikhoan), iconFont);

        rgbottombartaikhoan=findViewById(R.id.rgBottomBarTaiKhoan);
        layouttaikhoan=findViewById(R.id.layouttaikhoan);
        rbtintuc=findViewById(R.id.rbTinTucTaiKhoan);
        rbthucdon=findViewById(R.id.rbThucDonTaiKhoan);
        rbcuahang=findViewById(R.id.rbCuaHangTaiKhoan);
        rbtaikhoan=findViewById(R.id.rbTaiKhoanTaiKhoan);

        //Lắng nghe sự kiện click cho bottom bar
        rgbottombartaikhoan.setOnCheckedChangeListener(this);


//        //Lắng nghe sự kiện click BackButton
//        Button btnbackdscuahang = findViewById(R.id.btnBackDSCuaHang);
//        btnbackdscuahang.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case  R.id.rbTinTucTaiKhoan:
                Toast.makeText(getApplicationContext(), "Đã chọn tin tức", Toast.LENGTH_SHORT).show();
                Intent iTrangChu = new Intent(this, TrangChu.class);
                startActivity(iTrangChu);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
            case  R.id.rbThucDonTaiKhoan:
                Toast.makeText(getApplicationContext(), "Đã chọn thực đơn", Toast.LENGTH_SHORT).show();
                Intent iThucDon=new Intent(this, ThucDon.class);
                startActivity(iThucDon);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
            case  R.id.rbCuaHangTaiKhoan:
                rbcuahang.setChecked(true);
                Toast.makeText(getApplicationContext(), "Đã chọn cửa hàng", Toast.LENGTH_SHORT).show();
                Intent iCuaHang = new Intent(this, DSCuaHang.class);
                startActivity(iCuaHang);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
            case  R.id.rbTaiKhoanTaiKhoan:
                rbtaikhoan.setChecked(true);
                Toast.makeText(getApplicationContext(), "Đã chọn tài khoản", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    ///End Các phương thức default phải Override của implements  ViewPager.OnPageChangeListener:
    @Override
    protected void onResume() {
        super.onResume();
        onCheckedChanged(rgbottombartaikhoan,R.id.rbTaiKhoanTaiKhoan);
    }
}
