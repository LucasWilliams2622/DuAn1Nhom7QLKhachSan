package com.example.duan1nhom7qlkhachsan.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.duan1nhom7qlkhachsan.Fragment.BookRoomFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class RoomActivity extends AppCompatActivity {
    private ListView flRoom;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        getDataRoom();

    }
    @Override
    protected void onResume() {
        super.onResume();
        getDataRoom();
    }

    public void getDataRoom(){
        db.collection("room")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppRoom> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String,Object> map = document.getData();
                                String codeRoom = map.get("codeRoom").toString();
                                String nameRoom = map.get("nameRoom").toString();
                                String typeRoom = map.get("typeRoom").toString();
                                String priceRoom = map.get("priceRoom").toString();
                                String startDay = map.get("startDay").toString();
                                String endDay = map.get("endDay").toString();

                                AppRoom room = new AppRoom(-1,codeRoom,nameRoom,typeRoom,priceRoom,startDay,endDay);
                                room.setCodeRoom(document.getId());
                                list.add(room);
                            }
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.flRoom, BookRoomFragment.newInstance(list))
                                    .commit();
                        }
                    }
                });
    }

}