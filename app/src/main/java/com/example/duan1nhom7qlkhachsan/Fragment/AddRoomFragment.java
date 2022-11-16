package com.example.duan1nhom7qlkhachsan.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AddRoomFragment extends Fragment {
    private EditText edtIdRoom,edtNameRoom,edtTypeRoom,edtPriceRoom,edtStartDay,edtEndDay;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_add_room, container, false);
        edtIdRoom = view.findViewById(R.id.edtIdRoom);
        edtNameRoom = view.findViewById(R.id.edtNameRoom);
        edtTypeRoom = view.findViewById(R.id.edtTypeRoom);
        edtPriceRoom = view.findViewById(R.id.edtPriceRoom);
        edtStartDay = view.findViewById(R.id.edtStartDay);
        edtEndDay = view.findViewById(R.id.edtEndDay);

        return view;
    }

    public void onAddRoomClick(View view )
    {
        // private String idRoom,nameRoom,typeRoom,priceRoom,startDay,endDay;
        String idRoom =edtIdRoom.getText().toString();
        String nameRoom =edtNameRoom.getText().toString();
        String typeRoom =edtTypeRoom.getText().toString();
        String priceRoom =edtPriceRoom.getText().toString();
        String startDay =edtStartDay.getText().toString();
        String endDay =edtEndDay.getText().toString();


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
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                    }
                });
    }
    public void onClearClick(View view)
    {
        edtIdRoom.setText(null);
        edtNameRoom.setText(null);
        edtTypeRoom.setText(null);
        edtPriceRoom.setText(null);
        edtStartDay.setText(null);
        edtEndDay.setText(null);
    }


}