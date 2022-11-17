package com.example.duan1nhom7qlkhachsan.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddRoomFragment extends Fragment {
    private EditText edtIdRoom, edtNameRoom, edtTypeRoom, edtPriceRoom, edtStartDay, edtEndDay;
    private Button btnAddRoom, btnClear;
    private ArrayList<AppRoom> rooms;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public AddRoomFragment() {
        // Required empty public constructor
    }
    public static AddRoomFragment newInstance(ArrayList<AppRoom> rooms) {


        AddRoomFragment fragment = new AddRoomFragment();
        Bundle args = new Bundle();
        args.putSerializable("room", rooms);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rooms = (ArrayList<AppRoom>) getArguments().getSerializable("room");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_room, container, false);
        edtIdRoom = view.findViewById(R.id.edtIdRoom);
        edtNameRoom = view.findViewById(R.id.edtNameRoom);
        edtTypeRoom = view.findViewById(R.id.edtTypeRoom);
        edtPriceRoom = view.findViewById(R.id.edtPriceRoom);
        edtStartDay = view.findViewById(R.id.edtStartDay);
        edtEndDay = view.findViewById(R.id.edtEndDay);
        btnAddRoom = view.findViewById(R.id.btnAddRoom);
        btnClear = view.findViewById(R.id.btnClear);

        btnAddRoom.setOnClickListener(new View.OnClickListener() {
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
        db.collection("room")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        //Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        getData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    public void onClearClick(View view) {
        edtIdRoom.setText(null);
        edtNameRoom.setText(null);
        edtTypeRoom.setText(null);
        edtPriceRoom.setText(null);
        edtStartDay.setText(null);
        edtEndDay.setText(null);
    }
    private  void getData()
    {
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
                                list.add(appRoom);

                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.flAddRoom, AddRoomFragment.newInstance(list)).commit();
                            }
                        }
                    }
                });
    }

    private void getDataService() {
        db.collection("service")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull
                                                   Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        } else {
                                Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}