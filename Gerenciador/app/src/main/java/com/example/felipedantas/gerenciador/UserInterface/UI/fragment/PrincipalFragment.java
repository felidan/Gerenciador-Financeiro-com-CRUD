package com.example.felipedantas.gerenciador.UserInterface.UI.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felipedantas.gerenciador.DataLayer.DL.BDController;
import com.example.felipedantas.gerenciador.R;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalFragment extends Fragment implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    public PrincipalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        this.mViewHolder.cardReceita = (CardView) view.findViewById(R.id.cardReceita);
        this.mViewHolder.cardDespesa = (CardView) view.findViewById(R.id.cardDespesa);

        this.mViewHolder.txtReceitaTotal = (TextView) view.findViewById(R.id.txtReceitaTotal);
        this.mViewHolder.txtDespesaTotal = (TextView) view.findViewById(R.id.txtDespesaTotal);
        this.mViewHolder.txtSaldoTotal = (TextView) view.findViewById(R.id.txtSaldoTotal);

        atualizaValores();

        this.mViewHolder.cardReceita.setOnClickListener(this);
        this.mViewHolder.cardDespesa.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.cardReceita){

            Toast.makeText(getActivity().getApplicationContext(), "click do card receita", Toast.LENGTH_LONG).show();

        }
        if(id == R.id.cardDespesa){

            Toast.makeText(getActivity().getApplicationContext(), "click do card despesa", Toast.LENGTH_LONG).show();

        }
    }

    private static class ViewHolder{
        TextView txtDespesaTotal;
        TextView txtReceitaTotal;
        TextView txtSaldoTotal;
        CardView cardReceita;
        CardView cardDespesa;
    }

    private void atualizaValores(){
        BDController crud = new BDController(getContext());
        Double receitaTotal = crud.buscaReceiraTotal();
        Double despesaTotal = crud.buscaDespesaTotal();
        Double saldoTotal = Double.valueOf(String.format(Locale.US, "%.2f", receitaTotal - despesaTotal));

        try{
            this.mViewHolder.txtReceitaTotal.setText(String.valueOf(Double.valueOf(String.format(Locale.US, "%.2f", receitaTotal))));
            this.mViewHolder.txtDespesaTotal.setText(String.valueOf(Double.valueOf(String.format(Locale.US, "%.2f", despesaTotal))));
            this.mViewHolder.txtSaldoTotal.setText(String.valueOf(saldoTotal));

        } catch (Exception exception){
            Toast.makeText(getActivity().getApplicationContext(), "Erro" + exception, Toast.LENGTH_LONG).show();
        }
    }
}
