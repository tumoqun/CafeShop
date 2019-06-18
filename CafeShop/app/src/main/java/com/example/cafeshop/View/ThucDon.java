package com.example.cafeshop.View;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cafeshop.Adapters.AdapterViewPagerThucDon;
import com.example.cafeshop.FontManager;
import com.example.cafeshop.Model.DonHangModel;
import com.example.cafeshop.Model.GioHangModel;
import com.example.cafeshop.Model.ThanhVienModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class ThucDon  extends AppCompatActivity implements  ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    //Intent recv;
    RadioButton rbthucuongtd;
    RadioButton rbmonantd;
    RadioButton rbyeuthichtd;
    RadioGroup rgthucdontd;
    ViewPager viewPagerThucDon;

    Typeface iconFont;
    RadioGroup rgbottombarthucdon;
    RadioButton rbtintuctd;
    RadioButton rbthucdontd;
    RadioButton rbcuahangtd;
    RadioButton rbtaikhoantd;
    LinearLayout layoutthucdon;
    Dialog giohang;
    ImageView imgGioHang;
    TextView tvGioHang;
    TextView tvTongGia;
    Button btnTiepTucMuaHang;
    Button btnXacNhanDatHang;
    DatabaseReference mDatabase1= FirebaseDatabase.getInstance().getReference().child("thanhvien");
    DatabaseReference mDatabase2=FirebaseDatabase.getInstance().getReference().child("donhang");
//    Toolbar toolbarthucdon;
    String Hoten="";
    String Diachi="";
    String SDT="";



    private static final String DATE_FORMAT = "dd/MM/yyyy";
    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thucdon);

        //lấy thông tin người dùng : họ tên và địa chỉ
        final String IdUser= FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase1.child(IdUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ThanhVienModel thanhVienModel=dataSnapshot.getValue(ThanhVienModel.class);
                Hoten=thanhVienModel.getHoten();
                Diachi=thanhVienModel.getDiachi();
                SDT=thanhVienModel.getSdt();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        //Set toolbar  có chức năng như của Actionbar:
//        toolbarthucdon = (Toolbar) findViewById(R.id.toolbarThucDon);
//        setSupportActionBar(toolbarthucdon);
//        ActionBar actionBar = getSupportActionBar(); //Toobar đã như ActionBar

        //sử dụng font awesome:
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.layoutthucdon), iconFont);
        layoutthucdon = findViewById(R.id.layoutthucdon);

        rgbottombarthucdon = findViewById(R.id.rgBottomBarThucDon);
        rbtintuctd = findViewById(R.id.rbTinTucThucDon);
        rbthucdontd = findViewById(R.id.rbThucDonThucDon);
        rbcuahangtd = findViewById(R.id.rbCuaHangThucDon);
        rbtaikhoantd = findViewById(R.id.rbTaiKhoanThucDon);

        rbthucuongtd = findViewById(R.id.rbThucUongThucDon);
        rbmonantd = findViewById(R.id.rbMonAnThucDon);
        rbyeuthichtd = findViewById(R.id.rbYeuThichThucDon);
        rgthucdontd = findViewById(R.id.rgThucDon);
        giohang=new Dialog(ThucDon.this);
        giohang.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        giohang.setContentView(R.layout.giohang);
        imgGioHang=findViewById(R.id.imgGioHang);
        tvGioHang=giohang.findViewById(R.id.tvChiTietGioHang);
        tvTongGia=giohang.findViewById(R.id.tvTongGia);
        btnTiepTucMuaHang=giohang.findViewById(R.id.btnTiepTuc);
        btnXacNhanDatHang=giohang.findViewById(R.id.btnXacNhan);


        btnTiepTucMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giohang.dismiss();
            }
        });

        btnXacNhanDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lưu vào đơn hàng
                String idDonHang= UUID.randomUUID().toString();
                DonHangModel donHangModel=new DonHangModel();
                donHangModel.setThoigian(getCurrentDate());
                donHangModel.setDiachi(Diachi);
                donHangModel.setNguoidat(Hoten);
                donHangModel.setSDT(SDT);
                donHangModel.setIdUser(IdUser);
                donHangModel.setChitietdonhang(GioHangModel.Instance().ThongTinDoUong());
                donHangModel.setTonggia(String.valueOf(GioHangModel.Instance().TinhTongGia()));
                donHangModel.setMadonhang(idDonHang);
                //lưu xuống database
                mDatabase2.child(IdUser).child(idDonHang).setValue(donHangModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
                //làm trống lại giỏ hàng
                GioHangModel.Instance().ResetGioHang();
                //chuyển qua activity xuất thông tin đơn hàng
                Intent intent=new Intent(getApplicationContext(),DonHang.class);
                intent.putExtra("mã user",IdUser);
                intent.putExtra("mã đơn hàng",idDonHang);
                startActivity(intent);
            }
        });

        imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvGioHang.setText(GioHangModel.Instance().ThongTinDoUong());
                tvTongGia.setText(String.valueOf(GioHangModel.Instance().TinhTongGia()));
                giohang.show();
            }
        });
//

        //Tìm viewpagerTrangChu ở trang chủ gán vào viewPagerTrangChu
        viewPagerThucDon = (ViewPager) findViewById(R.id.viewpagerThucDon);
        //Khởi tạo adapter cho viewpager
        AdapterViewPagerThucDon adapterViewPagerThucDon = new AdapterViewPagerThucDon((getSupportFragmentManager()));
        //Gắn fragment vào viewPagerThucDon vừa khai báo.
        viewPagerThucDon.setAdapter(adapterViewPagerThucDon);
        //Lắng nghe sự kiện thay đổi page cho ViewPager
        viewPagerThucDon.addOnPageChangeListener(this);
        //Lắng nghe sự kiện click cho Radio group top
        rgthucdontd.setOnCheckedChangeListener(this);
        //Lắng nghe sự kiện click cho bottom bar

        rgbottombarthucdon.setOnCheckedChangeListener(this);
//        //Lắng nghe sự kiện click BackButton
//        Button btnbackthucdon = findViewById(R.id.btnBackThucDon);
//        btnbackthucdon.setOnClickListener(new View.OnClickListener(){
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
        Log.d("KiemTraPositionHienTai:", i + "");
        switch (i) {
            case 0:
                rbthucuongtd.setChecked(true);
                rbmonantd.setChecked(false);
                rbyeuthichtd.setChecked(false);
                break;
            case 1:
                rbmonantd.setChecked(true);
                rbthucuongtd.setChecked(false);
                rbyeuthichtd.setChecked(false);
                break;
            case 2:
                rbyeuthichtd.setChecked(true);
                rbthucuongtd.setChecked(false);
                rbmonantd.setChecked(false);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbThucUongThucDon:
                viewPagerThucDon.setCurrentItem(0);
                break;
            case R.id.rbMonAnThucDon:
                viewPagerThucDon.setCurrentItem(1);
                break;
            case R.id.rbYeuThichThucDon:
                viewPagerThucDon.setCurrentItem(2);
                break;

            case R.id.rbTinTucThucDon:
//                Toast.makeText(getApplicationContext(), "Đã chọn tin tức", Toast.LENGTH_SHORT).show();
                Intent iTinTuc = new Intent(this, TinTuc.class);
                startActivity(iTinTuc);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
            case R.id.rbThucDonThucDon:
                rbthucdontd.setChecked(true);
//                Toast.makeText(getApplicationContext(), "Đã chọn thực đơn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbCuaHangThucDon:
//                Toast.makeText(getApplicationContext(), "Đã chọn cửa hàng", Toast.LENGTH_SHORT).show();
                Intent iCuaHang = new Intent(this, DSCuaHang.class);
                startActivity(iCuaHang);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
            case R.id.rbTaiKhoanThucDon:
//                Toast.makeText(getApplicationContext(), "Đã chọn tài khoản", Toast.LENGTH_SHORT).show();
                Intent iTaiKhoan=new Intent(this, TaiKhoan.class);
                startActivity(iTaiKhoan);
                this.overridePendingTransition(0, 0); // bỏ hiệu ứng giật giật khi chuyển activity
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onCheckedChanged(rgbottombarthucdon,R.id.rbThucDonThucDon);
    }
}

