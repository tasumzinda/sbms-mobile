package org.totalit.sbms.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.totalit.sbms.R;
import org.totalit.sbms.domain.Client;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {
    List<Client> clients;

    public ClientAdapter(List<Client> clients) {
        this.clients = clients;
    }

    @NonNull
    @Override
    public ClientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clients_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientAdapter.ViewHolder holder, int position) {
        holder.name.setText(clients.get(position).getName());
        holder.descr.setText(clients.get(position).getDescription() );

    }

    @Override
    public int getItemCount() {
        return clients.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView descr;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.client_name);
            descr = itemView.findViewById(R.id.client_desciption);

        }
    }
}