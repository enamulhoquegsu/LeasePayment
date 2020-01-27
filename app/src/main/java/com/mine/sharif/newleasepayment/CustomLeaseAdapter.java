package com.mine.sharif.newleasepayment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class CustomLeaseAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Lease>arrayLease;

    public CustomLeaseAdapter( Context context, int resource,  ArrayList<Lease> arrayLease) {

        super(context, resource, arrayLease);

        this.context = context;
        this.resource = resource;
        this.arrayLease = arrayLease;
    }


    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        Lease mylease = arrayLease.get(position);

        TextView tvId   = (TextView)convertView.findViewById(R.id.tvId);
        TextView tvIncome = (TextView)convertView.findViewById(R.id.tvIncome);
        TextView tvDate   = (TextView)convertView.findViewById(R.id.tvDate);

        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

        tvId.setText(String.valueOf( mylease.id));
        tvDate.setText((CharSequence) mylease.leaseDate);
        tvIncome.setText("Income: " + defaultFormat.format(mylease.income));

        return convertView;
    }
}
