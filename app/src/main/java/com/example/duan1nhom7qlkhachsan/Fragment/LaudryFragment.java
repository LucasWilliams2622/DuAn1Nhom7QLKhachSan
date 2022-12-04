package com.example.duan1nhom7qlkhachsan.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duan1nhom7qlkhachsan.Adapter.AddServiceAdapter;
import com.example.duan1nhom7qlkhachsan.Adapter.LaudryAdapter;
import com.example.duan1nhom7qlkhachsan.Model.AppService;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;


public class LaudryFragment extends Fragment {
    private ArrayList<AppService> service;
    private ListView lvFragLaudry;
    public LaudryFragment() {
        // Required empty public constructor
    }

    public static LaudryFragment newInstance(ArrayList<AppService> services) {
        LaudryFragment fragment = new LaudryFragment();
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

        View view = inflater.inflate(R.layout.fragment_laudry, container, false);

        return view;

    }

    //logic
    //load data vào view
    //xử lý sự kiện
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvFragLaudry=view.findViewById(R.id.lvFragLaudry);
        LaudryAdapter adapter = new LaudryAdapter(service);
        lvFragLaudry.setAdapter(adapter);
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