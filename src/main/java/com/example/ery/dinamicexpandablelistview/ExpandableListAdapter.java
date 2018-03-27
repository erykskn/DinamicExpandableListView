package com.example.ery.dinamicexpandablelistview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ery on 22.02.2018.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private List<DersProgramiProperties> objects;
    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<DersProgramiProperties>> _listDataChild;


    public ExpandableListAdapter(List<DersProgramiProperties> objects, Context _context,
                                 List<String> _listDataHeader, HashMap<String, List<DersProgramiProperties>> _listDataChild) {
        this.objects = objects;
        this._context = _context;
        this._listDataHeader = _listDataHeader;
        this._listDataChild = _listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View v, ViewGroup parent) {
        DersProgramiProperties dersProgramiProperties = (DersProgramiProperties)getChild(groupPosition, childPosition);
        if (v == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = infalInflater.inflate(R.layout.ders_programi_item, null);
        }

        DersProgramiProperties i = dersProgramiProperties;

        if (i != null) {

            TextView DersAdı = (TextView) v.findViewById(R.id.DersAdı);
            TextView DERS_KODU = (TextView) v.findViewById(R.id.DERS_KODU);
            TextView BOL_AD = (TextView) v.findViewById(R.id.BOL_AD);
            TextView DERSLIK_GUN = (TextView) v.findViewById(R.id.DERSLIK_GUN);
            TextView DERS_SAAT = (TextView) v.findViewById(R.id.DERS_SAAT);
            TextView DERS_SAAT_BIT = (TextView) v.findViewById(R.id.DERS_SAAT_BIT);
            TextView DERSLIK_AD = (TextView) v.findViewById(R.id.DERSLIK_AD);

            TextView DersAdıData = (TextView) v.findViewById(R.id.DersAdıData);
            TextView DERS_KODUData = (TextView) v.findViewById(R.id.DERS_KODUData);
            TextView BOL_ADData = (TextView) v.findViewById(R.id.BOL_ADData);
            TextView DERSLIK_GUNData = (TextView) v.findViewById(R.id.DERSLIK_GUNData);
            TextView DERS_SAATData = (TextView) v.findViewById(R.id.DERS_SAATData);
            TextView DERS_SAAT_BITData = (TextView) v.findViewById(R.id.DERS_SAAT_BITData);
            TextView DERSLIK_ADData = (TextView) v.findViewById(R.id.DERSLIK_ADData);

            if (DersAdı != null) {
                DersAdı.setText("Ders Adı: ");
            }
            if (DersAdıData != null) {
                DersAdıData.setText(i.getDERS_ADI());
            }
            if (DERS_KODU != null) {
                DERS_KODU.setText("Ders Kodu: ");
            }
            if (DERS_KODUData != null) {
                DERS_KODUData.setText(i.getDERS_KODU());
            }
            if (BOL_AD != null) {
                BOL_AD.setText("Bölüm Adı: ");
            }
            if (BOL_ADData != null) {
                BOL_ADData.setText(i.getBOL_AD());
            }
            if (DERSLIK_GUN != null) {
                DERSLIK_GUN.setText("Derslik Gün: ");
            }
            if (DERSLIK_GUNData != null) {

                if (i.getDERSLIK_GUN().equals("0")) {
                    DERSLIK_GUNData.setText("Pazartesi");
                } else if (i.getDERSLIK_GUN().equals("1")) {
                    DERSLIK_GUNData.setText("Salı");
                } else if (i.getDERSLIK_GUN().equals("2")) {
                    DERSLIK_GUNData.setText("Çarşamba");
                } else if (i.getDERSLIK_GUN().equals("3")) {
                    DERSLIK_GUNData.setText("Perşembe");
                } else if (i.getDERSLIK_GUN().equals("4")) {
                    DERSLIK_GUNData.setText("Cuma");
                }
            }
            if (DERS_SAAT != null) {
                DERS_SAAT.setText("Ders Saati: ");
            }
            if (DERS_SAATData != null) {
                if (i.getDERS_SAAT().startsWith("any")) {
                    DERS_SAATData.setText("");
                } else {
                    DERS_SAATData.setText(i.getDERS_SAAT());
                }
            }
            if (DERS_SAAT_BIT != null) {
                DERS_SAAT_BIT.setText("Ders Bitiş Saati: ");
            }
            if (DERS_SAAT_BITData != null) {
                if (i.getDERS_SAAT_BIT().startsWith("any")) {
                    DERS_SAAT_BITData.setText("");
                } else {
                    DERS_SAAT_BITData.setText(i.getDERS_SAAT_BIT());
                }
            }
            if (DERSLIK_AD != null) {
                DERSLIK_AD.setText("Derslik Adı: ");
            }
            if (DERSLIK_ADData != null) {
                if (i.getDERSLIK_AD().startsWith("any")) {
                    DERSLIK_ADData.setText("");
                } else {
                    DERSLIK_ADData.setText(i.getDERSLIK_AD());
                }
            }
        }
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
