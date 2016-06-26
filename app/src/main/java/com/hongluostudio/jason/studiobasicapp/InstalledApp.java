package com.hongluostudio.jason.studiobasicapp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Jason on 2016/6/19.
 */
public class InstalledApp extends AppCompatActivity {

    private ListView lv;
    private ListAppAdapter listapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.installedapp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.listbody);
        listapp = new ListAppAdapter(this);
        lv.setAdapter(listapp);

    }
    public class ListAppAdapter extends BaseAdapter {
        private List<PackageInfo> pakeageinfo;
        private LayoutInflater inflater;
        private Context c;

        public ListAppAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            c = context;
            pakeageinfo = context.getPackageManager().getInstalledPackages(0);
            Toast.makeText(context,pakeageinfo.size() + " Appes are Installed", Toast.LENGTH_SHORT).show();
        }
        @Override
        public int getCount() {
            return pakeageinfo.size();
        }

        @Override
        public Object getItem(int position) {
            return pakeageinfo.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.list_item, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                holder.appsname = (TextView) convertView.findViewById(R.id.appsname);
                holder.appinfo = (TextView) convertView.findViewById(R.id.appinfo);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            PackageInfo pinfo = pakeageinfo.get(position);
            holder.icon.setImageDrawable(pinfo.applicationInfo.loadIcon(c.getPackageManager()));
            holder.appsname.setText(pinfo.applicationInfo.loadLabel(c.getPackageManager()));
            if ((pinfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) {
                holder.appinfo.setText(pinfo.applicationInfo.packageName + "(system)");
            } else {
                holder.appinfo.setText(pinfo.applicationInfo.packageName);
            }

            return convertView;
        }

        class ViewHolder {
            ImageView icon;
            TextView appsname;
            TextView appinfo;
        }
    }
}
