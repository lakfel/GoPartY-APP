package cam.grupo09.goparty.mundoGoParty;

import java.util.List;

/**
 * Created by Felipe on 17/04/2016.
 */
public  class EstablecimientoDTO
{

    /**
     * Puede eventualmente agregar otras cosas como
     *  La carta (Imagen)
     *  Ubicacion GPS
     *  Un areglo de promociones
     */

    private long id;

    private String nombre;

    private String descripcion;

    private String direccion;

    private String urlPagina;

    private double costoCover;

    private List<TipoMusicaDTO> tiposMusica;
    private List<TipoBebidaDTO> tiposBebidas;

    public List<TipoBebidaDTO> getTiposBebidas() {
        return tiposBebidas;
    }

    public void setTiposBebidas(List<TipoBebidaDTO> tiposBebidas) {
        this.tiposBebidas = tiposBebidas;
    }


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TipoMusicaDTO> getTiposMusica() {
        return tiposMusica;
    }

    public void setTiposMusica(List<TipoMusicaDTO> tiposMusica) {
        this.tiposMusica = tiposMusica;
    }

    public EstablecimientoDTO() {
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



}
