package com.example.josu.abenhumeya;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TomarNota extends Activity {


    TextView tv1, tv2;
    Spinner spCategoria, spSubcategoria;
    ListView lvPlatos;
    ArrayList<String> categorias, pedido;
    int posicion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomar_nota);
        initComponents();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tomar_nota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_ver_pedido) {
            return verPedido();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initComponents(){

        //el ArrayList de String pedido almacena el pedido de cada mesa.
        pedido = new ArrayList<String>();
        tv1 = (TextView)findViewById(R.id.tvMesa);
        tv2 = (TextView)findViewById(R.id.tvComensal);
        lvPlatos = (ListView)findViewById(R.id.lvPlatos);

        //Cargamos las categorías para pasárselos al spinner correspondiente.
        String cat[] = getApplicationContext().getResources().getStringArray(R.array.spCategorias);
        categorias = new ArrayList<String>();
        for(String s: cat){
            categorias.add(s);
        }

        /*Cogemos los datos que le hemos pasado desde la actividad principal (la posición en listaMesas) y pasamos los datos a los TextView donde se visualizan el número de mesa
        y el número de comensales.*/
        posicion = (Integer) getIntent().getExtras().get("posicion");
        tv1.setText(Principal.listaMesas.get(posicion).getNumMesa());
        tv2.setText(Principal.listaMesas.get(posicion).getNumComensal());

        spCategoria = (Spinner)findViewById(R.id.spCategorias);
        spSubcategoria = (Spinner)findViewById(R.id.spSubcategoria);

        ArrayAdapter<CharSequence> adapterCategorias = ArrayAdapter.createFromResource(this, R.array.spCategorias, android.R.layout.simple_spinner_item);
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategoria.setAdapter(adapterCategorias);

        //Creamos un escuchador en el Spinner para que cargue la lista de platos en el ListView según la categoría que esté seleccionada aquí.
        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                asociarSpinnerSubcategoria(i + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Creamos un escuchador en el ListView para que cada vez que pulsemos sobre un elemento añada ese plato a pedido y además muestre un mensaje avisando de ello.
        lvPlatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tostada(getResources().getString(R.string.ttAnadido) + " " + adapterView.getItemAtPosition(i).toString());
                pedido.add((String)adapterView.getItemAtPosition(i).toString());
            }
        });
    }

    /*Las categorías que se presentan en el Spinner están guardadas en un string-array en strings.xml, por tanto es fácil presentar la lista de platos según la posición
    * del elemento seleccionado en el Spinner.*/
    public void asociarSpinnerSubcategoria(int pos){
        ArrayAdapter<CharSequence> adapterSubcategorias;
        switch (pos){
            case 1:{
                adapterSubcategorias = ArrayAdapter.createFromResource(this,R.array.bebidas, android.R.layout.simple_spinner_item);
            }break;
            case 2:{
                adapterSubcategorias = ArrayAdapter.createFromResource(this,R.array.entrantes, android.R.layout.simple_spinner_item);
            }break;
            case 3:{
                adapterSubcategorias = ArrayAdapter.createFromResource(this,R.array.ensaladas, android.R.layout.simple_spinner_item);
            }break;
            case 4:{
                adapterSubcategorias = ArrayAdapter.createFromResource(this,R.array.carnes, android.R.layout.simple_spinner_item);
            }break;
            case 5:{
                adapterSubcategorias = ArrayAdapter.createFromResource(this,R.array.pescados, android.R.layout.simple_spinner_item);
            }break;
            case 6:{
                adapterSubcategorias = ArrayAdapter.createFromResource(this,R.array.pastas, android.R.layout.simple_spinner_item);
            }break;
            default:{
                adapterSubcategorias = ArrayAdapter.createFromResource(this,R.array.reposteria, android.R.layout.simple_spinner_item);
            }break;
        }
        lvPlatos.setAdapter(adapterSubcategorias);
    }

    public void tostada(String cad){
        Toast.makeText(this, cad, Toast.LENGTH_SHORT).show();
    }

    //Esta es la opción del menú Ver Pedido, que llama a otra activity, a la que le pasa el ArrayList pedido y la posición de la mesa en listaMesas.
    public boolean verPedido(){
        Intent intent = new Intent(this, VerPedido.class);
        intent.putExtra("pedido", pedido);
        intent.putExtra("posicion", posicion);
        startActivity(intent);
        return true;
    }


}
