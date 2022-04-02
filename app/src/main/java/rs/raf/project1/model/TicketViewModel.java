package rs.raf.project1.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class TicketViewModel extends ViewModel {
    //ticket lists
    private final MutableLiveData<List<Ticket>> toDo = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> inProgress = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> done = new MutableLiveData<>();

    private List<Ticket> toDoList = new ArrayList<>();
    private List<Ticket> inProgressList = new ArrayList<>();
    private List<Ticket> doneList = new ArrayList<>();

    //to_do counters
    private MutableLiveData<Integer> toDoCnt = new MutableLiveData<>(0);
    private MutableLiveData<Integer> toDoEnhancementCnt = new MutableLiveData<>(0);
    private MutableLiveData<Integer> toDoBugCnt = new MutableLiveData<>(0);

    //in progress counters
    private MutableLiveData<Integer> inProgressCnt = new MutableLiveData<>(0);
    private MutableLiveData<Integer> inProgressEnhancementCnt = new MutableLiveData<>(0);
    private MutableLiveData<Integer> inProgressBugCnt = new MutableLiveData<>(0);

    //done counters
    private MutableLiveData<Integer> doneCnt = new MutableLiveData<>(0);
    private MutableLiveData<Integer> doneEnhancementCnt = new MutableLiveData<>(0);
    private MutableLiveData<Integer> doneBugCnt = new MutableLiveData<>(0);


    public void addTicket(Ticket newTicket){

        toDoCnt.setValue(toDoCnt.getValue()+1);
        if(newTicket.getType() == TicketType.BUG)
            toDoBugCnt.setValue(toDoBugCnt.getValue()+1);
        else
            toDoEnhancementCnt.setValue(toDoEnhancementCnt.getValue()+1);

        toDoList.add(newTicket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(toDoList);
        toDo.setValue(listToSubmit);
    }


    public MutableLiveData<List<Ticket>> getToDo() {
        return toDo;
    }

    public MutableLiveData<List<Ticket>> getInProgress() {
        return inProgress;
    }

    public MutableLiveData<List<Ticket>> getDone() {
        return done;
    }

    public MutableLiveData<Integer> getToDoCnt() {
        return toDoCnt;
    }

    public MutableLiveData<Integer> getInProgressCnt() {
        return inProgressCnt;
    }

    public MutableLiveData<Integer> getDoneCnt() {
        return doneCnt;
    }

    public MutableLiveData<Integer> getToDoEnhancementCnt() {
        return toDoEnhancementCnt;
    }

    public MutableLiveData<Integer> getToDoBugCnt() {
        return toDoBugCnt;
    }

    public MutableLiveData<Integer> getInProgressEnhancementCnt() {
        return inProgressEnhancementCnt;
    }

    public MutableLiveData<Integer> getInProgressBugCnt() {
        return inProgressBugCnt;
    }

    public MutableLiveData<Integer> getDoneEnhancementCnt() {
        return doneEnhancementCnt;
    }

    public MutableLiveData<Integer> getDoneBugCnt() {
        return doneBugCnt;
    }
}
