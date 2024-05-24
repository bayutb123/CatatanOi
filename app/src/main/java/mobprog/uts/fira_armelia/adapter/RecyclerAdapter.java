package mobprog.uts.fira_armelia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mobprog.uts.fira_armelia.R;
import mobprog.uts.fira_armelia.response.DataCatatanItem;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<DataCatatanItem> dataCatatanItems;

    public RecyclerAdapter(List<DataCatatanItem> dataCatatanItems) {
        this.dataCatatanItems = dataCatatanItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataCatatanItem item = dataCatatanItems.get(position);
        holder.textViewTitle.setText(item.getJudulCatatan());
        holder.textViewDetail.setText(item.getDetailCatatan());
    }

    @Override
    public int getItemCount() {
        return dataCatatanItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.judulCatatanList);
            textViewDetail = itemView.findViewById(R.id.isiCatatanList);
        }
    }
}
