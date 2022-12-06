package com.example.duan1nhom7qlkhachsan.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duan1nhom7qlkhachsan.Adapter.BookedRoomAdapter;
import com.example.duan1nhom7qlkhachsan.Adapter.BookedServiceAdapter;
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


public class BookedServiceFragment extends Fragment {
    private ListView lvFrgBookedService;
    BookedServiceAdapter bookedServiceAdapter;
    private ArrayList<AppBookedService> service ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public BookedServiceFragment() {
        // Required empty public constructor
    }
    public static BookedServiceFragment newInstance(ArrayList<AppBookedService> service) {
        BookedServiceFragment fragment = new BookedServiceFragment();
        Bundle args = new Bundle();
        args.putSerializable("bookedService", service);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            service = (ArrayList<AppBookedService>) getArguments().getSerializable("bookedService");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booked_service,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvFrgBookedService = view.findViewById(R.id.lvFrgBookedService);
        onAdapter();
    }
    public void onAdapter()
    {
        db.collection("bookedService")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppBookedService> list= new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map= document.getData();
                                String idRoom=map.get("idRoom").toString();
                                String idService=map.get("idService").toString();
                                String nameService=map.get("nameService").toString();
                                String priceService=map.get("priceService").toString();
                                String typeService=map.get("typeService").toString();


                                AppBookedService service = new AppBookedService(-1,idRoom,idService,nameService,priceService,typeService);
                                service.setServiceId(document.getId());
                                list.add(service);

                            }
                            service = list;
                            bookedServiceAdapter = new BookedServiceAdapter(service,BookedServiceFragment.this);
                            lvFrgBookedService.setAdapter(bookedServiceAdapter);

                        }
                    }
                });

    }

}