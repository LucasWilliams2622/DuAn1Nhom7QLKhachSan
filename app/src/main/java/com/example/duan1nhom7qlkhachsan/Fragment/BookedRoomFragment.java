package com.example.duan1nhom7qlkhachsan.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1nhom7qlkhachsan.Adapter.BookedRoomAdapter;
import com.example.duan1nhom7qlkhachsan.Model.AppBookedRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;


public class BookedRoomFragment extends Fragment {
    private ListView lvFrgBookedRoom;
    BookedRoomAdapter bookedRoomAdapter;
    private ArrayList<AppBookedRoom>  room ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public BookedRoomFragment() {
        // Required empty public constructor
    }
    public static BookedRoomFragment newInstance(ArrayList<AppBookedRoom> room) {
        BookedRoomFragment fragment = new BookedRoomFragment();
        Bundle args = new Bundle();
        args.putSerializable("bookedRoom", room);

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            room = (ArrayList<AppBookedRoom>) getArguments().getSerializable("bookedRoom");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phong_da_dat,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvFrgBookedRoom = view.findViewById(R.id.lvFrgBookedRoom);
        onAdapter();
    }
    public void onAdapter()
    {
        db.collection("bookedRoom")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppBookedRoom> list= new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map= document.getData();
                                String code=map.get("codeRoom").toString();
                                String name=map.get("nameRoom").toString();
                                String type=map.get("typeRoom").toString();
                                String price=map.get("priceRoom").toString();
                                String startDay=map.get("startDay").toString();
                                String endDay=map.get("endDay").toString();

                                AppBookedRoom room = new AppBookedRoom(-1,code,name,type,price,startDay,endDay);
                                room.setRoomId(document.getId());
                                list.add(room);

                            }
                            room = list;
                            bookedRoomAdapter = new BookedRoomAdapter(room,BookedRoomFragment.this,getContext());
                            lvFrgBookedRoom.setAdapter(bookedRoomAdapter);

                        }
                    }
                });

    }
}