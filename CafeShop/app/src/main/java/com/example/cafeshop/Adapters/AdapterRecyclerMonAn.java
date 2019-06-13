package com.example.cafeshop.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cafeshop.Model.MonAnModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterRecyclerMonAn extends RecyclerView.Adapter<AdapterRecyclerMonAn.ViewHolder> {
    List<MonAnModel> monAnModelList;
    int resource; //giao diện layout_reyclerview_monan
//    ImageView imgMonAn;
//    Button btnDatHang;


    public  AdapterRecyclerMonAn(List<MonAnModel> monAnModelList, int resource){
        this.monAnModelList=monAnModelList;
        this.resource=resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenMonAn;
        ImageView imgMonAn;
        TextView txtDanhGia;
        TextView txtGiaTien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenMonAn=(TextView) itemView.findViewById(R.id.txtTenMonAn);
            imgMonAn=(ImageView) itemView.findViewById(R.id.imgMonAn);
            txtDanhGia =(TextView) itemView.findViewById(R.id.txtDanhGiaMonAn);
            txtGiaTien = (TextView) itemView.findViewById(R.id.txtGiaMonAn);
        }
    }


    @NonNull
    @Override
    //Khởi tạo giao diện:
    public AdapterRecyclerMonAn.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
        return new ViewHolder(view);
    }

    //Lấy đối tượng quán ăn là gọi ra
    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerMonAn.ViewHolder viewHolder, int i) {
        MonAnModel monAnModel=monAnModelList.get(i);
        viewHolder.txtTenMonAn.setText(monAnModel.getTenmonan());
        viewHolder.txtDanhGia.setText(monAnModel.getDanhgia()+"");
        viewHolder.txtGiaTien.setText(monAnModel.getGiatien()+"");
        //Kt quán ăn có hình không? Nếu như có hình thì download từ storage/hinhmonan, phải implement storage từ Firebase
        if(monAnModel.getBitmapList().size()>0){
            viewHolder.imgMonAn.setImageBitmap(monAnModel.getBitmapList().get(0));
        }
    }

    @Override
    public int getItemCount() {
        return monAnModelList.size();
    }


}
