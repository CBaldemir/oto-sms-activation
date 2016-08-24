package com.example.comer.autosmslogin.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.example.comer.autosmslogin.SmsApp;
import com.example.comer.autosmslogin.activation.ActivationSms;
import com.example.comer.autosmslogin.activation.ActivationSmsPresenter;
import com.example.comer.autosmslogin.activation.IActivationSmsView;

/**
 * Created by musta on 23.08.2016.
 */
public class SmsService extends BroadcastReceiver {
    IActivationSmsView activitionSmsView;
    ActivationSms activationSms = null;
    private String TAG = ActivationSmsPresenter.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        // Get the data (SMS data) bound to intent
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;

        String str = "";

        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            // For every SMS message received

                // Convert Object array
                msgs[0] = SmsMessage.createFromPdu((byte[]) pdus[0]);
                // Sender's phone number
               // str += "SMS from " + msgs[0].getOriginatingAddress() + " : ";
                // Fetch the text message
            if(msgs[0].getMessageBody().toString().substring(0,7).equals("şifre: "))
                str += msgs[0].getMessageBody().toString().substring(7, 13);
        }
        if (SmsApp.checkSms == false && str.equals("000000")) {
            SmsApp.checkSms = true;
            Intent in = new Intent(context, ActivationSms.class);
            in.putExtra("msg", str);
            context.startActivity(in);
        }
    }

}
