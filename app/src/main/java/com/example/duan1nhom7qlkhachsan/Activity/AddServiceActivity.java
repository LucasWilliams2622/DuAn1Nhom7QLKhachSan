package com.example.duan1nhom7qlkhachsan.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.Fragment.AddRoomFragment;
import com.example.duan1nhom7qlkhachsan.Fragment.AddServiceFragment;
import com.example.duan1nhom7qlkhachsan.MainActivity;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppService;
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

public class AddServiceActivity extends AppCompatActivity implements IAdapterAddServiceClickEvent {
    private EditText edtIdRoom, edtIdService, edtNameService, edtPriceService;
    private Button btnAddService, btnClear, btnBackToMain;
    private AppService appService = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Spinner spnTypeService;
    ArrayList<String> arrayTypeService;
    String typeService;
    SharedPreferences sharedPrefeTypeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        edtIdRoom = findViewById(R.id.edtIdRoom);
        edtIdService = findViewById(R.id.edtIdService);
        edtNameService = findViewById(R.id.edtNameService);
        edtPriceService = findViewById(R.id.edtPriceService);
        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        //Spinner
        sharedPrefeTypeService = getSharedPreferences("TypeService", 0);

        spnTypeService = findViewById(R.id.spnTypeService);
        arrayTypeService = new ArrayList<String>();
        arrayTypeService.add("Dịch vụ giặt ủi");
        arrayTypeService.add("Dịch vụ báo thức");
        arrayTypeService.add("Dịch vụ ngoài trời");
        ArrayAdapter arrayAdapterTypeService = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayTypeService);
        arrayAdapterTypeService.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnTypeService.setAdapter(arrayAdapterTypeService);

        spnTypeService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeService = arrayTypeService.get(position);
                SharedPreferences.Editor editor = sharedPrefeTypeService.edit();
                editor.putString("typeService", typeService);
                editor.apply();
                Log.d(">>>>>>>>>>>>>>>>>>", "typeService" + typeService);
                // Toast.makeText(AddServiceActivity.this, "typeService" + typeService, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //End Spinner
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataAddService();

    }

    public void getDataAddService() {
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
                                        .replace(R.id.flAddService, AddServiceFragment.newInstance(list)).commit();
                            }
                        }
                    }
                });
    }

    public void onAddServiceClick(View view) {
        //private EditText edtIdRoom, edtIdService, edtNameService, edtPriceService;
        sharedPrefeTypeService = getSharedPreferences("TypeService", 0);

        String idRoom = edtIdRoom.getText().toString();
        String idService = edtIdService.getText().toString();
        String nameService = edtNameService.getText().toString();
        String priceService = edtPriceService.getText().toString();
        String typeService = sharedPrefeTypeService.getString("typeService", "");
        if (idRoom.equals("") || idService.equals("") || nameService.equals("") || priceService.equals("")) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            // Create a new user with a first and last name
            Map<String, Object> service = new HashMap<>();
            service.put("idRoom", idRoom);
            service.put("idService", idService);



            service.put("nameService", nameService);
            service.put("priceService", priceService);
            service.put("typeService", typeService);


            Log.d(">>>>>>>>>>>>>>>>>>", "typeService" + typeService);


// Add a new document with a generated ID
            if (appService == null) {
                db.collection("service")
                        .add(service)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                getDataAddService();
                                //Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                            }
                        });
            } else {
                db.collection("service")
                        .document(appService.getServiceId())
                        .set(service)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                getDataAddService();
                                appService = null;
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }


    }

    public void onCancleClick(View view) {
        //private EditText edtIdRoom, edtIdService, edtNameService, edtPriceService;

        edtIdRoom.setText(null);
        edtIdService.setText(null);
        edtNameService.setText(null);
        edtPriceService.setText(null);


    }

    @Override
    public void onDeleteServiceClick(AppService service) {
        new AlertDialog.Builder(AddServiceActivity.this)
                .setTitle("Xóa")
                .setMessage("Xóa sẽ không phục hồi được")
                .setNegativeButton("Hủy", null)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.collection("service").document(service.getServiceId())
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(AddServiceActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                        getDataAddService();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(AddServiceActivity.this, "Xóa khong thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                })
                .show();
    }

    @Override
    public void onUpdateServiceClick(AppService service) {
        //private EditText edtIdRoom, edtIdService, edtNameService, edtPriceService;
        edtIdRoom.setText(service.getCodeRoom());
        edtIdService.setText(service.getCodeService());
        edtNameService.setText(service.getNameService());
        edtPriceService.setText(service.getPriceService());
        appService = service;
    }
}