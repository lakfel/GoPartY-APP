package cam.grupo09.goparty.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cam.grupo09.goparty.Enumerables.TipoBebida;
import cam.grupo09.goparty.Enumerables.TipoMusica;
import cam.grupo09.goparty.R;
import cam.grupo09.goparty.mundo.Establecimiento;
import cam.grupo09.goparty.mundo.Evento;
import cam.grupo09.goparty.mundo.GoPartY;
import cam.grupo09.goparty.persistencia.ManejadorPersistencia;

/**
 * Created by mike on 3/3/2016.
 */
public class VerEstablecimientosActivity extends AppCompatActivity {

    private ListView lvMusica;
    private ListView lvBebidas;
    private TextView lblNombre;
    private TextView lblCover;
    private TextView lblDescripcion;
    private TextView lblDireccion;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_establecimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvMusica = (ListView) findViewById(R.id.list_Musica_establecimiento);
        lvBebidas=(ListView) findViewById(R.id.list_Bebidas_establecimiento);
        lblNombre=(TextView) findViewById(R.id.lbl_nombre_establecimiento);
        lblCover=(TextView) findViewById(R.id.lbl_cover_establecimiento);
        lblDescripcion=(TextView) findViewById(R.id.lbl_descripcion_establecimiento);

        lblDireccion=(TextView) findViewById(R.id.lbl_direccion_establecimiento);

        Intent info=getIntent();
        String nombre=info.getStringExtra("establecimiento");
        ManejadorPersistencia mp= GoPartY.getInstance().getManejadorPersistencia();
        Establecimiento est= mp.darEtablecimientoNombre(nombre);

        lblNombre.setText(est.getNombre());
        lblCover.setText(String.valueOf(est.getCostoCover()));
        lblDescripcion.setText(est.getDescripcion());
        lblDireccion.setText(est.getDireccion());
        ArrayAdapter adapterMusica = new ArrayAdapter<TipoMusica>(this, R.layout.lista_item,R.id.label,est.getTiposMusica());
        lvMusica.setAdapter(adapterMusica);
        ArrayAdapter adapterBebida = new ArrayAdapter<TipoBebida>(this,R.layout.lista_item,R.id.label,est.getTipoBebidas());
        lvBebidas.setAdapter(adapterBebida);

    }

}
