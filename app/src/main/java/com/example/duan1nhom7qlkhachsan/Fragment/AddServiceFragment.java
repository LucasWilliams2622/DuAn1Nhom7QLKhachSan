package com.example.duan1nhom7qlkhachsan.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duan1nhom7qlkhachsan.Adapter.AddRoomAdapter;
import com.example.duan1nhom7qlkhachsan.Adapter.AddServiceAdapter;
import com.example.duan1nhom7qlkhachsan.Model.AppService;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class AddServiceFragment extends Fragment {
    private ArrayList<AppService> service;
    private ListView lvFragAddService;

    public AddServiceFragment() {
        // Required empty public constructor
    }
    public static AddServiceFragment newInstance(ArrayList<AppService> services) {
        AddServiceFragment fragment = new AddServiceFragment();
        Bundle args = new Bundle();
        args.putSerializable("service", services);

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            service = (ArrayList<AppService>) getArguments().getSerializable("service");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_service, container, false);

        return view;
    }
    //logic
    //load data vào view
    //xử lý sự kiện
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvFragAddService=view.findViewById(R.id.lvFragAddService);
        AddServiceAdapter adapter = new AddServiceAdapter(service);
        lvFragAddService.setAdapter(adapter);
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