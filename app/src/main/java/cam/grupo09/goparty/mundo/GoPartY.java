package cam.grupo09.goparty.mundo;

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
        ArrayList<Establecimiento> busqueda = new ArrayList<Establecimiento>();
        ArrayList<Establecimiento> totales = manejadorPersistencia.getEstablecimientos();
        for (Establecimiento a:totales)
        {
            if((a.getNombre().contains(nombre)||nombre.trim().isEmpty())
                    && (a.getDescripcion().contains(descripcion)||descripcion.isEmpty())
                    && (a.getCostoCover() <= maxCover || maxCover ==0))
            {
                boolean sePuede1 = false;
                boolean sePuede2 = false;
                if(tb == null  || tb.equals(""))
                {
                    sePuede1 = true;
                }
                else
                {
                    ArrayList<TipoBebida> beb = a.getTipoBebidas();
                    for (int i = 0; i <beb.size() && !sePuede1; i++)
                    {
                        sePuede1 = (beb.get(i).equals(tb));
                    }
                }
                if(tm == null || tm.equals(""))
                {
                    sePuede2 = true;
                }
                else
                {
                    ArrayList<TipoMusica> mus = a.getTiposMusica();
                    for (int i = 0; i <mus.size() && !sePuede2; i++)
                    {
                        sePuede2 = (mus.get(i).equals(tm));
                    }
                }

                if(sePuede1 || sePuede2)
                {
                    busqueda.add(a);
                }
            }
        }
        return busqueda;
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
