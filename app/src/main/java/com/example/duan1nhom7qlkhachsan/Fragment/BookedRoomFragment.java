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

import java.util.ArrayList;


public class BookedRoomFragment extends Fragment {
    private ListView lvFrgBookedRoom;

    private ArrayList<AppBookedRoom>  room ;

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
        BookedRoomAdapter bookedRoomAdapter = new BookedRoomAdapter(room);
        lvFrgBookedRoom.setAdapter(bookedRoomAdapter);
    }
}