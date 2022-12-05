package com.example.duan1nhom7qlkhachsan.Activity.hotro;

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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_frangment_them,container,false);

        EditText edtNameUserSp=view.findViewById(R.id.edtNameUserSp);
        EditText edtSDTUserSp=view.findViewById(R.id.edtSDTUserSp);
        EditText edtContentSp=view.findViewById(R.id.edtContentSp);
        Button btnSendReport=view.findViewById(R.id.btnSendReport);

        scaleUp = AnimationUtils.loadAnimation(getActivity(),R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(getActivity(),R.anim.scale_down);

        btnSendReport.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP){
                    btnSendReport.startAnimation(scaleDown);

                }else if(event.getAction()==MotionEvent.ACTION_DOWN){
                    btnSendReport.startAnimation(scaleUp);
                }
                return false;
            }
        });



        btnSendReport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String ma=edtNameUserSp.getText().toString();
                String ten=edtSDTUserSp.getText().toString();
                String gioithieu=edtContentSp.getText().toString();
                if (ma.equals("")||ten.equals("")||gioithieu.equals("")){
                    Toast.makeText(getContext(), "Quý khách vui lòng điển đủ thông tin để được hỗ trợ", Toast.LENGTH_SHORT).show();
                }else{
                    HoTro nhaPhanPhoi=new HoTro(ma,ten,gioithieu);
                    HoTroDAO nhaPhanPhoiDAO=new HoTroDAO(getContext());
                    boolean check=nhaPhanPhoiDAO.themNPPtest(nhaPhanPhoi);
                    if (check==true){
                        Toast.makeText(getContext(), "Cảm ơn quý khách đã sử dụng dịch vụ", Toast.LENGTH_SHORT).show();
                        edtNameUserSp.setText("");
                        edtSDTUserSp.setText("");
                        edtContentSp.setText("");


                    }else{
                        Toast.makeText(getContext(), "Chúng tôi sẽ nhanh chóng hỗ trợ dịch vụ này", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return view;
    }
}
