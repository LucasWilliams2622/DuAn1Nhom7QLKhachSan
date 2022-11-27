package com.example.duan1nhom7qlkhachsan.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.duan1nhom7qlkhachsan.Adapter.AddRoomAdapter;
import com.example.duan1nhom7qlkhachsan.Adapter.AddServiceAdapter;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppService;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class AddServiceFragment extends Fragment {
    private RecyclerView reAddService;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_service2, container, false);
        reAddService = view.findViewById(R.id.reAddService);
        return view;
    }
    //logic
    //load data vào view
    //xử lý sự kiện

    @Override
    public void onResume() {
        super.onResume();
        getDataAddRoom();
    }
    public void getDataAddRoom() {
        db.collection("service")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<AppService> sv = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String idRoom = map.get("idRoom").toString();
                                String idService = map.get("idService").toString();
                                String nameService= map.get("nameService").toString();
                                String priceService = map.get("priceService").toString();
                                String timeUseService = map.get("timeUseService").toString();

                                AppService appService = new AppService(idRoom, idService, nameService, priceService, timeUseService);
                                appService.setIdRoom(appService.getIdRoom());
                                sv.add(appService);
                            }

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            reAddService.setLayoutManager(linearLayoutManager);
                            AddServiceAdapter adapter = new AddServiceAdapter(sv);
                            reAddService.setAdapter(adapter);
                        }
                    }
                });
    }

}