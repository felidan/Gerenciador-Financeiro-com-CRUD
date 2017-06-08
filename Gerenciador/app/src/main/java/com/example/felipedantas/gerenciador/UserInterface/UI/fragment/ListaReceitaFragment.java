package com.example.felipedantas.gerenciador.UserInterface.UI.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.felipedantas.gerenciador.AlteraDadosDesActivity;
import com.example.felipedantas.gerenciador.AlteraDadosResActivity;
import com.example.felipedantas.gerenciador.Constants.ConstantsStrings;
import com.example.felipedantas.gerenciador.DataLayer.DL.BDController;
import com.example.felipedantas.gerenciador.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaReceitaFragment extends Fragment implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    public ListaReceitaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_receita, container, false);

        BDController crud = new BDController(getContext());
        final Cursor cursor = crud.buscaReceitasGrid();

        try {
            // banco

            String[] nomeCampos = new String[]{ConstantsStrings.RECEITAS_id_receita,
                    ConstantsStrings.RECEITAS_ds_receita,
                    ConstantsStrings.RECEITAS_vl_receita};

            int[] idViews = new int[]{R.id.idProduto,
                    R.id.descricaoProduto,
                    R.id.valorProduto};

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(),
                    R.layout.list_style_produto, cursor, nomeCampos, idViews, 0);

            this.mViewHolder.listReceita = (ListView) view.findViewById(R.id.listReceitas);
            this.mViewHolder.listReceita.setAdapter(adapter);

        } catch (Exception exception) {
            Toast.makeText(getActivity().getApplicationContext(), "Erro" + exception, Toast.LENGTH_LONG).show();
        }


        this.mViewHolder.listReceita.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsStrings.RECEITAS_id_receita));
                Intent intent = new Intent(getActivity().getApplicationContext(), AlteraDadosResActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

    }

    private static class ViewHolder {
        ListView listReceita;
    }

}
