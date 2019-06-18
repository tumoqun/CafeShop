package com.example.cafeshop.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cafeshop.Model.DonHangModel;
import com.example.cafeshop.R;

import java.util.List;

public class AdapterRecyclerDonHang extends RecyclerView.Adapter<AdapterRecyclerDonHang.ViewHolder> {
    List<DonHangModel> donHangModelList;
    int resource; //giao diện layout_reyclerview_donhang
    Dialog dialog;

    private Context context;


    public  AdapterRecyclerDonHang(List<DonHangModel> donHangModelList, int resource){
        this.donHangModelList=donHangModelList;
        this.resource=resource;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNgayDatHangLS;
        TextView txtMaDonHangLS;
//        ImageView imgDonHang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNgayDatHangLS=(TextView) itemView.findViewById(R.id.txtNgayDatHangLS);
            txtMaDonHangLS=(TextView) itemView.findViewById(R.id.txtMaDonHangLS);
//            imgDonHang=(ImageView) itemView.findViewById(R.id.imgDonHang);

//            itemRecyclerDonHang=(LinearLayout)itemView.findViewById(R.id.item_recyclerDonHang);
        }
    }


    @NonNull
    @Override
    //Khởi tạo giao diện:
    public AdapterRecyclerDonHang.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);

        ViewHolder viewHolder=new ViewHolder(view);
//        viewHolder.itemRecyclerDonHang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(viewGroup.getContext(),"Test click"+ String.valueOf(viewHolder.getAdapterPosition(),Toast.LENGTH_SHORT).show());
//            }
//        });

        context=viewGroup.getContext();
        return viewHolder;

//        return new ViewHolder(view);
    }

    //Lấy đối tượng món ăn và gọi ra
    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerDonHang.ViewHolder viewHolder, final int i) {
        final DonHangModel donHangModel=donHangModelList.get(i);
        viewHolder.txtNgayDatHangLS.setText(donHangModel.getThoigian());
        viewHolder.txtMaDonHangLS.setText(donHangModel.getMadonhang());

//        if(donHangModel.getBitmapList().size()>0){
//            viewHolder.imgTinTuc.setImageBitmap(tinTucModel.getBitmapList().get(0));
//        }

//        viewHolder.itemRecyclerTinTuc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"Test click"+ String.valueOf(viewHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return donHangModelList.size();
    }


}
