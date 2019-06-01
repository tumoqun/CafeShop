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

import com.example.cafeshop.Model.ThucUongModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterRecyclerThucUong extends RecyclerView.Adapter<AdapterRecyclerThucUong.ViewHolder> {
    List<ThucUongModel> thucUongModelList;
    int resource; //giao diện layout_reyclerview_thucuong
//    ImageView imgThucUong;
//    Button btnDatHang;


    public  AdapterRecyclerThucUong(List<ThucUongModel> thucUongModelList, int resource){
        this.thucUongModelList=thucUongModelList;
        this.resource=resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenThucUong;
        ImageView imgThucUong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenThucUong=(TextView) itemView.findViewById(R.id.txtTenThucUong);
            imgThucUong=(ImageView) itemView.findViewById(R.id.imgThucUong);
        }
    }


    @NonNull
    @Override
    //Khởi tạo giao diện:
    public AdapterRecyclerThucUong.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
        return new ViewHolder(view);
    }

    //Lấy đối tượng quán ăn là gọi ra
    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerThucUong.ViewHolder viewHolder, int i) {
        ThucUongModel thucUongModel=thucUongModelList.get(i);
        viewHolder.txtTenThucUong.setText(thucUongModel.getTenthucuong());
    //Kt quán ăn có hình không? Nếu như có hình thì download từ storage/hinhthucuong, phải implement storage từ Firebase
        if(thucUongModel.getBitmapList().size()>0){
            viewHolder.imgThucUong.setImageBitmap(thucUongModel.getBitmapList().get(0));
        }
    }

    @Override
    public int getItemCount() {
        return thucUongModelList.size();
    }


}
