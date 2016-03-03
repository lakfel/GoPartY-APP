package cam.grupo09.goparty.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import cam.grupo09.goparty.Enumerables.TipoBebida;
import cam.grupo09.goparty.R;
import cam.grupo09.goparty.mundo.GoPartY;

public class AgregarEstablecimientoEventoActivity extends AppCompatActivity
{

    private EditText txtNombre;
    private EditText txtDescripcion;
    private Spinner spTipoMusica;
    private Spinner spTipoBebidas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_establecimiento_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtDescripcion = (EditText)findViewById(R.id.txtDescripcion);
        spTipoBebidas = (Spinner)findViewById(R.id.spTipoBebida);
        spTipoMusica = (Spinner)findViewById(R.id.spTipoMusica);
        TipoBebida[] values = TipoBebida.values();
        ArrayList<TipoBebida>tbs = new ArrayList<TipoBebida>();
        for(TipoBebida a: values)
        {
            tbs.add(a);
        }


        }

}
