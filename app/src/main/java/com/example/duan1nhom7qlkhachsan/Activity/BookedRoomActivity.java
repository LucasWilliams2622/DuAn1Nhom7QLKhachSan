package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.duan1nhom7qlkhachsan.Fragment.BookedRoomFragment;
import com.example.duan1nhom7qlkhachsan.MainActivity;
import com.example.duan1nhom7qlkhachsan.Model.AppBookedRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class BookedRoomActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button btnBackToMainActivity;
    private AppBookedRoom appRoom = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_room);
        btnBackToMainActivity= findViewById(R.id.btnBackToMainActivity);
        btnBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookedRoomActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        getDataBookedRoom();

    }
    @Override
    protected void onResume() {
        super.onResume();
        getDataBookedRoom();
    }

    public void getDataBookedRoom()
    {
        db.collection("bookedRoom")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppBookedRoom> list= new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map= document.getData();
                                String code=map.get("codeRoom").toString();
                                String name=map.get("nameRoom").toString();
                                String type=map.get("typeRoom").toString();
                                String price=map.get("priceRoom").toString();
                                String startDay=map.get("startDay").toString();
                                String endDay=map.get("endDay").toString();

                                AppBookedRoom room = new AppBookedRoom(-1,code,name,type,price,startDay,endDay);
                                room.setRoomId(document.getId());
                                list.add(room);

                            }
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.flBookedRoom, BookedRoomFragment.newInstance(list))
                                    .commit();
                        }
                    }
                });
    }


}