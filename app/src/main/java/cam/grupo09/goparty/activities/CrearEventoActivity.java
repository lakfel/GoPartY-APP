package cam.grupo09.goparty.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cam.grupo09.goparty.R;
import cam.grupo09.goparty.mundo.Evento;
import cam.grupo09.goparty.mundo.Invitacion;
import cam.grupo09.goparty.mundo.OpcionPropuesta;

public class CrearEventoActivity extends AppCompatActivity {


    //Constantes de Acciones
    public final static int AGREGAR_INVITADO = 1;
    public final static int AGREGAR_ESTABLECIMIENTO = 2;

    private Evento evento;
    private EditText txtNombreEvento;
    private TextView lblFecha;
    private ListView lstFechasProp;
    private ListView lstInvitaciones;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        evento = new Evento();
        txtNombreEvento = (EditText)findViewById(R.id.txtNombre);
        lstFechasProp =(ListView)findViewById(R.id.lstFechasProP);
        lstInvitaciones = (ListView)findViewById(R.id.lstInvitaciones);
    }


    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setActivity(this);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void addFecha(Date toDate)
    {
        evento.getFechasPropuestas().add(new OpcionPropuesta<Date>(toDate));
        actualizarPantalla();
        /**
        fechaEvento = toDate;

        */
    }

    public void actualizarPantalla()
    {
        OpcionPropuesta<Date>[] fs = evento.darFechasPrpoestas();
        String[] fechas = new String[fs.length];
        SimpleDateFormat sf = new SimpleDateFormat("dd-mm-yyyy");
        for (int i = 0; i <fs.length ; i++)
        {
            fechas[i] = sf.format(fs[i].getOpcion());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.lista_item, R.id.label,fechas);
        lstFechasProp.setAdapter(adapter);
        ArrayAdapter<Invitacion> adapter2 = new ArrayAdapter<Invitacion>(this, R.layout.lista_item, R.id.label,evento.darInvitaciones());
        lstInvitaciones.setAdapter(adapter2);
    }

    /**
     * Clase que despliega un menu de selección de fecha
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        private CrearEventoActivity puente;
        private int dia;
        private int mes;
        private int ano;

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
            dia = -1;
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if(dia > 0 && dia <=31) {
                Calendar c = Calendar.getInstance();
                c.set(ano, mes, dia);
                Date seleccionada = new Date(c.getTimeInMillis());
                puente.addFecha(seleccionada);
            }
        }

        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            dia = day;
            mes = month;
            ano = year;
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
        Log.d("INVITADOS","Resultado " + (resultCode== RESULT_OK));
        if( resultCode== RESULT_OK)
        {
            Log.d("INVITADOS","Resultado2 " + (AGREGAR_INVITADO));
            if(requestCode == AGREGAR_INVITADO)
            {
                String nombreContacto = "";
                String idContact = "";
                Uri uriContacto = data.getData();
                if(uriContacto != null ){
                    try {
                        String[] cols = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME , ContactsContract.CommonDataKinds.Phone.CONTACT_ID};
                        Cursor cursor =  getContentResolver().query(uriContacto, cols, null, null, null);
                        cursor.moveToFirst();
                        nombreContacto = cursor.getString(0);
                        idContact = cursor.getString(1);
                        Log.d("INVITADOS","Nombre " + (nombreContacto));
                        evento.getInvitaciones().add(new Invitacion(idContact,nombreContacto));
                        actualizarPantalla();

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
                        e.printStackTrace();
                    }
                    //btContactos.setText(nombreContacto + "(Cambiar)");
                }


            }
        }
    }


    //-------------------- DIALOGODE BUSQUEDA -----------------------------------


}
