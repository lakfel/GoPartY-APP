package cam.grupo09.goparty.PersistenciaORMDTOS;

/**
 * Created by Felipe on 16/05/2016.
 */
public class OpcionesDTO
{

    public final static String OPC_FECHA = "OPC_FECHA";
    public final static String OPC_HORA_LL = "OPC_HORA_LL";
    public final static String OPC_HORA_SAL = "OPC_HORA_SAL";
    public final static String OPC_ESTABLECIMIENTO = "OPC_ESTABLECIMIENTO";
    public final static String OPC_LUGAR_PREVIA = "OPC_LUGAR_PREVIA";

    private String tipoOpcion;
    private String opcion;

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
    private int cantidadVotos;

    public OpcionesDTO() {
    }

    public String getTipoOpcion() {
        return tipoOpcion;
    }

    public void setTipoOpcion(String tipoOpcion) {
        this.tipoOpcion = tipoOpcion;
    }

    public int getCantidadVotos() {
        return cantidadVotos;
    }

    public void setCantidadVotos(int cantidadVotos) {
        this.cantidadVotos = cantidadVotos;
    }




}
