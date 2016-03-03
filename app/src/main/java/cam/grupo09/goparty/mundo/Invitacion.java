package cam.grupo09.goparty.mundo;

import java.io.Serializable;

import cam.grupo09.goparty.Enumerables.EstadoInvitacion;

/**
 * Created by Felipe on 29/02/2016.
 * Representa la invitación hacia un usuario
 */
public class Invitacion implements Serializable
{

    /**
     * Información del usuario al que se le envía la invitación.
     */
    private String identificadorInvitado;

    private String nombre;

    /**
     * Representa el estado de la invitación.
     */
    private Enum<EstadoInvitacion> estado;

    public Invitacion(String invitado, String nombre)
    {
        this.identificadorInvitado = invitado;
        this.nombre = nombre;
        estado = EstadoInvitacion.SIN_ENVIAR;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificadorInvitado() {
        return identificadorInvitado;
    }

    public void setIdentificadorInvitado(String identificadorInvitado) {
        this.identificadorInvitado = identificadorInvitado;
    }

    public void setEstado(Enum<EstadoInvitacion> estado) {
        this.estado = estado;
    }

    public Enum<EstadoInvitacion> getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
