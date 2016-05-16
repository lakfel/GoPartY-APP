package cam.grupo09.goparty.persistenciaORMModelos;

import com.orm.SugarRecord;

/**
 * Created by Felipe on 15/05/2016.
 */

public class Opcion extends SugarRecord
{

    private Long idOpcion;

    private Long idEvento;

    private String idTipoOpcion;

    private String opcion;

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }


    private int votos;

    public Long getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Long id) {
        this.idOpcion = id;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdTipoOpcion() {
        return idTipoOpcion;
    }

    public void setIdTipoOpcion(String idTipoOpcion) {
        this.idTipoOpcion = idTipoOpcion;
    }



    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }


    @Override
    public String toString() {
        return idTipoOpcion + " - " + opcion +  " - " + votos;
    }



}
