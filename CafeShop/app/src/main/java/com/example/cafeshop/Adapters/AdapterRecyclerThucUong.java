package com.example.cafeshop.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cafeshop.Model.ThucUongModel;
import com.example.cafeshop.R;

import java.util.List;

public class AdapterRecyclerThucUong extends RecyclerView.Adapter<AdapterRecyclerThucUong.ViewHolder> {
    List<ThucUongModel> thucUongModelList;
    int resource; //giao diện layout_reyclerview_thucuong

    public  AdapterRecyclerThucUong(List<ThucUongModel> thucUongModelList, int resource){
        this.thucUongModelList=thucUongModelList;
        this.resource=resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenThucUong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenThucUong=(TextView) itemView.findViewById(R.id.txtTenThucUong);
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
    public void onBindViewHolder(@NonNull AdapterRecyclerThucUong.ViewHolder viewHolder, int i) {
    ThucUongModel thucUongModel=thucUongModelList.get(i);
    viewHolder.txtTenThucUong.setText(thucUongModel.getTenthucuong());
    }

    @Override
    public int getItemCount() {
        return thucUongModelList.size();
    }


}
