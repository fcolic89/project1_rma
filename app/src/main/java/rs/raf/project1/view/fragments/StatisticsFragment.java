package rs.raf.project1.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import rs.raf.project1.R;
import rs.raf.project1.model.TicketPriority;
import rs.raf.project1.model.TicketViewModel;

public class StatisticsFragment extends Fragment {

    private TicketViewModel ticketViewModel;
    private TextView tvToDoCnt, tvToDoEnhancementCnt, tvToDoBugCnt;
    private TextView tvInProgressCnt, tvInProgressEnhancementCnt, tvInProgressBugCnt;
    private TextView tvDoneCnt, tvDoneEnhancementCnt, tvDoneBugCnt;



    public StatisticsFragment() {
        super(R.layout.fragment_statistics);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    private void init(View view){
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);

        initView(view);
        initObservers();
    }

    private void initView(View view){
        tvToDoCnt = view.findViewById(R.id.tvToDoCnt);
        tvToDoEnhancementCnt = view.findViewById(R.id.tvToDoEnhancementCnt);
        tvToDoBugCnt = view.findViewById(R.id.tvToDoBugsCnt);

        tvInProgressCnt = view.findViewById(R.id.tvInProgressCnt);
        tvInProgressEnhancementCnt = view.findViewById(R.id.tvInProgressEnhancementCnt);
        tvInProgressBugCnt = view.findViewById(R.id.tvInProgressBugsCnt);

        tvDoneCnt = view.findViewById(R.id.tvDoneCnt);
        tvDoneEnhancementCnt = view.findViewById(R.id.tvDoneEnhancementCnt);
        tvDoneBugCnt = view.findViewById(R.id.tvDoneBugsCnt);
    }

    private void initObservers(){
        ticketViewModel.getToDoCnt().observe(getViewLifecycleOwner(), (Integer) -> { tvToDoCnt.setText(Integer.toString());});
        ticketViewModel.getToDoEnhancementCnt().observe(getViewLifecycleOwner(), (Integer) -> { tvToDoEnhancementCnt.setText(Integer.toString());});
        ticketViewModel.getToDoBugCnt().observe(getViewLifecycleOwner(),(Integer) -> { tvToDoBugCnt.setText(Integer.toString());});

        ticketViewModel.getInProgressCnt().observe(getViewLifecycleOwner(),(Integer) -> { tvInProgressCnt.setText(Integer.toString());});
        ticketViewModel.getInProgressEnhancementCnt().observe(getViewLifecycleOwner(), (Integer -> { tvInProgressEnhancementCnt.setText(Integer.toString());}));
        ticketViewModel.getInProgressBugCnt().observe(getViewLifecycleOwner(), (Integer) -> { tvInProgressBugCnt.setText(Integer.toString());});

        ticketViewModel.getDoneCnt().observe(getViewLifecycleOwner(), (Integer) -> { tvDoneCnt.setText(Integer.toString());});
        ticketViewModel.getDoneEnhancementCnt().observe(getViewLifecycleOwner(),(Integer) -> {tvDoneEnhancementCnt.setText(Integer.toString());});
        ticketViewModel.getDoneBugCnt().observe(getViewLifecycleOwner(),(Integer) -> { tvDoneBugCnt.setText(Integer.toString());});
    }

}