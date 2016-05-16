package cam.grupo09.goparty.persistenciaORMModelos;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Felipe on 15/05/2016.
 */
public class EstablecimientoMusica extends SugarRecord
{

    private Long id;

    private Long idEstablecimiento;

    private String idMusica;

    public EstablecimientoMusica()
    {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Long idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(String idMusica) {
        this.idMusica = idMusica;
    }




}
