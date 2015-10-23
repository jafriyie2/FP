package com.bsystemslimited.flexpay;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Hecatonchries on 10/22/2015.
 */
public class WiFiDataConnectionDetector {

    private Context context;

    public WiFiDataConnectionDetector(Context _context) {
        context = _context;
    }
    public boolean checkMobileInternetConn(){
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity!=null)
        {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if(info != null){
                if(info.isConnected())
                {
                    return true;
                }
            }
        }

        return false;
    }

}
