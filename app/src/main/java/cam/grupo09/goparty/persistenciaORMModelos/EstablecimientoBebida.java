package cam.grupo09.goparty.persistenciaORMModelos;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Felipe on 15/05/2016.
 */
public class EstablecimientoBebida extends SugarRecord
{


    private Long id;

    private Long idEstablecimiento;

    private String idBebida;

    public EstablecimientoBebida()
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

    public String getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(String idBebida) {
        this.idBebida = idBebida;
    }




}

