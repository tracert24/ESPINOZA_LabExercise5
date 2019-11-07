package com.espinoza.espinoza_labexercise5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.espinoza.espinoza_labexercise5.R;

import java.util.List;

public class AndroidAdapter extends ArrayAdapter<Android> {
    Context mContext;
    int mResource;

    public AndroidAdapter(@NonNull Context context, int resource, @NonNull List<Android> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        int image = getItem(position).getLogo();
        String name = getItem(position).getName();
        String version = getItem(position).getVersion();
        String api = getItem(position).getApi();
        String rDate=  getItem(position).getRdate();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvVersion = convertView.findViewById(R.id.tvVersion);
        TextView tvApi = convertView.findViewById(R.id.tvApi);
        TextView tvRdate = convertView.findViewById(R.id.tvRdate);
        ImageView ivLogo = convertView.findViewById(R.id.logo);

        tvName.setText(name);
        tvVersion.setText(version);
        tvApi.setText(api);
        tvRdate.setText(rDate);
        ivLogo.setImageResource(image);
        return convertView;

    }
}
