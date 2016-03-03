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

    /**
     * Representa el estado de la invitación.
     */
    Enum<EstadoInvitacion> estado;

    public Invitacion(String invitado)
    {
        this.identificadorInvitado = invitado;
        estado = EstadoInvitacion.SIN_ENVIAR;
    }
}
