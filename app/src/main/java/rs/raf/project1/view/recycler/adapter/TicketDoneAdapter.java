package rs.raf.project1.view.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Consumer;

import rs.raf.project1.R;
import rs.raf.project1.model.Ticket;
import rs.raf.project1.model.TicketType;
import rs.raf.project1.model.TicketViewModel;

public class TicketDoneAdapter extends ListAdapter<Ticket, TicketDoneAdapter.ViewHolder> {
    private final Consumer<Ticket> onTicketClicked;

    public TicketDoneAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.done_list_item, parent, false);
        return new TicketDoneAdapter.ViewHolder(view, parent.getContext(), position -> {
            Ticket ticket = getItem(position);
            onTicketClicked.accept(ticket);
        });

    }

    @Override
    public void onBindViewHolder(@NonNull TicketDoneAdapter.ViewHolder holder, int position) {
        Ticket ticket = getItem(position);
        holder.bind(ticket);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final Context context;

        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClick) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(v -> {
                if(getBindingAdapterPosition() != RecyclerView.NO_POSITION){
                    onItemClick.accept(getBindingAdapterPosition());
                }
            });
        }

        public void bind(Ticket ticket){
            ImageView imageView = itemView.findViewById(R.id.imgDoneItem);
            if(ticket.getType() == TicketType.BUG)
                imageView.setImageResource(R.drawable.ic_bug);
            else
                imageView.setImageResource(R.drawable.ic_rocket);

            TextView tv1 = itemView.findViewById(R.id.tvDoneItemTitle);
            tv1.setText(ticket.getTitle());

            TextView tv2 = itemView.findViewById(R.id.tvDoneItemDesc);
            tv2.setText(ticket.getTicetDesc());
        }
    }
}
