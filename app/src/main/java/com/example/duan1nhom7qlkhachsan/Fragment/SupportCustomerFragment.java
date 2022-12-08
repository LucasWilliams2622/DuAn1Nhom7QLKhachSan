package com.example.duan1nhom7qlkhachsan.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duan1nhom7qlkhachsan.Adapter.SupportAdapter;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppSupport;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class SupportCustomerFragment extends Fragment {

    private ArrayList<AppSupport> support;
    private ListView lvFrgSupport;

    public SupportCustomerFragment() {

    }
    public static SupportCustomerFragment newInstance(ArrayList<AppSupport> support) {
        SupportCustomerFragment fragment = new SupportCustomerFragment();
        Bundle args = new Bundle();
        args.putSerializable("supportCustomer", support);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            support = (ArrayList<AppSupport>) getArguments().getSerializable("supportCustomer");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support_customer, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvFrgSupport=view.findViewById(R.id.lvFrgSupport);
        SupportAdapter adapter = new SupportAdapter(support);
        lvFrgSupport.setAdapter(adapter);

    }
}