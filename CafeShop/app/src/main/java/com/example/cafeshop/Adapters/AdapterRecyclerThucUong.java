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

    private OnItemClicklistener mListener;

    public  AdapterRecyclerThucUong(List<ThucUongModel> thucUongModelList, int resource){
        this.thucUongModelList=thucUongModelList;
        this.resource=resource;
    }

    public interface OnItemClicklistener{
        void OnPlusClick(int position);
        void OnMinusClick(int position);
    }
    public void setOnItemClickListener(OnItemClicklistener listener){
        mListener=listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenThucUong;
        ImageView imgThucUong;
        TextView txtDanhGia;
        TextView txtGiaThucUong;
        ImageButton btnThemDoUong;
        ImageButton btnXoaDoUong;
        public ViewHolder(@NonNull View itemView, final OnItemClicklistener listener) {
            super(itemView);
            txtTenThucUong=(TextView) itemView.findViewById(R.id.txtTenThucUong);
            imgThucUong=(ImageView) itemView.findViewById(R.id.imgThucUong);
            txtDanhGia=(TextView) itemView.findViewById(R.id.txtDanhGiaThucUong);
            txtGiaThucUong=(TextView) itemView.findViewById(R.id.txtGiaThucUong);
            btnThemDoUong=itemView.findViewById(R.id.btnThemDoUong);
            btnXoaDoUong=itemView.findViewById(R.id.btnXoaDoUong);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.OnPlusClick(position);
                        }
                    }
                }
            });
            btnThemDoUong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.OnPlusClick(position);
                        }
                    }
                }
            });
            btnXoaDoUong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.OnMinusClick(position);
                        }
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    //Khởi tạo giao diện:
    public AdapterRecyclerThucUong.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
        AdapterRecyclerThucUong.ViewHolder viewHolder=new AdapterRecyclerThucUong.ViewHolder(view,mListener);
        return viewHolder;
    }

    //Lấy đối tượng quán ăn là gọi ra
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        ThucUongModel thucUongModel=thucUongModelList.get(i);
        viewHolder.txtTenThucUong.setText(thucUongModel.getTenthucuong());
        viewHolder.txtDanhGia.setText(thucUongModel.getDanhgia() + "");
        viewHolder.txtGiaThucUong.setText(thucUongModel.getGiatien() +"");
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
