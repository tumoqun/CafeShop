package com.example.cafeshop.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cafeshop.FontManager;
import com.example.cafeshop.Model.CuaHangModel;
import com.example.cafeshop.Model.MonAnModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterRecyclerCuaHang extends RecyclerView.Adapter<AdapterRecyclerCuaHang.ViewHolder> {
    List<CuaHangModel> cuaHangModelList;
    int resource; //giao diện layout_reyclerview_cuahang



//    ImageView imgCuaHang;
//    Button btnDatHang;


    public  AdapterRecyclerCuaHang(List<CuaHangModel> cuaHangModelList, int resource){
        this.cuaHangModelList=cuaHangModelList;
        this.resource=resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenCuaHang;
        ImageView imgCuaHang;
        TextView txtDiaChi;
        Button btnXemCuaHang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenCuaHang=(TextView) itemView.findViewById(R.id.txtTenCuaHang);
            imgCuaHang=(ImageView) itemView.findViewById(R.id.imgCuaHang);
            txtDiaChi =(TextView) itemView.findViewById(R.id.txtDiaChi);
            btnXemCuaHang=(Button) itemView.findViewById(R.id.btnXemCuaHang);
        }
    }


    @NonNull
    @Override
    //Khởi tạo giao diện:
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
        return new ViewHolder(view);
    }

    //Lấy đối tượng quán ăn là gọi ra
    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerCuaHang.ViewHolder viewHolder, int i) {




        CuaHangModel cuaHangModel=cuaHangModelList.get(i);
        viewHolder.txtTenCuaHang.setText(cuaHangModel.getTencuahang());
        viewHolder.txtDiaChi.setText(cuaHangModel.getDiachi());
        //Kt cửa hàng có hình không? Nếu như có hình thì download từ storage/hinhcuahang, phải implement storage từ Firebase
        if(cuaHangModel.getBitmapList().size()>0){
            viewHolder.imgCuaHang.setImageBitmap(cuaHangModel.getBitmapList().get(0));
        }

//        Typeface iconCuaHang=FontManager.getTypeface(viewHolder.btnXemCuaHang.getContext(),FontManager.FONTAWESOME);
//        viewHolder.btnXemCuaHang.setTypeface(iconCuaHang);
//        viewHolder.btnXemCuaHang.setText(Html.fromHtml("&#xf494;"));
    }

    @Override
    public int getItemCount() {
        return cuaHangModelList.size();
    }


}
