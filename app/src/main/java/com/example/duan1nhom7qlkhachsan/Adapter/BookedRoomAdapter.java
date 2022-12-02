package com.example.duan1nhom7qlkhachsan.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.duan1nhom7qlkhachsan.Activity.AddRoomActivity;
import com.example.duan1nhom7qlkhachsan.Activity.BookedRoomActivity;
import com.example.duan1nhom7qlkhachsan.Model.AppBookedRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookedRoomAdapter extends BaseAdapter {
    private ArrayList<AppBookedRoom> list  ;
    private Button btnHuyPhong;
    private TextView tvTenPhong, tvMaPhong, tvLoaiPhong, tvGiaPhong,tvEndDay,tvStartDay;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public BookedRoomAdapter(ArrayList<AppBookedRoom> list) {
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
        if (view == null) {
            view = View.inflate(_viewGroup.getContext(), R.layout.item_recycler_booked_room, null);
            tvEndDay = view.findViewById(R.id.tvEndDay);
            tvStartDay = view.findViewById(R.id.tvStartDay);
            btnHuyPhong = view.findViewById(R.id.btnHuyPhong);
            tvTenPhong = view.findViewById(R.id.tvTenPhong);
            tvMaPhong = view.findViewById(R.id.tvMaPhong);
            tvLoaiPhong = view.findViewById(R.id.tvLoaiPhong);
            tvGiaPhong = view.findViewById(R.id.tvGiaPhong);
            ViewHolder holder = new ViewHolder(btnHuyPhong, tvTenPhong, tvMaPhong, tvLoaiPhong, tvGiaPhong,tvEndDay,tvStartDay);
            view.setTag(holder);
        }
        AppBookedRoom room = (AppBookedRoom) getItem(_i);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvMaPhong.setText(room.getCodeRoom());
        holder.tvTenPhong.setText(room.getNameRoom());
        holder.tvLoaiPhong.setText(room.getTypeRoom());
        holder.tvGiaPhong.setText(room.getPriceRoom());
        holder.tvStartDay.setText(room.getStartDay());
        holder.tvEndDay.setText(room.getEndDay());

        holder.btnHuyPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeRoom = holder.tvMaPhong.getText().toString();
                String nameRoom = holder.tvTenPhong.getText().toString();
                String typeRoom = holder.tvLoaiPhong.getText().toString();
                String priceRoom = holder.tvGiaPhong.getText().toString();
                String startDay = holder.tvStartDay.getText().toString();
                String endDay = holder.tvEndDay.getText().toString();

                // private String idRoom,nameRoom,typeRoom,priceRoom,startDay,endDay;
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("codeRoom", codeRoom);
                user.put("nameRoom", nameRoom);
                user.put("typeRoom", typeRoom);
                user.put("priceRoom", priceRoom);
                user.put("startDay", startDay);
                user.put("endDay", endDay);

                new AlertDialog.Builder(btnHuyPhong.getContext())
                        .setTitle("Xóa")
                        .setMessage("Xóa sẽ không phục hồi được")
                        .setNegativeButton("Hủy", null)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("room").document(room.getRoomId())
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(btnHuyPhong.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(btnHuyPhong.getContext(), "Xóa khong thành công", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            }
                        })
                        .show();


            }
        });
        return view;
    }

    private static class ViewHolder {
        final Button btnHuyPhong;
        final TextView tvTenPhong, tvMaPhong, tvLoaiPhong, tvGiaPhong,tvStartDay,tvEndDay;

        public ViewHolder(Button btnHuyPhong, TextView tvTenPhong, TextView tvMaPhong, TextView tvLoaiPhong, TextView tvGiaPhong, TextView tvStartDay, TextView tvEndDay) {
            this.btnHuyPhong = btnHuyPhong;
            this.tvTenPhong = tvTenPhong;
            this.tvMaPhong = tvMaPhong;
            this.tvLoaiPhong = tvLoaiPhong;
            this.tvGiaPhong = tvGiaPhong;
            this.tvStartDay = tvStartDay;
            this.tvEndDay = tvEndDay;
        }
    }


}
