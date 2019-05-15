package com.example.cafeshop.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafeshop.MainActivity;
import com.example.cafeshop.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;
import java.util.List;


public class DangNhap extends AppCompatActivity implements View.OnClickListener, FirebaseAuth.AuthStateListener {

    Button btnDangNhap;
    FirebaseAuth firebaseAuth;
    EditText edEmail;
    EditText edPassword;
    LoginButton btnDangNhapFacbook;


    Button btndangki;

    public static int KIEMTRA_PROVIDER_DANGNHAP=0;
    CallbackManager mCallbackFacebook;
    LoginManager loginManager;
    List<String> permissionFacebook= Arrays.asList("email","public_profile");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.dangnhap);

        mCallbackFacebook=CallbackManager.Factory.create();
        loginManager=LoginManager.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        btndangki=(Button) findViewById(R.id.btnDangKi_1);
        btnDangNhap = (Button) findViewById(R.id.DangNhap);
        edEmail = (EditText) findViewById(R.id.Email);
        edPassword = (EditText) findViewById(R.id.Password);


        btndangki.setOnClickListener(this);
        btnDangNhapFacbook=(LoginButton) findViewById(R.id.FacebookLogin);
        btnDangNhapFacbook.setReadPermissions("email","public_profile");
        btnDangNhapFacbook.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
    }

    private void DangNhap(){
                String Email=edEmail.getText().toString();
                String Password=edPassword.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(DangNhap.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                            Intent iTrangChu=new Intent(DangNhap.this, TrangChu.class);
                            startActivity(iTrangChu);
                        }
                        else {
                            Toast.makeText(DangNhap.this,"Sai tên tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

    private void DangNhapFacebook(){
        loginManager.registerCallback(mCallbackFacebook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                KIEMTRA_PROVIDER_DANGNHAP=2;
                String tokenID=loginResult.getAccessToken().getToken();
                ChungThucDangNhapFireBase(tokenID);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.FacebookLogin:
                DangNhapFacebook();
                break;
            case R.id.DangNhap:
                 DangNhap();
                Intent iDangNhap=new Intent(DangNhap.this, TrangChu.class);
                startActivity(iDangNhap);
=======

>>>>>>> 23212159ee530aea37c6883b9e8a3e1f0dfdfa28
                break;
            case R.id.btnDangKi_1:
                Intent iDangKi=new Intent(DangNhap.this, DangKi.class);
                startActivity(iDangKi);
        }
    }
    private void ChungThucDangNhapFireBase(String tokenID){
        if(KIEMTRA_PROVIDER_DANGNHAP==2){
            AuthCredential authCredential= FacebookAuthProvider.getCredential(tokenID);
            firebaseAuth.signInWithCredential(authCredential);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackFacebook.onActivityResult(requestCode,resultCode,data);

    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
    }
    public void onAuStateChange(@NonNull FirebaseAuth firebaseAuth){
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null){
        }
        else {
        }
    }
}