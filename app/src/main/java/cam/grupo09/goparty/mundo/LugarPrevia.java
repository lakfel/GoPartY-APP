package cam.grupo09.goparty.mundo;

import java.io.Serializable;

/**
 * Created by Felipe on 02/03/2016.
 * Define donde será el lugar de la previa.
 */
public class LugarPrevia implements Serializable
{
    private static final long serialVersionUID = 0L;
    /**
     * El nombre del lugar
     */
    private String nombre;

    /**
     * Dirección
     */
    private String direccion;
    //TODO Cambiar o agregar ubicación GPS.

    /**
     * Comentarios o indiaciones.
     */
    private String comentarios;

    /**
     * posible codigo de contacto de la duena
     */
    private String codigoDuena;


    public LugarPrevia(String nombre, String direccion, String comentarios)
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.comentarios = comentarios;
        this.codigoDuena = "";
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public String getCodigoDuena() {
        return codigoDuena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setCodigoDuena(String codigoDuena) {
        this.codigoDuena = codigoDuena;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
