package com.example.felipedantas.gerenciador.DataLayer.DL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.felipedantas.gerenciador.Classes.Despesas;
import com.example.felipedantas.gerenciador.Classes.Receitas;
import com.example.felipedantas.gerenciador.Classes.Usuario;
import com.example.felipedantas.gerenciador.Constants.ConstantsStrings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Felipe Dantas on 24/05/2017.
 */

public class BDController {
    private SQLiteDatabase bd;
    String mensagem;

    public BDController(Context context) {
        BDlite banco = new BDlite(context);
        bd = banco.getWritableDatabase();
    }

    public String insUsuario(String nome, String sobrenome, String password, int rememberPass) {
        ContentValues values = new ContentValues();
        long resultado;
        values.put(ConstantsStrings.USUARIOS_ds_nome, nome);
        values.put(ConstantsStrings.USUARIOS_ds_sobrenome, sobrenome);
        values.put(ConstantsStrings.USUARIOS_password, password);
        values.put(ConstantsStrings.USUARIOS_fl_remember_pass, rememberPass);

        resultado = bd.insert(ConstantsStrings.TABLE_USUARIOS, null, values);

        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "Cadastro realizado com sucesso";
        }
        return mensagem;
    }

    public String delUsuario(int id) {
        long resultado;

        resultado = bd.delete(ConstantsStrings.TABLE_USUARIOS,
                " " + ConstantsStrings.USUARIOS_id_usuario + " = " + id,
                null);

        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "Usuario deletado com sucesso";
        }
        
        return mensagem;
    }

    public Usuario buscaUsuario() {
        List<Usuario> list = new ArrayList<Usuario>();
        String[] colunas = new String[]{ConstantsStrings.USUARIOS_ds_nome,
                ConstantsStrings.USUARIOS_password,
                ConstantsStrings.USUARIOS_fl_remember_pass};

        Usuario u = new Usuario();
        Cursor cursor = bd.query(ConstantsStrings.TABLE_USUARIOS,
                colunas,
                null, // Clausula where
                null, // Condição
                null, // Group by
                null, // having
                ConstantsStrings.USUARIOS_ds_nome + " ASC"); // Order by

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                 u.setNome(cursor.getString(0));
                 u.setPassword(cursor.getString(1));
                 u.setRemember(cursor.getString(2));

            } while (cursor.moveToNext());
        }

        return u;
    }

    /*public int buscaSenha(String senha) {
        int confirmador = 0;
        String[] colunas = new String[]{ConstantsStrings.USUARIOS_password};

        Cursor cursor = bd.query(ConstantsStrings.TABLE_USUARIOS,
                colunas,
                ConstantsStrings.TABLE_USUARIOS + " = ?", // Clausula where
                new String[]{"" + senha}, // Condição
                null, // Group by
                null, // having
                null ); // Order by

        if (cursor.getCount() > 0) {
            confirmador = 1;
        }
        else{
            confirmador = 0;
        }

        return (confirmador);
    }*/

    public String insReceita(Receitas receitas) {

        ContentValues values = new ContentValues();
        long resultado;

        values.put(ConstantsStrings.RECEITAS_ds_receita, receitas.getDsReceita());
        values.put(ConstantsStrings.RECEITAS_vl_receita, receitas.getVlReceita());
        values.put(ConstantsStrings.RECEITAS_ds_categoria_receita, receitas.getDsCategReceita());
        values.put(ConstantsStrings.RECEITAS_dt_receita, receitas.getDtReceita());
        values.put(ConstantsStrings.RECEITAS_id_usuario, receitas.getIdUsuario());

        resultado = bd.insert(ConstantsStrings.TABLE_RECEITAS, null, values);

        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "Receita incluida com sucesso";
        }
        return mensagem;
    }

    public String updReceita(Receitas receitas) {
        ContentValues values = new ContentValues();
        long resultado;

        values.put(ConstantsStrings.RECEITAS_ds_receita, receitas.getDsReceita());
        values.put(ConstantsStrings.RECEITAS_vl_receita, receitas.getVlReceita());
        values.put(ConstantsStrings.RECEITAS_ds_categoria_receita, receitas.getDsCategReceita());

        resultado = bd.update(ConstantsStrings.TABLE_RECEITAS,
                values,
                ConstantsStrings.RECEITAS_id_receita + " = " + receitas.getIdReceita(),
                //new String[]{"" + receitas.getIdReceita()});
                null);
        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "Receita alterada com sucesso";
        }
        return mensagem;
    }

    public Cursor carregaReceita(int id){
        Cursor cursor;
        String[] campos =  {ConstantsStrings.RECEITAS_ds_receita,ConstantsStrings.RECEITAS_vl_receita};
        String where = ConstantsStrings.RECEITAS_id_receita+ "=" + id;

        cursor = bd.query(ConstantsStrings.TABLE_RECEITAS,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;
    }
    public String delReceita(int id) {
        long resultado;

        resultado = bd.delete(ConstantsStrings.TABLE_RECEITAS,
                " " + ConstantsStrings.RECEITAS_id_receita + " = " + id,
                null);

        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "Receita deletada com sucesso";
        }
        return mensagem;
    }

    public Cursor buscaReceitasGrid() {
        //List<Receitas> list = new ArrayList<Receitas>();
        String[] colunas = new String[]{ConstantsStrings.RECEITAS_id_receita,
                ConstantsStrings.RECEITAS_ds_receita,
                ConstantsStrings.RECEITAS_vl_receita};

        Cursor cursor = bd.query(ConstantsStrings.TABLE_RECEITAS,
                colunas,
                null, // Clausula where
                null, // Condição
                null, // Group by
                null, // having
                null); // Order by

        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }


    public String insDespesa(Despesas despesas) {

        ContentValues values = new ContentValues();
        long resultado;

        values.put(ConstantsStrings.DESPESAS_ds_despesa, despesas.getDsDespesa());
        values.put(ConstantsStrings.DESPESAS_vl_despesa, despesas.getVlDespesa());
        values.put(ConstantsStrings.DESPESAS_ds_categoria_despesa, despesas.getDsCategDespesa());
        values.put(ConstantsStrings.DESPESAS_dt_despesa, despesas.getDtDespesa());
        values.put(ConstantsStrings.DESPESAS_id_usuario, despesas.getIdUsuario());

        resultado = bd.insert(ConstantsStrings.TABLE_DESPESAS, null, values);

        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "Despesa incluida com sucesso";
        }
        return mensagem;
    }

    public String updDespesa(Despesas despesas) {
        ContentValues values = new ContentValues();
        long resultado;

        values.put(ConstantsStrings.DESPESAS_ds_despesa, despesas.getDsDespesa());
        values.put(ConstantsStrings.DESPESAS_vl_despesa, despesas.getVlDespesa());
        values.put(ConstantsStrings.DESPESAS_ds_categoria_despesa, despesas.getDsCategDespesa());

        resultado = bd.update(ConstantsStrings.TABLE_DESPESAS,
                values,
                ConstantsStrings.DESPESAS_id_despesa + " = " + despesas.getIdDespesa(),
                null);//new String[]{"" + despesas.getIdDespesa()});

        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "Despesa alterada com sucesso";
        }
        return mensagem;
    }

    public Cursor carregaDespesa(int id){
        Cursor cursor;
        String[] campos =  {ConstantsStrings.DESPESAS_ds_despesa,ConstantsStrings.DESPESAS_vl_despesa};
        String where = ConstantsStrings.DESPESAS_id_despesa+ "=" + id;

        cursor = bd.query(ConstantsStrings.TABLE_DESPESAS,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;
    }



    public String delDespesa(int id) {
        long resultado;

        resultado = bd.delete(ConstantsStrings.TABLE_DESPESAS,
                " " + ConstantsStrings.DESPESAS_id_despesa + " = " + id,
                null);

        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "Despesa deletada com sucesso";
        }
        return mensagem;
    }

    public Cursor buscaDespesaGrid() {
        String[] colunas = new String[]{ConstantsStrings.DESPESAS_id_despesa,
                ConstantsStrings.DESPESAS_ds_despesa,
                ConstantsStrings.DESPESAS_vl_despesa};

        Cursor cursor = bd.query(ConstantsStrings.TABLE_DESPESAS,
                colunas,
                null, // Clausula where
                null, // Condição
                null, // Group by
                null, // having
                null); // Order by

        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public String insTpCategReceita(String dsCategoria) {
        ContentValues values = new ContentValues();
        long resultado;

        values.put(ConstantsStrings.CATEGORIA_TP_RECEITA_ds_categ_receita, dsCategoria);

        resultado = bd.insert(ConstantsStrings.TABLE_CATEGORIA_TP_RECEITA, null, values);

        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "Categoria cadastrada com sucesso";
        }
        return mensagem;

    }

    public List<String> buscaCategReceita() {
        List<String> list = new ArrayList<String>();
        String[] colunas = new String[]{ConstantsStrings.CATEGORIA_TP_RECEITA_ds_categ_receita};

        Cursor cursor = bd.query(ConstantsStrings.TABLE_CATEGORIA_TP_RECEITA,
                colunas,
                null, // Clausula where
                null, // Condição
                null, // Group by
                null, // having
                ConstantsStrings.CATEGORIA_TP_RECEITA_ds_categ_receita + " ASC"); // Order by

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                list.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }

        return (list);
    }


    public String delCategReceita(int id) {
        long resultado;

        resultado = bd.delete(ConstantsStrings.TABLE_CATEGORIA_TP_RECEITA,
                " " + ConstantsStrings.CATEGORIA_TP_RECEITA_id_categ_receita + " = " + id,
                null);

        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "categoria deletada com sucesso";
        }
        return mensagem;
    }

    public String insTpCategDespesa(String dsCategoria) {
        ContentValues values = new ContentValues();
        long resultado;

        values.put(ConstantsStrings.CATEGORIA_TP_DESPESA_ds_categ_despesa, dsCategoria);

        resultado = bd.insert(ConstantsStrings.TABLE_CATEGORIA_TP_DESPESA, null, values);

        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "Categoria cadastrada com sucesso";
        }
        return mensagem;
    }

    public List<String> buscaCategDespesa() {
        List<String> list = new ArrayList<String>();
        String[] colunas = new String[]{ConstantsStrings.CATEGORIA_TP_DESPESA_ds_categ_despesa};

        Cursor cursor = bd.query(ConstantsStrings.TABLE_CATEGORIA_TP_DESPESA,
                colunas,
                null, // Clausula where
                null, // Condição
                null, // Group by
                null, // having
                ConstantsStrings.CATEGORIA_TP_DESPESA_id_categ_despesa + " ASC"); // Order by

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                list.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }

        return (list);
    }


    public String delCategDespesa(int id) {
        long resultado;

        resultado = bd.delete(ConstantsStrings.TABLE_CATEGORIA_TP_DESPESA,
                " " + ConstantsStrings.CATEGORIA_TP_DESPESA_id_categ_despesa + " = " + id,
                null);

        if (resultado == -1) {
            mensagem = "Falha na conexão com o banco";
        } else {
            mensagem = "categoria deletada com sucesso";
        }
        return mensagem;
    }

    public Double buscaReceiraTotal(){

        String[] colunas = new String[]{ConstantsStrings.RECEITAS_vl_receita};
        Double resultado = 0.0;
        Cursor cursor = bd.query(ConstantsStrings.TABLE_RECEITAS,
                colunas,
                null, // Clausula where
                null, // Condição
                null, // Group by
                null, // having
                null); // Order by

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                resultado = resultado + cursor.getDouble(0);

            } while (cursor.moveToNext());
        }

        return resultado;

    }

    public Double buscaDespesaTotal(){

        String[] colunas = new String[]{ConstantsStrings.DESPESAS_vl_despesa};
        Double resultado = 0.0;
        Cursor cursor = bd.query(ConstantsStrings.TABLE_DESPESAS,
                colunas,
                null, // Clausula where
                null, // Condição
                null, // Group by
                null, // having
                null); // Order by

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                resultado = resultado + cursor.getDouble(0);

            } while (cursor.moveToNext());
        }

        return resultado;

    }
}