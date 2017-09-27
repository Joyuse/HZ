package com.example.vladimir.sityinfov113;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Vladimir on 12.09.2017.
 */

public class LocationAdress {
    private static final String TAG = "LocationAddress";

    public static void getAddressFromLocation(final double latitude, final double longitude, final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                try {
                    List<Address> addressList = null;
                    try {
                        addressList = geocoder.getFromLocation(latitude, longitude, 1);
                    } catch (IOException e) {
                        Log.e(TAG, "Unable connect to Geocoder", e);
                    }
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append("\n");
                        }
                        sb.append(address.getLocality()).append("\n");
                        //sb.append(address.getPostalCode()).append("\n");
                        //sb.append(address.getCountryName());

                        //Определение города
                        result = sb.toString();
                        Log.w("W","RESULT = " + result);
                    }
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    Log.w("RE","result = " + result);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        //result = "Latitude: " + latitude + " Longitude: " + longitude + "\nAddress: " + result;
                        String result1;
                        result1 = result;
                        //bundle.putString("ADRESS", result);
                        bundle.putString("ADRESS",result1);
                        Log.w("RES", "RESULT PUT STRING = " + result1);
                        message.setData(bundle);
                    } else {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Latitude: " + latitude + " Longitude: " + longitude +
                                "\n Адреса нет.";
                        bundle.putString("address", result);
                        message.setData(bundle);
                        Log.w("W","WWWWWWWW RESULT = " + result);
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }
}
