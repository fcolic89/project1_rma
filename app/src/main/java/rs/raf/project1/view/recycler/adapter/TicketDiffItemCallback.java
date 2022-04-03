package rs.raf.project1.view.recycler.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.project1.model.Ticket;

public class TicketDiffItemCallback extends DiffUtil.ItemCallback<Ticket> {
    @Override
    public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getType().equals(newItem.getType())
                && oldItem.getPriority().equals(newItem.getPriority())
                && oldItem.getTitle().equals(newItem.getTitle())
                && oldItem.getTicetDesc().equals(newItem.getTicetDesc());
    }
}
