package com.example.cafeshop.Adapters;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cafeshop.R;
import com.example.cafeshop.Model.BinhLuanModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class BinhLuanAdapter extends ArrayAdapter<BinhLuanModel> {
    @NonNull
    private Activity activity;
    private int resource;
    @NonNull
    private List<BinhLuanModel> objects;
    private StorageReference storageReference;

    public BinhLuanAdapter(@NonNull Activity activity, int resource, @NonNull List<BinhLuanModel> objects) {
        super(activity, resource, objects);
        this.activity=activity;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.activity.getLayoutInflater();
        View view=inflater.inflate(this.resource,null);

        TextView txtThoiGian=view.findViewById(R.id.txtThoiGian);
        TextView txtTieuDe=view.findViewById(R.id.txtTieuDe);
        TextView txtBinhLuan=view.findViewById(R.id.txtBinhLuan);
        final ImageView imgHinhBinhLuan=view.findViewById(R.id.imgHinhBinhLuan);
        final ProgressBar progressBar=view.findViewById(R.id.progressbarOfBinhLuanItem);

        final BinhLuanModel binhLuan=this.objects.get(position);
        //tạm ẩn hình bình luận
        imgHinhBinhLuan.setVisibility(View.GONE);
        //xử lý load hình
        //lấy url của hình
        String url;
        storageReference=FirebaseStorage.getInstance().getReference();
        storageReference.child("/binhluan/"+binhLuan.getHinh())
                .getDownloadUrl()
                // ông cần hiểu là, bất cứ hoạt động kết nối mạng nào, để chạy ở background
                // nghĩa là : nó sẽ tạo 1 thread, chạy song son với mainthread
                // khi có kết quả, nó sẽ gọi 1 hoặc vài hàm nào đó để trả kết quả

                // ở đây là "listener", với hàm onSuccess(..) sẽ dc gọi nếu nó lấy dc hình thành công

                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // chỗ này ông sẽ có uri
                        Glide.with(getContext())
                                .load(uri)
                                .into(imgHinhBinhLuan);
                        progressBar.setVisibility(View.GONE);
                        imgHinhBinhLuan.setVisibility(View.VISIBLE);
                    }
                })

                // ở đây ngược lại, ko lấy dc thì gọi hàm onFailure(..)
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Sorry, cann't load this image",Toast.LENGTH_SHORT).show();
            }
        });
        //truyền bình luận vào đây
        txtThoiGian.setText(binhLuan.getThoigian());
        txtTieuDe.setText(binhLuan.getTieude());
        txtBinhLuan.setText(binhLuan.getBinhluan());
        return view;
    }
}
