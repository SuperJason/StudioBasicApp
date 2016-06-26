package com.hongluostudio.jason.studiobasicapp;

import android.app.ListActivity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2016/6/26.
 */
public class InstalledApp2 extends ListActivity {

    private List<PackageInfo> pakeageinfo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pakeageinfo = this.getPackageManager().getInstalledPackages(0);

        SimpleAdapter adapter = new SimpleAdapter(this, getListValues(), R.layout.installedapp2,
                new String [] {"name", "info", "icon"},
                new int [] {R.id.name, R.id.info, R.id.icon});

        setListAdapter(adapter);
    }

    private List<Map<String, Object>> getListValues() {
        List<Map<String,  Object>> values = new ArrayList<Map<String,  Object>>();
        PackageInfo pinfo;
        int length = pakeageinfo.size();
        for (int i = 0; i < length; i++) {
            Map<String, Object> v = new HashMap<String, Object>();
            pinfo = pakeageinfo.get(i);
            v.put("name", pinfo.applicationInfo.loadLabel(this.getPackageManager()).toString());
            v.put("info", pinfo.applicationInfo.packageName);
            v.put("icon", pinfo.applicationInfo.loadIcon(this.getPackageManager()));
            values.add(v);
        }
        return values;
    }

}
