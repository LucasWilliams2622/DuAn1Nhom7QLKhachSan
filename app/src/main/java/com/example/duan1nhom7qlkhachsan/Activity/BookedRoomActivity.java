package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.duan1nhom7qlkhachsan.Fragment.BookedRoomFragment;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_room);

    }
    @Override
    protected void onResume() {
        super.onResume();
        getDataBookedRoom();
    }

    private  void getDataBookedRoom()
    {
        db.collection("bookedRoom")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppRoom> list= new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map= document.getData();
                                String name=map.get("nameRoom").toString();
                                String type=map.get("typeRoom").toString();
                                String id=map.get("idRoom").toString();
                                String price=map.get("priceRoom").toString();
                                String startDay=map.get("startDay").toString();
                                String endDay=map.get("endDay").toString();


                                Log.d(">>>>>>>>>>>>>>>>>","name"+name);
                                Log.d(">>>>>>>>>>>>>>>>>","type"+type);
                                Log.d(">>>>>>>>>>>>>>>>>","id"+id);
                                Log.d(">>>>>>>>>>>>>>>>>","price"+price);
                                Log.d(">>>>>>>>>>>>>>>>>","startDay"+startDay);
                                Log.d(">>>>>>>>>>>>>>>>>","endDay"+endDay);

                                AppRoom room = new AppRoom(-1,id,name,type,price,startDay,endDay);
                                list.add(room);
                            }
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.flmBookedRoom, BookedRoomFragment.newInstance(list))
                                    .commit();
                        }
                    }
                });
    }


}