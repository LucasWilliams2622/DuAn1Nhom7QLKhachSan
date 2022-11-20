package com.example.duan1nhom7qlkhachsan.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class DatPhongFragment extends Fragment {
    private ArrayList<AppRoom> courses;
    private ListView lvDatPhong;

    public DatPhongFragment()
    {

    }
    // truyền data vào fragment

    public static DatPhongFragment newInstance(ArrayList<AppRoom> room) {
        DatPhongFragment fragment = new DatPhongFragment();
        Bundle args = new Bundle();
        args.putSerializable("room", room);
        fragment.setArguments(args);
        return fragment;
    }
    // đọc data
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courses = (ArrayList<AppRoom>) getArguments().getSerializable("room");

        }
    }
    // load layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dat_phong, container, false);
        return view;
    }
    //logic
    //load data vào view
    //xử lý sự kiện
    /*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvDatPhong =view.findViewById(R.id.lv);
        CourseAdapter adapter = new CourseAdapter(courses);
        lvDatPhong.setAdapter(adapter);
        lvDatPhong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppCourse course = (AppCourse) parent.getItemAtPosition(position);
                // truyền ra activity
                CourseActivity activity= (CourseActivity) view.getContext();
                activity.onCoursesItemClick(course);
            }
        });
    }*/
}