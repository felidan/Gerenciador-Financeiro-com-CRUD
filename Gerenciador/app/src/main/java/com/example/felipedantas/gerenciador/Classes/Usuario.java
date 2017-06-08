package com.example.felipedantas.gerenciador.Classes;

/**
 * Created by Felipe Dantas on 04/06/2017.
 */

public class Usuario {
    private String nome;
    private String Sobrenome;
    private String password;
    private String remember;

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }

    public void setSobrenome(String sobrenome){
        this.Sobrenome = sobrenome;
    }
    public String getSobrenome(){
        return this.Sobrenome;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    public void setRemember(String remember){
        this.remember = remember;
    }
    public String getRemember(){
        return this.remember;
    }
}
