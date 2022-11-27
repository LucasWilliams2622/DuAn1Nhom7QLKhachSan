package com.example.duan1nhom7qlkhachsan.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duan1nhom7qlkhachsan.Activity.IAdapterClickEvent;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class BookRoomAdapter extends BaseAdapter {
    private Button btnBookRoom;
    private TextView tvNameBookRoom,tvTypeBookRoom,tvPriceBookRoom;
    private EditText edtCheckInDay,edtCheckOutDay;
    private ArrayList<AppRoom> list;

    public BookRoomAdapter(ArrayList<AppRoom> list) {
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
            view = View.inflate(_viewGroup.getContext(), R.layout.item_book_room,null);
            btnBookRoom = view.findViewById(R.id.btnUpdate);

            tvNameBookRoom = view.findViewById(R.id.tvNameBookRoom);
            tvTypeBookRoom = view.findViewById(R.id.tvTypeBookRoom);
            tvPriceBookRoom = view.findViewById(R.id.tvPriceBookRoom);
            edtCheckInDay = view.findViewById(R.id.edtCheckInDay);
            edtCheckOutDay = view.findViewById(R.id.edtCheckOutDay);

            BookRoomAdapter.ViewHolder holder = new BookRoomAdapter.ViewHolder(btnBookRoom,tvNameBookRoom,tvTypeBookRoom,tvPriceBookRoom,edtCheckInDay,edtCheckOutDay);
            view.setTag(holder);
        }
        AppRoom room = (AppRoom) getItem(_i);
        BookRoomAdapter.ViewHolder holder = (BookRoomAdapter.ViewHolder) view.getTag();
        holder.tvNameBookRoom.setText(room.getEndDay());
        holder.tvTypeBookRoom.setText(room.getStartDay());
        holder.tvPriceBookRoom.setText(room.getNameRoom());
        holder.edtCheckInDay.setText(room.getIdRoom());
        holder.edtCheckOutDay.setText(room.getTypeRoom());

        holder.btnBookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IAdapterClickEvent iAdapterClickEvent = (IAdapterClickEvent) _viewGroup.getContext();
                iAdapterClickEvent.onUpdateRoomClick(room);
            }
        });

        return view;

    }
    private static class ViewHolder{
        final Button btnBookRoom;
        final TextView tvNameBookRoom,tvTypeBookRoom,tvPriceBookRoom;
        final EditText edtCheckInDay,edtCheckOutDay;

        public ViewHolder( Button btnBookRoom ,TextView tvNameBookRoom,TextView tvTypeBookRoom, TextView tvPriceBookRoom, EditText edtCheckInDay, EditText edtCheckOutDay) {
            this.btnBookRoom = btnBookRoom;
            this.tvNameBookRoom = tvNameBookRoom;
            this.tvTypeBookRoom = tvTypeBookRoom;
            this.tvPriceBookRoom = tvPriceBookRoom;
            this.edtCheckInDay = edtCheckInDay;
            this.edtCheckOutDay = edtCheckOutDay;

        }
    }
}
