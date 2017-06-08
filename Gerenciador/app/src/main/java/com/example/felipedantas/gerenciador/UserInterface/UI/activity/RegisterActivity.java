package com.example.felipedantas.gerenciador.UserInterface.UI.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.felipedantas.gerenciador.DataLayer.DL.BDController;
import com.example.felipedantas.gerenciador.MenuActivity;
import com.example.felipedantas.gerenciador.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.mViewHolder.editName = (EditText) findViewById(R.id.editName);
        this.mViewHolder.editLastName = (EditText) findViewById(R.id.editLastName);
        this.mViewHolder.editPassword = (EditText) findViewById(R.id.editPassword);
        this.mViewHolder.editConfirmPassword = (EditText) findViewById(R.id.editCnfirmPassword);
        this.mViewHolder.btnRegister = (Button) findViewById(R.id.btnCadastrar);
        this.mViewHolder.btnCancelar = (Button) findViewById(R.id.btnCancelar);
        this.mViewHolder.checkRememberPass = (CheckBox) findViewById(R.id.checkRememberPass);

        this.mViewHolder.btnRegister.setOnClickListener(this);
        this.mViewHolder.btnCancelar.setOnClickListener(this);
        validatePassword();

        verifyActionBar();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnCadastrar) {

            if (this.mViewHolder.editName.getText().toString().equals("")) {
                this.mViewHolder.editName.setError("Insira o Nome");

            } else if (this.mViewHolder.editLastName.getText().toString().equals("")) {
                this.mViewHolder.editLastName.setError("Insira o Sobrenome");

            } else if (this.mViewHolder.editPassword.getText().toString().equals("")
                    && this.mViewHolder.editConfirmPassword.getText().toString() != "") {

                this.mViewHolder.editPassword.setError("Digite a Senha");

            } else {
                if (this.mViewHolder.editPassword.getText().toString()
                        .equals(this.mViewHolder.editConfirmPassword.getText().toString())) {

                        try {

                            BDController crud = new BDController(getBaseContext());
                            String resultado;
                            String nome, sobrenome, password;
                            int rememberPass;

                            nome = this.mViewHolder.editName.getText().toString();
                            sobrenome = this.mViewHolder.editLastName.getText().toString();
                            password = this.mViewHolder.editPassword.getText().toString();

                            if (this.mViewHolder.checkRememberPass.isChecked()) {
                                rememberPass = 1;
                            } else {
                                rememberPass = 0;
                            }

                            resultado = crud.insUsuario(nome, sobrenome, password, rememberPass);


                            Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(this, MenuActivity.class);
                            startActivity(intent);

                        } catch (Exception exeption) {
                            Toast.makeText(getApplication().getApplicationContext(),
                                    "Erro: " + exeption, Toast.LENGTH_LONG).show();
                        }

                } else {
                    this.mViewHolder.editConfirmPassword.setError("Senha Incorreta!");
                }
            }
        }
        if (id == R.id.btnCancelar) {
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
        EditText editName;
        EditText editLastName;
        EditText editPassword;
        EditText editConfirmPassword;
        Button btnRegister;
        Button btnCancelar;
        CheckBox checkRememberPass;
    }

    private void validatePassword() {

    }

    private void verifyActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


}
