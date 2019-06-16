package com.example.cafeshop.View;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cafeshop.Model.DonHangModel;
import com.example.cafeshop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonHang extends AppCompatActivity {
    TextView tvMaDonHang;
    TextView tvThoiGian;
    TextView tvNguoiDat;
    TextView tvDiaChi;
    TextView tvDonHang;
    TextView tvTongGia;
    TextView tvSDT;
    Button btnQuayVeTrangChu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donhang);
        Intent intent=this.getIntent();
        String IdUser=intent.getStringExtra("mã user");
        String madonhang=intent.getStringExtra("mã đơn hàng");
        tvMaDonHang=findViewById(R.id.tvMaDonHang);
        tvThoiGian=findViewById(R.id.tvThoiGian);
        tvNguoiDat=findViewById(R.id.tvNguoiDat);
        tvDiaChi=findViewById(R.id.tvDiaChi);
        tvDonHang=findViewById(R.id.tvDonHang);
        tvTongGia=findViewById(R.id.tvTongGia);
        tvSDT=findViewById(R.id.tvSDT);
        btnQuayVeTrangChu=findViewById(R.id.btnTroVeTrangChu);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("donhang");
        databaseReference.child(IdUser).child(madonhang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DonHangModel donHangModel=dataSnapshot.getValue(DonHangModel.class);
                tvMaDonHang.setText(donHangModel.getMadonhang());
                tvThoiGian.setText(donHangModel.getThoigian());
                tvNguoiDat.setText(donHangModel.getNguoidat());
                tvDiaChi.setText(donHangModel.getDiachi());
                tvDonHang.setText(donHangModel.getChitietdonhang());
                tvTongGia.setText(donHangModel.getTonggia());
                tvSDT.setText(donHangModel.getSDT());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnQuayVeTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),TinTuc.class);
                startActivity(intent);
            }
        });
    }
}
