package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.Adapter.BookedRoomAdapter;
import com.example.duan1nhom7qlkhachsan.Fragment.PhongDaDatFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookedRoomActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText edtStartDay,edtEndDay,edtTenPhong,edtLoaiPhong,edtMaPhong,edtGiaPhong;
    private AppRoom room = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_room);
        edtStartDay = findViewById(R.id.edtStartDay);
        edtEndDay = findViewById(R.id.edtEndDay);
        edtTenPhong = findViewById(R.id.edtTenPhong);
        edtLoaiPhong = findViewById(R.id.edtLoaiPhong);
        edtMaPhong = findViewById(R.id.edtMaPhong);
        edtGiaPhong = findViewById(R.id.edtGiaPhong);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
    public  void onSaveClick(View view) {
        String name = edtTenPhong.getText().toString();
        String type = edtLoaiPhong.getText().toString();
        String id = edtMaPhong.getText().toString();
        String price = edtGiaPhong.getText().toString();
        String startDay = edtStartDay.getText().toString();
        String endDay = edtEndDay.getText().toString();


        // Create a new user with a first and last name
        Map<String, Object> item = new HashMap<>();
        item.put("nameRoom", name);
        item.put("typeRoom", type);
        item.put("idRoom", id);
        item.put("priceRoom", price);
        item.put("startDay", startDay);
        item.put("endDay", endDay);

        if (room == null) {
            // Add a new document with a generated ID
            db.collection("room")
                    .add(item)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(BookedRoomActivity.this, "Insert thành công", Toast.LENGTH_SHORT).show();
                            getData();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

        }
    }
    public void onCancelClick(View view)
    {
        edtTenPhong.setText(null);
        edtLoaiPhong.setText(null);
        edtMaPhong.setText(null);
        edtGiaPhong.setText(null);
        edtStartDay.setText(null);
        edtEndDay.setText(null);

    }
    private  void getData()
    {
        db.collection("room")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppRoom> rooms= new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map= document.getData();
                                String name=map.get("nameRoom").toString();
                                String type=map.get("typeRoom").toString();
                                String id=map.get("idRoom").toString();
                                String price=map.get("priceRoom").toString();
                                String startDay=map.get("startDay").toString();
                                String endDay=map.get("endDay").toString();

                                AppRoom room = new AppRoom(name,type,id,price,startDay,endDay);
                                rooms.add(room);

                            }

                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.flm, PhongDaDatFragment.newInstance(rooms)).commit();
                        }
                    }
                });
    }


}