package com.example.felipedantas.gerenciador.UserInterface.UI.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.felipedantas.gerenciador.Constants.ConstantsStrings;
import com.example.felipedantas.gerenciador.DataLayer.DL.SecurityPreferences;
import com.example.felipedantas.gerenciador.R;
import com.example.felipedantas.gerenciador.UserInterface.UI.activity.LoginActivity;
import com.example.felipedantas.gerenciador.UserInterface.UI.activity.RegisterActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mSecurityPreferences.storeString(ConstantsStrings.KEY_PRIMARY_LOGIN, ConstantsStrings.VALUE_YES);
        this.mSecurityPreferences.storeString(ConstantsStrings.KEY_REMEMBER_PASSWORD, ConstantsStrings.VALUE_NO);

        this.mViewHolder.btnLogin = (Button) findViewById(R.id.btnLogin);
        this.mViewHolder.btnRegister = (Button) findViewById(R.id.btnRegister);

        this.mViewHolder.btnLogin.setOnClickListener(this);
        this.mViewHolder.btnRegister.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyLogin();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnLogin) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        if (id == R.id.btnRegister) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    private static class ViewHolder {
        Button btnLogin;
        Button btnRegister;
    }

    private void verifyLogin() {
        String login = this.mSecurityPreferences.getStoreString(ConstantsStrings.KEY_PRIMARY_LOGIN);

        if (login.equals(ConstantsStrings.VALUE_NO)) {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}