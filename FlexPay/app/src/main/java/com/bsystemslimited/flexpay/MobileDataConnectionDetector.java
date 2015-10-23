package com.bsystemslimited.flexpay;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Hecatonchries on 10/22/2015.
 */
public class MobileDataConnectionDetector {

    private Context context;

    public MobileDataConnectionDetector(Context _context) {
        this.context = _context;
    }

    public boolean checkMobileInternetConn(){
        ConnectivityManager connectivity =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null){
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if(info != null){
                if(info.isConnected()){
                    return true;
                }
            }
        }
        return false;
    }

}
