package com.example.duan1nhom7qlkhachsan.Fragment;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.duan1nhom7qlkhachsan.Model.AppBookedRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppBookedService;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;
import com.example.duan1nhom7qlkhachsan.SharedPreferances.MySharedPerferances;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class DoanhThuFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Integer> sumDoanhThu = new ArrayList<>();
    ArrayList<Integer> sumService = new ArrayList<>();
    SharedPreferences sharedDoanhThu = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        sharedDoanhThu = getActivity().getSharedPreferences("DoanhThu", 0);

        EditText edtStartDay = view.findViewById(R.id.edtStartDay);
        EditText edtEndDay = view.findViewById(R.id.edtEndDay);
        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        TextView tvResult = view.findViewById(R.id.tvResult);
        TextView tvMoneyRoom = view.findViewById(R.id.tvMoneyRoom);
        TextView tvMoneyService = view.findViewById(R.id.tvMoneyService);

        Calendar calendar = Calendar.getInstance();
        edtStartDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
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
                                edtStartDay.setText(year + "/" + thang + "/" + ngay);//month in DatePickerDialog 0 -->11
                            }
                        }
                        , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)

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
                                edtEndDay.setText(year + "/" + thang + "/" + ngay);//month in DatePickerDialog 0 -->11
                            }
                        }
                        , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ngaybatdau = edtStartDay.getText().toString().trim();
                String ngayketthuc = edtEndDay.getText().toString().trim()  ;
                ngaybatdau = ngaybatdau.replace("/", "");
                ngayketthuc = ngayketthuc.replace("/", "");

                Log.d(">>>>>>>>>>", "ngaybatdau " + ngaybatdau);
                Log.d(">>>>>>>>>>", "ngayketthuc " + ngayketthuc);

                db.collection("bookedService")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
//                                    ArrayList<AppBookedService> list = new ArrayList<>();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Map<String, Object> map = document.getData();
                                        String priceService = map.get("priceService").toString().trim();
                                        sumService.add(Integer. valueOf(priceService));
//                                        Log.d(">>>>>>>>>>", "Price: " + priceService);
                                    }
                                    Log.d(">>>>>>>>>>", "List: " + sumService);
                                    int service = 0;
                                    for (int i = 0; i < sumService.size(); i++) {
                                        service = service + sumService.get(i);

                                    }
                                    sumService.clear();
                                    Log.d(">>>>>>>>>>", "Sum Dich vu: " + service);

                                    SharedPreferences.Editor editorDaonhThu = sharedDoanhThu.edit();
                                    editorDaonhThu.putString("moneyOfService", String.valueOf(service));
                                    Log.d(">>>>>>>>>>>Daonh thu ", "money ò sẻvice" + String.valueOf(service));
                                    editorDaonhThu.apply();


                                }
                            }
                        });


                db.collection("bookedRoom")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
//                                    ArrayList<AppBookedRoom> list = new ArrayList<>();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Map<String, Object> map = document.getData();
                                        String priceRoom = map.get("priceRoom").toString();
                                        sumDoanhThu.add(Integer.valueOf(priceRoom));
//                                        Log.d(">>>>>>>>>>", "Price: " + priceRoom);
                                    }
                                    Log.d(">>>>>>>>>>", "List: " + sumDoanhThu);
                                    int sum = 0;
                                    for (int i = 0; i < sumDoanhThu.size(); i++) {
                                        sum = sum + sumDoanhThu.get(i);
                                    }
                                    sumDoanhThu.clear();
                                    Log.d(">>>>>>>>>>", "Sum Room: " + sum);

                                    SharedPreferences.Editor editorDaonhThu = sharedDoanhThu.edit();
                                    editorDaonhThu.putString("moneyOfRoom", String.valueOf(sum));
                                    editorDaonhThu.apply();

                                    int moneyOfService = Integer.parseInt(sharedDoanhThu.getString("moneyOfService", ""));
                                    int moneyOfRoom = Integer.parseInt(sharedDoanhThu.getString("moneyOfRoom", ""));
                                    int tongDoanhThu = moneyOfRoom + moneyOfService;
                                    Log.d(">>>>>>>>>>>", "Total" + tongDoanhThu);
                                    tvMoneyRoom.setText(moneyOfRoom + " $");
                                    tvMoneyService.setText(moneyOfService + " $");
                                    tvResult.setText(tongDoanhThu + " $");

                                }
                            }
                        });
            }
        });
        return view;
    }


}