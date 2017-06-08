package com.example.felipedantas.gerenciador.Classes;

import java.util.Date;

/**
 * Created by Felipe Dantas on 24/05/2017.
 */

public class Receitas {
    private int id_receita;
    private String ds_receita;
    private Double vl_receita;
    private String ds_categoria_receita;
    private String dt_receita;
    private int id_usuario;

    public void setIdReceita(int id_receita){
        this.id_receita = id_receita;
    }
    public int getIdReceita(){
        return this.id_receita;
    }
    public void setDsReceita(String ds_receita){
        this.ds_receita = ds_receita;
    }
    public String getDsReceita(){
        return this.ds_receita;
    }
    public void setVlReceita(Double vl_receita){
        this.vl_receita = vl_receita;
    }
    public Double getVlReceita(){
        return this.vl_receita;
    }
    public void setDsCategReceita(String ds_categoria_receita){
        this.ds_categoria_receita = ds_categoria_receita;
    }
    public String getDsCategReceita(){
        return this.ds_categoria_receita;
    }
    public void setDtReceita(String dt_receita){
        this.dt_receita = dt_receita;
    }
    public String getDtReceita(){
        return this.dt_receita;
    }
    public void setIdUsuario(int id_usuario){
        this.id_usuario = id_usuario;
    }
    public int getIdUsuario() {
        return this.id_usuario;
    }
}
