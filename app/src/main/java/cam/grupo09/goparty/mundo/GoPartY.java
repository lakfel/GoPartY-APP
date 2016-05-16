package cam.grupo09.goparty.mundo;

import cam.grupo09.goparty.persistencia.ManejadorPersistencia;

/**
 * Created by Felipe on 29/02/2016.
 * Clase principal que maneja y administra el programa.
 */
public class GoPartY {

    private static GoPartY instance;
    private static ManejadorPersistencia manejadorPersistencia;


    public static GoPartY getInstance()
    {
        if(instance == null)
        {
            instance = new GoPartY();

        }
        return instance;
    }

    public static ManejadorPersistencia getManejadorPersistencia()
    {
        if(manejadorPersistencia == null)
        {

            manejadorPersistencia = new ManejadorPersistencia();
        }
        return manejadorPersistencia;
    }








}
