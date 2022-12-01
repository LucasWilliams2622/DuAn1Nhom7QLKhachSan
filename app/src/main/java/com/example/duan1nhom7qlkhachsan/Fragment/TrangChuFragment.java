package com.example.duan1nhom7qlkhachsan.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


import com.example.duan1nhom7qlkhachsan.Activity.BookRoomActivity;
import com.example.duan1nhom7qlkhachsan.Activity.EditProfileActivity;
import com.example.duan1nhom7qlkhachsan.Activity.hotro.HoTroFragment;
import com.example.duan1nhom7qlkhachsan.R;


public class TrangChuFragment extends Fragment {

ImageView ivSuport,ivService,ivBookRoom,ivProfile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);

        ivProfile = view.findViewById(R.id.ivProfile);
        ivSuport = view.findViewById(R.id.ivSuport);
        ivService = view.findViewById(R.id.ivService);
        ivBookRoom = view.findViewById(R.id.ivBookRoom);

        ivBookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), BookRoomActivity.class);
                startActivity(i);
            }
        });
        ivService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ServiceFragment.class);
                startActivity(i);
            }
        });
        ivSuport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), HoTroFragment.class);
                startActivity(i);
            }
        });
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EditProfileActivity.class);
                startActivity(i);
            }
        });
        return view;

    }
}