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
    String name = "Name";
    String disc = "Disc";

    private String[] nameList;
    private String[] discList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pakeageinfo = this.getPackageManager().getInstalledPackages(0);

        setListAdapter(new SimpleAdapter(this, getListValues(), android.R.layout.simple_list_item_2,
                new String[] {name, disc}, new int[] {android.R.id.text1, android.R.id.text2}));
    }

    private List<Map<String, String>> getListValues() {
        List<Map<String, String>> values = new ArrayList<Map<String, String>>();
        PackageInfo pinfo;
        int length = pakeageinfo.size();
        nameList = new String[length];
        discList = new String[length];
        for (int i = 0; i < length; i++) {
            Map<String, String> v = new HashMap<String, String>();
            pinfo = pakeageinfo.get(i);
            String nnn =  pinfo.applicationInfo.loadLabel(this.getPackageManager()).toString();
            nameList[i] = pinfo.applicationInfo.loadLabel(this.getPackageManager()).toString();
            discList[i] = pinfo.applicationInfo.packageName;
            v.put(name, nameList[i]);
            v.put(disc,discList[i]);
            values.add(v);
        }
        return values;
    }

}
