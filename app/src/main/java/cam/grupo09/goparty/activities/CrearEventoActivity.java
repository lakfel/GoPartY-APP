package cam.grupo09.goparty.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.codinguser.android.contactpicker.ContactsPickerActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cam.grupo09.goparty.R;

public class CrearEventoActivity extends AppCompatActivity {


    //Constantes de Acciones
    public final static int AGREGAR_INVITADO = 1;
    public final static int AGREGAR_ESTABLECIMIENTO = 2;


    private EditText txtNombreEvento;
    private TextView lblFecha;
    private Date fechaEvento;
    private ListView lstInvitados;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtNombreEvento = (EditText)findViewById(R.id.txtNombre);
        lblFecha = (TextView)findViewById(R.id.lblFechaEvento);
        lstInvitados =(ListView)findViewById(R.id.lstInvitados);

    }


    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setActivity(this);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void setFecha(Date toDate)
    {
        fechaEvento = toDate;
        SimpleDateFormat sf = new SimpleDateFormat("dd-mm-yyyy");
        lblFecha.setText(sf.format(fechaEvento) );

    }

    /**
     * Clase que despliega un menu de selección de fecha
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        private CrearEventoActivity puente;

        public void setActivity(CrearEventoActivity propia)
        {
            puente = propia;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            Date seleccionada = new Date(c.getTimeInMillis());
            puente.setFecha(seleccionada);
            // Do something with the date chosen by the user
        }
    }




    public void launchMultiplePhonePicker(View view)
    {
        startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), AGREGAR_INVITADO );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK)
        {
            if(resultCode == AGREGAR_INVITADO)
            {
                String nombreContacto = "";
                Uri uriContacto = data.getData();
                if(uriContacto != null ){
                    try {
                        String[] cols = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
                        Cursor cursor =  getContentResolver().query(uriContacto, cols, null, null, null);
                        cursor.moveToFirst();
                        nombreContacto = cursor.getString(0);

                        /**Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                        String[] columnas = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                        String seleccion = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "='" + nombreContacto + "'";
                        Cursor c = getContentResolver().query(phoneUri,columnas,seleccion,null, null );

                        if(c.moveToFirst()){
                            numeroTelefonico = c.getString(0);
                        }
                        */



                    } catch (Exception e) {
                        //numeroTelefonico = e.getMessage();
                        //showDialog(DIALOGO_ERROR);
                    }
                    //btContactos.setText(nombreContacto + "(Cambiar)");
                }


            }
        }
    }


    //-------------------- DIALOGODE BUSQUEDA -----------------------------------


}
