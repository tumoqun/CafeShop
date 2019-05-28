package com.example.cafeshop.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cafeshop.Adapters.BinhLuanAdapter;
import com.example.cafeshop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class XuatBinhLuan extends AppCompatActivity {

    private ListView lvBinhLuan;
    private ArrayList<BinhLuan> binhLuanArrayList;
    private BinhLuanAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuatbinhluan);
        lvBinhLuan = findViewById(R.id.lvBinhLuan);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        binhLuanArrayList = new ArrayList<>();
        GetData();
        adapter = new BinhLuanAdapter(this, R.layout.binhluan_item, binhLuanArrayList);

        lvBinhLuan.setAdapter(adapter);
    }

    private void GetData() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("binhluan");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    BinhLuan binhLuan = data.getValue(BinhLuan.class);
                    if (binhLuan != null) {
                        adapter.add(binhLuan);
                    }
                }
                Toast.makeText(getApplicationContext(), "load thành công!", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "load thất bại!", Toast.LENGTH_LONG).show();
                Log.d("TAG", "onCancelled" + databaseError.toString());
            }
        });
    }
}

