package com.example.duan1nhom7qlkhachsan.Fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AddRoomandServiceFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_roomand_service, container, false);
        BottomNavigationView bottomNav = view.findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // as soon as the application opens the first
        // fragment should be shown to the user
        // in this case it is algorithm fragment
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddRoomFragment()).commit();


        return view;
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        // By using switch we can easily get
        // the selected fragment
        // by using there id.
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.bottom_nav_room) {
            selectedFragment = new AddRoomFragment();
        } else if (itemId == R.id.bottom_nav_service) {
            selectedFragment = new AddService();
        }
        // It will help to replace the
        // one fragment to other.
        if (selectedFragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        }
        return true;
    };
}