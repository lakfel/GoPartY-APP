package cam.grupo09.goparty.persistenciaORMModelos;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Felipe on 15/05/2016.
 */

public class Establecimiento extends SugarRecord
{


    private Long id;

    private String nombre;

    private String descripcion;

    private String direccion;

    private String urlPagina;

    private double costoCover;

    public Establecimiento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUrlPagina() {
        return urlPagina;
    }

    public void setUrlPagina(String urlPagina) {
        this.urlPagina = urlPagina;
    }

    public double getCostoCover() {
        return costoCover;
    }

    public void setCostoCover(double costoCover) {
        this.costoCover = costoCover;
    }

    @Override
    public String toString() {
        return nombre + " - " + descripcion;
    }
}
