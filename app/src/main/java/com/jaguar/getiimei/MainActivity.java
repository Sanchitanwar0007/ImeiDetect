package com.jaguar.getiimei;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button imei_button;
    private TextView imei_text;
    String IMEI_Number_Holder;
    TelephonyManager telephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imei_button = (Button) findViewById(R.id.imei_buttonID);
        imei_text = (TextView) findViewById(R.id.imei_textViewID);

        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        int permission_All = 1;
        final String[] Permission = {android.Manifest.permission.CALL_PHONE, android.Manifest.permission.CAMERA,
                android.Manifest.permission.ACCESS_FINE_LOCATION,};
        if (!hasPermissions(this, Permission)) {
            ActivityCompat.requestPermissions(this, Permission, permission_All);
        }


        imei_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(MainActivity.this, "You must enable Phone Call Permission to Detect IMEI", Toast.LENGTH_LONG).show();
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                IMEI_Number_Holder = telephonyManager.getDeviceId();

                imei_text.setText(IMEI_Number_Holder);
                Toast.makeText(MainActivity.this, "Reach To permisison", Toast.LENGTH_SHORT).show();

                }

        });

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }


        }
        return true;
    }
}
