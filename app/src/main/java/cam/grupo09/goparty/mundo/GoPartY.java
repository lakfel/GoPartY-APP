package cam.grupo09.goparty.mundo;

import java.util.ArrayList;

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

    public void crearEvento()
    {
        if(eventoActual != null)
        {
            manejadorPersistencia.getEventos().add(eventoActual);
            eventoActual = null;
        }
    }
}
