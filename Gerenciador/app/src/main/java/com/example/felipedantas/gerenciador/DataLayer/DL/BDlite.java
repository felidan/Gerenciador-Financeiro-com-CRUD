package com.example.felipedantas.gerenciador.DataLayer.DL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.felipedantas.gerenciador.Constants.ConstantsStrings;

/**
 * Created by Felipe Dantas on 24/05/2017.
 */

public class BDlite extends SQLiteOpenHelper {

    private static final String BANCO_DASDOS = "GerencFinanc";
    private static final int VERSAO = 16;

    public BDlite(Context context){
        super(context, BANCO_DASDOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ConstantsStrings.TABLE_USUARIOS + " ("
                + ConstantsStrings.USUARIOS_id_usuario + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ConstantsStrings.USUARIOS_ds_nome + " TEXT,"
                + ConstantsStrings.USUARIOS_ds_sobrenome + " TEXT,"
                + ConstantsStrings.USUARIOS_password + " TEXT,"
                + ConstantsStrings.USUARIOS_fl_remember_pass + " INTEGER );");

        db.execSQL("CREATE TABLE " + ConstantsStrings.TABLE_RECEITAS + " ("
                + ConstantsStrings.RECEITAS_id_receita + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ConstantsStrings.RECEITAS_ds_receita + " TEXT,"
                + ConstantsStrings.RECEITAS_vl_receita + " DOUBLE,"
                + ConstantsStrings.RECEITAS_ds_categoria_receita + " TEXT,"
                + ConstantsStrings.RECEITAS_dt_receita + " TEXT,"
                + ConstantsStrings.RECEITAS_id_usuario + " INTEGER );");

        db.execSQL("CREATE TABLE " + ConstantsStrings.TABLE_DESPESAS + " ("
                + ConstantsStrings.DESPESAS_id_despesa + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ConstantsStrings.DESPESAS_ds_despesa + " TEXT,"
                + ConstantsStrings.DESPESAS_vl_despesa + " DOUBLE,"
                + ConstantsStrings.DESPESAS_ds_categoria_despesa + " TEXT,"
                + ConstantsStrings.DESPESAS_dt_despesa + " TEXT,"
                + ConstantsStrings.DESPESAS_id_usuario + " INTEGER );");

        db.execSQL("CREATE TABLE " + ConstantsStrings.TABLE_CATEGORIA_TP_RECEITA + " ("
                + ConstantsStrings.CATEGORIA_TP_RECEITA_id_categ_receita + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ConstantsStrings.CATEGORIA_TP_RECEITA_ds_categ_receita + " TEXT );");


        db.execSQL("CREATE TABLE " + ConstantsStrings.TABLE_CATEGORIA_TP_DESPESA + " ("
                + ConstantsStrings.CATEGORIA_TP_DESPESA_id_categ_despesa + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ConstantsStrings.CATEGORIA_TP_DESPESA_ds_categ_despesa + " TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + ConstantsStrings.TABLE_USUARIOS + ";");
        db.execSQL("DROP TABLE " + ConstantsStrings.TABLE_RECEITAS + ";");
        db.execSQL("DROP TABLE " + ConstantsStrings.TABLE_DESPESAS + ";");
        db.execSQL("DROP TABLE " + ConstantsStrings.TABLE_CATEGORIA_TP_RECEITA + ";");
        db.execSQL("DROP TABLE " + ConstantsStrings.TABLE_CATEGORIA_TP_DESPESA + ";");

        onCreate(db);
    }
}
