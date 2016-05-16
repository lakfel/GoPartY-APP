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
import java.util.List;

import cam.grupo09.goparty.PersistenciaORMDTOS.EstablecimientoDTO;
import cam.grupo09.goparty.PersistenciaORMDTOS.EventoDTO;
import cam.grupo09.goparty.PersistenciaORMDTOS.OpcionesDTO;
import cam.grupo09.goparty.PersistenciaORMDTOS.UsuarioDTO;
import cam.grupo09.goparty.R;

import cam.grupo09.goparty.mundo.GoPartY;
import cam.grupo09.goparty.persistencia.LeerSMS;
import cam.grupo09.goparty.persistenciaORMModelos.Establecimiento;
import cam.grupo09.goparty.persistenciaORMModelos.Invitacion;
import cam.grupo09.goparty.persistenciaORMModelos.Opcion;
import cam.grupo09.goparty.persistenciaORMModelos.Usuario;


public class CrearEventoActivity extends AppCompatActivity {


    //Constantes de Acciones
    public final static int AGREGAR_INVITADO = 1;
    public final static int AGREGAR_ESTABLECIMIENTO = 2;


    private EditText txtNombreEvento;
    private TextView lblFecha;
    private ListView lstFechasProp;
    private ListView lstInvitaciones;
    private ListView lstLgares;
    private ListView lstHorasPrev;
    private ListView lstHorasSal;
    private ListView lstEstablecimiento;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        if(item.getItemId() == R.id.action_settings)
        {
            MainActivity.actual.setNombre(txtNombreEvento.getText().toString());
            confirmarCreacionEvento();
            showDialog("Eventos", "El evento fue guardado");
            MainActivity.guardarEventoActual();

        }
        else {
            String fechasS = "";

            List<OpcionesDTO> fs = MainActivity.actual.getOpciones();
            SimpleDateFormat sf = new SimpleDateFormat("dd-mm-yyyy");
            String fechaS = "1";
            String horasS1 = "1";
            String horasS2 = "1";
            String lugares = "1";
            if (!fs.isEmpty()) {
                for (int i = 0; i < fs.size(); i++) {
                    if (fs.get(i).getTipoOpcion().equalsIgnoreCase(OpcionesDTO.OPC_FECHA))
                        fechasS += ";" + sf.format(fs.get(i).getOpcion());
                    else if (fs.get(i).getTipoOpcion().equalsIgnoreCase(OpcionesDTO.OPC_HORA_SAL))
                        horasS1 += ";" + sf.format(fs.get(i).getOpcion());
                    else if (fs.get(i).getTipoOpcion().equalsIgnoreCase(OpcionesDTO.OPC_HORA_SAL))
                        horasS2 += ";" + sf.format(fs.get(i).getOpcion());
                    else if (fs.get(i).getTipoOpcion().equalsIgnoreCase(OpcionesDTO.OPC_LUGAR_PREVIA))
                        lugares += ";" + sf.format(fs.get(i).getOpcion());
                }
            }


            List<UsuarioDTO> invitaciones = MainActivity.actual.getInvitaciones();
            EventoDTO e = MainActivity.actual;
            for (UsuarioDTO a : invitaciones) {
                Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String[] columnas = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                String seleccion = ContactsContract.CommonDataKinds.Phone.NUMBER + "='" + a.getCelular() + "'";
                Cursor c = getContentResolver().query(phoneUri, columnas, seleccion, null, null);
                String numeroTelefonico;

                if (c.moveToFirst()) {
                    numeroTelefonico = c.getString(0);
                    TelephonyManager tMgr = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
                    String mPhoneNumber = tMgr.getLine1Number();

                    String msn = "GoPartY-" + mPhoneNumber + "-" + e.getNombre() + "-" + fechasS + "-" + horasS1 + "-" + lugares;
                    //LeerSMS.getInstance().enviarSMS(msn,numeroTelefonico);
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
        MainActivity.actual.setNombre(txtNombreEvento.getText().toString());
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        EventoDTO evento = MainActivity.actual;
        if(evento == null)
            evento = new EventoDTO();
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
        OpcionesDTO op = new OpcionesDTO();
        op.setTipoOpcion(OpcionesDTO.OPC_FECHA);
        op.setCantidadVotos(0);
        op.setOpcion(toDate.getTime()+"");
        MainActivity.actual.addOpcion(op);
        actualizarPantalla();
    }

    public void addHoraPrev(String hora)
    {
        OpcionesDTO op = new OpcionesDTO();
        op.setTipoOpcion(OpcionesDTO.OPC_HORA_LL);
        op.setCantidadVotos(0);
        op.setOpcion(hora);
        MainActivity.actual.addOpcion(op);
        actualizarPantalla();
    }



    public void addHoraSal(String hora)
    {
        OpcionesDTO op = new OpcionesDTO();
        op.setTipoOpcion(OpcionesDTO.OPC_HORA_SAL);
        op.setCantidadVotos(0);
        op.setOpcion(hora);
        MainActivity.actual.addOpcion(op);
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
                OpcionesDTO op = new OpcionesDTO();
                op.setTipoOpcion(OpcionesDTO.OPC_LUGAR_PREVIA);
                op.setCantidadVotos(0);
                op.setOpcion(direccionBox.getText().toString());
                MainActivity.actual.addOpcion(op);
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

    public void actualizarPantalla()
    {
        List<OpcionesDTO> fs = MainActivity.actual.getOpciones();
        ArrayList<String> fechas = new ArrayList<String>();
        ArrayList<String> horas1 = new ArrayList<String>();
        ArrayList<String> horas2 = new ArrayList<String>();
        ArrayList<String> lugarPrevia = new ArrayList<String>();
        ArrayList<EstablecimientoDTO> establecimienots = new ArrayList<EstablecimientoDTO>();

        SimpleDateFormat sf = new SimpleDateFormat("dd-mm-yyyy");
        for (int i = 0; i < fs.size(); i++) {
            if(fs.get(i).getTipoOpcion().equalsIgnoreCase(OpcionesDTO.OPC_FECHA))
                fechas.add(sf.format(new Date(Long.parseLong(fs.get(i).getOpcion()))));
            else if(fs.get(i).getTipoOpcion().equalsIgnoreCase(OpcionesDTO.OPC_HORA_SAL))
                horas1.add(fs.get(i).getOpcion());
            else if(fs.get(i).getTipoOpcion().equalsIgnoreCase(OpcionesDTO.OPC_HORA_LL))
                horas2.add(fs.get(i).getOpcion());
            else if(fs.get(i).getTipoOpcion().equalsIgnoreCase(OpcionesDTO.OPC_LUGAR_PREVIA))
                lugarPrevia.add(fs.get(i).getOpcion());
            else if(fs.get(i).getTipoOpcion().equalsIgnoreCase(OpcionesDTO.OPC_ESTABLECIMIENTO))
            {
                Establecimiento n = Establecimiento.find(Establecimiento.class, "id_Establecimiento = ?", fs.get(i).getOpcion()).get(0);
                EstablecimientoDTO nn = new EstablecimientoDTO();
                nn.setNombre(n.getNombre());
                nn.setDescripcion(n.getDescripcion());
                nn.setCostoCover(n.getCostoCover());
                nn.setDireccion(n.getDireccion());

                establecimienots.add(nn);
            }


        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.lista_item, R.id.label, fechas);
        lstFechasProp.setAdapter(adapter);
        ArrayAdapter<UsuarioDTO> adapter2 = new ArrayAdapter<UsuarioDTO>(this, R.layout.lista_item, R.id.label, MainActivity.actual.getInvitaciones());
        lstInvitaciones.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.lista_item, R.id.label, horas1);
        lstHorasPrev.setAdapter(adapter3);
        ArrayAdapter<EstablecimientoDTO> adapter4 = new ArrayAdapter<EstablecimientoDTO>(this, R.layout.lista_item, R.id.label, establecimienots);
        lstEstablecimiento.setAdapter(adapter4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, R.layout.lista_item, R.id.label, lugarPrevia);
        lstLgares.setAdapter(adapter5);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, R.layout.lista_item, R.id.label, horas2 );
        lstHorasSal.setAdapter(adapter6);

    }

    public void agregarEstablecimiento(View view)
    {
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
                String idCo = "";
                Uri uriContacto = data.getData();
                if (uriContacto != null) {
                    try {
                        String[] cols = {ContactsContract.Contacts._ID,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY, ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER};
                        Cursor cursor = getContentResolver().query(uriContacto, cols, null, null, null);
                        cursor.moveToFirst();
                        idCo = cursor.getString(0);
                        nombreContacto = cursor.getString(1);
                        idContact = cursor.getString(2);

                        String cel = "";

                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ idCo, null, null);
                        if (phones.moveToNext())
                        {
                            cel = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
                            Log.i("CEL  ", cel);
                        }
                        UsuarioDTO nuevo = new UsuarioDTO();
                        nuevo.setNombre(nombreContacto);
                        nuevo.setCelular(cel);
                        Log.d("INVITADOS", "Nombre " + (nombreContacto));
                        MainActivity.actual.addOInvitacion(nuevo);
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
        GoPartY.getManejadorPersistencia().guardarInfo(MainActivity.sinReportar);
    }

}
