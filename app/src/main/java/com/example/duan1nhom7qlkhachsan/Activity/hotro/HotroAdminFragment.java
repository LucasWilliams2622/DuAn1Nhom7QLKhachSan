package com.example.duan1nhom7qlkhachsan.Activity.hotro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class HotroAdminFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_quanly,container,false);
        RecyclerView recyclerviewnpp=view.findViewById(R.id.recyclerviewnpp);

        HoTroDAO nhaPhanPhoiDAO=new HoTroDAO(getContext());
        ArrayList<HoTro>list=nhaPhanPhoiDAO.getlistDSNPP();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerviewnpp.setLayoutManager(linearLayoutManager);
        HoTroAdapter adapter=new HoTroAdapter(getContext(),list);
        recyclerviewnpp.setAdapter(adapter);


        return view;
    }
}
