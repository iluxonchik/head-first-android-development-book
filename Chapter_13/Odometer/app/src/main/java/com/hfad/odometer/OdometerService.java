package com.hfad.odometer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

public class OdometerService extends Service {

    private static double distanceInMetes;
    private static Location lastLocation = null;

    private final IBinder binder = new OdometerBinder();

    public class OdometerBinder extends Binder {
        OdometerService getOdometer() {
            return OdometerService.this;
        }
    }

    public OdometerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       // Used the service to an activity
        /*
            When the activity binds to service with a service connection, the connection will
            call onBind(), which will return OdometerBinder object. The activity will then receive
            this object and call getOdometer() to get the odometer service and work with it
            directly.
         */
        return binder;
    }

    @Override
    public void onCreate() {
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (lastLocation == null)
                    lastLocation = location;

                distanceInMetes += location.distanceTo(lastLocation);
                lastLocation = location;
            }

            // Methods below get called when the GPS gets enabled or disables on when its
            // status changes.
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
    }

    public double getMeters() {
        return this.distanceInMetes;
    }
}
