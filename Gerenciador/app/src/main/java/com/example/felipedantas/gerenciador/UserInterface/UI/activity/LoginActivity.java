package com.example.felipedantas.gerenciador.UserInterface.UI.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felipedantas.gerenciador.Classes.Receitas;
import com.example.felipedantas.gerenciador.Classes.Usuario;
import com.example.felipedantas.gerenciador.Constants.ConstantsStrings;
import com.example.felipedantas.gerenciador.DataLayer.DL.BDController;
import com.example.felipedantas.gerenciador.DataLayer.DL.SecurityPreferences;
import com.example.felipedantas.gerenciador.MenuActivity;
import com.example.felipedantas.gerenciador.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.txtNameUser = (TextView) findViewById(R.id.txtUser);
        this.mViewHolder.editPassword = (EditText) findViewById(R.id.editPassword);
        this.mViewHolder.checkRemember = (CheckBox) findViewById(R.id.checkRemember);
        this.mViewHolder.btnLogin = (Button) findViewById(R.id.btnLogin);
        this.mViewHolder.nmApp = (TextView) findViewById(R.id.nm_app);


        this.mViewHolder.checkRemember.setOnClickListener(this);
        this.mViewHolder.btnLogin.setOnClickListener(this);
        this.mViewHolder.nmApp.setOnClickListener(this);
        verifyActionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUsuario();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.checkRemember) {
            if (this.mViewHolder.checkRemember.isChecked()) {
                this.mSecurityPreferences.storeString(ConstantsStrings.KEY_REMEMBER_PASSWORD, ConstantsStrings.VALUE_YES);
                Toast.makeText(this, "Lembrar Senha", Toast.LENGTH_LONG).show();
            } else {
                this.mSecurityPreferences.storeString(ConstantsStrings.KEY_REMEMBER_PASSWORD, ConstantsStrings.VALUE_NO);
            }
        }
        if (id == R.id.btnLogin) {
            try{
                if(this.mViewHolder.editPassword.getText().toString().equals(user.getPassword())){
                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(this, "senha incorreta", Toast.LENGTH_LONG).show();
                }

            } catch (Exception exeption){
                Toast.makeText(getApplication().getApplicationContext(),
                        "Erro: " + exeption, Toast.LENGTH_LONG).show();
            }
        }
        if(id == R.id.nm_app){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private static class ViewHolder {
        TextView txtNameUser;
        EditText editPassword;
        CheckBox checkRemember;
        Button btnLogin;
        TextView nmApp;
    }

    private void verifyActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void getUsuario(){
        // banco
        try{
            BDController crud = new BDController(getBaseContext());
            user = crud.buscaUsuario();

            if(user.getNome() == null){
                this.mViewHolder.txtNameUser.setText("Você ainda não possui cadastro");
                this.mViewHolder.editPassword.setVisibility(View.GONE);
                this.mViewHolder.btnLogin.setVisibility(View.GONE);
                this.mViewHolder.checkRemember.setVisibility(View.GONE);
            } else{
                this.mViewHolder.txtNameUser.setText("Olá "+ user.getNome());

                if(user.getRemember().equals("1")){
                    this.mViewHolder.checkRemember.setChecked(true);
                    this.mViewHolder.editPassword.setText(user.getPassword());
                }
            }
        } catch (Exception exception){
            Toast.makeText(this, "Erro: " + exception, Toast.LENGTH_LONG).show();
        }
    }
}



