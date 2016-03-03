package cam.grupo09.goparty.Enumerables;

import java.io.Serializable;

/**
 * Created by Felipe on 29/02/2016.
 * Define los posibles estados de un evento.
 */
public enum EstadoEvento implements Serializable
{

    /*
        En configuración: El evento se creó, no se ha configurado completamente
        Configurado: Ya se definieron los diferentes parámetros del evento
        Anulado: El evento fue cancelado
        Finalizado: El evento ya pasó.
     */

    EN_CONFIGURACION("En Configuracion"),
    CONFIGURADO("Configurado"),
    ANULADO("Anulado"),
    FINALIZADO("Finalizado");

    private String valor;

    EstadoEvento(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
