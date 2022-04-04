package rs.raf.project1.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;

import rs.raf.project1.R;
import rs.raf.project1.model.Ticket;
import rs.raf.project1.model.TicketPriority;
import rs.raf.project1.model.TicketType;

public class EditActivity extends AppCompatActivity {

    private Spinner spTicketType, spTicketPriority;
    private EditText etEstimate, etTicketTitle, etTicketDesc;
    private Button btnSave;
    private Ticket ticket;

    public static final String EDITED = "EDITED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
    }

    private void init(){
        initView();
        initListener();
    }

    private void initView(){
        ticket = getIntent().getParcelableExtra(EDITED);

        spTicketType = findViewById(R.id.spTicketTypeEdit);
        spTicketPriority = findViewById(R.id.spTicketPriorityEdit);
        etEstimate = findViewById(R.id.etEstimateEdit);
        etTicketTitle = findViewById(R.id.etTicketTitleEdit);
        etTicketDesc = findViewById(R.id.etTicketDescEdit);
        btnSave = findViewById(R.id.btnSaveEdit);

        etEstimate.setText(ticket.getEstemated()+"");
        etTicketTitle.setText(ticket.getTitle());
        etTicketDesc.setText(ticket.getTicetDesc());

        ArrayAdapter typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Arrays.stream(TicketType.values()).toArray());
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTicketType.setAdapter(typeAdapter);

        ArrayAdapter priotityAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Arrays.stream(TicketPriority.values()).toArray());
        priotityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTicketPriority.setAdapter(priotityAdapter);

    }

    private void initListener() {
        btnSave.setOnClickListener(v -> {
            ticket.setType(TicketType.valueOf(spTicketType.getSelectedItem().toString()));
            ticket.setPriority(TicketPriority.valueOf(spTicketPriority.getSelectedItem().toString()));
            if(checkDigit(etEstimate.getText().toString()))
                ticket.setEstemated(Integer.parseInt(etEstimate.getText().toString()));
            ticket.setTitle(etTicketTitle.getText().toString());
            ticket.setTicetDesc(etTicketDesc.getText().toString());

            Intent intent = new Intent();
            intent.putExtra(EDITED, ticket);
            setResult(RESULT_OK, intent);
            finish();
        });
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