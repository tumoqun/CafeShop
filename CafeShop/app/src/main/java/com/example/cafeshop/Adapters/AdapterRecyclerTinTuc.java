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

import com.example.cafeshop.Model.TinTucModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterRecyclerTinTuc extends RecyclerView.Adapter<AdapterRecyclerTinTuc.ViewHolder> {
    List<TinTucModel> tinTucModelList;
    int resource; //giao diện layout_reyclerview_tintuc



    public  AdapterRecyclerTinTuc(List<TinTucModel> tinTucModelList, int resource){
        this.tinTucModelList=tinTucModelList;
        this.resource=resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTieuDeTinTuc;
        TextView txtNoiDungTinTuc;
        ImageView imgTinTuc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTieuDeTinTuc=(TextView) itemView.findViewById(R.id.txtTieuDeTinTuc);
            txtNoiDungTinTuc=(TextView) itemView.findViewById(R.id.txtNoiDungTinTuc);
            imgTinTuc=(ImageView) itemView.findViewById(R.id.imgTinTuc);
        }
    }


    @NonNull
    @Override
    //Khởi tạo giao diện:
    public AdapterRecyclerTinTuc.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
        return new ViewHolder(view);
    }

    //Lấy đối tượng quán ăn là gọi ra
    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerTinTuc.ViewHolder viewHolder, int i) {
        TinTucModel tinTucModel=tinTucModelList.get(i);
        viewHolder.txtTieuDeTinTuc.setText(tinTucModel.getTieude());
        viewHolder.txtNoiDungTinTuc.setText(tinTucModel.getNoidung());
        //Kt tin tức có hình không? Nếu như có hình thì download từ storage/hinhtintuc, phải implement storage từ Firebase
        if(tinTucModel.getBitmapList().size()>0){
            viewHolder.imgTinTuc.setImageBitmap(tinTucModel.getBitmapList().get(0));
        }
    }


    @Override
    public int getItemCount() {
        return tinTucModelList.size();
    }


}
