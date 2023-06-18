package com.example.semana9.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana9.R;
import com.example.semana9.entities.Comentario;
import com.example.semana9.ui.theme.ListaComentariosActivity;

import java.util.List;

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioAdapter.ComentarioViewHolder> {

    private List<Comentario> items;
    private ListaComentariosActivity listaComentariosActivity;
    private Context context;
    public ComentarioAdapter(List<Comentario> items, ListaComentariosActivity listaComentariosActivity) {
        this.items = items;
        this.listaComentariosActivity = listaComentariosActivity;
    }

    @NonNull
    @Override
    public ComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.comentario_string, parent, false);
        ComentarioViewHolder viewHolder = new ComentarioViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioViewHolder holder, int position) {
        Comentario item = items.get(position);
        View view = holder.itemView;

        TextView tvComentario = view.findViewById(R.id.tvComentario);
        tvComentario.setText(item.getContenido());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ComentarioViewHolder extends RecyclerView.ViewHolder {

        public ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void actualizarLista(List<Comentario> nuevaLista) {
        items.clear();
        items.addAll(nuevaLista);
        notifyDataSetChanged();
    }
}
