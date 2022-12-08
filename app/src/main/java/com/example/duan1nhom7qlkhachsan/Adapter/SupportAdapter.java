package com.example.duan1nhom7qlkhachsan.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.duan1nhom7qlkhachsan.Fragment.HandleSupportRequestFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppSupport;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SupportAdapter extends BaseAdapter {
    private ArrayList<AppSupport> list ;
    private Button btnHuySupport;
    private TextView tvNameSupport,tvPhoneSupport,tvContentSupport;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    HandleSupportRequestFragment handleSupportRequestFragment;

    public SupportAdapter(ArrayList<AppSupport> list, HandleSupportRequestFragment handleSupportRequestFragment) {

        this.list = list;
        this.handleSupportRequestFragment = handleSupportRequestFragment;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int _i, View _view, ViewGroup _viewGroup) {
        View view = _view;
        if (view == null)
        {
            view = View.inflate(_viewGroup.getContext(), R.layout.item_support_customer,null);
            btnHuySupport = view.findViewById(R.id.btnHuySupport);
            tvNameSupport = view.findViewById(R.id.tvNameSupport);
            tvPhoneSupport = view.findViewById(R.id.tvPhoneSupport);
            tvContentSupport = view.findViewById(R.id.tvContentSupport);

            ViewHolder holder = new ViewHolder(tvNameSupport,tvPhoneSupport,tvContentSupport,btnHuySupport);

            view.setTag(holder);
        }
        AppSupport support = (AppSupport) getItem(_i);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvNameSupport.setText(support.getNameCustomer());
        holder.tvPhoneSupport.setText( support.getPhoneNumberCustomer());
        holder.tvContentSupport.setText(support.getContentSupport());


        holder.btnHuySupport .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameCustomer = holder.tvNameSupport.getText().toString();
                String phoneNumCustomer = holder.tvPhoneSupport.getText().toString();
                String contentSupport = holder.tvContentSupport.getText().toString();


                // private String idRoom,nameRoom,typeRoom,priceRoom,startDay,endDay;
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("nameCustomer", nameCustomer);
                user.put("phoneNumCustomer", phoneNumCustomer);
                user.put("contentSupport", contentSupport);
                new AlertDialog.Builder(btnHuySupport.getContext())
                        .setTitle("Complete ?")
                        .setMessage("Bạn có chắc đã hỗ trợ khách ??")
                        .setNegativeButton("Chưa", null)
                        .setPositiveButton("Đã hỗ trợ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("supportCustomer").document(support.getSupportId())
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(btnHuySupport.getContext(), "Xác nhận hỗ trợ", Toast.LENGTH_SHORT).show();
                                                handleSupportRequestFragment.onAdapter();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(btnHuySupport.getContext(), "Hỗ trợ thất bại", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            }
                        })
                        .show();


            }
        });

        return view;
    }
    private static class ViewHolder{

        final TextView tvNameSupport,tvPhoneSupport,tvContentSupport;
        final Button btnHuySupport;

        public ViewHolder(TextView tvNameSupport, TextView tvPhoneSupport, TextView tvContentSupport, Button btnHuySupport) {
            this.tvNameSupport = tvNameSupport;
            this.tvPhoneSupport = tvPhoneSupport;
            this.tvContentSupport = tvContentSupport;
            this.btnHuySupport = btnHuySupport;
        }
    }
}
