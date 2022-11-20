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
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan1nhom7qlkhachsan.Adapter.AddRoomAdapter;
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
    private ArrayList<AppRoom> rooms;
    private ListView lvFragAddRoom;

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

        return view;
    }
    //logic
    //load data vào view
    //xử lý sự kiện
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvFragAddRoom=view.findViewById(R.id.lvFragAddRoom);
        AddRoomAdapter adapter = new AddRoomAdapter(rooms);
        lvFragAddRoom.setAdapter(adapter);
//        lvFragCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                AppCourse course = (AppCourse) parent.getItemAtPosition(position);
//                // truyền ra activity
//                CourseActivity activity= (CourseActivity) view.getContext();
//                activity.onCoursesItemClick(course);
//            }
//        });
    }

}