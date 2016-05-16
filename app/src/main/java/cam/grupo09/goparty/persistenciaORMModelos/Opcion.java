package cam.grupo09.goparty.persistenciaORMModelos;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Felipe on 15/05/2016.
 */

public class Opcion extends SugarRecord
{

    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
