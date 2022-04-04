package rs.raf.project1.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.project1.R;
import rs.raf.project1.model.Ticket;
import rs.raf.project1.model.TicketViewModel;
import rs.raf.project1.view.activity.DetailActivity;
import rs.raf.project1.view.activity.LoginActivity;
import rs.raf.project1.view.recycler.adapter.TicketDiffItemCallback;
import rs.raf.project1.view.recycler.adapter.TicketToDoAdapter;

public class ToDoFragment extends Fragment {

    private EditText etToDoSearch;
    private RecyclerView recyclerView;
    private TicketViewModel ticketViewModel;
    private TicketToDoAdapter adapter;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;

    public ToDoFragment() {
        super(R.layout.fragment_to_do);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
         someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            Ticket ticket = data.getParcelableExtra(DetailActivity.TICKET_DETAIL_TODO);
                            ticketViewModel.updateInToDo(ticket);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void init(View view){
        ticketViewModel = new ViewModelProvider(getActivity()).get(TicketViewModel.class);

        initView(view);
        initListeners();
        initRecycler();
        initObservers();
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.ToDoRecyclerView);
        etToDoSearch = view.findViewById(R.id.etToDoSeatch);
    }

    private void initListeners(){
        etToDoSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                ticketViewModel.filterToDo(s.toString());
            }
        });
    }

    private void initObservers(){
        ticketViewModel.getToDo().observe(requireActivity(), tickets -> {
            adapter.submitList(tickets);
        });
    }



    private void initRecycler(){
        adapter = new TicketToDoAdapter(new TicketDiffItemCallback(), ticketViewModel, ticket -> {
            //Toast.makeText(requireActivity(), ticket.getTitle(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(requireActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.TICKET_DETAIL_TODO, ticket);
            someActivityResultLauncher.launch(intent);

        });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(adapter);
    }
}