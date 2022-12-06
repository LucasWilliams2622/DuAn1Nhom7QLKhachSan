package com.example.duan1nhom7qlkhachsan.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.duan1nhom7qlkhachsan.Fragment.BookedRoomFragment;
import com.example.duan1nhom7qlkhachsan.Fragment.BookedServiceFragment;
import com.example.duan1nhom7qlkhachsan.Model.AppBookedRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppBookedService;
import com.example.duan1nhom7qlkhachsan.Model.AppService;
import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookedServiceAdapter extends BaseAdapter {
    private ArrayList<AppBookedService> list  ;
    private Button btnHuyService;
    BookedServiceFragment bookedServiceFragment;
    private EditText tvIdRoom;
    private TextView tvIdService, tvNameService, tvTypeService, tvPriceService;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public BookedServiceAdapter(ArrayList<AppBookedService> list, BookedServiceFragment bookedServiceFragment) {
        this.list = list;
        this.bookedServiceFragment= bookedServiceFragment;
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
        if (view == null) {
            view = View.inflate(_viewGroup.getContext(), R.layout.item_booked_service, null);
            tvIdRoom = view.findViewById(R.id.tvIdRoom);
            tvIdService = view.findViewById(R.id.tvIdService);
            tvTypeService = view.findViewById(R.id.tvTypeService);
            tvNameService = view.findViewById(R.id.tvNameService);
            tvPriceService = view.findViewById(R.id.tvPriceService);
            btnHuyService = view.findViewById(R.id.btnHuyService);
            ViewHolder holder = new ViewHolder(btnHuyService,tvIdService, tvNameService, tvTypeService, tvPriceService, tvIdRoom);
            view.setTag(holder);
        }
        AppBookedService service = (AppBookedService) getItem(_i);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvIdRoom.setText(service.getCodeRoom());
        holder.tvIdService.setText(service.getCodeService());
        holder.tvNameService.setText(service.getNameService());
        holder.tvTypeService.setText(service.getTypeService());
        holder.tvPriceService.setText(service.getPriceService());

        holder.btnHuyService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idRoom = holder.tvIdRoom.getText().toString();
                String idService = holder.tvIdService.getText().toString();
                String nameService = holder.tvNameService.getText().toString();
                String typeService = holder.tvTypeService.getText().toString();
                String priceService = holder.tvPriceService.getText().toString();


                Map<String, Object> user = new HashMap<>();
                user.put("idRoom",idRoom);
                user.put("idService",idService);
                user.put("nameService", nameService);
                user.put("typeService", typeService);
                user.put("priceService", priceService);


                new AlertDialog.Builder(btnHuyService.getContext())
                        .setTitle("Xóa")
                        .setMessage("Bạn có chắc muốn hủy phòng")
                        .setNegativeButton("Không", null)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("bookedService").document(service.getServiceId())
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(btnHuyService.getContext(), "Hủy phòng thành công", Toast.LENGTH_SHORT).show();
                                                bookedServiceFragment.onAdapter();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(btnHuyService.getContext(), "Hủy phòng không thành công", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            }
                        })
                        .show();


            }
        });
        return view;
    }
    private static class ViewHolder {
        final Button btnHuyService;
        final TextView tvIdService, tvNameService, tvPriceService, tvTypeService;
        final EditText tvIdRoom;

        public ViewHolder(Button btnHuyService, TextView tvIdService, TextView tvNameService, TextView tvTypeService, TextView tvPriceService, EditText tvIdRoom) {
            this.btnHuyService = btnHuyService;
            this.tvIdService = tvIdService;
            this.tvNameService = tvNameService;
            this.tvPriceService = tvPriceService;
            this.tvTypeService = tvTypeService;
            this.tvIdRoom = tvIdRoom;
        }
    }
}
