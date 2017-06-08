package com.example.felipedantas.gerenciador;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.felipedantas.gerenciador.Classes.Despesas;
import com.example.felipedantas.gerenciador.Constants.ConstantsStrings;
import com.example.felipedantas.gerenciador.DataLayer.DL.BDController;

import static com.example.felipedantas.gerenciador.R.array.tp_receita;

public class AlteraDadosDesActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_dados_despesa);

        BDController crud = new BDController(getBaseContext());
        String codigo = this.getIntent().getStringExtra("codigo");
        Cursor cursor = crud.carregaDespesa(Integer.parseInt(codigo));

        this.mViewHolder.editDescricao = (EditText) findViewById(R.id.editAlterarDescricao);
        this.mViewHolder.editValor = (EditText) findViewById(R.id.editAlterarValor);
        this.mViewHolder.spinTpMovimento = (Spinner) findViewById(R.id.spinAlterarTpMovimento);
        this.mViewHolder.btnAlterar = (Button) findViewById(R.id.btnAlterar);
        this.mViewHolder.btnExcluir = (Button) findViewById(R.id.btnExcluir);


        this.mViewHolder.editDescricao.setText(cursor.getString(cursor.getColumnIndexOrThrow(ConstantsStrings.DESPESAS_ds_despesa)));
        this.mViewHolder.editValor.setText(cursor.getString(cursor.getColumnIndexOrThrow(ConstantsStrings.DESPESAS_vl_despesa)));

        ArrayAdapter adapter = ArrayAdapter.createFromResource(AlteraDadosDesActivity.this,
                tp_receita,
                android.R.layout.simple_spinner_dropdown_item);

        this.mViewHolder.spinTpMovimento .setAdapter(adapter);

        this.mViewHolder.btnAlterar.setOnClickListener(this);
        this.mViewHolder.btnExcluir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.btnAlterar){


            try {
                BDController crud = new BDController(getBaseContext());
                String codigo = this.getIntent().getStringExtra("codigo");


                    Despesas des = new Despesas();
                    des.setIdDespesa(Integer.parseInt(codigo));
                    des.setDsDespesa(this.mViewHolder.editDescricao.getText().toString());
                    des.setVlDespesa(Double.valueOf(this.mViewHolder.editValor.getText().toString()));
                    des.setDsCategDespesa(this.mViewHolder.spinTpMovimento.getSelectedItem().toString());

                    String resul = crud.updDespesa(des);

                    Toast.makeText(this, resul, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);


            } catch (Exception ex){
                Toast.makeText(this, "Erro: " + ex, Toast.LENGTH_LONG).show();
            }
        }
        if (id == R.id.btnExcluir){
            try{
                BDController crud = new BDController(getBaseContext());
                String codigo = this.getIntent().getStringExtra("codigo");
                String resul = crud.delDespesa(Integer.parseInt(codigo));

                Toast.makeText(this, resul, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
            }
            catch (Exception ex){
                Toast.makeText(this, "Erro: " + ex, Toast.LENGTH_LONG).show();
            }
        }
    }

    private static class ViewHolder{
        EditText editDescricao;
        EditText editValor;
        Spinner spinTpMovimento;
        Button btnAlterar;
        Button btnExcluir;
    }
}
