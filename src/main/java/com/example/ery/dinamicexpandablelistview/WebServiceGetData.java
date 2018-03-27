package com.example.ery.dinamicexpandablelistview;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

/**
 * Created by Ery on 7.12.2017.
 */

public class WebServiceGetData extends AsyncTask<String   , String , Object > {
    public static final String NAMESPACE = "http://tempuri.org/";
    public String METHOD_NAME = "";
    public String SOAP_ACTION = "";
    public String URL = "";

    SoapObject response;
    public List<WebServisIstemciPrmItem> ParametreListesi;

    @Override
    protected Object doInBackground(String... strings) {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        if( ParametreListesi != null ){
            for (WebServisIstemciPrmItem Prm:ParametreListesi)
            {
                request.addProperty(Prm.ParametreName, Prm.ParametreValue);
            }
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet= true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpsTransportSE = new HttpTransportSE(URL);
        try{
            httpsTransportSE.call(SOAP_ACTION, envelope);
            response = (SoapObject) envelope.getResponse();

        }catch ( Exception ex){
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

}
