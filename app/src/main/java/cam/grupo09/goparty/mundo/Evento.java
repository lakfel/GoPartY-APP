package cam.grupo09.goparty.mundo;

import android.util.Log;

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

    private LugarPrevia lugarSelecciondo;

    private String horaPreviaSeleccionada;

    private String horaSalidaSeleccionada;

    private Establecimiento establecimientoSeleccionado;

    /**
     * Lista de invitaciones al evento
     */
    private ArrayList<Invitacion> invitaciones;


    //TODO Realizar laopcin de seleccionar varias fechas.


    // Alternativas
    /**
     * Posibilidades de fechas propuestas
     */
    private ArrayList<OpcionPropuesta<Date>> fechasPropuestas;

    //Previas
    private ArrayList<OpcionPropuesta<LugarPrevia>> lugaresPropuestos;
    private ArrayList<OpcionPropuesta<String>>  horasPropuestasPrevia;

    /**
     * Lugares propuestos
     */
    private ArrayList<OpcionPropuesta<Establecimiento>> establecimientosPropuestos;
    private ArrayList<OpcionPropuesta<String>>  horasPropuestasSalida;


    public Evento()
    {
        invitaciones = new ArrayList<Invitacion>();
        fechasPropuestas = new ArrayList<OpcionPropuesta<Date>>();
        lugaresPropuestos = new ArrayList<OpcionPropuesta<LugarPrevia>>();
        horasPropuestasPrevia = new ArrayList<OpcionPropuesta<String>>();
        establecimientosPropuestos = new ArrayList<OpcionPropuesta<Establecimiento>>();
        horasPropuestasSalida = new ArrayList<OpcionPropuesta<String>>();

    }


    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(Date fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    public LugarPrevia getLugarSelecciondo() {
        return lugarSelecciondo;
    }

    public void setLugarSelecciondo(LugarPrevia lugarSelecciondo) {
        this.lugarSelecciondo = lugarSelecciondo;
    }

    public String getHoraPreviaSeleccionada() {
        return horaPreviaSeleccionada;
    }

    public void setHoraPreviaSeleccionada(String horaPreviaSeleccionada) {
        this.horaPreviaSeleccionada = horaPreviaSeleccionada;
    }

    public String getHoraSalidaSeleccionada() {
        return horaSalidaSeleccionada;
    }

    public void setHoraSalidaSeleccionada(String horaSalidaSeleccionada) {
        this.horaSalidaSeleccionada = horaSalidaSeleccionada;
    }

    public Establecimiento getEstablecimientoSeleccionado() {
        return establecimientoSeleccionado;
    }

    public void setEstablecimientoSeleccionado(Establecimiento establecimientoSeleccionado) {
        this.establecimientoSeleccionado = establecimientoSeleccionado;
    }

    public ArrayList<Invitacion> getInvitaciones() {
        return invitaciones;
    }

    public void setInvitaciones(ArrayList<Invitacion> invitaciones) {
        this.invitaciones = invitaciones;
    }

    public ArrayList<OpcionPropuesta<Date>> getFechasPropuestas() {
        return fechasPropuestas;
    }

    public void setFechasPropuestas(ArrayList<OpcionPropuesta<Date>> fechasPropuestas) {
        this.fechasPropuestas = fechasPropuestas;
    }

    public ArrayList<OpcionPropuesta<LugarPrevia>> getLugaresPropuestos() {
        return lugaresPropuestos;
    }

    public void setLugaresPropuestos(ArrayList<OpcionPropuesta<LugarPrevia>> lugaresPropuestos) {
        this.lugaresPropuestos = lugaresPropuestos;
    }

    public ArrayList<OpcionPropuesta<String>> getHorasPropuestasPrevia() {
        return horasPropuestasPrevia;
    }

    public void setHorasPropuestasPrevia(ArrayList<OpcionPropuesta<String>> horasPropuestasPrevia) {
        this.horasPropuestasPrevia = horasPropuestasPrevia;
    }

    public ArrayList<OpcionPropuesta<Establecimiento>> getEstablecimientosPropuestos() {
        return establecimientosPropuestos;
    }

    public void setEstablecimientosPropuestos(ArrayList<OpcionPropuesta<Establecimiento>> establecimientosPropuestos) {
        this.establecimientosPropuestos = establecimientosPropuestos;
    }

    public ArrayList<OpcionPropuesta<String>> getHorasPropuestasSalida() {
        return horasPropuestasSalida;
    }

    public void setHorasPropuestasSalida(ArrayList<OpcionPropuesta<String>> horasPropuestasSalida) {
        this.horasPropuestasSalida = horasPropuestasSalida;
    }

    public Invitacion[] darInvitaciones()
    {
        Invitacion[] invis = new Invitacion[invitaciones.size()];
        for (int i = 0; i <invitaciones.size(); i++)
        {
            invis[i] = invitaciones.get(i);
        }
        return invis;
    }


    public OpcionPropuesta<Date>[] darFechasPrpoestas()
    {
        OpcionPropuesta<Date>[] fechs = new OpcionPropuesta[fechasPropuestas.size()];
        for (int i = 0; i <fechasPropuestas.size(); i++)
        {
            fechs[i] = fechasPropuestas.get(i);
        }
        return fechs;
    }

    public OpcionPropuesta<LugarPrevia>[] darLugaresPrpuestos()
    {
        OpcionPropuesta<LugarPrevia>[] lugs = new OpcionPropuesta[lugaresPropuestos.size()];
        for (int i = 0; i <lugaresPropuestos.size(); i++)
        {
            lugs[i] = lugaresPropuestos.get(i);
        }
        return lugs;
    }

    public OpcionPropuesta<String>[] darHorasPreviaPropuestas()
    {
        OpcionPropuesta<String>[] horsPrev = new OpcionPropuesta[horasPropuestasPrevia.size()];
        for (int i = 0; i <horasPropuestasPrevia.size(); i++)
        {
            horsPrev[i] = horasPropuestasPrevia.get(i);
        }
        return horsPrev;
    }
    public OpcionPropuesta<Establecimiento>[] darEstablecimientosPropuestos()
    {
        OpcionPropuesta<Establecimiento>[] estas = new OpcionPropuesta[establecimientosPropuestos.size()];
        for (int i = 0; i <establecimientosPropuestos.size(); i++)
        {
            Log.d("Establecimientos", "Agregando el establecimiento " + establecimientosPropuestos.get(i).toString());
            estas[i] = establecimientosPropuestos.get(i);
        }
        return estas;
    }

    public OpcionPropuesta<String>[] darHorasSalidaPropuestas()
    {

        OpcionPropuesta<String>[] horsSals = new OpcionPropuesta[horasPropuestasSalida.size()];
        for (int i = 0; i <horasPropuestasSalida.size(); i++)
        {
            horsSals[i] = horasPropuestasSalida.get(i);
        }
        return horsSals;
    }

        public boolean estaEstablecimento(String nombreEvento)
        {
            for (OpcionPropuesta<Establecimiento> a:establecimientosPropuestos)
            {
                if(a.getOpcion().getNombre().equals(nombreEvento))
                    return true;
            }
            return false;
        }

}
