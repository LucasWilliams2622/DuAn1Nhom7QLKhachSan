package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.duan1nhom7qlkhachsan.Fragment.AddServiceFragment;
import com.example.duan1nhom7qlkhachsan.Fragment.LaudryFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppService;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.Value;

import java.util.ArrayList;
import java.util.Map;

public class LaudryActivity extends AppCompatActivity {
    private AppService appService = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button btnBack, btnSearchService, btnHide;
    private AutoCompleteTextView tvSearch;
    private LinearLayout containerLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laudry);
        btnBack = findViewById(R.id.btnBack);
        tvSearch = findViewById(R.id.tvSearch);
        containerLL = (LinearLayout) findViewById(R.id.containerLL);
        btnSearchService = findViewById(R.id.btnSearchService);
        btnHide = findViewById(R.id.btnHide);
        tvSearch = findViewById(R.id.tvSearch);
        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerLL.setVisibility(View.INVISIBLE);
                containerLL.setLayoutParams(new LinearLayoutCompat.LayoutParams(0, 0));

            }
        });
        btnSearchService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerLL.setVisibility(View.VISIBLE);
                Log.d(">>>>>", "sadsad");
                containerLL.setPadding(50, 0, 10, 10);
                containerLL.setLayoutParams(new LinearLayoutCompat.LayoutParams(1060, 120));
//                tvSearch.setLayoutParams(new LinearLayoutCompat.LayoutParams(860, 100));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



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
                                        .replace(R.id.flLaudry, LaudryFragment.newInstance(list)).commit();
                            }
                        }
                    }
                });
    }
}