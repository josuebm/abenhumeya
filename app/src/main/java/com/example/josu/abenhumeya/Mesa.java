package com.example.josu.abenhumeya;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Josué on 19/10/2014.
 */

/*La clase Mesa implementa Comparable para poder ordenar los objetos en el ListView. Tiene un String con el número de mesa, otro con el número de comensales, la imagen que
  se presentará en el ListView, un ArrayList de tipo String donde se almacenan los pedidos y un String donde guarda la hora a la que se creó el objeto. Los métodos que tiene
  son los constructores vacios y con parámetros, los set, los get y el compareTo para poder ordenar, en el que lo único que tengo en cuenta es el número de mesa, pues no
  son repetibles y están enumerados del 1 al 25.*/
public class Mesa implements Comparable <Mesa>{
    
    private String numMesa, numComensal;
    private Bitmap foto;
    private ArrayList<String>pedidos;
    private String hora;

    public Mesa() {
    }

    public Mesa(String numMesa, String numComensal, Bitmap foto, Date hora) {
        this.numMesa = numMesa;
        this.numComensal = numComensal;
        this.foto = foto;
        this.hora = hora.getHours() + ":" + hora.getMinutes();
    }

    public String getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(String numMesa) {
        this.numMesa = numMesa;
    }

    public String getNumComensal() {
        return numComensal;
    }

    public void setNumComensal(String numComensal) {
        this.numComensal = numComensal;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public ArrayList<String> getPedidos() {
        return pedidos;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setPedidos(ArrayList<String> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public int compareTo(Mesa mesa) {
        String a = getNumMesa();
        String b = mesa.getNumMesa();
        return a.compareTo(b);
    }
}
