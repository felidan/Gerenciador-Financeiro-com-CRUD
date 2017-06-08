package com.example.felipedantas.gerenciador.UserInterface.UI.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.felipedantas.gerenciador.Classes.Despesas;
import com.example.felipedantas.gerenciador.DataLayer.DL.BDController;
import com.example.felipedantas.gerenciador.R;

import java.util.List;
import java.util.StringTokenizer;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriaFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();

    public CategoriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_categoria, container, false);

        try{
            // banco
            BDController crud = new BDController(getContext());
            List<String> categReceita = crud.buscaCategReceita();

            this.mViewHolder.listCategReceita = (ListView) view.findViewById(R.id.listCategReceita);

            ArrayAdapter<String> adapterReceita = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_list_item_1, categReceita);

            this.mViewHolder.listCategReceita.setAdapter(adapterReceita);

            //-------------------------------------

            List<String> categDespesa = crud.buscaCategDespesa();

            this.mViewHolder.listCategDespesa = (ListView) view.findViewById(R.id.listCategDespesa);

            ArrayAdapter<String> adapterDespesa = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_list_item_1, categDespesa);

            this.mViewHolder.listCategDespesa.setAdapter(adapterDespesa);


        }catch (Exception execption){
            Toast.makeText(getActivity().getApplicationContext(), "Erro" + execption, Toast.LENGTH_LONG).show();
        }


        return view;
    }


    private static class ViewHolder {
        ListView listCategReceita;
        ListView listCategDespesa;
    }

}
