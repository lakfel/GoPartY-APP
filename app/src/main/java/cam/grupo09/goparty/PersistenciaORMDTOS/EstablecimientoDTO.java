package cam.grupo09.goparty.PersistenciaORMDTOS;

import java.util.List;

/**
 * Created by Felipe on 16/05/2016.
 */
public class EstablecimientoDTO
{

    /**
     * Puede eventualmente agregar otras cosas como
     *  La carta (Imagen)
     *  Ubicacion GPS
     *  Un areglo de promociones
     */

    private Long id;

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

    public EstablecimientoDTO(String nombre, String descripcion, String direccion, String urlPagina, double costoCover, List<TipoMusicaDTO> tiposMusica, List<TipoBebidaDTO> tiposBebidas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.urlPagina = urlPagina;
        this.costoCover = costoCover;
        this.tiposMusica = tiposMusica;
        this.tiposBebidas = tiposBebidas;
    }


    public Long getId() {
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

    @Override
    public String toString() {
        return nombre + " - " + descripcion;
    }
}
