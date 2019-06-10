package com.example.cafeshop.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.cafeshop.Adapters.AdapterViewPagerTrangChu;
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

public class TrangChu  extends AppCompatActivity implements  ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    //Intent recv;
    RadioButton rbthucuong;
    RadioButton rbmonan;
    RadioGroup  rgmonanthucuong;
    RecyclerView rcThucUong;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu;
    ImageView navigation_avartar;
    TextView navigation_username;
    TextView navigation_email;
    String IdUser;
    ViewPager viewPagerTrangChu;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    public  void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);
        //recv=getIntent();
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
                IdUser=FirebaseAuth.getInstance().getCurrentUser().getUid();
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

        rbthucuong = findViewById(R.id.rbThucUong);
        rbmonan = findViewById(R.id.rbMonAn);
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

        //Lắng nghe sự kiện click cho Radio group
        rgmonanthucuong.setOnCheckedChangeListener( this);
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
                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager_layout,new Profile()).addToBackStack(null).commit();
                break;
            case R.id.home:
                Intent iTrangChu=new Intent(this, TrangChu.class);
                startActivity(iTrangChu);
                break;
        }
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();
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
                    break;
            case 1: rbmonan.setChecked(true);
                    rbthucuong.setChecked(false);
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
            case R.id.rbThucUong:
                viewPagerTrangChu.setCurrentItem(0);
                break;
            case  R.id.rbMonAn:
                viewPagerTrangChu.setCurrentItem(1);
                break;
        }
    }

    ///End Các phương thức default phải Override của implements  ViewPager.OnPageChangeListener:
}
