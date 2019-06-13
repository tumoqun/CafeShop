package com.example.cafeshop;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);
    }

}
//
//    Typeface iconFont;
//    Typeface iconFontItemThucUong;
////sử dụng font awesome:
//        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
//                FontManager.markAsIconContainer(findViewById(R.id.trangchulayout), iconFont);
//
//                iconFontItemThucUong = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
//                FontManager.markAsIconContainer(findViewById(R.id.layoutitemthucuong), iconFontItemThucUong);