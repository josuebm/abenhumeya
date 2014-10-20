package com.example.josu.abenhumeya;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class VerPedido extends Activity {

    ArrayList<String> pedido;
    ListView lv;
    AdaptadorVerPedido adaptadorPedido;
    int posicion;
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedido);
        initComponents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ver_pedido, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.longclick_ver_pedido, menu);
    }

    //Esta es la opción eliminar del menú contextual para eliminar platos del pedido.
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id=item.getItemId();
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int index= info.position;
        Object o= info.targetView.getTag();
        AdaptadorVerPedido.ViewHolder vh;
        vh = (AdaptadorVerPedido.ViewHolder)o;
        if (id == R.id.action_eliminar) {
            tostada(getResources().getString(R.string.ttEliminado)+ " "+vh.tv1.getText().toString());
            pedido.remove(index);
            adaptadorPedido.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void tostada(String cad){
        Toast.makeText(this, cad, Toast.LENGTH_SHORT).show();
    }

    /*Este es el método asociado al onClick del botón Confirmar pedido. Asignamos el pedido a la mesa que le corresponda y finalizamos la activity.*/
    public void confirmarPedido(View v){
        Principal.listaMesas.get(posicion).setPedidos(pedido);
        finish();
    }

    public void initComponents(){
        posicion = (Integer)getIntent().getExtras().get("posicion");

        /*Obtenemos de la activity de la que esta ha sido llamada el ArrayList pedido pero tenemos que asegurarnos de no machacar el contenido previo si ya teníamos un pedido
        * asignado a esta mesa. En tal caso, añadimos el nuevo pedido a continuación.*/
        if(Principal.listaMesas.get(posicion).getPedidos() == null)
            pedido = (ArrayList<String>)getIntent().getExtras().get("pedido");
        else{
            pedido = Principal.listaMesas.get(posicion).getPedidos();
            ArrayList<String> aux = (ArrayList<String>)getIntent().getExtras().get("pedido");
            for (int i=0; i<aux.size(); i++)
                pedido.add(aux.get(i));
        }

        tv1 = (TextView)findViewById(R.id.tvMesa);
        tv2 = (TextView)findViewById(R.id.tvComensal);
        tv1.setText(Principal.listaMesas.get(posicion).getNumMesa());
        tv2.setText(Principal.listaMesas.get(posicion).getNumComensal());
        lv = (ListView)findViewById(R.id.lvVerPedido);
        adaptadorPedido = new AdaptadorVerPedido(this, R.layout.detalle_pedido, pedido);
        lv.setAdapter(adaptadorPedido);
        registerForContextMenu(lv);
    }
}
