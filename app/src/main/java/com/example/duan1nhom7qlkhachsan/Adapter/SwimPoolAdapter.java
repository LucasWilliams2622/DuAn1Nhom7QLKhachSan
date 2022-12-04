package com.example.duan1nhom7qlkhachsan.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.duan1nhom7qlkhachsan.Activity.IAdapterAddServiceClickEvent;
import com.example.duan1nhom7qlkhachsan.Model.AppService;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class SwimPoolAdapter extends BaseAdapter {
    private ArrayList<AppService> list;

    private Button btnUpdateService, btnDeleteService;
    private TextView tvIdRoom, tvIdService, tvNameService, tvPriceService, tvTypeService;
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
            view = View.inflate(_viewGroup.getContext(), R.layout.item_swim_pool, null);

            tvIdRoom = view.findViewById(R.id.tvIdRoom);
            tvIdService = view.findViewById(R.id.tvIdService);
            tvNameService = view.findViewById(R.id.tvNameService);
            tvTypeService = view.findViewById(R.id.tvPriceService);
            tvPriceService = view.findViewById(R.id.tvTypeService);
            btnDeleteService = view.findViewById(R.id.btnDeleteService);
            btnUpdateService = view.findViewById(R.id.btnBookService);

            ViewHolder holder = new ViewHolder(tvIdRoom, tvIdService, tvNameService, tvTypeService, tvPriceService, btnDeleteService, btnUpdateService);

            view.setTag(holder);
        }
        AppService service = (AppService) getItem(_i);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvIdRoom.setText("Id Room: "+service.getCodeRoom());
        holder.tvIdService.setText("Id: "+service.getCodeService());
        holder.tvNameService.setText("Tên: "+service.getNameService());
        holder.tvTypeService.setText("Loại: "+service.getTypeService());
        holder.tvPriceService.setText("Giá: "+service.getPriceService());



        holder.btnUpdateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IAdapterAddServiceClickEvent iAdapterAddServiceClickEvent = (IAdapterAddServiceClickEvent) _viewGroup.getContext();
                iAdapterAddServiceClickEvent.onUpdateServiceClick(service);
            }
        });
        holder.btnDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IAdapterAddServiceClickEvent iAdapterAddServiceClickEvent = (IAdapterAddServiceClickEvent) _viewGroup.getContext();
                iAdapterAddServiceClickEvent.onDeleteServiceClick(service);
            }
        });

        return view;
    }
    private static class ViewHolder {
        final Button btnUpdateService, btnDeleteService;
        final TextView tvIdRoom, tvIdService, tvNameService, tvPriceService, tvTypeService;

        public ViewHolder(TextView tvIdRoom, TextView tvIdService, TextView tvNameService, TextView tvPriceService, TextView tvTypeService, Button btnDeleteService, Button btnUpdateService) {
            this.tvIdRoom = tvIdRoom;
            this.tvIdService = tvIdService;
            this.tvNameService = tvNameService;
            this.tvPriceService = tvPriceService;
            this.tvTypeService = tvTypeService;
            this.btnDeleteService = btnDeleteService;
            this.btnUpdateService = btnUpdateService;

        }
    }
}
