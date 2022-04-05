package rs.raf.project1.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import rs.raf.project1.R;
import rs.raf.project1.model.TicketViewModel;
import rs.raf.project1.view.activity.DetailActivity;
import rs.raf.project1.view.recycler.adapter.TicketDiffItemCallback;
import rs.raf.project1.view.recycler.adapter.TicketDoneAdapter;
import rs.raf.project1.view.recycler.adapter.TicketToDoAdapter;

public class DoneFragment extends Fragment {
    private EditText etDoneSearch;
    private RecyclerView recyclerView;
    private TicketViewModel ticketViewModel;
    private TicketDoneAdapter adapter;

    public DoneFragment() {
        super(R.layout.fragment_done);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    private void init(View view){
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);

        initView(view);
        initListeners();
        initRecycler();
        initObservers();
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.doneRecyclerView);
        etDoneSearch = view.findViewById(R.id.etDoneSeatch);
    }

    private void initListeners(){
        etDoneSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                ticketViewModel.filterDone(s.toString());
            }
        });
    }

    private void initObservers(){
        ticketViewModel.getDone().observe(requireActivity(), tickets -> {
            adapter.submitList(tickets);
        });
    }

    private void initRecycler(){
        adapter = new TicketDoneAdapter(new TicketDiffItemCallback(), ticket -> {
            //Toast.makeText(requireActivity(), ticket.getTitle(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(requireActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.TICKET_DETAIL_DONE, ticket);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(adapter);
    }

}