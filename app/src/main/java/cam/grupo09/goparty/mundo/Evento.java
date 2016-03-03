package cam.grupo09.goparty.mundo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Felipe on 29/02/2016.
 * Clase que representa un evento.
 */
public class Evento implements Serializable
{

    /**
     * Identificador del evento
     */
    private long id;

    /**
     * Nombre del evento
     */
    private String nombreEvento;

    /**
     * Fecha seleccionada para el evento.
     */
    private Date fechaSeleccionada ;

    /**
     * Lista de invitaciones al evento
     */
    private ArrayList<Invitacion> invitaciones;


    //TODO Realizar laopción de seleccionar varias fechas.
    /**
     * Posibilidades de fechas propuestas
     */
    private ArrayList<OpcionPropuesta<Date>> fechasPropuestas;

    /**
     * Lugares propuestos
     */
    private ArrayList<OpcionPropuesta<Establecimiento>> establecimientosPropuestos;

    public Evento()
    {
        invitaciones = new ArrayList<Invitacion>();
        establecimientosPropuestos = new ArrayList<OpcionPropuesta<Establecimiento>>();
    }

    public long getId() {
        return id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public ArrayList<Invitacion> getInvitaciones() {
        return invitaciones;
    }

    public ArrayList<OpcionPropuesta<Establecimiento>> getEstablecimientosPropuestos() {
        return establecimientosPropuestos;
    }
}
