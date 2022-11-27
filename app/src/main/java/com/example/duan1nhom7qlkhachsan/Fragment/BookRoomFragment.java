package com.example.duan1nhom7qlkhachsan.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1nhom7qlkhachsan.Adapter.BookRoomAdapter;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class BookRoomFragment extends Fragment {
    private ArrayList<AppRoom> rooms;
    private ListView lvFragmentDatPhong;

    public BookRoomFragment()
    {

    }
    // truyền data vào fragment

    public static BookRoomFragment newInstance(ArrayList<AppRoom> rooms) {
        BookRoomFragment fragment = new BookRoomFragment();
        Bundle args = new Bundle();
        args.putSerializable("room", rooms);
        fragment.setArguments(args);
        return fragment;
    }
    // đọc data
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rooms = (ArrayList<AppRoom>) getArguments().getSerializable("room");


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvFragmentDatPhong =view.findViewById(R.id.lvFragmentDatPhong);
        BookRoomAdapter adapter = new BookRoomAdapter(rooms);
        lvFragmentDatPhong.setAdapter(adapter);
//        lvDatPhong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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