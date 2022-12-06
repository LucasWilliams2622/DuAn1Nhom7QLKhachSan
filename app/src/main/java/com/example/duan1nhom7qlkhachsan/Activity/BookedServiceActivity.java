package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duan1nhom7qlkhachsan.Fragment.BookedRoomFragment;
import com.example.duan1nhom7qlkhachsan.Fragment.BookedServiceFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppBookedRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppBookedService;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class BookedServiceActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button btnBackToMainActivity;
    private AppBookedService appBookedService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_service);
        btnBackToMainActivity = findViewById(R.id.btnBackToMainActivity);
        btnBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getDataBookedService();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getDataBookedService();
    }
    private void getDataBookedService() {
        db.collection("bookedService")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppBookedService> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String idRoom = map.get("idRoom").toString();
                                String idService = map.get("idService").toString();
                                String nameService = map.get("nameService").toString();
                                String typeService = map.get("priceService").toString();
                                String priceService = map.get("typeService").toString();


                                AppBookedService room = new AppBookedService(-1, idRoom, idService, nameService, typeService, priceService);
                                room.setServiceId(document.getId());
                                list.add(room);

                            }
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.flBookedService, BookedServiceFragment.newInstance(list))
                                    .commit();
                        }
                    }
                });
    }

}