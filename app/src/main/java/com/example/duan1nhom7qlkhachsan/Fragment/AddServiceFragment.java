package com.example.duan1nhom7qlkhachsan.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AddServiceFragment extends Fragment {
    private EditText edtIdRoomUseService, edtIdService, edtNameService, edtTimeUseService, edtPriceService;
    private Button btnClear, btnAddService;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_service, container, false);
        edtIdRoomUseService = view.findViewById(R.id.edtIdRoomUseService);
        edtIdService = view.findViewById(R.id.edtIdService);
        edtNameService = view.findViewById(R.id.edtNameService);
        edtTimeUseService = view.findViewById(R.id.edtIdRoomUseService);
        edtPriceService = view.findViewById(R.id.edtPriceService);
        btnClear = view.findViewById(R.id.btnClear);
        btnAddService = view.findViewById(R.id.btnAddService);

        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddRoomClick(v);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClick(v);
            }
        });

        return view;
    }

    public void onAddRoomClick(View view) {

        //    private EditText edtIdRoomUseService,edtIdService,edtNameService,edtTimeUseService,edtPriceService;
        //    private String idRoom ,idService,nameService,priceService,timeUseService;
        String idRoom = edtIdRoomUseService.getText().toString();
        String idService = edtIdService.getText().toString();
        String nameService = edtNameService.getText().toString();
        String priceService = edtTimeUseService.getText().toString();
        String timeUseService = edtPriceService.getText().toString();


        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("idRoom", idRoom);
        user.put("idService", idService);
        user.put("nameService", nameService);
        user.put("priceService", priceService);
        user.put("timeUseService", timeUseService);


// Add a new document with a generated ID
        db.collection("service")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "Thêm dich vụ thành công", Toast.LENGTH_SHORT).show();
                        //Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Thêm dich vụ thất bại", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void onClearClick(View view) {
        //    private EditText edtIdRoomUseService,edtIdService,edtNameService,edtTimeUseService,edtPriceService;
        edtIdRoomUseService.setText(null);
        edtIdService.setText(null);
        edtNameService.setText(null);
        edtTimeUseService.setText(null);
        edtPriceService.setText(null);

    }
}