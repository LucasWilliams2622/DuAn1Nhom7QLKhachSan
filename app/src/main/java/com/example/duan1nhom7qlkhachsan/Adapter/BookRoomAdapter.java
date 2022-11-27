package com.example.duan1nhom7qlkhachsan.Adapter;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.Activity.AdapterAddRoomClick;

import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class BookRoomAdapter extends BaseAdapter {
    private Button btnBookRoom;
    private TextView tvNameBookRoom,tvTypeBookRoom,tvPriceBookRoom;
    private EditText edtCheckInDay,edtCheckOutDay;
    private ArrayList<AppRoom> list;
    Calendar calendar = Calendar.getInstance();
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
        ViewHolder holder = (BookRoomAdapter.ViewHolder) view.getTag();


        holder.tvNameBookRoom.setText("Tên phòng: "+room.getNameRoom());
        holder.tvTypeBookRoom.setText("Loại phòng: "+room.getTypeRoom());
        holder.tvPriceBookRoom.setText("Gía phòng: "+room.getPriceRoom());

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
                                String ngay  ="";
                                String  thang ="";
                                if(dayOfMonth<10)
                                {
                                    ngay ="0"+dayOfMonth;
                                }
                                else
                                {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if (month<10)
                                {
                                    thang ="0"+(month+1);
                                }else
                                {
                                    thang = String.valueOf(month+1);
                                }
                                holder.edtCheckInDay.setText(year+"/"+thang+"/"+ngay);//month in DatePickerDialog 0 -->11
                                Log.d(">>>>>>>>>>>>>>>>>>","date:"+year+"/"+thang+"/"+ngay);
                            }
                        }
                        ,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)

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
                                String ngay  ="";
                                String  thang ="";
                                if(dayOfMonth<10)
                                {
                                    ngay ="0"+dayOfMonth;
                                }
                                else
                                {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if (month<10)
                                {
                                    thang ="0"+(month+1);
                                }else
                                {
                                    thang = String.valueOf(month+1);
                                }
                                holder.edtCheckOutDay.setText(year+"/"+thang+"/"+ngay);//month in DatePickerDialog 0 -->11
                                Log.d(">>>>>>>>>>>>>>>>>>","date:"+year+"/"+thang+"/"+ngay);

                            }
                        }
                        ,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog.show();
            }
        });


        holder.btnBookRoom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                AdapterAddRoomClick iAdapterClickEvent = (AdapterAddRoomClick) _viewGroup.getContext();
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
