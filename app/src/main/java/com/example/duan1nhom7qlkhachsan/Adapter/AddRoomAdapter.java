package com.example.duan1nhom7qlkhachsan.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1nhom7qlkhachsan.Activity.AdapterAddRoomClick;

import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class AddRoomAdapter extends BaseAdapter {
    private ArrayList<AppRoom> list;


    private Button btnUpdate,btnDelete;
    private TextView tvCodeRoom,tvNameRoom,tvTypeRoom,tvPriceRoom,tvStartDay,tvEndDay;

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
            tvCodeRoom = view.findViewById(R.id.tvCodeRoom);
            tvNameRoom = view.findViewById(R.id.tvNameRoom);
            tvTypeRoom = view.findViewById(R.id.tvTypeRoom);
            tvPriceRoom = view.findViewById(R.id.tvPriceRoom);

          ViewHolder holder = new ViewHolder(tvCodeRoom,tvNameRoom,tvTypeRoom,tvPriceRoom,tvStartDay,tvEndDay,btnDelete,btnUpdate);

            view.setTag(holder);
        }
        AppRoom room = (AppRoom) getItem(_i);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvCodeRoom.setText("Mã phòng: " + room.getCodeRoom());
        holder.tvNameRoom.setText("Tên phòng: " + room.getNameRoom());
        holder.tvTypePhong.setText("Loại phòng: " + room.getTypeRoom());
        holder.tvPricePhong.setText("Giá phòng: " + room.getPriceRoom());
        holder.tvStartDay.setText("Ngày ở: " + room.getStartDay());
        holder.tvEndDay.setText("Ngày về: " + room.getEndDay());


        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AdapterAddRoomClick iAdapterClickEvent = (AdapterAddRoomClick) _viewGroup.getContext();
                iAdapterClickEvent.onUpdateRoomClick(room);
            }
        });
        holder.btnDelete .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdapterAddRoomClick  iAdapterClickEvent = (AdapterAddRoomClick) _viewGroup.getContext();
                iAdapterClickEvent.onDeleteRoomClick(room);
            }
        });

        return view;

    }
    private static class ViewHolder{

        final TextView tvCodeRoom,tvNameRoom,tvTypePhong,tvPricePhong,tvStartDay,tvEndDay;
        final Button btnDelete,btnUpdate;

        public ViewHolder(TextView tvCodeRoom, TextView tvNameRoom, TextView tvTypePhong, TextView tvPricePhong, TextView tvStartDay, TextView tvEndDay, Button btnDelete, Button btnUpdate) {
            this.tvCodeRoom = tvCodeRoom;
            this.tvNameRoom = tvNameRoom;
            this.tvTypePhong = tvTypePhong;
            this.tvPricePhong = tvPricePhong;
            this.tvStartDay = tvStartDay;
            this.tvEndDay = tvEndDay;
            this.btnDelete = btnDelete;
            this.btnUpdate = btnUpdate;
        }
    }
}
