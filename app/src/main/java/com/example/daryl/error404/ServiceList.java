package com.example.daryl.error404;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.List;

public class ServiceList extends ArrayAdapter<Service> {


    private Activity context;
    private List<Service> serviceList;

    public ServiceList(Activity context, List<Service> serviceList){
        super(context, R.layout.activity_admin_service);
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewIem = inflater.inflate(R.layout.activity_admin_service, null, true );

        TextView textViewName = (TextView) listViewIem.findViewById(R.id.nameEdit);
        TextView textViewPrice = (TextView) listViewIem.findViewById(R.id.priceEdit);

        Service service = serviceList.get(position);

        textViewName.setText(service.getName());
        textViewPrice.setText(service.getPrice());

        return listViewIem;

    }
}
