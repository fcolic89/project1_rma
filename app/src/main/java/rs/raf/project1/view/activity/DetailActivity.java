package rs.raf.project1.view.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import rs.raf.project1.R;
import rs.raf.project1.model.Ticket;
import rs.raf.project1.model.TicketType;
import rs.raf.project1.model.TicketViewModel;

public class DetailActivity extends AppCompatActivity {

    public static final String TICKET_DETAIL_TODO = "TICKET_DETAIL_TODO";
    public static final String TICKET_DETAIL_INPROGRESS = "TICKET_DETAIL_INPROGRESS";
    public static final String TICKET_DETAIL_DONE = "TICKET_DETAIL_DONE";

    private Ticket ticket;
    private String ticketFrom;

    private TextView tvTicketType, tvTicketPriority, tvEstimation, tvTicketDesctiprtion, tvTicketName;
    private Button btnLoggedTime, btnEdit;
    private ImageView ticketIcon;

    private ActivityResultLauncher<Intent> someActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            Ticket ticket = data.getParcelableExtra(EditActivity.EDITED);
                            setView(ticket);
                            setTicket(ticket);
                        }
                    }
                });

        init();
    }

    private void init(){
        getTicket();
        initView();
        setView(ticket);
        initListeners();
    }

    private void initView(){
        ticketIcon = findViewById(R.id.imgDetailIcon);
        tvTicketName = findViewById(R.id.tvDetailName);
        tvTicketType = findViewById(R.id.tvDetailTicketType_2);
        tvTicketPriority = findViewById(R.id.tvDetailTicketPriority_2);
        tvEstimation = findViewById(R.id.tvDetailEstimation_2);
        tvTicketDesctiprtion = findViewById(R.id.tvDetailTicketDesc_2);
        btnLoggedTime = findViewById(R.id.btnDetailLoggedTime);
        btnEdit = findViewById(R.id.btnDetailEdit);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        if(sharedPreferences.getString(LoginActivity.PREF_LOGGEDIN, "").equals("REGULAR") || ticketFrom.equals("done"))
            btnEdit.setVisibility(View.GONE);
    }


    private void setView(Ticket ticket){
        if(ticket == null)
            finish();

        if(ticket.getType() == TicketType.BUG)
            ticketIcon.setImageResource(R.drawable.ic_bug);
        else
            ticketIcon.setImageResource(R.drawable.ic_rocket);

        if(ticket.getTitle() != null && !ticket.getTitle().equals(""))
            tvTicketName.setText(ticket.getTitle());
        if(ticket.getType().toString() != null && !ticket.getType().toString().equals(""))
            tvTicketType.setText(ticket.getType().toString());
        if(ticket.getPriority().toString() != null && !ticket.getPriority().toString().equals(""))
            tvTicketPriority.setText(ticket.getPriority().toString());
        if(ticket.getTicetDesc() != null && !ticket.getTicetDesc().equals(""))
            tvTicketDesctiprtion.setText(ticket.getTicetDesc());
        tvEstimation.setText(ticket.getEstemated()+"");
        btnLoggedTime.setText(ticket.getLoggedTime()+"");
    }

    private void initListeners(){
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra(EditActivity.EDITED, ticket);
            someActivityResultLauncher.launch(intent);
        });
        btnLoggedTime.setOnClickListener(v -> {
            ticket.setLoggedTime(ticket.getLoggedTime()+1);
            btnLoggedTime.setText(ticket.getLoggedTime()+"");
        });
        btnLoggedTime.setOnLongClickListener(v -> {
            ticket.setLoggedTime(ticket.getLoggedTime()-1);
            btnLoggedTime.setText(ticket.getLoggedTime()+"");
            return true;
        });
    }

    private void getTicket(){
        if((ticket = getIntent().getParcelableExtra(TICKET_DETAIL_TODO)) != null)
            ticketFrom = "todo";
        else if((ticket = getIntent().getParcelableExtra(TICKET_DETAIL_INPROGRESS)) != null)
            ticketFrom = "inProgress";
        else if((ticket = getIntent().getParcelableExtra(TICKET_DETAIL_DONE)) != null)
            ticketFrom = "done";
    }

    @Override
    protected void onPause() {
        Intent intent = new Intent();
        intent.putExtra(TICKET_DETAIL_TODO, ticket);
        setResult(RESULT_OK, intent);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(TICKET_DETAIL_TODO, ticket);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    private void setTicket(Ticket t){
        ticket = t;
    }
}