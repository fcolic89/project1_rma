package rs.raf.project1.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.project1.R;
import rs.raf.project1.model.TicketViewModel;
import rs.raf.project1.view.recycler.adapter.TicketDiffItemCallback;
import rs.raf.project1.view.recycler.adapter.TicketToDoAdapter;

public class ToDoFragment extends Fragment {

    private EditText etToDoSearch;
    private RecyclerView recyclerView;
    private TicketViewModel ticketViewModel;
    private TicketToDoAdapter adapter;

    public ToDoFragment() {
        super(R.layout.fragment_to_do);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    private void init(View view){
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);

        initView(view);
//        initListeners();
        initRecycler();
        initObservers();
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.ToDoRecyclerView);
        etToDoSearch = view.findViewById(R.id.etToDoSeatch);
    }

    private void initObservers(){
        ticketViewModel.getToDo().observe(requireActivity(), tickets -> {
            adapter.submitList(tickets);
        });
    }

    private void initRecycler(){
        adapter = new TicketToDoAdapter(new TicketDiffItemCallback(), ticketViewModel, ticket -> {
            Toast.makeText(requireActivity(), ticket.getTitle(), Toast.LENGTH_LONG).show();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(adapter);
    }

}