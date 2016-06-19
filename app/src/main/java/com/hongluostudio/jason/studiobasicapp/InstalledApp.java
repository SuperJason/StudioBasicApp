package com.hongluostudio.jason.studiobasicapp;

import android.app.Activity;
import android.content.Context;
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
                holder.pname = (TextView) convertView.findViewById(R.id.pname);
                holder.versionname = (TextView) convertView.findViewById(R.id.versionname);
                holder.versioncode = (TextView) convertView.findViewById(R.id.versioncode);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            PackageInfo pinfo = pakeageinfo.get(position);
            holder.icon.setImageDrawable(pinfo.applicationInfo.loadIcon(c.getPackageManager()));
            holder.appsname.setText("名称："	+ pinfo.applicationInfo.loadLabel(c.getPackageManager()));
            holder.pname.setText("包名：" + pinfo.applicationInfo.packageName);
            holder.versionname.setText("版本名称：" + pinfo.versionName);
            holder.versioncode.setText("版本号：" + pinfo.versionCode);
            return convertView;
        }

        class ViewHolder {
            ImageView icon;
            TextView appsname;
            TextView pname;
            TextView versionname;
            TextView versioncode;
        }
    }
}
