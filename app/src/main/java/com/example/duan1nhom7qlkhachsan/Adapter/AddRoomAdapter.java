package com.example.duan1nhom7qlkhachsan.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.duan1nhom7qlkhachsan.Activity.IAdapterClickEvent;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class AddRoomAdapter extends BaseAdapter {
    private ArrayList<AppRoom> list;


    private Button btnUpdate,btnDelete;
    private TextView tvIdRoom,tvNameRoom,tvTypeRoom,tvPriceRoom,tvStartDay,tvEndDay;

    public AddRoomAdapter(ArrayList<AppRoom> list) {
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
            view = View.inflate(_viewGroup.getContext(), R.layout.item_add_room,null);
            btnUpdate = view.findViewById(R.id.btnUpdate);
            btnDelete = view.findViewById(R.id.btnDelete);
            tvEndDay = view.findViewById(R.id.tvEndDay);
            tvStartDay = view.findViewById(R.id.tvStartDay);
            tvIdRoom = view.findViewById(R.id.tvIdRoom);
            tvNameRoom = view.findViewById(R.id.tvNameRoom);
            tvTypeRoom = view.findViewById(R.id.tvTypeRoom);
            tvPriceRoom = view.findViewById(R.id.tvPriceRoom);
            AddRoomAdapter.ViewHolder holder = new AddRoomAdapter.ViewHolder(btnDelete,btnUpdate,tvEndDay,tvStartDay,tvIdRoom,tvNameRoom,tvTypeRoom,tvPriceRoom);
            view.setTag(holder);
        }
        AppRoom room = (AppRoom) getItem(_i);
        AddRoomAdapter.ViewHolder holder = (AddRoomAdapter.ViewHolder) view.getTag();
        holder.tvEndDay.setText(room.getEndDay());
        holder.tvStartDay.setText(room.getStartDay());
        holder.tvTenPhong.setText(room.getNameRoom());
        holder.tvMaPhong.setText(room.getIdRoom());
        holder.tvLoaiPhong.setText(room.getTypeRoom());
        holder.tvGiaPhong.setText(room.getPriceRoom());
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IAdapterClickEvent  iAdapterClickEvent = (IAdapterClickEvent) _viewGroup.getContext();
                iAdapterClickEvent.onUpdateRoomClick(room);
            }
        });
        holder.btnDelete .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IAdapterClickEvent  iAdapterClickEvent = (IAdapterClickEvent) _viewGroup.getContext();
                iAdapterClickEvent.onDeleteRoomClick(room);
            }
        });

        return view;

    }
    private static class ViewHolder{
        final Button btnDelete,btnUpdate;
        final TextView tvTenPhong,tvMaPhong,tvLoaiPhong,tvGiaPhong,tvStartDay,tvEndDay;

        public ViewHolder( Button btnDelete, Button btnUpdate, TextView tvTenPhong,TextView txtEndDay, TextView tvStartDay, TextView tvMaPhong, TextView tvLoaiPhong, TextView tvGiaPhong) {
            this.btnDelete = btnDelete;
            this.btnUpdate = btnUpdate;
            this.tvStartDay = tvStartDay;
            this.tvEndDay = txtEndDay;
            this.tvTenPhong = tvTenPhong;
            this.tvMaPhong = tvMaPhong;
            this.tvLoaiPhong = tvLoaiPhong;
            this.tvGiaPhong = tvGiaPhong;
        }
    }
}
