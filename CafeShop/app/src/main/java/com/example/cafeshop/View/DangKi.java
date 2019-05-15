package com.example.cafeshop.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangKi extends AppCompatActivity {
    EditText edtEmail;
    EditText edtPassword;
    EditText edtRetypePassword;
    Button btnDangKy;
    FirebaseAuth auth;
    Intent recv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangki);
        auth = FirebaseAuth.getInstance();
        recv = getIntent();

        btnDangKy = findViewById(R.id.btnDangKi_2);
        edtEmail = findViewById(R.id.Email_DangKy);
        edtPassword = findViewById(R.id.Password_DangKy);
        edtRetypePassword = findViewById(R.id.RetypePassword);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String edtpassword = edtRetypePassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu không được để trống!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu quá ngắn, tối thiểu 6 kí tự!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtpassword)) {
                    Toast.makeText(getApplicationContext(), "Nhập lại mật khẩu!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(edtpassword)) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu phải trùng khớp!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(DangKi.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        Toast.makeText(DangKi.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(DangKi.this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(DangKi.this, DangNhap.class));
                            finish();
                        }
                    }
                });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}