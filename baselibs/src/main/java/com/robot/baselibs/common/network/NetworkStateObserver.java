package com.robot.baselibs.common.network;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

public class NetworkStateObserver {

    private static class SingletonHolder {
        private static final NetworkStateObserver instance = new NetworkStateObserver();
    }

    public static NetworkStateObserver getInstance() {
        return SingletonHolder.instance;
    }

    public interface Listener {
        void onNetworkStateChanged(boolean isConnected, boolean isConnectedWifi);
    }

    List<Listener> listeners = new ArrayList<Listener>();
    Context context;
    BroadcastReceiver receiver;

    boolean mIsConnected = false;
    boolean mIsConnectedWifi = false;

    protected NetworkStateObserver() {

    }

    public void initialize(Context context) {
        this.context = context;
        this.receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (!action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                    return;
                }

                boolean isConnected = isConnected();
                boolean isConnectedWifi = isConnectedWifi();

                boolean isStateChanged = false;
                if (mIsConnected!=isConnected) {
                    isStateChanged = true;
                }

                mIsConnected = isConnected;
                mIsConnectedWifi = isConnectedWifi;

                if (isStateChanged) {
                    handleNetworkStateChanged(isConnected, isConnectedWifi);
                }
            }
        };

        mIsConnected = isConnected();
        mIsConnectedWifi = isConnectedWifi();

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(this.receiver, filter);
    }

    public void cleanup() {
        listeners.clear();

        if (context !=null && receiver!=null) {
            context.unregisterReceiver(receiver);
            receiver=null;
            context=null;
        }
    }

    public void addListener(Listener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public boolean isConnected(){
        if (context!=null) {
            NetworkInfo info = getNetworkInfo(context);
            return (info != null && info.isConnected());
        }
        return false;
    }

    public boolean isConnectedWifi(){
        if (context!=null) {
            NetworkInfo info = getNetworkInfo(context);
            return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
        }
        return false;
    }

    NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    //通知给listener
    void handleNetworkStateChanged(boolean isConnected, boolean isConnectedWifi) {
        for (Listener listener : listeners) {
            listener.onNetworkStateChanged(isConnected, isConnectedWifi);
        }
    }
}
