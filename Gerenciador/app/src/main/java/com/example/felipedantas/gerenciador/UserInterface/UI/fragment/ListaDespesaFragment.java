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
import com.example.felipedantas.gerenciador.Constants.ConstantsStrings;
import com.example.felipedantas.gerenciador.DataLayer.DL.BDController;
import com.example.felipedantas.gerenciador.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaDespesaFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();

    public ListaDespesaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_despesa, container, false);

        BDController crud = new BDController(getContext());
        final Cursor cursor = crud.buscaDespesaGrid();
        try {
            // banco

            String[] nomeCampos = new String[]{ConstantsStrings.DESPESAS_id_despesa,
                    ConstantsStrings.DESPESAS_ds_despesa,
                    ConstantsStrings.DESPESAS_vl_despesa};

            int[] idViews = new int[]{R.id.idProduto,
                    R.id.descricaoProduto,
                    R.id.valorProduto};

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(),
                    R.layout.list_style_produto, cursor, nomeCampos, idViews, 0);

            this.mViewHolder.listDespesa = (ListView) view.findViewById(R.id.listDespesas);
            this.mViewHolder.listDespesa.setAdapter(adapter);

        } catch (Exception exception) {
            Toast.makeText(getActivity().getApplicationContext(), "Erro" + exception, Toast.LENGTH_LONG).show();
        }

        this.mViewHolder.listDespesa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(ConstantsStrings.DESPESAS_id_despesa));
                Intent intent = new Intent(getActivity().getApplicationContext(), AlteraDadosDesActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
            }
        });

        return view;
    }

    private static class ViewHolder{
        ListView listDespesa;
    }
}
