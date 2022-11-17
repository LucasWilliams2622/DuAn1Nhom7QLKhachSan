package com.example.duan1nhom7qlkhachsan.Activity.hotro;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class HoTroAdapter extends RecyclerView.Adapter<HoTroAdapter.ViewHoler>{
    private Context context;
    private ArrayList<HoTro>list;

    public HoTroAdapter(Context context, ArrayList<HoTro> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.recycler_item,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {

        holder.txtmanpp.setText(list.get(position).getManpp());
        holder.txttennpp.setText(list.get(position).getTennpp());
        holder.txtgioithieu.setText(list.get(position).getGioithieu());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView txtmanpp,txttennpp,txtgioithieu;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            txtmanpp=itemView.findViewById(R.id.txtmanpp);
            txttennpp=itemView.findViewById(R.id.txttennpp);
            txtgioithieu=itemView.findViewById(R.id.txtgioithieu);
        }
    }
}
