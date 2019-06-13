package com.example.cafeshop.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafeshop.Model.TinTucModel;
import com.example.cafeshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterRecyclerTinTuc extends RecyclerView.Adapter<AdapterRecyclerTinTuc.ViewHolder> {
    List<TinTucModel> tinTucModelList;
    int resource; //giao diện layout_reyclerview_tintuc
    Dialog dialog;

    private Context context;


    public  AdapterRecyclerTinTuc(List<TinTucModel> tinTucModelList, int resource){
        this.tinTucModelList=tinTucModelList;
        this.resource=resource;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        LinearLayout itemRecyclerTinTuc;

        TextView txtTieuDeTinTuc;
        TextView txtNoiDungTinTuc;
        ImageView imgTinTuc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTieuDeTinTuc=(TextView) itemView.findViewById(R.id.txtTieuDeTinTuc);
            txtNoiDungTinTuc=(TextView) itemView.findViewById(R.id.txtNoiDungTinTuc);
            imgTinTuc=(ImageView) itemView.findViewById(R.id.imgTinTuc);

//            itemRecyclerTinTuc=(LinearLayout)itemView.findViewById(R.id.item_recyclerTinTuc);
        }
    }


    @NonNull
    @Override
    //Khởi tạo giao diện:
    public AdapterRecyclerTinTuc.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);

        ViewHolder viewHolder=new ViewHolder(view);
//        viewHolder.itemRecyclerTinTuc.setOnClickListener(new View.OnClickListener() {
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
    public void onBindViewHolder(@NonNull final AdapterRecyclerTinTuc.ViewHolder viewHolder, final int i) {
        final TinTucModel tinTucModel=tinTucModelList.get(i);
        viewHolder.txtTieuDeTinTuc.setText(tinTucModel.getTieude());
        viewHolder.txtNoiDungTinTuc.setText(tinTucModel.getNoidung());

//        viewHolder.txtTieuDeTinTuc.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_animation));
        //Kt tin tức có hình không? Nếu như có hình thì download từ storage/hinhtintuc, phải implement storage từ Firebase
        if(tinTucModel.getBitmapList().size()>0){
            viewHolder.imgTinTuc.setImageBitmap(tinTucModel.getBitmapList().get(0));
        }

//        viewHolder.itemRecyclerTinTuc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"Test click"+ String.valueOf(viewHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return tinTucModelList.size();
    }


}
