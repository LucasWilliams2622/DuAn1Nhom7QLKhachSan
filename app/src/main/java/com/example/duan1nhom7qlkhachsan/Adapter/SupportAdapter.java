package com.example.duan1nhom7qlkhachsan.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.duan1nhom7qlkhachsan.Model.AppRoom;
import com.example.duan1nhom7qlkhachsan.Model.AppSupport;
import com.example.duan1nhom7qlkhachsan.R;

import java.util.ArrayList;

public class SupportAdapter extends BaseAdapter {
    private ArrayList<AppSupport> list = null;

    private Button btnHuySupport;
    private TextView tvNameSupport,tvPhoneSupport,tvContentSupport;

    public SupportAdapter(ArrayList<AppSupport> list) {
        this.list = list;
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
            tvNameSupport = view.findViewById(R.id.tvNameRoom);
            tvPhoneSupport = view.findViewById(R.id.tvTypeRoom);
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
