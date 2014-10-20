package com.example.josu.abenhumeya;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Josué on 19/10/2014.
 */

/*Este es el adaptador del ListView de la activity principal que hereda de ArrayAdapter de tipo Mesa. El ViewHolder tiene tres TextView: número de mesa, número de comensales
* y hora de creación del objeto; un ImageView con la imagen que presentará en el ListView y un entero con la posición.*/
public class Adaptador extends ArrayAdapter <Mesa>{

    private Context contexto;
    private int recurso;
    private ArrayList <Mesa> lista;
    private LayoutInflater inflador;

    public Adaptador (Context context, int resource, ArrayList <Mesa> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.recurso = resource;
        this.lista = objects;
        inflador = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ViewHolder{
        public TextView tv1, tv2, tv3;
        public ImageView iv;
        public int posicion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null){
            convertView = inflador.inflate(recurso, null);
            vh = new ViewHolder();
            vh.tv1 = (TextView)convertView.findViewById(R.id.tvMesa);
            vh.tv2 = (TextView)convertView.findViewById(R.id.tvComensal);
            vh.tv3 = (TextView)convertView.findViewById(R.id.tvHora);
            vh.iv = (ImageView)convertView.findViewById(R.id.ivMesa);
            convertView.setTag(vh);
        }
        else
            vh = (ViewHolder)convertView.getTag();
        vh.tv1.setText(lista.get(position).getNumMesa());
        vh.tv2.setText(lista.get(position).getNumComensal());
        vh.tv3.setText(lista.get(position).getHora());
        vh.iv.setImageBitmap((lista.get(position).getFoto()));
        vh.posicion = position;
        return convertView;
    }
}
