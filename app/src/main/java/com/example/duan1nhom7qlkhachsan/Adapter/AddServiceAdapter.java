package com.example.duan1nhom7qlkhachsan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1nhom7qlkhachsan.Activity.IAdapterServiceClickEvent;
import com.example.duan1nhom7qlkhachsan.Model.AppService;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class AddServiceAdapter extends RecyclerView.Adapter<AddServiceAdapter.ViewHolder> {
    private ArrayList<AppService> dsService;
    Context context;

    public AddServiceAdapter(ArrayList<AppService> dsService) {
        this.dsService = dsService;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppService service = dsService.get(position);

        holder.tvIdRoom.setText(service.getIdRoom());
        holder.tvIdService.setText(service.getIdService());
        holder.tvNameService.setText(service.getNameService());
        holder.tvTimeUseService.setText(service.getTimeUseService());
        holder.tvPriceService.setText(service.getPriceService());

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Update", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Clear", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return dsService.size();
    }



    //private Button btnAddService,btnClearService, btnUpdateService;
    //private EditText edtIdRoom, edtIdService, edtNameService,edtTimeService, edtPriceService;
    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvIdRoom ,tvIdService, tvNameService, tvPriceService, tvTimeUseService;
        final Button btnUpdate, btnClear;
        public  ViewHolder(@NonNull View itemView){
            super(itemView);
            tvIdRoom = itemView.findViewById(R.id.tvIdRoom1);
            tvIdService = itemView.findViewById(R.id.tvIdService1);
            tvNameService = itemView.findViewById(R.id.tvNameService1);
            tvPriceService = itemView.findViewById(R.id.tvPriceService1);
            tvTimeUseService =itemView.findViewById(R.id.tvTimeService1);
            btnUpdate = itemView.findViewById(R.id.btnUpdateService);
            btnClear = itemView.findViewById(R.id.btnClearService);
        }
    }
}
