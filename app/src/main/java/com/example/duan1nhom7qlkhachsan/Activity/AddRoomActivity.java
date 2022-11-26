package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.Fragment.AddRoomFragment;
import com.example.duan1nhom7qlkhachsan.MainActivity;
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

public class AddRoomActivity extends AppCompatActivity implements IAdapterClickEvent {
    private EditText edtIdRoom, edtNameRoom, edtTypeRoom, edtPriceRoom, edtStartDay, edtEndDay;
    private Button btnAddRoom, btnClear,btnBackToMain;
    private AppRoom appRoom = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        edtIdRoom = findViewById(R.id.edtIdRoom);
        edtNameRoom = findViewById(R.id.edtNameRoom);
        edtTypeRoom = findViewById(R.id.edtTypeRoom);
        edtPriceRoom = findViewById(R.id.edtPriceRoom);
        edtStartDay = findViewById(R.id.edtStartDay);
        edtEndDay = findViewById(R.id.edtEndDay);

        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddRoomActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataAddRoom();
    }

    public void onAddRoomClick(View view) {
        // private String idRoom,nameRoom,typeRoom,priceRoom,startDay,endDay;
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
        if (appRoom == null) {
            db.collection("room")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddRoomActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            getDataAddRoom();
                            //Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddRoomActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            db.collection("room")
                    .document(appRoom.getIdRoom())
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AddRoomActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            getDataAddRoom();
                            appRoom=null;
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddRoomActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void onCancleClick(View view) {
        edtIdRoom.setText(null);
        edtNameRoom.setText(null);
        edtTypeRoom.setText(null);
        edtPriceRoom.setText(null);
        edtStartDay.setText(null);
        edtEndDay.setText(null);

    }

    public void getDataAddRoom() {
        db.collection("room")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppRoom> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String id = map.get("idRoom").toString();
                                String name = map.get("nameRoom").toString();
                                String type = map.get("typeRoom").toString();
                                String price = map.get("priceRoom").toString();
                                String start = map.get("startDay").toString();
                                String end = map.get("endDay").toString();

                                AppRoom appRoom = new AppRoom(id, name, type, price, start, end);
                                appRoom.setIdRoom(appRoom.getIdRoom());
                                list.add(appRoom);

                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.flAddRoom, AddRoomFragment.newInstance(list)).commit();
                            }
                        }
                    }
                });
    }
//
    @Override
    public void onDeleteRoomClick(AppRoom room) {
        new AlertDialog.Builder(AddRoomActivity.this)
                .setTitle("Xóa")
                .setMessage("Xóa sẽ không phục hồi được")
                .setNegativeButton("Hủy", null)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.collection("room").document(room.getIdRoom())
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(AddRoomActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                        getDataAddRoom();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(AddRoomActivity.this, "Xóa khong thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                })
                .show();
    }


    @Override
    public void onUpdateRoomClick(AppRoom room) {
        edtIdRoom.setText(room.getIdRoom());
        edtNameRoom.setText(room.getNameRoom());
        edtTypeRoom.setText(room.getTypeRoom());
        edtPriceRoom.setText(room.getPriceRoom());
        edtStartDay.setText(room.getStartDay());
        edtEndDay.setText(room.getEndDay());
        appRoom = room;
    }
}