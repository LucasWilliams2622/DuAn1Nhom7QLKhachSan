package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duan1nhom7qlkhachsan.Fragment.BookedRoomFragment;
import com.example.duan1nhom7qlkhachsan.Fragment.HandleSupportRequestFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppBookedRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppSupport;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class HandleSupportRequestActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button btnBackToMainActivity;
    private AppSupport appSupport = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_support_request);
        btnBackToMainActivity = findViewById(R.id.btnBackToMainActivity);
        btnBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getDataListSupportRequest();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getDataListSupportRequest();
    }
    private void getDataListSupportRequest() {
        db.collection("supportCustomer")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppSupport> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String nameCustomer = map.get("nameCustomer").toString();
                                String phoneNumCustomer = map.get("phoneNumCustomer").toString();
                                String contentSupport = map.get("contentSupport").toString();


                                AppSupport support = new AppSupport(-1,nameCustomer,phoneNumCustomer,contentSupport);
                                support.setSupportId(document.getId());
                                list.add(support);

                            }
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.flListSupport, HandleSupportRequestFragment.newInstance(list))
                                    .commit();
                        }
                    }
                });
    }
}