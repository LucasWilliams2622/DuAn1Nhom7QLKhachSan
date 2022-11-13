package com.example.duan1nhom7qlkhachsan.Fragment;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.duan1nhom7qlkhachsan.R;


public class DoanhThuFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        EditText edtStartDay = view.findViewById(R.id.edtStartDay);
        EditText edtEndDay = view.findViewById(R.id.edtEndDay);
        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        TextView tvResult = view.findViewById(R.id.tvResult);

        Calendar calendar = Calendar.getInstance();
        edtStartDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
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
                                edtStartDay.setText(year+"/"+thang+"/"+ngay);//month in DatePickerDialog 0 -->11
                            }
                        }
                        ,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog.show();
            }
        });

        edtEndDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
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
                                edtEndDay.setText(year+"/"+thang+"/"+ngay);//month in DatePickerDialog 0 -->11
                            }
                        }
                        ,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
//                String ngaybatdau = edtStartDay.getText().toString();
//                String ngayketthuc = edtEndDay.getText().toString();
//                int doanhthu = thongKeDAO.getDoanhThu(ngaybatdau,ngayketthuc);
//                tvResult.setText(doanhthu+"VND");


            }
        });
        return view;
    }
}