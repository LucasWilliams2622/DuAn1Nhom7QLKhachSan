package com.example.duan1nhom7qlkhachsan.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.duan1nhom7qlkhachsan.R;

public class RoomActivity extends AppCompatActivity {
    private EditText  edtStartDay, edtEndDay;
    private TextView edtIdRoom, edtNameRoom, edtTypeRoom, edtPriceRoom;
private ImageView ivRoom;
   // FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation_scale);
        ivRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(animation);
            }
        });
//        edtIdRoom = findViewById(R.id.edtIdRoom);
//        edtNameRoom = findViewById(R.id.edtNameRoom);
//        edtTypeRoom = findViewById(R.id.edtTypeRoom);
//        edtPriceRoom = findViewById(R.id.edtPriceRoom);
//        edtStartDay = findViewById(R.id.edtStartDay);
//        edtEndDay = findViewById(R.id.edtEndDay);

    }
    @Override
    protected void onResume() {
        super.onResume();
       // getData();
    }
    /*
    public void getData(){
        db.collection("room")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppRoom> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String,Object> map = document.getData();
                                String idRoom = map.get("idRoom").toString();
                                String nameRoom = map.get("nameRoom").toString();
                                String typeRoom = map.get("typeRoom").toString();
                                String priceRoom = map.get("priceRoom").toString();
                                String startDay = map.get("startDay").toString();
                                String endDay = map.get("endDay").toString();

                                AppRoom room = new AppRoom(idRoom,nameRoom,typeRoom,priceRoom,startDay,endDay);
                                room.setIdRoom(document.getId());
                                list.add(room);
                            }
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.flRoom, DatPhongFragment.newInstance(list))
                                    .commit();
                        }
                    }
                });
    }
    public void onSaveClick(View view) {
        String idRoom = edtIdRoom.getText().toString();
        String nameRoom = edtNameRoom.getText().toString();
        String typeRoom = edtTypeRoom.getText().toString();
        String priceRoom = edtPriceRoom.getText().toString();
        String startDay = edtStartDay.getText().toString();
        String endDay = edtEndDay.getText().toString();

// Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("idRoom", idRoom);
        user.put("nameRoom", nameRoom);
        user.put("typeRoom", typeRoom);
        user.put("priceRoom", priceRoom);
        user.put("startDay", startDay);
        user.put("endDay", endDay);


// Add a new document with a generated ID
        db.collection("room")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RoomActivity.this, "Inserted success", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RoomActivity.this, "Faied", Toast.LENGTH_SHORT).show();
                    }
                });
    }
//    @Override
//    public void onCancelClick(View view course) {
//        edtcode.setText(course.getCode());
//        edtname.setText(course.getName());
//        edttime.setText(course.getTime());
//        edtroom.setText(course.getRoom());
//        appCourse=course;
*/
}