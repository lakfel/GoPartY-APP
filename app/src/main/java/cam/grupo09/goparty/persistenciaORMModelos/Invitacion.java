package cam.grupo09.goparty.persistenciaORMModelos;

import com.orm.SugarRecord;

/**
 * Created by Felipe on 15/05/2016.
 */

public class Invitacion extends SugarRecord
{


    private Long idInvitacion;

    private Long idEvento;

    private String idUsuario;


    public Long getIdInvitacion() {
        return idInvitacion;
    }

    public void setIdInvitacion(Long id) {
        this.idInvitacion = id;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return idEvento + " - " + idUsuario;
    }
}


