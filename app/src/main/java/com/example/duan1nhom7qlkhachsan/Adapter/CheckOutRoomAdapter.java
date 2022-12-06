package com.example.duan1nhom7qlkhachsan.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.duan1nhom7qlkhachsan.Activity.CheckOutActivity;
import com.example.duan1nhom7qlkhachsan.Fragment.CheckOutRoomFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppBookedRoom;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckOutRoomAdapter extends BaseAdapter {
    private ArrayList<AppBookedRoom> list  ;
    private Button btnCheckOutRoom;
    CheckOutRoomFragment checkOutRoomFragment;

    private TextView tvTenPhong, tvMaPhong, tvLoaiPhong, tvGiaPhong,tvEndDay,tvStartDay;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public CheckOutRoomAdapter(ArrayList<AppBookedRoom> list, CheckOutRoomFragment checkOutRoomFragment) {
        this.list = list;
        this.checkOutRoomFragment= checkOutRoomFragment;
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
            view = View.inflate(_viewGroup.getContext(), R.layout.item_check_out_room, null);
            tvEndDay = view.findViewById(R.id.tvEndDay);
            tvStartDay = view.findViewById(R.id.tvStartDay);
            btnCheckOutRoom = view.findViewById(R.id.btnCheckOutRoom);
            tvTenPhong = view.findViewById(R.id.tvTenPhong);
            tvMaPhong = view.findViewById(R.id.tvMaPhong);
            tvLoaiPhong = view.findViewById(R.id.tvLoaiPhong);
            tvGiaPhong = view.findViewById(R.id.tvGiaPhong);
            ViewHolder holder = new ViewHolder(btnCheckOutRoom, tvTenPhong, tvMaPhong, tvLoaiPhong, tvGiaPhong,tvEndDay,tvStartDay);
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

        holder.btnCheckOutRoom.setOnClickListener(new View.OnClickListener() {
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

                new AlertDialog.Builder(btnCheckOutRoom.getContext())
                        .setTitle("Xóa")
                        .setMessage("Bạn muốn trả phòng ?")
                        .setNegativeButton("Không", null)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("bookedRoom").document(room.getRoomId())
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(btnCheckOutRoom.getContext(), "Trả phòng thành công", Toast.LENGTH_SHORT).show();
                                                checkOutRoomFragment.onAdapter();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(btnCheckOutRoom.getContext(), "Trả phòng không thành công", Toast.LENGTH_SHORT).show();
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
        final Button btnCheckOutRoom;
        final TextView tvTenPhong, tvMaPhong, tvLoaiPhong, tvGiaPhong,tvStartDay,tvEndDay;

        public ViewHolder(Button btnCheckOutRoom, TextView tvTenPhong, TextView tvMaPhong, TextView tvLoaiPhong, TextView tvGiaPhong, TextView tvStartDay, TextView tvEndDay) {
            this.btnCheckOutRoom = btnCheckOutRoom;
            this.tvTenPhong = tvTenPhong;
            this.tvMaPhong = tvMaPhong;
            this.tvLoaiPhong = tvLoaiPhong;
            this.tvGiaPhong = tvGiaPhong;
            this.tvStartDay = tvStartDay;
            this.tvEndDay = tvEndDay;
        }
    }


}
