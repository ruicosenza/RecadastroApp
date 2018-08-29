package br.com.vasco.anistia;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import br.com.vasco.anistia.models.Socio;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private List<Socio> mResultItems;

    public ResultAdapter(List<Socio> resultItems) {
        mResultItems = resultItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.item, viewGroup, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Socio result = mResultItems.get(i);

        if (i % 2 == 0) {
            viewHolder.itemContainer.setBackgroundColor(Color.parseColor("#cccccc"));
        } else {
            viewHolder.itemContainer.setBackgroundColor(Color.parseColor("#666666"));
        }

        TextView itemName = viewHolder.itemNome;
        TextView itemCpf = viewHolder.itemCpf;
        TextView itemMatricula = viewHolder.itemMatricula;

        itemName.setText(result.getNome());
        itemCpf.setText(result.getCpf());
        itemMatricula.setText(result.getMatricula());
    }

    @Override
    public int getItemCount() {
        return mResultItems.size();
    }

        public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemNome;
        public TextView itemCpf;
        public TextView itemMatricula;
        public RelativeLayout itemContainer;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            itemNome = itemView.findViewById(R.id.item_nome);
            itemCpf = itemView.findViewById(R.id.item_cpf);
            itemMatricula = itemView.findViewById(R.id.item_matricula);
            itemContainer = itemView.findViewById(R.id.item_container);
        }
    }
}