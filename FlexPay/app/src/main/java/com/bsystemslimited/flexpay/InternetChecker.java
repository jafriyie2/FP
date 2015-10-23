package com.bsystemslimited.flexpay;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Hecatonchries on 10/22/2015.
 */
public class InternetChecker {

    Context mContext;

    public InternetChecker(Context mContext) {
        this.mContext = mContext;
    }

    //Checking Network Availability
    protected boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }
}
