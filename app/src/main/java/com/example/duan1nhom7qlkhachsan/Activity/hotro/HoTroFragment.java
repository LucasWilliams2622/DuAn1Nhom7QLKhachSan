package com.example.duan1nhom7qlkhachsan.Activity.hotro;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1nhom7qlkhachsan.R;


public class HoTroFragment extends Fragment {
    Animation scaleUp,scaleDown;
    private Context HoTroFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_frangment_them,container,false);

        EditText edtmanpp=view.findViewById(R.id.edtmanpp);
        EditText edttennpp=view.findViewById(R.id.edttennpp);
        EditText edtgioithiue=view.findViewById(R.id.edtgioithieu);
        Button btnthem=view.findViewById(R.id.btnthem);

        scaleUp = AnimationUtils.loadAnimation(getActivity(),R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(getActivity(),R.anim.scale_down);

        btnthem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP){
                    btnthem.startAnimation(scaleDown);

                }else if(event.getAction()==MotionEvent.ACTION_DOWN){
                    btnthem.startAnimation(scaleUp);
                }
                return false;
            }
        });



        btnthem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String ma=edtmanpp.getText().toString();
                String ten=edttennpp.getText().toString();
                String gioithieu=edtgioithiue.getText().toString();
                if (ma.equals("")||ten.equals("")||gioithieu.equals("")){
                    Toast.makeText(getContext(), "Ko dc de trong!", Toast.LENGTH_SHORT).show();
                }else{
                    HoTro nhaPhanPhoi=new HoTro(ma,ten,gioithieu);
                    HoTroDAO nhaPhanPhoiDAO=new HoTroDAO(getContext());
                    boolean check=nhaPhanPhoiDAO.themNPPtest(nhaPhanPhoi);
                    if (check==true){
                        Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                        edtmanpp.setText("");
                        edttennpp.setText("");
                        edtgioithiue.setText("");

                    }else{
                        Toast.makeText(getContext(), "Da ton tai nha phan phoi nay!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



        return view;
    }
}
