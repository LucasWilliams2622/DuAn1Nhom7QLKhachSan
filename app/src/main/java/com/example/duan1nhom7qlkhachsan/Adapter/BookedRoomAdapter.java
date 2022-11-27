package com.example.duan1nhom7qlkhachsan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class BookedRoomAdapter extends BaseAdapter {


    private ArrayList<AppRoom> list;
    private Context context;
    EditText txtEndDay,txtStartDay;
    Button btnHuyPhong;
    TextView tvTenPhong,tvMaPhong,tvLoaiPhong,tvGiaPhong;

    public BookedRoomAdapter(ArrayList<AppRoom> list) {
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int _i, View _view, ViewGroup _viewGroup) {
        View view = _view;
        if (view == null)
        {
            view = View.inflate(_viewGroup.getContext(), R.layout.item_recycler_booked_room,null);
            txtEndDay = view.findViewById(R.id.txtEndDay);
            txtStartDay = view.findViewById(R.id.txtStartDay);
            btnHuyPhong = view.findViewById(R.id.btnHuyPhong);
            tvTenPhong = view.findViewById(R.id.tvTenPhong);
            tvMaPhong = view.findViewById(R.id.tvMaPhong);
            tvLoaiPhong = view.findViewById(R.id.tvLoaiPhong);
            tvGiaPhong = view.findViewById(R.id.tvGiaPhong);
            ViewHolder holder = new ViewHolder(txtEndDay,txtStartDay,btnHuyPhong,tvTenPhong,tvMaPhong,tvLoaiPhong,tvGiaPhong);
            view.setTag(holder);
        }
            AppRoom room = (AppRoom) getItem(_i);
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.txtEndDay.setText(room.getEndDay());
            holder.txtStartDay.setText(room.getStartDay());
            holder.tvTenPhong.setText(room.getNameRoom());
            holder.tvMaPhong.setText(room.getCodeRoom());
            holder.tvLoaiPhong.setText(room.getTypeRoom());
            holder.tvGiaPhong.setText(room.getPriceRoom());

        return view;
    }
    private static class ViewHolder{
        final EditText txtEndDay,txtStartDay;
        final Button btnHuyPhong;
        final TextView tvTenPhong,tvMaPhong,tvLoaiPhong,tvGiaPhong;

        public ViewHolder(EditText txtEndDay, EditText txtStartDay, Button btnHuyPhong, TextView tvTenPhong, TextView tvMaPhong, TextView tvLoaiPhong, TextView tvGiaPhong) {
            this.txtEndDay = txtEndDay;
            this.txtStartDay = txtStartDay;
            this.btnHuyPhong = btnHuyPhong;
            this.tvTenPhong = tvTenPhong;
            this.tvMaPhong = tvMaPhong;
            this.tvLoaiPhong = tvLoaiPhong;
            this.tvGiaPhong = tvGiaPhong;
        }
    }


}
