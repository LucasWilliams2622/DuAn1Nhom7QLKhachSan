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
import com.example.duan1nhom7qlkhachsan.Adapter.SupportAdapter;
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

public class HandleSupportRequestFragment extends Fragment {

    private ListView lvFrgHandleSPRequest;
    SupportAdapter supportAdapter;
    private ArrayList<AppSupport> supports;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public HandleSupportRequestFragment() {
        // Required empty public constructor
    }

    public static HandleSupportRequestFragment newInstance(ArrayList<AppSupport> supports) {
        HandleSupportRequestFragment fragment = new HandleSupportRequestFragment();
        Bundle args = new Bundle();
        args.putSerializable("bookedRoom", supports);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            supports = (ArrayList<AppSupport>) getArguments().getSerializable("supportCustomer");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_handle_support_request, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvFrgHandleSPRequest = view.findViewById(R.id.lvFrgHandleSPRequest);
        onAdapter();
    }

    public void onAdapter() {
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
                                AppSupport room = new AppSupport(-1, nameCustomer, phoneNumCustomer, contentSupport);
                                room.setSupportId(document.getId());
                                list.add(room);

                            }
                            supports = list;
                            supportAdapter = new SupportAdapter(supports, HandleSupportRequestFragment.this);
                            lvFrgHandleSPRequest.setAdapter(supportAdapter);

                        }
                    }
                });

    }
}