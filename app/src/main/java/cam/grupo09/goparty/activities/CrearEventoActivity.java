package cam.grupo09.goparty.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cam.grupo09.goparty.R;
import cam.grupo09.goparty.mundo.Establecimiento;
import cam.grupo09.goparty.mundo.Evento;
import cam.grupo09.goparty.mundo.GoPartY;
import cam.grupo09.goparty.mundo.Invitacion;
import cam.grupo09.goparty.mundo.LugarPrevia;
import cam.grupo09.goparty.mundo.OpcionPropuesta;
import cam.grupo09.goparty.persistencia.LeerSMS;

public class CrearEventoActivity extends AppCompatActivity {


    //Constantes de Acciones
    public final static int AGREGAR_INVITADO = 1;
    public final static int AGREGAR_ESTABLECIMIENTO = 2;

    private Evento evento;
    private EditText txtNombreEvento;
    private TextView lblFecha;
    private ListView lstFechasProp;
    private ListView lstInvitaciones;
    private ListView lstLgares;
    private ListView lstHorasPrev;
    private ListView lstHorasSal;
    private ListView lstEstablecimiento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        evento = new Evento();
        txtNombreEvento = (EditText) findViewById(R.id.txtNombre);
        lstFechasProp = (ListView) findViewById(R.id.lstFechasProP);
        lstInvitaciones = (ListView) findViewById(R.id.lstInvitaciones);
        lstHorasPrev = (ListView)findViewById(R.id.lstHorasPrevias);
        lstEstablecimiento = (ListView)findViewById(R.id.lstEstablecimientos);
        lstLgares = (ListView)findViewById(R.id.lstLugares);
        lstHorasSal = (ListView)findViewById(R.id.lstHoraSal);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.action_settings) {
            GoPartY.getInstance().getEventoActual().setNombreEvento(txtNombreEvento.getText().toString());
            confirmarCreacionEvento();
            showDialog("Evento", "El evento fue guardado");
            GoPartY.getInstance().empezarEventoActivity();
            actualizarPantalla();
        }
            else
        {

            String fechasS = "";
            OpcionPropuesta<Date>[] fs = GoPartY.getInstance().getEventoActual().darFechasPrpoestas();
            SimpleDateFormat sf = new SimpleDateFormat("dd-mm-yyyy");
            fechasS = sf.format(fs[0].getOpcion());
            for (int i = 1; i < fs.length; i++) {
                fechasS += ";" + sf.format(fs[i].getOpcion());
            }

            String horasS = "";
            OpcionPropuesta<String>[] hs = GoPartY.getInstance().getEventoActual().darHorasPreviaPropuestas();
            horasS = hs[0].toString();
            for (int i = 1; i < hs.length; i++) {
                horasS += ";" + hs[i];
            }

            String lugares = "";
            OpcionPropuesta<Establecimiento>[] ls = GoPartY.getInstance().getEventoActual().darEstablecimientosPropuestos();
            lugares = ls[0].toString();
            for (int i = 1; i < ls.length; i++) {
                lugares += ";" + ls[i];
            }
            ArrayList<Invitacion> invitaciones = GoPartY.getInstance().getEventoActual().getInvitaciones();
            Evento e = GoPartY.getInstance().getEventoActual();
            for (Invitacion a: invitaciones)
            {
                Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String[] columnas = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                String seleccion = ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY + "='" + a.getIdentificadorInvitado() + "'";
                Cursor c = getContentResolver().query(phoneUri,columnas,seleccion,null, null );
                String numeroTelefonico;

                if(c.moveToFirst()){
                    numeroTelefonico = c.getString(0);
                    TelephonyManager tMgr = (TelephonyManager)getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
                    String mPhoneNumber = tMgr.getLine1Number();

                    String msn = "GoPartY-" + mPhoneNumber+"-"+e.getNombreEvento()+"-"+fechasS+"-"+horasS+"-"+lugares;
                    LeerSMS.getInstance().enviarSMS(msn,numeroTelefonico);
                }

            }

        }

        return super.onOptionsItemSelected(item);
    }


    private void showDialog(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {

            }
        });
        AlertDialog dialog= alertDialog.create();
        dialog.show();

    }

    public void confirmarCreacionEvento()
    {
        GoPartY.getInstance().getEventoActual().setNombreEvento(txtNombreEvento.getText().toString());
        GoPartY.getInstance().crearEvento();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        evento = GoPartY.getInstance().getEventoActual();
        if(evento == null)
            evento = new Evento();
        actualizarPantalla();
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setActivity(this);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        TimePickerFragment  newFragment = new TimePickerFragment();
        int num = (v.getId() == R.id.btnHoraPrev)?1:2;
        newFragment.setActivity(this, num);
        newFragment.show(getFragmentManager(), "datePicker");
    }


    public void addFecha(Date toDate)
    {
        GoPartY.getInstance().getEventoActual().getFechasPropuestas().add(new OpcionPropuesta<Date>(toDate));
        actualizarPantalla();
    }

    public void addHoraPrev(String hora)
    {
        GoPartY.getInstance().getEventoActual().getHorasPropuestasPrevia().add(new OpcionPropuesta<String>(hora));
        actualizarPantalla();
    }



    public void addHoraSal(String hora)
    {
        GoPartY.getInstance().getEventoActual().getHorasPropuestasSalida().add(new OpcionPropuesta<String>(hora));
        actualizarPantalla();
    }


    public void agregarLugarPrevia(View view)
    {

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText nombreBox = new EditText(this);
        nombreBox.setHint("Nombre");
        layout.addView(nombreBox);

        final EditText direccionBox = new EditText(this);
        direccionBox.setHint("Direccion");
        layout.addView(direccionBox);

        final EditText descriptionBox = new EditText(this);
        descriptionBox.setHint("Description");
        layout.addView(descriptionBox);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Agregar un lugar a la previa");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Agregue un nombre del lugar, direccion y un comentario si desea");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                GoPartY.getInstance().getEventoActual().getLugaresPropuestos().add(new OpcionPropuesta<LugarPrevia>(new LugarPrevia(
                        nombreBox.getText().toString(),direccionBox.getText().toString(),descriptionBox.getText().toString()
                )));
            }
        });
        AlertDialog dialog= alertDialog.create();
        dialog.show();
        actualizarPantalla();


        dialog.setView(layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_crear_evento, menu);
        return true;
    }

    public void actualizarPantalla() {
        OpcionPropuesta<Date>[] fs = GoPartY.getInstance().getEventoActual().darFechasPrpoestas();
        String[] fechas = new String[fs.length];
        SimpleDateFormat sf = new SimpleDateFormat("dd-mm-yyyy");
        for (int i = 0; i < fs.length; i++) {
            fechas[i] = sf.format(fs[i].getOpcion());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.lista_item, R.id.label, fechas);
        lstFechasProp.setAdapter(adapter);
        ArrayAdapter<Invitacion> adapter2 = new ArrayAdapter<Invitacion>(this, R.layout.lista_item, R.id.label, GoPartY.getInstance().getEventoActual().darInvitaciones());
        lstInvitaciones.setAdapter(adapter2);
        ArrayAdapter<OpcionPropuesta<String>> adapter3 = new ArrayAdapter<OpcionPropuesta<String>>(this, R.layout.lista_item, R.id.label, GoPartY.getInstance().getEventoActual().darHorasPreviaPropuestas());
        lstHorasPrev.setAdapter(adapter3);
        ArrayAdapter<OpcionPropuesta<Establecimiento>> adapter4 = new ArrayAdapter<OpcionPropuesta<Establecimiento>>(this, R.layout.lista_item, R.id.label, GoPartY.getInstance().getEventoActual().darEstablecimientosPropuestos());
        lstEstablecimiento.setAdapter(adapter4);
        ArrayAdapter<OpcionPropuesta<LugarPrevia>> adapter5 = new ArrayAdapter<OpcionPropuesta<LugarPrevia>>(this, R.layout.lista_item, R.id.label, GoPartY.getInstance().getEventoActual().darLugaresPrpuestos());
        lstLgares.setAdapter(adapter5);
        ArrayAdapter<OpcionPropuesta<String>> adapter6 = new ArrayAdapter<OpcionPropuesta<String>>(this, R.layout.lista_item, R.id.label, GoPartY.getInstance().getEventoActual().darHorasSalidaPropuestas() );
        lstHorasSal.setAdapter(adapter6);

    }

    public void agregarEstablecimiento(View view)
    {
        //GoPartY.getInstance().setEventoActual(evento);
        startActivity(new Intent(this,AgregarEstablecimientoEventoActivity.class));
    }

    public void launchMultiplePhonePicker(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), AGREGAR_INVITADO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("INVITADOS", "Resultado " + (resultCode == RESULT_OK));
        if (resultCode == RESULT_OK) {
            Log.d("INVITADOS", "Resultado2 " + (AGREGAR_INVITADO));
            if (requestCode == AGREGAR_INVITADO) {
                String nombreContacto = "";
                String idContact = "";
                Uri uriContacto = data.getData();
                if (uriContacto != null) {
                    try {
                        String[] cols = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY};
                        Cursor cursor = getContentResolver().query(uriContacto, cols, null, null, null);
                        cursor.moveToFirst();
                        nombreContacto = cursor.getString(0);
                        idContact = cursor.getString(1);
                        Log.d("INVITADOS", "Nombre " + (nombreContacto));
                        GoPartY.getInstance().getEventoActual().getInvitaciones().add(new Invitacion(idContact, nombreContacto));
                        actualizarPantalla();




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

    // --------------------------------- PICKERS
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener
    {

        private CrearEventoActivity puente;
        private int tipoHora;
        private int hora;
        private int minutos;

        public void setActivity(CrearEventoActivity nAc, int nHor)
        {
            puente = nAc;
            tipoHora = nHor;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            hora = -1;
            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            hora = hourOfDay;
            minutos = minute;

        }

        @Override
        public void onDestroy()
        {
            super.onDestroy();
            if(hora >= 0 && hora < 24)
            {
                if(tipoHora == 1)
                {
                    puente.addHoraPrev(hora + ":" + minutos);
                }
                else
                {
                    puente.addHoraSal(hora + ":" + minutos);
                }
            }
        }
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

        public void setActivity(CrearEventoActivity propia) {
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
            if (dia > 0 && dia <= 31) {
                Calendar c = Calendar.getInstance();

                c.set(ano, mes, dia);
                Date seleccionada = new Date(c.getTimeInMillis());
                puente.addFecha(c.getTime());
            }
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            dia = day;
            mes = month;
            ano = year;
            Log.d("FECHA",dia + "-" + mes + "-" + ano);
            // Do something with the date chosen by the user
        }
    }

    @Override
    protected void onDestroy()
    {

        super.onDestroy();
        GoPartY.getInstance().guardar();
    }

}
