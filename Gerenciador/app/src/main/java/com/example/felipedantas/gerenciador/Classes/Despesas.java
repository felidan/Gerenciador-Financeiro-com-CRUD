package com.example.felipedantas.gerenciador.Classes;

import java.util.Date;

/**
 * Created by Felipe Dantas on 24/05/2017.
 */

public class Despesas {
    private int id_despesa;
    private String ds_despesa;
    private Double vl_despesa;
    private String ds_categoria_despesa;
    private String dt_despesa;
    private int id_usuario;

    public void setIdDespesa(int id_despesa){
        this.id_despesa = id_despesa;
    }
    public int getIdDespesa(){
        return id_despesa;
    }
    public void setDsDespesa(String ds_despesa){
        this.ds_despesa = ds_despesa;
    }
    public String getDsDespesa(){
        return this.ds_despesa;
    }
    public void setVlDespesa(Double vl_despesa){
        this.vl_despesa = vl_despesa;
    }
    public Double getVlDespesa(){
        return this.vl_despesa;
    }
    public void setDsCategDespesa(String ds_categoria_despesa){
        this.ds_categoria_despesa = ds_categoria_despesa;
    }
    public String getDsCategDespesa(){
        return this.ds_categoria_despesa;
    }
    public void setDtDespesa(String dt_despesa){
        this.dt_despesa = dt_despesa;
    }
    public String getDtDespesa(){
        return this.dt_despesa;
    }
    public void setIdUsuario(int id_usuario){
        this.id_usuario = id_usuario;
    }
    public int getIdUsuario(){
        return this.id_usuario;
    }
}
