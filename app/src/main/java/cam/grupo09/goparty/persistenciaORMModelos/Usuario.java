package cam.grupo09.goparty.persistenciaORMModelos;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Felipe on 15/05/2016.
 */

public class Usuario extends SugarRecord
{
    private String celular;

    private String nombre;

    private String correo;


    public Usuario() {
    }


    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }



}
