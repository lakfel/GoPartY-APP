package cam.grupo09.goparty.persistencia;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import cam.grupo09.goparty.PersistenciaORMDTOS.EventoDTO;
import cam.grupo09.goparty.persistenciaORMModelos.Evento;


/**
 * Created by Felipe on 02/03/2016.
 */
public class ManejadorPersistencia
{

    // A futuro se va a manejar con SQLite. Por ahora se hará con objetos serializables.
    private static final String NOMBRE_ARCHIVO =  "persistenciaGoP.data";
    private  File PATH_ARCHIVO;

    public void setPath(File pPath)
    {
        PATH_ARCHIVO = pPath;
    }




    public void guardarInfo(List<EventoDTO> sinReportar)
    {
        Log.d("ManejadorPersistencia","Guardando");
        try
        {
            File archivo = new File(PATH_ARCHIVO,NOMBRE_ARCHIVO);
            if(!archivo.exists())
                archivo.createNewFile();
            Log.d("ManejadorPersistencia","Escribiendo elementos" +PATH_ARCHIVO+"-"+NOMBRE_ARCHIVO );
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            Log.d("ManejadorPersistencia", "Eventos guardado");
                oos.writeObject(sinReportar);
            Log.d("ManejadorPersistencia", "Establecimientos guardado");
                oos.close();
            File ar2 = new File(PATH_ARCHIVO,NOMBRE_ARCHIVO);
            Log.d("Manejador", "Archivo existente : " + ar2.exists());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<EventoDTO> cargarInfor()
    {
        List<EventoDTO> sinReportar = new ArrayList<EventoDTO>();
        Log.d("ManejadorPersistencia", "Cargando");
        try
        {
            File archivo = new File(PATH_ARCHIVO, NOMBRE_ARCHIVO);
            Log.d("ManejadorPersistencia", "Consulta del archivo path : " +PATH_ARCHIVO+"-"+NOMBRE_ARCHIVO );
            if(archivo.exists())
            {
                Log.d("ManejadorPersistencia", "Archivo existe");
                Log.d("ManejadorPersistencia", "File " + PATH_ARCHIVO.exists());
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
                sinReportar =  (ArrayList)ois.readObject();
                ois.close();
            }
            else
            {
                Log.d("ManejadorPersistencia", "Archivo no existe");
                Log.d("ManejadorPersistencia", "File " + PATH_ARCHIVO.exists());



            }
        }
        catch (Exception e)
        {

            e.printStackTrace();
        }
        return sinReportar;
    }




}
