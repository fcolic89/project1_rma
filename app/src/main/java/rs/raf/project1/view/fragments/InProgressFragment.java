package rs.raf.project1.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import rs.raf.project1.R;
import rs.raf.project1.model.Ticket;
import rs.raf.project1.model.TicketViewModel;
import rs.raf.project1.view.activity.DetailActivity;
import rs.raf.project1.view.recycler.adapter.TicketDiffItemCallback;
import rs.raf.project1.view.recycler.adapter.TicketInProgressAdapter;
import rs.raf.project1.view.recycler.adapter.TicketToDoAdapter;

public class InProgressFragment extends Fragment {

    private EditText etInProgressSearch;
    private RecyclerView recyclerView;
    private TicketViewModel ticketViewModel;
    private TicketInProgressAdapter adapter;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;

    public InProgressFragment() {
        super(R.layout.fragment_in_progress);
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
                            ticketViewModel.updateInInProgress(ticket);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void init(View view){
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);

        initView(view);
        initListeners();
        initRecycler();
        initObservers();
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.inProgressRecyclerView);
        etInProgressSearch = view.findViewById(R.id.etInProgressSeatch);
    }

    private void initListeners(){
        etInProgressSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                ticketViewModel.filetrInProgress(s.toString());
            }
        });
    }

    private void initObservers(){
        ticketViewModel.getInProgress().observe(requireActivity(), tickets -> {
            adapter.submitList(tickets);
        });
    }

    private void initRecycler(){
        adapter = new TicketInProgressAdapter(new TicketDiffItemCallback(), ticketViewModel, ticket -> {
            Intent intent = new Intent(requireActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.TICKET_DETAIL_INPROGRESS, ticket);
            someActivityResultLauncher.launch(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(adapter);
    }
}