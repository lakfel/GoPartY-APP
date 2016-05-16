package cam.grupo09.goparty.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cam.grupo09.goparty.Enumerables.TipoBebida;
import cam.grupo09.goparty.mundo.Establecimiento;
import cam.grupo09.goparty.mundo.Evento;
import cam.grupo09.goparty.mundoGoParty.EstablecimientoDTO;
import cam.grupo09.goparty.mundoGoParty.EventoDTO;
import cam.grupo09.goparty.mundoGoParty.TipoBebidaDTO;
import cam.grupo09.goparty.mundoGoParty.TipoMusicaDTO;

/**
 * Created by Felipe on 08/04/2016.
 */
public class PersistenceManager extends SQLiteOpenHelper
{
    public final static String BD_NAME = "GoParty_BD";
    public final static int BD_VERSION = 2;

    public PersistenceManager(Context context)
    {
        super(context, BD_NAME, null, BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(BDConstants.CREATE_EVENTOS_SCRIPT);
        db.execSQL(BDConstants.CREATE_ESTABLECIMIENTOS_SCRIPT);
        db.execSQL(BDConstants.CREATE_TIPO_BEBIDA_SCRIPT);
        db.execSQL(BDConstants.CREATE_TIPO_MUSICA_SCRIPT);
        db.execSQL(BDConstants.CREATE_USUARIO_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {


       // File n = new File(db.getPath());
       // n.delete();
       // onCreate(db);
        //Actualizar la base de datos
    }

    public void insertarObjeto(Object t)
    {
        String sqlSentence="";
        if (t instanceof EventoDTO)
        {
            EventoDTO e = (EventoDTO)t;
            String nombre = (e.getNombre()== null)?"":e.getNombre();
            String descripcion = (e.getDescripcion() == null)?"":e.getDescripcion();
            String fechaSeleccionada = (e.getFecha()==null)?"":e.getFecha().toString();
            String lugarSeleccionado = (e.getLugarPrevia()==null)?"":e.getLugarPrevia().toString();
            String horaPrevia = (e.getHoraPrevia()==null)?"":e.getHoraPrevia();
            String horaLlegada = (e.getHoraLlegada() == null)?"":e.getHoraLlegada();
            long establecimientoID = (e.getEstablecimiento() == null)?0:e.getEstablecimiento().getId();
            String estado = (e.getEstado() == null)?"":e.getEstado().toString();

            sqlSentence = "INSERT INTO " + BDConstants.EVENTOS_TABLE_NAME + " values("+
                            "null," +
                            "'"+nombre+ "'," +
                            "'"+descripcion + "'," +
                            "'"+fechaSeleccionada.toString()+"'," +
                            "'"+lugarSeleccionado+"'," +
                            "'"+horaPrevia+"'," +
                            "'"+horaLlegada+"'," +
                            ""+establecimientoID +"," +
                            "'"+estado + "')";
        }
        else if(t instanceof EstablecimientoDTO)
        {
            EstablecimientoDTO e = (EstablecimientoDTO)t;
            Long id = e.getId();
            String nombre = (e.getNombre()==null)?"":e.getNombre();
            String descripcion = (e.getDescripcion()==null)?"":e.getDescripcion();
            String deireccion = (e.getDireccion()==null)?"":e.getDireccion();
            String url = (e.getUrlPagina()==null)?"":e.getUrlPagina();
            double costo = e.getCostoCover();
            sqlSentence = "INSERT INTO " + BDConstants.ESTABLECIMIENTOS_TABLE_NAME + " values("+
                    "id," +
                    "'"+nombre+ "'," +
                    "'"+descripcion + "'," +
                    "'"+deireccion+"'," +
                    "'"+url+"'," +
                    ""+costo+ "')";
        }
        else if(t instanceof TipoBebidaDTO)
        {
            TipoBebidaDTO e = (TipoBebidaDTO)t;
            String id = (e.getTipo()== null)?"":e.getTipo();

            sqlSentence = "INSERT INTO " + BDConstants.TIPO_BEBIDA_TABLE_NAME + " values("+
                    "'" + id + "')";
        }
        else if(t instanceof TipoMusicaDTO)
        {
            TipoMusicaDTO e = (TipoMusicaDTO)t;
            String id = (e.getTipo()== null)?"":e.getTipo();

            sqlSentence = "INSERT INTO " + BDConstants.TIPO_MUSICA_TABLE_NAME + " values("+
                    "'" + id + "')";
        }

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlSentence);
        db.close();
    }

    public void actuaizarTabla(List nuevaList)
    {
        SQLiteDatabase db = getWritableDatabase();
        Object t = nuevaList.get(0);
        String sqlTableNam ="";
        String sqlCreateSentence = "";
        if(t instanceof EstablecimientoDTO)
        {
            sqlTableNam = BDConstants.ESTABLECIMIENTOS_TABLE_NAME;
            sqlCreateSentence = BDConstants.CREATE_ESTABLECIMIENTOS_SCRIPT;
        }
        else if(t instanceof EventoDTO)
        {
            sqlTableNam = BDConstants.EVENTOS_TABLE_NAME;
            sqlCreateSentence= BDConstants.CREATE_EVENTOS_SCRIPT;
        }
        else if(t instanceof TipoBebidaDTO)
        {
            sqlTableNam = BDConstants.TIPO_BEBIDA_TABLE_NAME;
            sqlCreateSentence = BDConstants.CREATE_TIPO_BEBIDA_SCRIPT;
        }
        else if(t instanceof TipoMusicaDTO)
        {
            sqlTableNam = BDConstants.TIPO_MUSICA_TABLE_NAME;
            sqlCreateSentence = BDConstants.TIPO_MUSICA_TABLE_NAME;
        }
        db.execSQL("drop table if exists " + sqlTableNam );
        db.execSQL(sqlCreateSentence);
        for (int i = 0; i < nuevaList.size(); i++)
        {
            insertarObjeto(nuevaList.get(i));
        }
    }

    public List consultar(Object t)
    {
        List resp = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String stlSentence = "";
        Cursor c;
        if(t instanceof  Evento)
        {
            String type = BDConstants.EVENTOS_TABLE_NAME;
            c = db.query(
                    type,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            while(c.moveToNext())
            {
                Evento n = new Evento();

            }
        }
        else if(t instanceof  Establecimiento)
        {
            String type = BDConstants.EVENTOS_TABLE_NAME;
            c = db.query(
                    type,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }
        else if(t instanceof  TipoBebidaDTO)
        {
            String type = BDConstants.EVENTOS_TABLE_NAME;
            c = db.query(
                    type,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }
        else if(t instanceof  TipoMusicaDTO)
        {
            String type = BDConstants.EVENTOS_TABLE_NAME;
            c = db.query(
                    type,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }
        return resp;
    }


    public List<Evento> darEventos()
    {
        List<Evento> eventos = new ArrayList<Evento>();
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.query(
                BDConstants.EVENTOS_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while(c.moveToNext())
        {
            Evento ev= new Evento();
            ev.setNombreEvento(c.getString(c.getColumnIndex(BDConstants.EventosPropios.NOMBRE_EVENTO)));
            ev.setDescripcion(c.getString(c.getColumnIndex(BDConstants.EventosPropios.DESCRIPCION_EVENTO)));
            //ev.setEstablecimientoSeleccionado(c.getString(c.getColumnIndex(BDConstants.EventosPropios.FK_ESTABLECIMIENTO)));
            //ev.setEstado(new EstadoEvento(c.getString(c.getColumnIndex(BDConstants.EventosPropios.ESTADO))));
            //ev.setNombreEvento(c.getString(c.getColumnIndex(BDConstants.EventosPropios.NOMBRE_EVENTO)));
            //ev.setNombreEvento(c.getString(c.getColumnIndex(BDConstants.EventosPropios.NOMBRE_EVENTO)));
            //ev.setNombreEvento(c.getString(c.getColumnIndex(BDConstants.EventosPropios.NOMBRE_EVENTO)));

                    eventos.add(ev);
        }

        db.close();
        return eventos;

    }



}
