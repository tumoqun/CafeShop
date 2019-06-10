package com.example.cafeshop.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cafeshop.Model.ThanhVienModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.app.Activity.RESULT_OK;

public class Profile extends Fragment {
    @Nullable
    EditText edtHoten;
    EditText edtDiaChi;
    EditText edtSDT;
    TextView tvUsername;
    TextView tvEmail;
    ImageView imgUser;
    ImageButton btnAvartar;
    EditText edtUsername;
    Uri img;
    Button btnCapNhatThongTin;
    final int RESULT_IMG=111;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    String idHinhAnh;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.profile,container,false);
        edtHoten=view.findViewById(R.id.edtHoten);
        edtDiaChi=view.findViewById(R.id.edtDiaChi);
        edtSDT=view.findViewById(R.id.edtSDT);
        tvUsername=view.findViewById(R.id.tvUsername);
        tvEmail=view.findViewById(R.id.tvEmail);
        imgUser=view.findViewById(R.id.imgUser);
        btnAvartar=view.findViewById(R.id.btnAvartar);
        btnCapNhatThongTin=view.findViewById(R.id.btnCapNhatUser);
        edtUsername=view.findViewById(R.id.edtUserNname);
        final String userID= FirebaseAuth.getInstance().getCurrentUser().getUid(); //lấy user ID
        databaseReference= FirebaseDatabase.getInstance().getReference().child("thanhvien");
        storageReference= FirebaseStorage.getInstance().getReference();
        idHinhAnh=FirebaseAuth.getInstance().getCurrentUser().getUid();
        //Xuất thông tin user
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ThanhVienModel thanhVienModel = dataSnapshot.child(userID).getValue(ThanhVienModel.class);
                edtHoten.setText(thanhVienModel.getHoten());
                edtDiaChi.setText(thanhVienModel.getDiachi());
                edtSDT.setText(thanhVienModel.getSdt());
                edtUsername.setText(thanhVienModel.getUsername());
                tvEmail.setText(thanhVienModel.getEmail());
                tvUsername.setText(thanhVienModel.getUsername());
                storageReference.child("/thanhvien/" + thanhVienModel.getHinhanh())
                        .getDownloadUrl()
                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Glide.with(getContext())
                                        .load(uri)
                                        .into(imgUser);
                            }
                        });


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
        //Cập nhật thông tin user
        btnCapNhatThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThanhVienModel thanhVienModel=new ThanhVienModel();
                thanhVienModel.setHoten(edtHoten.getText().toString());
                thanhVienModel.setUsername(edtUsername.getText().toString());
                thanhVienModel.setSdt(edtSDT.getText().toString());
                thanhVienModel.setDiachi(edtDiaChi.getText().toString());
                thanhVienModel.setEmail(tvEmail.getText().toString());
                if(img!=null) //nếu có chọn lại hình thì mới upload lên lại hình mới
                {
                //lưu tên của id vào tên hình
                thanhVienModel.setHinhanh(idHinhAnh);
                //cho hình đó lưu dưới tên của id vừa được tạo
                storageReference=storageReference.child("/thanhvien/"+ idHinhAnh);
                //tải hình lên
                storageReference.putFile(img);}
                //còn không thì lấy hình cũ
                thanhVienModel.setHinhanh(idHinhAnh);
                databaseReference.child(userID).setValue(thanhVienModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                        Intent iTrangChu=new Intent(getContext(), TinTuc.class);
                        startActivity(iTrangChu);
                    }
                });
            }
        });
        btnAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,RESULT_IMG);
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && requestCode==RESULT_IMG){
            imgUser.setImageURI(data.getData());
            //lấy uri của hình ảnh
            img=data.getData();
        }
    }
}
