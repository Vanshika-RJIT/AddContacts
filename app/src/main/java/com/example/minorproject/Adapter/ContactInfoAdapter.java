package com.example.minorproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.minorproject.Model.ContactInfo;
import com.example.minorproject.R;

import java.util.List;

public class ContactInfoAdapter extends ArrayAdapter {

    private List contactsInfoList;
    private Context context;

    public ContactInfoAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.contactsInfoList = objects;
        this.context = context;
    }

    private class ViewHolder {
        TextView displayName;
        TextView phoneNumber;
        CardView cardView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.contact_info, null);

            holder = new ViewHolder();
            holder.displayName = (TextView) convertView.findViewById(R.id.displayName);
            holder.phoneNumber = (TextView) convertView.findViewById(R.id.phoneNumber);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ContactInfo contactInfo = (ContactInfo) contactsInfoList.get(position);
        holder.displayName.setText(contactInfo.getDisplayName());
        holder.phoneNumber.setText(contactInfo.getPhoneNumber());
        return convertView;
    }



}
