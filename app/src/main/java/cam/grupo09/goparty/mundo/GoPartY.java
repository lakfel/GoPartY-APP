package cam.grupo09.goparty.mundo;

import android.util.Log;

import java.util.ArrayList;

import cam.grupo09.goparty.Enumerables.TipoBebida;
import cam.grupo09.goparty.Enumerables.TipoMusica;
import cam.grupo09.goparty.persistencia.ManejadorPersistencia;

/**
 * Created by Felipe on 29/02/2016.
 * Clase principal que maneja y administra el programa.
 */
public class GoPartY
{

    private static GoPartY instance;


    private ManejadorPersistencia manejadorPersistencia;
    private Evento eventoActual;


    public static GoPartY getInstance()
    {
        if(instance == null)
        {
            instance = new GoPartY();
        }
        return instance;
    }


    public void empezarEventoActivity()
    {
        eventoActual = new Evento();
    }

    public GoPartY()
    {
        manejadorPersistencia = new ManejadorPersistencia();
    }

    public ManejadorPersistencia getManejadorPersistencia()
    {
        return manejadorPersistencia;
    }

    public Evento getEventoActual()
    {
        return eventoActual;
    }

    public void setEventoActual(Evento eventoActual)
    {
        this.eventoActual = eventoActual;
    }

    public ArrayList<Establecimiento> darBusquedaFiltrada(String nombre,
                                                          String descripcion, String tm, String tb,
                                                          double maxCover)
    {

        Log.d("BUSQUEDA  -  Nombre :" ,nombre);
        Log.d("BUSQUEDA  -  Des :" ,descripcion);
        Log.d("BUSQUEDA  -  TMu :" ,tm);
        Log.d("BUSQUEDA  -  TB :" ,tb);

        ArrayList<Establecimiento> busqueda = new ArrayList<Establecimiento>();
        ArrayList<Establecimiento> totales = manejadorPersistencia.getEstablecimientos();
        for (Establecimiento a:totales)
        {
            Log.d("BUSQUEDA -- ",a.getNombre());
            Log.d("BUSQUEDA", "Nombre Vacio : " + nombre.trim().isEmpty());
            Log.d("BUSQUEDA", "Nombre Desc : " + descripcion.trim().isEmpty());
            if((a.getNombre().contains(nombre)||nombre.trim().isEmpty())
                    && (a.getDescripcion().contains(descripcion)||descripcion.trim().isEmpty())
                    && (a.getCostoCover() <= maxCover || maxCover ==0))
            {
                Log.d("BUSQUEDA", "Paso primer filtro");
                boolean sePuede1 = false;
                boolean sePuede2 = false;
                if(tb == null  || tb.equals(""))
                {
                    sePuede1 = true;
                }
                else
                {
                    Log.d("BUSQUEDA  1", "TiposBebida E - " + tb);
                    ArrayList<TipoBebida> beb = a.getTipoBebidas();
                    for (int i = 0; i <beb.size() && !sePuede1; i++)
                    {

                        Log.d("BUSQUEDA  1", "TipoB bus - " + beb.get(i) +"-"+ tb);
                        Log.d("BUSQUEDA  1", "Iguales " +(beb.get(i).toString().equals(tb.toString())) );
                        sePuede1 = (beb.get(i).toString().equals(tb.toString())) ;
                    }
                }
                if(tm == null || tm.equals(""))
                {
                    sePuede2 = true;
                }
                else
                {
                    Log.d("BUSQUEDA  2", "TiposMUS - " + tm);
                    ArrayList<TipoMusica> mus = a.getTiposMusica();
                    for (int i = 0; i <mus.size() && !sePuede2; i++)
                    {
                        Log.d("BUSQUEDA  2", "TipoM bus - " + mus.get(i)+"-"+ tb);
                        Log.d("BUSQUEDA  2", "Iguales -" +(mus.get(i).equals(tm)) );
                        sePuede2 = (mus.get(i).toString().equals(tm.toString()));
                    }
                }

                if(sePuede1 || sePuede2)
                {
                    Log.d("BUSQUEDA", "Paso segundo filtro");
                    busqueda.add(a);
                }
            }
        }
        return busqueda;
    }

    public Establecimiento darEtablecimientoNombre(String nombre)
    {
        return manejadorPersistencia.darEtablecimientoNombre(nombre);
    }

    public void crearEvento()
    {
        if(eventoActual != null)
        {
            manejadorPersistencia.getEventos().add(eventoActual);
            eventoActual = null;
        }
    }


}
