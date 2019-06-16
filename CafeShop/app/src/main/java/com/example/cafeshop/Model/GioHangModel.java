package com.example.cafeshop.Model;

import java.util.ArrayList;
import java.util.List;

public class GioHangModel {
    List<ThucUongModel> thucUongModelList;
    long TongGia;

    private static GioHangModel INSTANCE;

    public GioHangModel(){
        thucUongModelList=new ArrayList<ThucUongModel>();
        TongGia=Long.valueOf(0);
    }

    public static GioHangModel Instance(){
        if(INSTANCE==null)
        {
            INSTANCE=new GioHangModel();
        }
        return INSTANCE;
    }

    public void ThemVaoGio(ThucUongModel thucUongModel){
        thucUongModelList.add(thucUongModel);
    }
    public void XoaKhoiGio(ThucUongModel thucUongModel){
        for(int i=0;i<thucUongModelList.size();i++)
        {
            if(thucUongModel.getTenthucuong().equals(thucUongModelList.get(i).getTenthucuong())){
                if(thucUongModelList.get(i).getSoLuong()==1)
                {thucUongModelList.remove(thucUongModelList.get(i));}
                else thucUongModelList.get(i).GiamSoLuong();
                return;
            }
        }
    }
    //Xử lý phần tử trùng
    public void XuLyPhanTuTrung()
    {
        for (int i=0;i<thucUongModelList.size()-1;i++){
            for(int j=i+1;j<thucUongModelList.size();j++)
            {
                if(thucUongModelList.get(i).getTenthucuong().equals(thucUongModelList.get(j).getTenthucuong())){
                    thucUongModelList.remove(thucUongModelList.get(j));
                    thucUongModelList.get(i).TangSoLuong();
                }
            }
        }

    }
    public String ThongTinDoUong()
    {
        XuLyPhanTuTrung();
        String temp="";
        for(int i=0;i<thucUongModelList.size();i++){
            temp=temp+thucUongModelList.get(i).getSoLuong()+" "+thucUongModelList.get(i).getTenthucuong()+" "
                    +thucUongModelList.get(i).getGiatien();
            if(i<thucUongModelList.size()-1){
                temp=temp+"\n";
            }
        }
        return temp;
    }
    public int SoLuong(){
        XuLyPhanTuTrung();
        int temp=0;
        for(int i=0;i<thucUongModelList.size();i++)
        {
            temp=temp+thucUongModelList.get(i).getSoLuong();
        }
        return temp;
    }
    public long TinhTongGia(){
        XuLyPhanTuTrung();
        long temp=0;
        for (int i=0;i<thucUongModelList.size();i++){
            temp=temp+thucUongModelList.get(i).getGiatien();
        }
        return temp;
    }
    public void ResetGioHang(){
        thucUongModelList.clear();
        TongGia=0;
    }
}
