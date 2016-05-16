package cam.grupo09.goparty.persistenciaORMModelos;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Felipe on 15/05/2016.
 */

public class TipoMusica extends SugarRecord
{

    private String tipo;

    public TipoMusica() {
    }

    public TipoMusica(String tipo) {
        this.tipo = tipo;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}
