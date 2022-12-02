package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.duan1nhom7qlkhachsan.Fragment.AddServiceFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppService;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class SwinPoolActivity extends AppCompatActivity {
    private AppService appService = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swin_pool);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getDataService();

    }

    public void getDataService() {
        db.collection("service")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppService> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                //    private String idRoom, idService, nameService, priceService, typeService;
                                String idRoom = map.get("idRoom").toString();
                                String idService = map.get("idService").toString();
                                String nameService = map.get("nameService").toString();
                                String priceService = map.get("priceService").toString();
                                String typeService = map.get("typeService").toString();

                                AppService appService = new AppService(-1, idRoom, idService, nameService, priceService, typeService);
                                appService.setServiceId(document.getId());
                                list.add(appService);

                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.flSwimPool, AddServiceFragment.newInstance(list)).commit();
                            }
                        }
                    }
                });
    }
}