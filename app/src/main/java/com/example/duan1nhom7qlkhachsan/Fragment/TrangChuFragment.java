package com.example.duan1nhom7qlkhachsan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.duan1nhom7qlkhachsan.Activity.BookRoomActivity;
import com.example.duan1nhom7qlkhachsan.Activity.EditProfileActivity;
import com.example.duan1nhom7qlkhachsan.Activity.LaudryActivity;
import com.example.duan1nhom7qlkhachsan.Activity.hotro.HoTroFragment;
import com.example.duan1nhom7qlkhachsan.MainActivity;
import com.example.duan1nhom7qlkhachsan.R;
import com.example.duan1nhom7qlkhachsan.SlideShow.Photo;
import com.example.duan1nhom7qlkhachsan.SlideShow.PhotoViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class TrangChuFragment extends Fragment {

    ImageView ivSuport, ivService, ivBookRoom, ivProfile;
    private ViewPager mViewPager;

    private CircleIndicator mCircleIndicator;
private List<Photo> mListPhoto;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);

        ivProfile = view.findViewById(R.id.ivProfile);
        ivSuport = view.findViewById(R.id.ivSuport);
        ivService = view.findViewById(R.id.ivService);
        ivBookRoom = view.findViewById(R.id.ivBookRoom);

        mViewPager = view.findViewById(R.id.viewPager);
        mCircleIndicator = view.findViewById(R.id.circleIndicator);
        mListPhoto = getListPhoto();
        PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter(mListPhoto);
        mViewPager.setAdapter(adapter);

        mCircleIndicator.setViewPager(mViewPager);

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
                Intent i = new Intent(getContext(), LaudryActivity.class);
                startActivity(i);
            }
        });
        ivSuport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).openSupportFragment();
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

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.hotel_room_slide));
        list.add(new Photo(R.drawable.hotel_room_slide2));
        list.add(new Photo(R.drawable.hotel_service_slide));
        list.add(new Photo(R.drawable.hotel_food_slide));
        return list;
    }
}