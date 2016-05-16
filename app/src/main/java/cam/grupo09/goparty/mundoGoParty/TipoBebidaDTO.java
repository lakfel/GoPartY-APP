package cam.grupo09.goparty.mundoGoParty;

import java.util.List;

/**
 * Created by Felipe on 17/04/2016.
 */
public class TipoBebidaDTO
{




    private String tipo;

    private List<EstablecimientoDTO> establecimientos;



    public TipoBebidaDTO() {
    }



    public TipoBebidaDTO(String tipo) {
        this.tipo = tipo;
    }

    public List<EstablecimientoDTO> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<EstablecimientoDTO> establecimientos) {
        this.establecimientos = establecimientos;
    }

    private List<EventoDTO> eventos;

    public List<EventoDTO> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoDTO> eventos) {
        this.eventos = eventos;
    }



    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}
