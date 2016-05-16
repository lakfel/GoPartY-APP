package cam.grupo09.goparty.persistencia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;


/**
 * Created by Felipe on 03/03/2016.
 */
public class LeerSMS extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();
    static LeerSMS instance;

    public static LeerSMS  getInstance()
    {
        if(instance == null)
            instance = new LeerSMS();
        return instance;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();    //mensaje recibido



                    if(message.startsWith("GoPartY"))
                    {

                        String[] divs = message.split("-");
                        String nombre = divs[2];

                        String[] fechas = divs[3].split(";");
                        for(String l: fechas)
                        {

                        }
                        String[] horas = divs[4].split(";");
                        for(String l: horas)
                        {
                        }
                        String[] estas = divs[5].split(";");
                        for(String l: estas)
                        {

                        }



                    }

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);



                }
            }

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }
    public void enviarSMS(String mensaje,String numero){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(numero, null, mensaje, null, null);
    }
}