package cam.grupo09.goparty.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cam.grupo09.goparty.R;
import cam.grupo09.goparty.mundo.Evento;
import cam.grupo09.goparty.mundo.GoPartY;

public class MainActivity extends AppCompatActivity {


    private ListView lstEventos;
    private TextView lblEventos;

    public void guardarTodo(View view)
    {
        GoPartY.getInstance().guardar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lstEventos = (ListView)findViewById(R.id.lstEventos);
        lblEventos = (TextView)findViewById(R.id.lblEventos);
        GoPartY.getInstance().getManejadorPersistencia().setPath(getFilesDir());
        GoPartY.getInstance().getManejadorPersistencia().cargarInfor();
        lstEventos = (ListView)findViewById(R.id.lstEventos);





      /**  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    public void actualizarEventos()
    {

        ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(this, R.layout.lista_item, R.id.label, GoPartY.getInstance().getManejadorPersistencia().getEventos());
        lstEventos.setAdapter(adapter);
        int num= GoPartY.getInstance().getManejadorPersistencia().getEventos().size();
        if (num == 0)
            lblEventos.setText("No tienes eventos =(");
        else
            lblEventos.setText("Tienes " + num + " eventos");
                }

    @Override
    protected void onResume() {

        super.onResume();
        actualizarEventos();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void crearEvento(View v)
    {
        GoPartY.getInstance().empezarEventoActivity();
        startActivity(new Intent(this, CrearEventoActivity.class));
    }



    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        GoPartY.getInstance().guardar();

    }

}
