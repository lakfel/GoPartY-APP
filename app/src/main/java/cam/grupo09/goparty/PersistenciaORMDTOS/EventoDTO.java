package cam.grupo09.goparty.PersistenciaORMDTOS;

import java.util.Date;
import java.util.List;

/**
 * Created by Felipe on 16/05/2016.
 */
public class EventoDTO
{

    private Long id;

    private String nombre;

    private String descripcion;

    private Date fecha;

    private String lugarPrevia;

    private String horaPrevia;

    private String horaLlegada;

    private String estado;

    private TipoBebidaDTO tiposBebida;

    private EstablecimientoDTO establecimiento;

    private UsuarioDTO admin;

    private List<UsuarioDTO> invitaciones;

    private List<OpcionesDTO> opciones;




    public EventoDTO() {
    }

    public TipoBebidaDTO getTiposBebida() {
        return tiposBebida;
    }

    public void setTiposBebida(TipoBebidaDTO tiposBebida) {
        this.tiposBebida = tiposBebida;
    }

    public EstablecimientoDTO getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(EstablecimientoDTO establecimiento) {
        this.establecimiento = establecimiento;
    }

    public UsuarioDTO getAdmin() {
        return admin;
    }

    public void setAdmin(UsuarioDTO admin) {
        this.admin = admin;
    }

    public List<UsuarioDTO> getInvitaciones() {
        return invitaciones;
    }

    public void setInvitaciones(List<UsuarioDTO> invitaciones) {
        this.invitaciones = invitaciones;
    }

    public List<OpcionesDTO> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<OpcionesDTO> opciones) {
        this.opciones = opciones;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugarPrevia() {
        return lugarPrevia;
    }

    public void setLugarPrevia(String lugarPrevia) {
        this.lugarPrevia = lugarPrevia;
    }

    public String getHoraPrevia() {
        return horaPrevia;
    }

    public void setHoraPrevia(String horaPrevia) {
        this.horaPrevia = horaPrevia;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }






}
