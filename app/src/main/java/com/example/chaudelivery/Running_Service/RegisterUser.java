package com.example.chaudelivery.Running_Service;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.chaudelivery.R;
import com.example.chaudelivery.utils.utils;

import java.net.URL;

import me.pushy.sdk.Pushy;

public class RegisterUser extends AsyncTask<Void,Void,Object> {

    private Activity activity;
    private String TAG ="RegisterUser";

    public RegisterUser(Activity context) {
        this.activity = context;
    }

    @Override
    protected Object doInBackground(Void... voids) {
        try {
            String deviceToken = Pushy.register(activity.getApplicationContext());
            new URL("https://com.example.chauvendor/regsiter/device?token="+deviceToken).openConnection();
            return  deviceToken;
        } catch (Exception e) {

            return e;
        }
    }


    @Override
    protected void onPostExecute(Object o) {

       if(o instanceof Exception){
           new utils().init(activity.getApplicationContext()).edit().putString(activity.getString(R.string.DEVICE_TOKEN),"").apply();
           Toast.makeText(activity, "Error Occurred couldn't not get device token !", Toast.LENGTH_SHORT).show();
       }
       else
           new utils().init(activity.getApplicationContext()).edit().putString(activity.getString(R.string.DEVICE_TOKEN),String.valueOf(o)).apply();
        Log.d(TAG, "onPostExecute: "+o);
    }
}
