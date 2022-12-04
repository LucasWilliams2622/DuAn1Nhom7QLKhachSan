package com.example.duan1nhom7qlkhachsan.Adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.duan1nhom7qlkhachsan.Activity.AdapterAddRoomClick;

import com.example.duan1nhom7qlkhachsan.Activity.AddRoomActivity;
import com.example.duan1nhom7qlkhachsan.Activity.BookRoomActivity;
import com.example.duan1nhom7qlkhachsan.Fragment.BookRoomFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookRoomAdapter extends BaseAdapter {
    private Button btnBookRoom;
    private TextView tvCodeBookRoom, tvNameBookRoom, tvTypeBookRoom, tvPriceBookRoom;
    private EditText edtCheckInDay, edtCheckOutDay;
    private ImageView ivHotelRoom;
    private ArrayList<AppRoom> list;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context context;
    Calendar calendar = Calendar.getInstance();

    public BookRoomAdapter(ArrayList<AppRoom> list, Context context) {
        this.context = context;
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
            view = View.inflate(_viewGroup.getContext(), R.layout.item_book_room, null);
            btnBookRoom = view.findViewById(R.id.btnBookRoom);
            tvCodeBookRoom = view.findViewById(R.id.tvCodeBookRoom);
            tvNameBookRoom = view.findViewById(R.id.tvNameBookRoom);
            tvTypeBookRoom = view.findViewById(R.id.tvTypeBookRoom);
            tvPriceBookRoom = view.findViewById(R.id.tvPriceBookRoom);
            edtCheckInDay = view.findViewById(R.id.edtCheckInDay);
            edtCheckOutDay = view.findViewById(R.id.edtCheckOutDay);

//            view.findViewById(R.id.btnBookRoom).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ivHotelRoom.startActionMode( AnimationUtils.loadAnimation(
//                           context.getApplicationContext(),
//                            R.anim.animation_scale
//                    ));
//                }
//            });


            BookRoomAdapter.ViewHolder holder = new BookRoomAdapter.ViewHolder(btnBookRoom, tvCodeBookRoom, tvNameBookRoom, tvTypeBookRoom, tvPriceBookRoom, edtCheckInDay, edtCheckOutDay);
            view.setTag(holder);
        }
        AppRoom room = (AppRoom) getItem(_i);
        ViewHolder holder = (BookRoomAdapter.ViewHolder) view.getTag();

        holder.tvCodeBookRoom.setText(room.getCodeRoom());
        holder.tvNameBookRoom.setText("Tên phòng: " + room.getNameRoom());
        holder.tvTypeBookRoom.setText("Loại phòng: " + room.getTypeRoom());
        holder.tvPriceBookRoom.setText(room.getPriceRoom());
        holder.edtCheckOutDay.setText(room.getEndDay());
        holder.edtCheckInDay.setText(room.getStartDay());

        edtCheckInDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        edtCheckInDay.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";
                                if (dayOfMonth < 10) {
                                    ngay = "0" + dayOfMonth;
                                } else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if (month < 10) {
                                    thang = "0" + (month + 1);
                                } else {
                                    thang = String.valueOf(month + 1);
                                }
                                holder.edtCheckInDay.setText("Check In Day: " + year + "/" + thang + "/" + ngay);//month in DatePickerDialog 0 -->11
                                Log.d(">>>>>>>>>>>>>>>>>>", "date:" + year + "/" + thang + "/" + ngay);
                            }
                        }
                        , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog.show();
            }
        });


        holder.edtCheckOutDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        edtCheckOutDay.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";
                                if (dayOfMonth < 10) {
                                    ngay = "0" + dayOfMonth;
                                } else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if (month < 10) {
                                    thang = "0" + (month + 1);
                                } else {
                                    thang = String.valueOf(month + 1);
                                }
                                holder.edtCheckOutDay.setText("Check Out Day: " + year + "/" + thang + "/" + ngay);//month in DatePickerDialog 0 -->11
                                Log.d(">>>>>>>>>>>>>>>>>>", "date:" + year + "/" + thang + "/" + ngay);

                            }
                        }
                        , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog.show();
            }
        });


        holder.btnBookRoom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


//                private TextView tvIdBookRoom, tvNameBookRoom, tvTypeBookRoom, tvPriceBookRoom;
//                private EditText edtCheckInDay, edtCheckOutDay;
                String codeRoom = holder.tvCodeBookRoom.getText().toString();
                String nameRoom = holder.tvNameBookRoom.getText().toString();
                String typeRoom = holder.tvTypeBookRoom.getText().toString();
                String priceRoom = holder.tvPriceBookRoom.getText().toString();
                String startDay = holder.edtCheckInDay.getText().toString();
                String endDay = holder.edtCheckOutDay.getText().toString();

                // private String idRoom,nameRoom,typeRoom,priceRoom,startDay,endDay;
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("codeRoom", codeRoom);
                user.put("nameRoom", nameRoom);
                user.put("typeRoom", typeRoom);
                user.put("priceRoom", priceRoom);
                user.put("startDay", startDay);
                user.put("endDay", endDay);


                db.collection("bookedRoom")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                //Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Log.d(">>>>>>>>>>>>", "Booked room successful");
                                holder.btnBookRoom.setText("Đã đặt thành công");
                                holder.btnBookRoom.setVisibility(View.INVISIBLE);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(">>>>>>>>>>>>", "Booked room failed");
                            }
                        });
                Toast.makeText(holder.edtCheckInDay.getContext(), "Đặt phòng thành công", Toast.LENGTH_SHORT).show();
            }
        });


        return view;

    }

    private static class ViewHolder {
        final Button btnBookRoom;
        final TextView tvCodeBookRoom, tvNameBookRoom, tvTypeBookRoom, tvPriceBookRoom;
        final EditText edtCheckInDay, edtCheckOutDay;


        public ViewHolder(Button btnBookRoom, TextView tvCodeBookRoom, TextView tvNameBookRoom, TextView tvTypeBookRoom, TextView tvPriceBookRoom, EditText edtCheckInDay, EditText edtCheckOutDay) {
            this.btnBookRoom = btnBookRoom;
            this.tvCodeBookRoom = tvCodeBookRoom;
            this.tvNameBookRoom = tvNameBookRoom;
            this.tvTypeBookRoom = tvTypeBookRoom;
            this.tvPriceBookRoom = tvPriceBookRoom;
            this.edtCheckInDay = edtCheckInDay;
            this.edtCheckOutDay = edtCheckOutDay;

        }
    }
}
