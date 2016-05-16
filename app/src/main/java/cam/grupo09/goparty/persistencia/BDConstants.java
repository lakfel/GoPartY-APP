package cam.grupo09.goparty.persistencia;

import android.provider.BaseColumns;

/**
 * Created by Felipe on 08/04/2016.
 */
public class BDConstants
{

    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -------------------------------------- TIPOS DE DATOS  ----------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------

    public final static String STRING_TYPE = "text";
    public final static String INT_TYPE = "integer";
    public final static String REAL_TYPE = "real";
    public final static String NUMERIC_TYPE= "numeric";

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------- CONF TABLAS  -----------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------


    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -------------------------- TABLA DE LOS ESTABLECIMIENTOS --------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------


    public final static String  ESTABLECIMIENTOS_TABLE_NAME = "establecimiento";

    public static class Establecimientos
    {
        public static final String ID = "id";
        public static final String NOMBRE_ESTABLECIMIENTO = "nombre";
        public static final String DESCRIPCION_ESTABLECIMIENTO = "descripcion";
        public static final String DIRECCION = "direccion";
        public static final String URL_PAGINA = "urlpagina";
        public static final String COVER = "costocover";
    }

    public static final String CREATE_ESTABLECIMIENTOS_SCRIPT =
            "create table if not exists " + ESTABLECIMIENTOS_TABLE_NAME + "(" +
                    Establecimientos.ID + " " + REAL_TYPE + " primary key , " +
                    Establecimientos.NOMBRE_ESTABLECIMIENTO + " " + STRING_TYPE + "," +
                    Establecimientos.DESCRIPCION_ESTABLECIMIENTO + " " + STRING_TYPE + "," +
                    Establecimientos.DIRECCION + " " + STRING_TYPE + "," +
                    Establecimientos.URL_PAGINA + " " + STRING_TYPE + "," +
                    Establecimientos.COVER + " " + REAL_TYPE + ")";

    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -------------------------- TABLA DE LAS OPCIONES ----------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    public final static String  OPCIONES_EVENTOS_TABLE_NAME = "opciones";

    public static class OPCIONES
    {
        public static final String ID = "id";
        public static final String ID_EVENTO = "idEvento";
        public static final String TIPO = "tipo";
        public static final String TIPO_ESTABLECIMIENTO = "establecimiento";
        public static final String TIPO_FECHA = "fecha";
        public static final String TIPO_HORA_LLEGADA = "hora llegada";
        public static final String TIPO_HORA_SALIDA = "hora salida";
        public static final String TIPO_BEBIDA = "bebida";
        public static final String TIPO_LUGAR_PREVIA = "lugar previa";
    }

    public static final String CREATE_OPCIONES_SCRIPT =
            "create table if not exists " + ESTABLECIMIENTOS_TABLE_NAME + "(" +
                    Establecimientos.ID + " " + REAL_TYPE + " primary key , " +
                    Establecimientos.NOMBRE_ESTABLECIMIENTO + " " + STRING_TYPE + "," +
                    Establecimientos.DESCRIPCION_ESTABLECIMIENTO + " " + STRING_TYPE + "," +
                    Establecimientos.DIRECCION + " " + STRING_TYPE + "," +
                    Establecimientos.URL_PAGINA + " " + STRING_TYPE + "," +
                    Establecimientos.COVER + " " + REAL_TYPE + ")";


    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -------------------------- TABLA DE LOS EVENTOS -----------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------



    public final static String EVENTOS_TABLE_NAME = "evento";
    //Tabla Eventos Propios
    public static class EventosPropios
    {
        public static final String ID = BaseColumns._ID;
        public static final String ID_OWNER = "id_owner" ;
        public static final String NOMBRE_EVENTO = "nombre";
        public static final String DESCRIPCION_EVENTO = "descripcion";
        public static final String FECHA_EVENTO = "dateE";
        public static final String LUGAR_PREVIA = "lugarPrevia";
        public static final String HORA_PREVIA = "horaPrevia";
        public static final String HORA_LLEGADA_EST = "horaLlegada";
        public static final String FK_ESTABLECIMIENTO = "fk_establecimiento";
        public static final String ESTADO = "estado";
    }
    public static final String CREATE_EVENTOS_SCRIPT =
            "create table if not exists " + EVENTOS_TABLE_NAME + "(" +
                    EventosPropios.ID + " " + INT_TYPE + " primary key autoincrement, " +
                    EventosPropios.NOMBRE_EVENTO + " " + STRING_TYPE + "," +
                    EventosPropios.DESCRIPCION_EVENTO + " " + STRING_TYPE + "," +
                    EventosPropios.FECHA_EVENTO + " " + STRING_TYPE + "," +
                    EventosPropios.LUGAR_PREVIA + " " + STRING_TYPE + "," +
                    EventosPropios.HORA_PREVIA + " " + STRING_TYPE + "," +
                    EventosPropios.HORA_LLEGADA_EST + " " + STRING_TYPE + "," +
                    EventosPropios.FK_ESTABLECIMIENTO + " " + STRING_TYPE + "," +
                    EventosPropios.ESTADO + " " + STRING_TYPE + ")";

    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -------------------------- TABLA DE LOS TIPO BEBIDA -----------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------



    public final static String TIPO_BEBIDA_TABLE_NAME = "tipobebida";
    //Tabla Eventos Propios
    public static class TipoBebida
    {
        public static final String ID = BaseColumns._ID;
        public static final String TIPO = "tipo";
    }
    public static final String CREATE_TIPO_BEBIDA_SCRIPT =
            "create table if not exists " + TIPO_BEBIDA_TABLE_NAME + "(" +
                    TipoBebida.ID + " " + STRING_TYPE + " primary key)";


    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -------------------------- TABLA DE LOS TIPO MUSICA -----------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------



    public final static String TIPO_MUSICA_TABLE_NAME = "tipomusica";
    //Tabla Eventos Propios
    public static class TipoMusica
    {
        public static final String ID = BaseColumns._ID;
        public static final String TIPO = "tipo";
    }
    public static final String CREATE_TIPO_MUSICA_SCRIPT =
            "create table if not exists " + TIPO_MUSICA_TABLE_NAME + "(" +
                    TipoMusica.ID + " " + STRING_TYPE + " primary key)";



    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -------------------------- TABLA DE LOS TIPO USUARIOS -----------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------



    public final static String USUARIO_TABLE_NAME = "usuario";
    //Tabla Eventos Propios
    public static class Usuario
    {
        public static final String ID = "celular";
        public static final String NOMBRE = "nombre";
        public static final String CORREO = "corroe";
    }
    public static final String CREATE_USUARIO_SCRIPT =
            "create table if not exists  " + USUARIO_TABLE_NAME + "(" +
                    Usuario.ID + " " + STRING_TYPE + " primary key, " +
                    Usuario.NOMBRE + " " + STRING_TYPE + ", " +
                    Usuario.CORREO + " " + STRING_TYPE + ")";








}
