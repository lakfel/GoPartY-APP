package cam.grupo09.goparty.persistencia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

import cam.grupo09.goparty.mundo.Establecimiento;
import cam.grupo09.goparty.mundo.Evento;
import cam.grupo09.goparty.mundo.GoPartY;
import cam.grupo09.goparty.mundo.OpcionPropuesta;

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
                        Evento n = new Evento();
                        String[] divs = message.split("-");
                        String nombre = divs[2];
                        n.setNombreEvento(nombre);
                        String[] fechas = divs[3].split(";");
                        for(String l: fechas)
                        {
                            n.getFechasPropuestas().add(new OpcionPropuesta<Date>(new Date(l)));
                        }
                        String[] horas = divs[4].split(";");
                        for(String l: horas)
                        {
                            n.getHorasPropuestasPrevia().add(new OpcionPropuesta<String>(l));
                        }
                        String[] estas = divs[5].split(";");
                        for(String l: estas)
                        {
                            Establecimiento t = GoPartY.getInstance().getManejadorPersistencia().darEtablecimientoNombre(l);
                            n.getEstablecimientosPropuestos().add(new OpcionPropuesta<Establecimiento>(t));
                        }
                        GoPartY.getInstance().getManejadorPersistencia().getEventos().add(n);


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