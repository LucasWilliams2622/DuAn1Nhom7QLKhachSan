package com.example.duan1nhom7qlkhachsan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


import com.example.duan1nhom7qlkhachsan.Activity.ClockActivity;
import com.example.duan1nhom7qlkhachsan.Activity.LaudryActivity;
import com.example.duan1nhom7qlkhachsan.Activity.SwinPoolActivity;
import com.example.duan1nhom7qlkhachsan.R;


public class ServiceFragment extends Fragment {
    ImageView ivWashingMachine,ivClock,ivSwimPool;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        ivSwimPool = view.findViewById(R.id.ivSwimPool);
        ivWashingMachine = view.findViewById(R.id.ivWashingMachine);
        ivClock = view.findViewById(R.id.ivClock);

        ivWashingMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LaudryActivity.class);
                startActivity(i);
            }
        });
        ivClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ClockActivity.class);
                startActivity(i);

            }
        });
        ivSwimPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SwinPoolActivity.class);
                startActivity(i);

            }
        });


        return view;
    }
}