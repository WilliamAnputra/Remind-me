package receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import Extras.Util;

public class BootReciever extends BroadcastReceiver {
    private String TAG="wil";

    public BootReciever() {
        Log.d("TAG","Boot receiver");
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG","received");
       Util.scheduleAlarm(context);
    }
}
