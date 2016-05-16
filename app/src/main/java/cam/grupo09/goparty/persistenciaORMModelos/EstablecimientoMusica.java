package cam.grupo09.goparty.persistenciaORMModelos;

import com.orm.SugarRecord;

/**
 * Created by Felipe on 15/05/2016.
 */
public class EstablecimientoMusica extends SugarRecord
{

    private Long idEstabMus;

    private Long idEstablecimiento;

    private String idMusica;

    public EstablecimientoMusica()
    {

    }


    public Long getIdEstabMus() {
        return idEstabMus;
    }

    public void setIdEstabMus(Long id) {
        this.idEstabMus = id;
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
