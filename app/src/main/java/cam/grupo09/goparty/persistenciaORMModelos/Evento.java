package cam.grupo09.goparty.persistenciaORMModelos;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Felipe on 15/05/2016.
 */

public class Evento extends SugarRecord
{


    private Long idEvento;

    private String nombre;

    private String descripcion;

    private Date fecha;

    private String lugarPrevia;

    private String horaPrevia;

    private String horaLlegada;

    private String estado;

    private String admin;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }


    public Evento() {
    }

    public Long getId() {
        return idEvento;
    }

    public void setId(Long id) {
        this.idEvento = id;
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


    @Override
    public String toString() {
        return nombre + " - " + descripcion;
    }

}
