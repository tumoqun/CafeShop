package com.example.cafeshop.View;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cafeshop.FontManager;
import com.example.cafeshop.Model.ThanhVienModel;
import com.example.cafeshop.R;
import com.example.cafeshop.View.Fragments.FragmentTinTuc;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class TinTuc  extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    Typeface iconFont;
    RadioGroup rgbottombartintuc;
    RadioButton rbtintuc;
    RadioButton rbthucdon;
    RadioButton rbcuahang;
    RadioButton rbtaikhoan;
//    LinearLayout layouttintuc;
    FrameLayout frametintuc;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu;
    ImageView navigation_avartar;
    TextView navigation_username;
    TextView navigation_email;
    String IdUser;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    public  void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);
        //recv=getIntent();

        //sử dụng font awesome:
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.drawer_trangchu), iconFont);

        rgbottombartintuc=findViewById(R.id.rgBottomBarTinTuc);
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


        //bật navigation drawer
        menu=findViewById(R.id.menu);
        drawerLayout=findViewById(R.id.drawer_trangchu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
                //setup navigation header theo thông tin user
                View headview=navigationView.getHeaderView(0);
                databaseReference= FirebaseDatabase.getInstance().getReference().child("thanhvien");
                storageReference= FirebaseStorage.getInstance().getReference();
                IdUser= FirebaseAuth.getInstance().getCurrentUser().getUid();
                navigation_avartar=headview.findViewById(R.id.navigation_avartar);
                navigation_username=headview.findViewById(R.id.navigation_username);
                navigation_email=headview.findViewById(R.id.navigation_email);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ThanhVienModel thanhVienModel = dataSnapshot.child(IdUser).getValue(ThanhVienModel.class);
                        navigation_username.setText(thanhVienModel.getUsername());
                        navigation_email.setText(thanhVienModel.getEmail());
                        storageReference.child("/thanhvien/"+thanhVienModel.getHinhanh())
                                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Glide.with(getApplicationContext())
                                        .load(uri)
                                        .into(navigation_avartar);
                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        //tương tác với các nút trên navigationview
        navigationView=findViewById(R.id.nvTrangChu);
        setupDrawerContent(navigationView);
        //
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });

    }
    private void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameTinTuc,new Profile()).addToBackStack(null).commit();
                break;
            case R.id.home:
                Intent iTrangChu=new Intent(this, TinTuc.class);
                startActivity(iTrangChu);
                break;
        }
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();
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
