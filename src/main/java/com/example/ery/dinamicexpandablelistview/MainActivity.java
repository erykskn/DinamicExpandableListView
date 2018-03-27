package com.example.ery.dinamicexpandablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String eposta;
    android.widget.ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<DersProgramiProperties>> listDataChild;
    List<DersProgramiProperties> list = new ArrayList<DersProgramiProperties>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eposta = getIntent().getExtras().getString("eposta");
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);


        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter( list, this, listDataHeader, listDataChild );

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<DersProgramiProperties>>();
        List<DersProgramiProperties> pazartesi = new ArrayList<DersProgramiProperties>();
        List<DersProgramiProperties> sali = new ArrayList<DersProgramiProperties>();
        List<DersProgramiProperties> carsamba = new ArrayList<DersProgramiProperties>();
        List<DersProgramiProperties> persembe = new ArrayList<DersProgramiProperties>();
        List<DersProgramiProperties> cuma = new ArrayList<DersProgramiProperties>();

        // Adding Header
        listDataHeader.add("Pazartesi");
        listDataHeader.add("Salı");
        listDataHeader.add("Çarşamba");
        listDataHeader.add("Perşembe");
        listDataHeader.add("Cuma");

        list =  GetData();

            if (list.size() > 0) {
                Collections.sort(list, new Comparator<DersProgramiProperties>() {
                    @Override
                    public int compare(DersProgramiProperties o1, DersProgramiProperties o2) {

                        int comp = o1.getDERSLIK_GUN().compareTo(o2.getDERSLIK_GUN());

                        if(comp != 0){
                            return comp;
                        }else {
                            return o1.getDERS_SAAT().compareTo(o2.getDERS_SAAT());
                        }
                    }
                });
            }

            if ( list.size() > 0 ){
                for( int a = 0; a < list.size(); a++){
                    if(list.get(a).getDERSLIK_GUN().equals("0")){
                        pazartesi.add(list.get(a));
                    }else if(list.get(a).getDERSLIK_GUN().equals("1")){
                        sali.add(list.get(a));
                    }else if(list.get(a).getDERSLIK_GUN().equals("2")){
                        carsamba.add(list.get(a));
                    }else if(list.get(a).getDERSLIK_GUN().equals("3")){
                        persembe.add(list.get(a));
                    }else if(list.get(a).getDERSLIK_GUN().equals("4")){
                        cuma.add(list.get(a));
                    }
                }
            }

            listDataChild.put(listDataHeader.get(0), pazartesi); // Header, Child data
            listDataChild.put(listDataHeader.get(1), sali);
            listDataChild.put(listDataHeader.get(2), carsamba);
            listDataChild.put(listDataHeader.get(3), persembe);
            listDataChild.put(listDataHeader.get(4), cuma);


    }

    private  ArrayList<DersProgramiProperties> GetData(){


        WebServiceGetData webServisIstemci = new WebServiceGetData();
        webServisIstemci.ParametreListesi=new ArrayList<>();

        WebServisIstemciPrmItem Name = new WebServisIstemciPrmItem();
        WebServisIstemciPrmItem Pass = new WebServisIstemciPrmItem();
        WebServisIstemciPrmItem Email = new WebServisIstemciPrmItem();

        String pass = getResources().getString(R.string.Password);
        String name = getResources().getString(R.string.UserName);

        Name.ParametreName=  "UserName";
        Name.ParametreValue= name;

        Pass.ParametreName= "Password";
        Pass.ParametreValue= pass;

        Email.ParametreName=  "Email";
        Email.ParametreValue=  eposta;

        webServisIstemci.ParametreListesi.add(Name);
        webServisIstemci.ParametreListesi.add(Pass);
        webServisIstemci.ParametreListesi.add(Email);
        webServisIstemci.METHOD_NAME = getResources().getString(R.string.DersProgrami);
        webServisIstemci.SOAP_ACTION = "http://tempuri.org/"+getResources().getString(R.string.DersProgrami);
        webServisIstemci.URL = getResources().getString(R.string.URL);

        try {
            SoapObject sonuc= (SoapObject) webServisIstemci.execute("","","").get();

            if (sonuc.toString().equals("anyType{}") || sonuc == null) {
                list= null;

            } else {
                list = new ArrayList<DersProgramiProperties>();
                for( int a = 0; a < sonuc.getPropertyCount(); a++){

                    SoapObject soapEmployee = (SoapObject) sonuc.getProperty(a);
                    DersProgramiProperties item = new DersProgramiProperties();

                    if (soapEmployee.hasProperty("DERS_ADI")) {
                        item.setDERS_ADI(soapEmployee.getPropertyAsString("DERS_ADI"));
                    }

                    if (soapEmployee.hasProperty("DERS_KODU")) {
                        item.setDERS_KODU(soapEmployee.getPropertyAsString("DERS_KODU"));
                    }

                    if (soapEmployee.hasProperty("BOL_AD")) {
                        item.setBOL_AD(soapEmployee.getPropertyAsString("BOL_AD"));
                    }

                    if (soapEmployee.hasProperty("DERSLIK_GUN")) {
                        item.setDERSLIK_GUN(soapEmployee.getPropertyAsString("DERSLIK_GUN"));
                    }
                    if (soapEmployee.hasProperty("DERS_SAAT")) {
                        item.setDERS_SAAT(soapEmployee.getPropertyAsString("DERS_SAAT"));
                    }

                    if (soapEmployee.hasProperty("DERS_SAAT_BIT")) {
                        item.setDERS_SAAT_BIT(soapEmployee.getPropertyAsString("DERS_SAAT_BIT"));
                    }

                    if (soapEmployee.hasProperty("DERSLIK_AD")) {
                        item.setDERSLIK_AD(soapEmployee.getPropertyAsString("DERSLIK_AD"));
                    }

                    list.add(item);
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return (ArrayList<DersProgramiProperties>) list;
    }
}
