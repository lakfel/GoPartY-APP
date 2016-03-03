package cam.grupo09.goparty.mundo;

import java.io.Serializable;

/**
 * Created by Felipe on 29/02/2016.
 * Clase que representa una opción propuesta de manera genérica. Es decir para cualquier tipo de dato.
 */
public class OpcionPropuesta <T > implements Serializable
{
    private static final long serialVersionUID = 0L;
    /**
     * Cantidad de votos a favor
     */
    private int cantidadVotos;
    /**
     * Elemento de la opcion;
     */
    private T opcion;


    /**
     * Crea una nueva opcion
      * @param opcion La opcion de la que hace parte
     */
    public OpcionPropuesta(T opcion)
    {
        this.opcion = opcion;
        cantidadVotos = 0;
    }

    public int getCantidadVotos()
    {
        return cantidadVotos;
    }

    public T getOpcion()
    {
        return opcion;
    }

    public void votar()
    {
        cantidadVotos++;
    }

    public void modificarVotos(int nVotos)
    {
        cantidadVotos = nVotos;
    }



    @Override
    public String toString() {
        return opcion.toString();
    }
}
