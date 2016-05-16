package cam.grupo09.goparty.mundoGoParty;

import java.util.List;

/**
 * Created by Felipe on 17/04/2016.
 */
public class TipoMusicaDTO
{




    private String tipo;

    private List<EstablecimientoDTO> establecimientos;

    public List<EstablecimientoDTO> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<EstablecimientoDTO> establecimientos) {
        this.establecimientos = establecimientos;
    }

    public TipoMusicaDTO() {
    }

    public TipoMusicaDTO(String tipo) {
        this.tipo = tipo;
    }




    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}
