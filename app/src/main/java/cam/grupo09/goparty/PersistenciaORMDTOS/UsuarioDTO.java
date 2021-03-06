package cam.grupo09.goparty.PersistenciaORMDTOS;

/**
 * Created by Felipe on 16/05/2016.
 */
public class UsuarioDTO
{

    private String celular;



    private String nombre;

    private String correo;

    public UsuarioDTO() {
    }



    public UsuarioDTO(String correo) {
        this.correo = correo;
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

    @Override
    public String toString() {
        return nombre + " - " + celular;
    }
}
