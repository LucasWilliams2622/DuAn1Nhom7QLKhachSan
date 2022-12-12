package com.example.duan1nhom7qlkhachsan.Adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.duan1nhom7qlkhachsan.Model.AppService;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaudryAdapter extends BaseAdapter {
    private ArrayList<AppService> list;
    private EditText edtIdRoomBookService;
    private Button btnBookService;
    private TextView tvIdService, tvNameService, tvPriceService, tvTypeService;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public int getCount() {
        return list.size();
    }

    public LaudryAdapter(ArrayList<AppService> list) {
        this.list = list;
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

        if (view == null) {
            view = View.inflate(_viewGroup.getContext(), R.layout.item_laudry, null);

            edtIdRoomBookService = view.findViewById(R.id.edtIdRoomBookService);
            tvIdService = view.findViewById(R.id.tvIdService);
            tvNameService = view.findViewById(R.id.tvNameService);
            tvTypeService = view.findViewById(R.id.tvPriceService);
            tvPriceService = view.findViewById(R.id.tvTypeService);
            btnBookService = view.findViewById(R.id.btnBookService);

            ViewHolder holder = new ViewHolder(btnBookService, tvIdService, tvNameService, tvTypeService, tvPriceService, edtIdRoomBookService);
            view.setTag(holder);
        }
        AppService service = (AppService) getItem(_i);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvIdRoom.setText(service.getCodeRoom());
        holder.tvIdService.setText(service.getCodeService());
        holder.tvNameService.setText(service.getNameService());
        holder.tvTypeService.setText(" "+service.getTypeService());
        holder.tvPriceService.setText(" "+service.getPriceService());


        holder.btnBookService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String idRoomBookService = edtIdRoomBookService.getText().toString().trim();
//                if ((idRoomBookService.length() == 0 || idRoomBookService.length() < 3)) {
//                    edtIdRoomBookService.setError("Bạn chưa chọn ngày check in");
//                    edtIdRoomBookService.requestFocus();
//                    return;
//                }
                String idRoom = holder.tvIdRoom.getText().toString();
                String idService = holder.tvIdService.getText().toString();
                String nameService = holder.tvNameService.getText().toString();
                String typeService = holder.tvTypeService.getText().toString();
                String priceService = holder.tvPriceService.getText().toString();

                // private String idRoom,nameRoom,typeRoom,priceRoom,startDay,endDay;
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("idRoom", idRoom);
                user.put("idService", idService);
                user.put("nameService", nameService);
                user.put("priceService", priceService);
                user.put("typeService", typeService);


                db.collection("bookedService")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                //Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Log.d(">>>>>>>>>>>>", "Booked Service successful");
                                holder.btnBookService.setText("Đã đặt thành công");
                                holder.btnBookService.setVisibility(View.INVISIBLE);
                                Toast.makeText(holder.btnBookService.getContext(), "Đặt dịch vụ thành công", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(">>>>>>>>>>>>", "Booked Service failed");
                                Toast.makeText(holder.btnBookService.getContext(), "Đặt dịch vụ không thành công", Toast.LENGTH_SHORT).show();
                            }
                        });

            }


        });


        return view;
    }

    private static class ViewHolder {
        final Button btnBookService;
        final TextView tvIdService, tvNameService, tvPriceService, tvTypeService;
        final EditText tvIdRoom;

        public ViewHolder(Button btnBookService, TextView tvIdService, TextView tvNameService, TextView tvPriceService, TextView tvTypeService, EditText tvIdRoom) {
            this.btnBookService = btnBookService;
            this.tvIdService = tvIdService;
            this.tvNameService = tvNameService;
            this.tvPriceService = tvPriceService;
            this.tvTypeService = tvTypeService;
            this.tvIdRoom = tvIdRoom;
        }
    }
}
