package com.vertlix.utils.network;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;


public  class internetAvailable {
    public static boolean hasActiveInternet(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        Network network = cm.getActiveNetwork();

        if (network == null) return false;

        NetworkCapabilities capabilities =
                cm.getNetworkCapabilities(network);

        return capabilities != null &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
    }

}

