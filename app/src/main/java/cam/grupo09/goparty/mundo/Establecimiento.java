package cam.grupo09.goparty.mundo;

import java.io.Serializable;
import java.util.ArrayList;

import cam.grupo09.goparty.Enumerables.TipoBebida;
import cam.grupo09.goparty.Enumerables.TipoMusica;

/**
 * Created by Felipe on 29/02/2016.
 * Clase que modela un establecimiento en bogota.
 */
public class Establecimiento implements Serializable
{
    /**
     * Nombre del establecimiento
     */
    private String nombre;

    /**
     * Descripcion del establecimiento
     */
    private String descripcion;

    /**
     * Dirección del establacimiento*
     */
    private String direccion;

    /**
     * Lista de estilos de musica que se reproducen
     */
    private ArrayList<TipoMusica> tiposMusica;

    /**
     * Lista de bebidas que se distribuyen
     */
    private ArrayList<TipoBebida> tipoBebidas;

    /**
     * Costo cover
     */
    private double costoCover;

    /**
     * Url del bar
     */
    private String url;



    //TODO agregar fotos


    public Establecimiento(String nombre, String descripcion, String direccion,
                           ArrayList<TipoMusica> tiposMusica, ArrayList<TipoBebida> tipoBebidas, double costoCover, String url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.tiposMusica = tiposMusica;
        this.tipoBebidas = tipoBebidas;
        this.costoCover = costoCover;
        this.url = url;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public ArrayList<TipoMusica> getTiposMusica()
    {
        return tiposMusica;
    }

    public ArrayList<TipoBebida> getTipoBebidas()
    {
        return tipoBebidas;
    }

    public double getCostoCover()
    {
        return costoCover;
    }

    public String getUrl() {
        return url;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTiposMusica(ArrayList<TipoMusica> tiposMusica) {
        this.tiposMusica = tiposMusica;
    }

    public void setTipoBebidas(ArrayList<TipoBebida> tipoBebidas) {
        this.tipoBebidas = tipoBebidas;
    }

    public void setCostoCover(double costoCover) {
        this.costoCover = costoCover;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
