package rs.raf.project1.view.recycler.adapter;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Consumer;

import rs.raf.project1.R;
import rs.raf.project1.model.Ticket;
import rs.raf.project1.model.TicketType;
import rs.raf.project1.model.TicketViewModel;

public class TicketToDoAdapter extends ListAdapter<Ticket, TicketToDoAdapter.ViewHolder> {
    private final Consumer<Ticket> onTicketClicked;
    private TicketViewModel ticketViewModel;

    public TicketToDoAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, TicketViewModel ticketViewModel, Consumer<Ticket> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
        this.ticketViewModel = ticketViewModel;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false);
        return new ViewHolder(view, parent.getContext(), position -> {
           Ticket ticket = getItem(position);
           onTicketClicked.accept(ticket);
        });

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = getItem(position);
        holder.bind(ticket);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        //private TicketViewModel ticketViewModel;

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
            ImageView imageView = itemView.findViewById(R.id.imgToDoItem);
            if(ticket.getType() == TicketType.BUG)
                imageView.setImageResource(R.drawable.ic_bug);
            else
                imageView.setImageResource(R.drawable.ic_rocket);

            TextView tv1 = itemView.findViewById(R.id.tvToDoItemTitle);
            tv1.setText(ticket.getTitle());

            TextView tv2 = itemView.findViewById(R.id.tvToDoItemDesc);
            tv2.setText(ticket.getTicetDesc());

            ImageView imgRemoveTicket = itemView.findViewById(R.id.imgToDoItemMinus);
            imgRemoveTicket.setOnClickListener(v -> {
                ticketViewModel.removeFromToDo(ticket);
            });

            ImageView imgMoveToInProgress = itemView.findViewById(R.id.imgToDoItemRigth);
            imgMoveToInProgress.setOnClickListener(v -> {
                ticketViewModel.moveToInProgress(ticket);
            });
        }
    }
}
