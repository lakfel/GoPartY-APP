package cam.grupo09.goparty.Enumerables;

import java.io.Serializable;

/**
 * Created by Felipe on 29/02/2016.
 * Estado de las invitaciones.
 */
public enum EstadoInvitacion implements Serializable
{

    /*
        Sin enviar: Por alguna razón no se ha realizado un envío de la invitación. A la espera de enviarse
        Enviado: Se envió la invitación pero no se ha recibido respuesta
        Aceptado: Respuesta confirmada a la asistencia
        Tal vez: Respuesta en duda de la asistencia
        Rechazado: Respuesta rechazando la asistencia
     */

    SIN_ENVIAR("Sin enviar"),
    ENVIADO("Enviado"),
    ACEPTADO("Aceptado"),
    TAL_VEZ("Tal vez"),
    RECHAZADO("Rechazado");

    private String valor;

    EstadoInvitacion(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
