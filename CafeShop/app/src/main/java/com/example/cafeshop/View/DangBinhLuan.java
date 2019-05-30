package com.example.cafeshop.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafeshop.Model.BinhLuanModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class DangBinhLuan extends AppCompatActivity implements View.OnClickListener {

    final int RESULT_IMG=111;
    final int IMAGE_PICK_CODE=1000;
    final int PERMISSION_CODE=10001;

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }

    private DatabaseReference mDatabase;


    EditText edtTieuDe;
    EditText edtBinhLuan;
    TextView txtDangBinhLuan;
    ImageButton imgChonHinh;
    ImageView imgHinhTam;
    private StorageReference mStorage;
    Uri img;
    UploadTask uploadTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangbinhluan);

        txtDangBinhLuan=findViewById(R.id.txtDangBinhLuan);
        edtTieuDe=findViewById(R.id.edtTieuDe);
        edtBinhLuan=findViewById(R.id.edtVietBinhLuan);
        imgChonHinh=findViewById(R.id.btnChonHinh);
        imgHinhTam=findViewById(R.id.imgHinhTam);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorage= FirebaseStorage.getInstance().getReference();

        imgChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,RESULT_IMG);
            }
        });

        txtDangBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Lấy 1 id bất kì và convert sang 1 String
                String id=UUID.randomUUID().toString();
                BinhLuanModel binhLuan=new BinhLuanModel();
                binhLuan.setTieude(edtTieuDe.getText().toString());
                binhLuan.setBinhluan(edtBinhLuan.getText().toString());
                binhLuan.setThoigian(getCurrentDate());
                //lưu tên của id vào tên hình
                binhLuan.setHinh(id);
                mDatabase.child("binhluan").push().setValue(binhLuan).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "đăng bình luận thành công!", Toast.LENGTH_SHORT).show();
                    }
                });
                //cho hình đó lưu dưới tên của id vừa được tạo
                mStorage=mStorage.child("binhluan/"+ id);
                //tải hình lên
                mStorage.putFile(img);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && requestCode==RESULT_IMG){
            //tải ảnh lên thanh bình luận
            imgHinhTam.setImageURI(data.getData());
            //lấy uri của hình ảnh
            img=data.getData();
        }
    }


    @Override
    public void onClick(View v) {

    }
}

