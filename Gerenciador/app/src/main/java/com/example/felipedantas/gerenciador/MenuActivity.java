package com.example.felipedantas.gerenciador;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.felipedantas.gerenciador.Classes.Despesas;
import com.example.felipedantas.gerenciador.Classes.Receitas;
import com.example.felipedantas.gerenciador.DataLayer.DL.BDController;
import com.example.felipedantas.gerenciador.UserInterface.UI.activity.LoginActivity;
import com.example.felipedantas.gerenciador.UserInterface.UI.fragment.CategoriaFragment;
import com.example.felipedantas.gerenciador.UserInterface.UI.fragment.ListaDespesaFragment;
import com.example.felipedantas.gerenciador.UserInterface.UI.fragment.ListaReceitaFragment;
import com.example.felipedantas.gerenciador.UserInterface.UI.fragment.PrincipalFragment;

import java.text.DecimalFormat;
import java.util.List;

import static com.example.felipedantas.gerenciador.R.array.tp_receita;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Instancia o fragmento principal
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_container, new PrincipalFragment())
                    .commit();
        }

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fabAddReceita = (FloatingActionButton) findViewById(R.id.floatAddReceita);
        FloatingActionButton fabAddDespesa = (FloatingActionButton) findViewById(R.id.floatAddDespesa);
        FloatingActionButton fabAddCategoria = (FloatingActionButton) findViewById(R.id.floatAddCategoria);
        final LinearLayout layoutReceita = (LinearLayout) findViewById(R.id.layoutAddReceita);
        final LinearLayout layoutDespesa = (LinearLayout) findViewById(R.id.layoutAddDespesa);
        final LinearLayout layoutCategoria = (LinearLayout) findViewById(R.id.layoutAddCategoria);

        final Animation mShowButton = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.show_button);
        final Animation mHideButton = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.hide_button);
        final Animation mShowLayout = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.show_layout);
        final Animation mHideLayout = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.hide_layout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Saldo atual: R$ 2500,00", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();

                if (layoutReceita.getVisibility() == View.VISIBLE
                        && layoutDespesa.getVisibility() == View.VISIBLE
                        && layoutCategoria.getVisibility() == View.VISIBLE) {

                    layoutReceita.setVisibility(View.GONE);
                    layoutDespesa.setVisibility(View.GONE);
                    layoutCategoria.setVisibility(View.GONE);

                    layoutReceita.startAnimation(mHideLayout);
                    layoutDespesa.startAnimation(mHideLayout);
                    layoutCategoria.startAnimation(mHideLayout);

                    fab.startAnimation(mHideButton);

                } else {
                    layoutReceita.setVisibility(View.VISIBLE);
                    layoutDespesa.setVisibility(View.VISIBLE);
                    layoutCategoria.setVisibility(View.VISIBLE);

                    layoutReceita.startAnimation(mShowLayout);
                    layoutDespesa.startAnimation(mShowLayout);
                    layoutCategoria.startAnimation(mShowLayout);

                    fab.startAnimation(mShowButton);
                }
            }
        });

        fabAddReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layoutReceita.setVisibility(View.GONE);
                layoutDespesa.setVisibility(View.GONE);
                layoutCategoria.setVisibility(View.GONE);

                layoutReceita.startAnimation(mHideLayout);
                layoutDespesa.startAnimation(mHideLayout);
                layoutCategoria.startAnimation(mHideLayout);
                fab.startAnimation(mHideButton);


                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_add_receita, null);

                final EditText mDescricao = (EditText) mView.findViewById(R.id.editDescricao);
                final EditText mVlReceita = (EditText) mView.findViewById(R.id.editValor);
                final Spinner mTpReceita = (Spinner) mView.findViewById(R.id.spinTpReceita);
                final Button mAddReceita = (Button) mView.findViewById(R.id.btnAddReceita);

                //BDController crud = new BDController(getBaseContext());
                //List<String> tp_receitas = crud.buscaCategReceita();

                ArrayAdapter adapter = ArrayAdapter.createFromResource(MenuActivity.this,
                        tp_receita,
                        android.R.layout.simple_spinner_dropdown_item);

                mTpReceita.setAdapter(adapter);

                mBuilder.setView(mView);
                final AlertDialog alert = mBuilder.create();

                mAddReceita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();

                        if (id == R.id.btnAddReceita) {

                            if (mDescricao.getText().toString().isEmpty()) {
                                mDescricao.setError("descrição");
                            } else if (mVlReceita.getText().toString().isEmpty()) {
                                mVlReceita.setError("valor da receita");
                            } else {
                                try {
                                    // banco
                                    BDController crud = new BDController(getBaseContext());
                                    Receitas res = new Receitas();
                                    String resultado;


                                    res.setDsReceita(mDescricao.getText().toString());
                                    res.setVlReceita(Double.valueOf(mVlReceita.getText().toString()));
                                    res.setDsCategReceita(mTpReceita.getSelectedItem().toString());

                                    resultado = crud.insReceita(res);

                                    Toast.makeText(getApplication().getApplicationContext(),
                                            resultado, Toast.LENGTH_LONG).show();
                                    alert.dismiss();

                                } catch (Exception exeption) {
                                    Toast.makeText(getApplication().getApplicationContext(),
                                            "Erro: " + exeption, Toast.LENGTH_LONG).show();
                                }
                            }

                        }
                    }
                });
                alert.show();
            }
        });

        fabAddDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layoutReceita.setVisibility(View.GONE);
                layoutDespesa.setVisibility(View.GONE);
                layoutCategoria.setVisibility(View.GONE);

                layoutReceita.startAnimation(mHideLayout);
                layoutDespesa.startAnimation(mHideLayout);
                layoutCategoria.startAnimation(mHideLayout);
                fab.startAnimation(mHideButton);

                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_add_despesa, null);

                final EditText mDescricao = (EditText) mView.findViewById(R.id.editDescricao);
                final EditText mVlDespesa = (EditText) mView.findViewById(R.id.editValor);
                final Spinner mTpDespesa = (Spinner) mView.findViewById(R.id.spinTpReceita);
                final Button mAddDespesa = (Button) mView.findViewById(R.id.btnAddDespesa);

                ArrayAdapter adapter = ArrayAdapter.createFromResource(MenuActivity.this,
                        tp_receita,
                        android.R.layout.simple_spinner_dropdown_item);
                mTpDespesa.setAdapter(adapter);

                mBuilder.setView(mView);
                final AlertDialog alert = mBuilder.create();

                mAddDespesa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();

                        if (id == R.id.btnAddDespesa) {

                            if (mDescricao.getText().toString().isEmpty()) {
                                mDescricao.setError("descrição");
                            } else if (mVlDespesa.getText().toString().isEmpty()) {
                                mVlDespesa.setError("valor da receita");
                            } else {
                                try {
                                    // banco
                                    BDController crud = new BDController(getBaseContext());
                                    Despesas des = new Despesas();
                                    String resultado;

                                    DecimalFormat format = new DecimalFormat("0.##");

                                    des.setDsDespesa(mDescricao.getText().toString());
                                    des.setVlDespesa(Double.valueOf(mVlDespesa.getText().toString()));
                                    des.setDsCategDespesa(mTpDespesa.getSelectedItem().toString());

                                    resultado = crud.insDespesa(des);

                                    Toast.makeText(getApplication().getApplicationContext(),
                                            resultado, Toast.LENGTH_LONG).show();
                                    alert.dismiss();

                                } catch (Exception exeption) {
                                    Toast.makeText(getApplication().getApplicationContext(),
                                            "Erro: " + exeption, Toast.LENGTH_LONG).show();
                                }
                            }

                        }
                    }
                });
                alert.show();
            }
        });

        fabAddCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutReceita.setVisibility(View.GONE);
                layoutDespesa.setVisibility(View.GONE);
                layoutCategoria.setVisibility(View.GONE);

                layoutReceita.startAnimation(mHideLayout);
                layoutDespesa.startAnimation(mHideLayout);
                layoutCategoria.startAnimation(mHideLayout);
                fab.startAnimation(mHideButton);

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_add_categoria, null);

                final EditText mDescricao = (EditText) mView.findViewById(R.id.editDescricao);
                final CheckBox mCheckDespesa = (CheckBox) mView.findViewById(R.id.checkDespesa);
                final CheckBox mCheckReceita = (CheckBox) mView.findViewById(R.id.checkReceita);
                Button mAddCategoria = (Button) mView.findViewById(R.id.btnAddCategoria);

                mBuilder.setView(mView);
                final AlertDialog alert = mBuilder.create();

                mAddCategoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();

                        if (id == R.id.btnAddCategoria) {
                            if (mDescricao.getText().toString().isEmpty()) {
                                mDescricao.setError("descrição");
                            } else if (mCheckDespesa.isChecked() && mCheckReceita.isChecked()) {
                                Toast.makeText(getApplicationContext(), "selecione apenas uma opção", Toast.LENGTH_LONG).show();
                                mCheckDespesa.setError("!");
                                mCheckReceita.setError("!");
                            } else if (!mCheckDespesa.isChecked() && !mCheckReceita.isChecked()) {
                                Toast.makeText(getApplicationContext(), "selecione uma opção", Toast.LENGTH_LONG).show();
                                mCheckDespesa.setError("!");
                                mCheckReceita.setError("!");
                            } else {
                                try {
                                    // banco
                                    BDController crud = new BDController(getBaseContext());
                                    String dsCategoria;
                                    String resultado;

                                    dsCategoria = mDescricao.getText().toString();

                                    if (mCheckDespesa.isChecked()) {
                                         resultado = crud.insTpCategDespesa(mDescricao.getText().toString());
                                    } else {
                                         resultado = crud.insTpCategReceita(mDescricao.getText().toString());
                                    }


                                    Toast.makeText(getApplication().getApplicationContext(),
                                            resultado, Toast.LENGTH_LONG).show();
                                    alert.dismiss();


                                } catch (Exception exeption) {
                                    Toast.makeText(getApplication().getApplicationContext(),
                                            "Erro: " + exeption, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });
                alert.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.itemResumo) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new PrincipalFragment()).commit();

        } else if (id == R.id.itemReceita) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new ListaReceitaFragment()).commit();

        } else if (id == R.id.itemDespesa) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new ListaDespesaFragment()).commit();

        } else if (id == R.id.itemCategoria) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new CategoriaFragment()).commit();

        } else if (id == R.id.itemSair) {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static class ViewHolder {
        FloatingActionButton fab;
    }
}
