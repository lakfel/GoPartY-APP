package cam.grupo09.goparty.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orm.SugarContext;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cam.grupo09.goparty.PersistenciaORMDTOS.EventoDTO;
import cam.grupo09.goparty.PersistenciaORMDTOS.UsuarioDTO;
import cam.grupo09.goparty.R;
import cam.grupo09.goparty.backGround.ConsultaWEB;
import cam.grupo09.goparty.backGround.WebListenerQuery;


import cam.grupo09.goparty.mundo.GoPartY;
import cam.grupo09.goparty.persistenciaORMModelos.Establecimiento;
import cam.grupo09.goparty.persistenciaORMModelos.EstablecimientoBebida;
import cam.grupo09.goparty.persistenciaORMModelos.EstablecimientoMusica;
import cam.grupo09.goparty.persistenciaORMModelos.Evento;
import cam.grupo09.goparty.persistenciaORMModelos.Invitacion;
import cam.grupo09.goparty.persistenciaORMModelos.Opcion;
import cam.grupo09.goparty.persistenciaORMModelos.TipoBebida;
import cam.grupo09.goparty.persistenciaORMModelos.TipoMusica;
import cam.grupo09.goparty.persistenciaORMModelos.Usuario;

public class MainActivity extends AppCompatActivity implements WebListenerQuery
{

    public final static String PREFERENCES_NAME = "Preferences";
    public final static String FIRST_TIME_VALUE = "FirstRun";
    public final static String NUMBER_VALUE = "NumberValue";
    public final static String NAME_VALUE = "NameValue";
    public final static String EMAIL_VALUE = "EmavilValue";


    public final static String QUERY_REGISTER = "Query register";
    public final static String QUERY_ESTABLECIMIENTOS = "Query establecimientos";


    public final static String QUERY_ACTUALIZAR = "ACTUALIZAR::";
    public final static String QUERY_UPLOAD_EVENT = "UPLOAD::";

    public static List<EventoDTO> sinReportar;
    public static EventoDTO actual;

    public static SharedPreferences sharedPreferences;

    private ListView lstEventos;
    private TextView lblEventos;
    private ListView lstEventosSin;
    private TextView lblEventosSin;

    public void guardarTodo(View view)
    {
        GoPartY.getManejadorPersistencia().guardarInfo(sinReportar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);


        if(sharedPreferences == null)
        {
            Context context = getBaseContext();
            sharedPreferences = context.getSharedPreferences(
                    FIRST_TIME_VALUE, Context.MODE_PRIVATE
            );
        }
        if(sharedPreferences.getBoolean(FIRST_TIME_VALUE,true))
        {
            startActivity(new Intent(this, RegistroNuemeroActivity.class));
        }
        else
        {
            String numero = sharedPreferences.getString(NUMBER_VALUE, "00000000000");
            new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/users/usuarios/"+numero,"GET",this,QUERY_REGISTER ).execute();

        }


        sinReportar = GoPartY.getManejadorPersistencia().cargarInfor();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lstEventos = (ListView)findViewById(R.id.lstEventos);
        lblEventos = (TextView)findViewById(R.id.lblEventos);
        lstEventosSin  = (ListView)findViewById(R.id.lstEvSinReportar);
        lblEventosSin = (TextView)findViewById(R.id.txtSinReportar);
        GoPartY.getManejadorPersistencia().setPath(getFilesDir());
        GoPartY.getManejadorPersistencia().cargarInfor();

        actualizarEventos();

    }


    public void revisarEventos(View view   )
    {
        String numero = sharedPreferences.getString(NUMBER_VALUE, "00000000000");
        ConsultaWEB consultaWEB = new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/users/evento/"+numero,"GET",this,"EVENTOS" );
//        consultaWEB.execute();
    }

    public static void guardarEventoActual()
    {
        sinReportar.add(actual);
        actual = null ;
    }


    boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }

    public void reportarEvento(View view)
    {
        for(int i = 0; i < sinReportar.size(); i++)
            if(isNetworkConnectionAvailable())
            {
                try
                {
                    ObjectMapper map = new ObjectMapper();
                    String st= map.writeValueAsString(sinReportar.get(i));
                    Log.i("---- EVENTO -- " , st);
                    new ConsultaWEB(new JSONObject(st),
                                "https://gopartyserver.herokuapp.com/users/evento","POST",this,QUERY_UPLOAD_EVENT + sinReportar.get(i).getId() ).execute();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
    }

    public void actualizarBD(View view)
    {
        String numero = sharedPreferences.getString(NUMBER_VALUE, "00000000000");
        new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/query/establecimiento", "GET", this, QUERY_ACTUALIZAR + "establecimiento" ).execute();
        new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/query/establecimientoBebida", "GET", this, QUERY_ACTUALIZAR + "establecimientoBebida" ).execute();
        new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/query/establecimientoMusica", "GET", this, QUERY_ACTUALIZAR + "establecimientoMusica" ).execute();
        new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/users/eventoInvitados/"+ numero, "GET", this, QUERY_ACTUALIZAR + "evento" ).execute();
        new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/query/invitacion", "GET", this, QUERY_ACTUALIZAR + "invitacion" ).execute();
        new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/query/opcion", "GET", this, QUERY_ACTUALIZAR + "opcion" ).execute();
        new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/query/tipobebida", "GET", this, QUERY_ACTUALIZAR + "tipobebida" ).execute();
        new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/query/tipomusica", "GET", this, QUERY_ACTUALIZAR + "tipomusica" ).execute();
        new ConsultaWEB(null,"https://gopartyserver.herokuapp.com/query/usuario", "GET", this, QUERY_ACTUALIZAR + "usuario" ).execute();
    }

    public void actualizarEventos()
    {
        Log.i("Actualizando vista --","" +  Evento.listAll(Evento.class).size());
        ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(this, R.layout.lista_item, R.id.label, Evento.listAll(Evento.class));
        lstEventos.setAdapter(adapter);
        int num=  Evento.listAll(Evento.class).size();
        if (num == 0)
            lblEventos.setText("No tienes eventos =(");
        else
            lblEventos.setText("Tienes " + num + " eventos");


        ArrayAdapter<EventoDTO> adapter2 = new ArrayAdapter<EventoDTO>(this, R.layout.lista_item, R.id.label, sinReportar);
        lstEventosSin.setAdapter(adapter2);
        int num2= sinReportar.size();
        if (num == 0)
            lblEventosSin.setText("Todos tus eventos han sido sincronizados, no hay pendientes");
        else
            lblEventosSin.setText("Tienes " + num + " eventos pedientes por sincronizar");
    }

    @Override
    protected void onResume() {

        super.onResume();
       // actualizarEventos();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void crearEvento(View v)
    {
        actual = new EventoDTO();
        String nombre = sharedPreferences.getString(NAME_VALUE, "00000000000");
        String correo = sharedPreferences.getString(EMAIL_VALUE, "00000000000");
        String numero = sharedPreferences.getString(NUMBER_VALUE, "00000000000");
        UsuarioDTO admin = new UsuarioDTO();
        admin.setNombre(nombre);
        admin.setCelular(numero);
        admin.setCorreo(correo);
        actual.setAdmin(admin);
        Random rn = new Random();
        actual.setId(rn.nextLong());
        startActivity(new Intent(this, CrearEventoActivity.class));
    }



    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        SugarContext.terminate();
        GoPartY.getManejadorPersistencia().guardarInfo(sinReportar);

    }

    @Override
    public synchronized void receive(JSONArray response, String query)
    {
        if(query.equalsIgnoreCase(QUERY_ESTABLECIMIENTOS)) {
            List<EventoDTO> eventos = new ArrayList<EventoDTO>();
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject actual = response.getJSONObject(i);
                    Log.i("Actualizando eventos  ", actual.toString());
                    EventoDTO nuevo = new EventoDTO();
                    nuevo.setDescripcion(actual.getString("descripcion"));
                    nuevo.setNombre(actual.getString("nombre"));
                    nuevo.setEstado(actual.getString("estado"));
                    JSONObject admin = actual.getJSONObject("admin");
                    UsuarioDTO adminUsuario = new UsuarioDTO();
                    adminUsuario.setCelular(admin.getString("celular"));
                    nuevo.setAdmin(adminUsuario);
                    eventos.add(nuevo);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            //if (!eventos.isEmpty())
              //  GoPartY.getInstance().getPersistenceManager().actuaizarTabla(eventos);

            actualizarEventos();
        }
        else if(query.equalsIgnoreCase(QUERY_REGISTER))
        {
            Log.i("BUSCANDO REGISTRO" , "Pre");
            if (response == null || response.length() == 0)
            {
                String nombre = sharedPreferences.getString(NAME_VALUE, "00000000000");
                String correo = sharedPreferences.getString(EMAIL_VALUE, "00000000000");
                String numero = sharedPreferences.getString(NUMBER_VALUE, "00000000000");
                try {
                    JSONObject data = new JSONObject();
                    data.put("nombre", nombre);
                    data.put("correo", correo);
                    data.put("celular", numero);
                    Log.i("BUSCANDO REGISTRO", "Registro no encontrado");
                    new ConsultaWEB(data, "https://gopartyserver.herokuapp.com/users/usuarios", "POST", this, QUERY_REGISTER).execute();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
            else
            {
                Log.i("Buscando registro", "Registro encontrado");
            }
        }
        else if (query.startsWith(QUERY_ACTUALIZAR))
        {
            try {

                ObjectMapper map = new ObjectMapper();
                String tabla = query.split("::")[1];
                if (tabla.equals("establecimiento"))
                {
                    Establecimiento.deleteAll(Establecimiento.class);
                    for (int i = 0; i < response.length(); i++)
                    {
                        Establecimiento establecimiento = map.readValue(response.get(i).toString(),Establecimiento.class);
                        establecimiento.save();
                    }
                    Log.i("GUARDANDO ESTABS ", Establecimiento.listAll(Establecimiento.class).size() + "");
                }
                else if (tabla.equals("establecimientoBebida"))
                {
                    EstablecimientoBebida.deleteAll(EstablecimientoBebida.class);
                    for (int i = 0; i < response.length(); i++)
                    {
                        EstablecimientoBebida establecimiento = map.readValue(response.get(i).toString(),EstablecimientoBebida.class);
                        establecimiento.save();
                    }
                }
                else if (tabla.equals("establecimientoMusica"))
                {
                    EstablecimientoMusica.deleteAll(EstablecimientoMusica.class);
                    for (int i = 0; i < response.length(); i++)
                    {
                        EstablecimientoMusica establecimiento = map.readValue(response.get(i).toString(),EstablecimientoMusica.class);
                        establecimiento.save();
                    }
                }
                else if (tabla.equals("evento"))
                {
                    Evento.deleteAll(Evento.class);
                    for (int i = 0; i < response.length(); i++)
                    {
                        Evento establecimiento = map.readValue(response.get(i).toString(),Evento.class);
                        establecimiento.save();
                    }
                    actualizarEventos();
                }
                else if (tabla.equals("invitacion"))
                {
                    Invitacion.deleteAll(Invitacion.class);
                    for (int i = 0; i < response.length(); i++)
                    {
                        Invitacion establecimiento = map.readValue(response.get(i).toString(),Invitacion.class);
                        establecimiento.save();
                    }
                }
                else if (tabla.equals("opcion"))
                {
                    Opcion.deleteAll(Opcion.class);
                    for (int i = 0; i < response.length(); i++)
                    {
                        Opcion establecimiento = map.readValue(response.get(i).toString(),Opcion.class);
                        establecimiento.save();
                    }
                }
                else if (tabla.equals("tipobebida"))
                {
                    TipoBebida.deleteAll(TipoBebida.class);
                    for (int i = 0; i < response.length(); i++)
                    {
                        TipoBebida establecimiento = map.readValue(response.get(i).toString(),TipoBebida.class);
                        establecimiento.save();
                    }
                }
                else if (tabla.equals("tipomusica"))
                {
                    TipoMusica.deleteAll(TipoMusica.class);
                    for (int i = 0; i < response.length(); i++)
                    {
                        TipoMusica establecimiento = map.readValue(response.get(i).toString(),TipoMusica.class);
                        establecimiento.save();
                    }
                }
                else if (tabla.equals("usuario"))
                {
                    Usuario.deleteAll(Usuario.class);
                    for (int i = 0; i < response.length(); i++)
                    {
                        Usuario establecimiento = map.readValue(response.get(i).toString(),Usuario.class);
                        establecimiento.save();
                    }
                }


            }
            catch(Exception e)
            {
                e.printStackTrace();
            }



        }
        else if (query.startsWith(QUERY_UPLOAD_EVENT))
        {
            long id = Long.parseLong(query.split("::")[1]);
            for(int i = 0 ; i < sinReportar.size(); i++)
            {
                if(sinReportar.get(i).getId() == id) {
                    sinReportar.remove(i);
                    break;
                }
            }
        }
    }
}
