package com.example.sms_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
public class MainActivity extends AppCompatActivity {
private  static final int PERMISSION_RQST_SEND = 0; // It will work when user will grant permission ..................................
Button button1;
EditText phoneNo;
EditText myMessage;
String phoneNum;
String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Now we will create objects .....
        button1 =(Button) findViewById(R.id.btnSendSMS);
        phoneNo = (EditText) findViewById(R.id.EditText);
        myMessage  = (EditText) findViewById(R.id.EditText2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sendSMS();
            }
        });

    }
    protected void sendSMS()
    {
        phoneNum = phoneNo.getText().toString(); // We'll get the phone number from the user .....
        message = myMessage.getText().toString(); // We'll get the message from the user .....
        // We'll check the permission is granted or not .. It not we'll change...
        if(ContextCompat.checkSelfPermission(this , Manifest.permission.SEND_SMS)
                !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_RQST_SEND);
        }
        else{
            sendTextMsg();

        }
    } // send Sms() method ends here .......................................


    // Now once the permission is there or not  would be checked..............


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode)
        {
            case PERMISSION_RQST_SEND:
            {
                if (grantResults.length >  0 && grantResults [0] == PackageManager.PERMISSION_GRANTED )
                {

                    sendTextMsg();
                }

                else
                {

                    Toast.makeText(getApplicationContext(), "SMS FAILED , YOU MAY TRY AGAIN LATER ............", Toast.LENGTH_LONG).show();
                    return;

                }



            }


        }

    }


    private  void sendTextMsg()
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNum , null , message , null , null);

        Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_LONG).show();


    }












} // main method...............................................