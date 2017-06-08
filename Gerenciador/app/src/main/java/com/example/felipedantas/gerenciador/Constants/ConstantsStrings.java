package com.example.felipedantas.gerenciador.Constants;

/**
 * Created by Felipe Dantas on 28/04/2017.
 */

public class ConstantsStrings {

    // Flags activity

    // Keys
    public static final String KEY_PRIMARY_LOGIN = "primary_login";
    public static final String KEY_REMEMBER_PASSWORD = "remember_password";
    // Values
    public static final String VALUE_YES = "yes";
    public static final String VALUE_NO = "no";

    // Table
    // USUARIOS
    public static final String TABLE_USUARIOS = "USUARIOS";
    public static final String USUARIOS_id_usuario = "_id";
    public static final String USUARIOS_ds_nome = "ds_nome";
    public static final String USUARIOS_ds_sobrenome = "ds_sobrenome";
    public static final String USUARIOS_password = "password";
    public static final String USUARIOS_fl_remember_pass = "fl_remember_pass";


    // RECEITA
    public static final String TABLE_RECEITAS = "RECEITAS";
    public static final String RECEITAS_id_receita = "_id";
    public static final String RECEITAS_ds_receita = "ds_receita";
    public static final String RECEITAS_vl_receita = "vl_receita";
    public static final String RECEITAS_ds_categoria_receita = "ds_categoria_receita";
    public static final String RECEITAS_dt_receita = "dt_receita";
    public static final String RECEITAS_id_usuario = "id_usuario";

    // DESPESA
    public static final String TABLE_DESPESAS = "DESPESAS";
    public static final String DESPESAS_id_despesa = "_id";
    public static final String DESPESAS_ds_despesa = "ds_despesa";
    public static final String DESPESAS_vl_despesa = "vl_despesa";
    public static final String DESPESAS_ds_categoria_despesa = "ds_categoria_despesa";
    public static final String DESPESAS_dt_despesa = "dt_despesa";
    public static final String DESPESAS_id_usuario = "id_usuario";

    // CATEGORIAS_TP_RECEITA
    public static final String TABLE_CATEGORIA_TP_RECEITA = "categoria_tp_receita";
    public static final String CATEGORIA_TP_RECEITA_id_categ_receita = "_id";
    public static final String CATEGORIA_TP_RECEITA_ds_categ_receita = "ds_categoria_receita";

    // CATEGORIAS_TP_DESPESA
    public static final String TABLE_CATEGORIA_TP_DESPESA = "categoria_tp_despesa";
    public static final String CATEGORIA_TP_DESPESA_id_categ_despesa = "_id";
    public static final String CATEGORIA_TP_DESPESA_ds_categ_despesa = "ds_categoria_despesa";
}