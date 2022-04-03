package rs.raf.project1.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rs.raf.project1.R;
import rs.raf.project1.model.Ticket;
import rs.raf.project1.model.TicketPriority;
import rs.raf.project1.model.TicketType;
import rs.raf.project1.model.TicketViewModel;

public class NewTicketFragment extends Fragment {

    private Spinner spTicketType;
    private Spinner spTicketPriority;
    private EditText etEstimate;
    private EditText etTicketTitle;
    private EditText etTicketDesc;
    private Button btnAddTicket;

    private TicketViewModel ticketViewModel;

    public NewTicketFragment() {

        super(R.layout.fragment_new_ticket);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    private void init(View view){
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);

        spTicketType = view.findViewById(R.id.spTicketType);
        spTicketPriority = view.findViewById(R.id.spTicketPriority);
        etEstimate = view.findViewById(R.id.etEstimate);
        etTicketTitle = view.findViewById(R.id.etTicketTitle);
        etTicketDesc = view.findViewById(R.id.etTicketDesc);
        btnAddTicket = view.findViewById(R.id.btnAddNewTicket);

        ArrayAdapter typeAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, Arrays.stream(TicketType.values()).toArray());
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTicketType.setAdapter(typeAdapter);

        ArrayAdapter priotityAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, Arrays.stream(TicketPriority.values()).toArray());
        priotityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTicketPriority.setAdapter(priotityAdapter);



        btnAddTicket.setOnClickListener(v -> {
            if(!isFilled())
                Toast.makeText(requireActivity(), "All fields must be filled!", Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(requireActivity(), "OK", Toast.LENGTH_LONG).show();
                ticketViewModel.moveToToDo(new Ticket(etTicketTitle.getText().toString(),
                                                    etTicketDesc.getText().toString(),
                                                    Integer.parseInt(etEstimate.getText().toString()),
                                                    TicketType.valueOf(spTicketType.getSelectedItem().toString()),
                                                    TicketPriority.valueOf(spTicketPriority.getSelectedItem().toString())));
            }
        });
    }

    private boolean isFilled(){
        return spTicketPriority.getSelectedItem() != null
                && spTicketType.getSelectedItem() != null
                && etEstimate.getText() != null && !etEstimate.getText().toString().equals("") && checkDigit(etEstimate.getText().toString())
                && etTicketTitle.getText() != null && !etTicketTitle.getText().toString().equals("")
                && etTicketDesc.getText() != null && !etTicketDesc.getText().toString().equals("");
    }

    private boolean checkDigit(String text){

        try{
            Integer.parseInt(text);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}