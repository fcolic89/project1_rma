package rs.raf.project1.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketViewModel extends ViewModel {

    private static int idCounter = 0;
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

    public TicketViewModel() {
        moveToToDo(new Ticket("title",  "ticetDesc", 1, TicketType.BUG, TicketPriority.HIGH));

    }

    public void moveToToDo(Ticket ticket){
        toDoCnt.setValue(toDoCnt.getValue()+1);
        if(ticket.getType() == TicketType.BUG)
            toDoBugCnt.setValue(toDoBugCnt.getValue()+1);
        else
            toDoEnhancementCnt.setValue(toDoEnhancementCnt.getValue()+1);
        if(ticket.getId() == 0)
            ticket.setId(++idCounter);
        toDoList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(toDoList);
        toDo.setValue(listToSubmit);
    }

    public void removeFromToDo(Ticket ticket){
        toDoCnt.setValue(toDoCnt.getValue()-1);
        if(ticket.getType() == TicketType.BUG)
            toDoBugCnt.setValue(toDoBugCnt.getValue()-1);
        else
            toDoEnhancementCnt.setValue(toDoEnhancementCnt.getValue()-1);
        toDoList.remove(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(toDoList);
        toDo.setValue(listToSubmit);
    }

    public void removeFromInProgress(Ticket ticket){
        inProgressCnt.setValue(inProgressCnt.getValue()-1);
        if(ticket.getType() == TicketType.BUG)
            inProgressBugCnt.setValue(inProgressBugCnt.getValue()-1);
        else
            inProgressEnhancementCnt.setValue(inProgressEnhancementCnt.getValue()-1);

        inProgressList.remove(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(inProgressList);
        inProgress.setValue(listToSubmit);
    }

    public void moveToInProgress(Ticket ticket){
        inProgressCnt.setValue(inProgressCnt.getValue()+1);
        if(ticket.getType() == TicketType.BUG)
            inProgressBugCnt.setValue(inProgressBugCnt.getValue()+1);
        else
            inProgressEnhancementCnt.setValue(inProgressEnhancementCnt.getValue()+1);

        inProgressList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(inProgressList);
        inProgress.setValue(listToSubmit);

        removeFromToDo(ticket);
    }

    public void moveBackToToDo(Ticket ticket){
        removeFromInProgress(ticket);
        moveToToDo(ticket);
    }

    public void moveToDone(Ticket ticket){
        doneCnt.setValue(doneCnt.getValue()+1);
        if(ticket.getType() == TicketType.BUG)
            doneBugCnt.setValue(doneBugCnt.getValue()+1);
        else
            doneEnhancementCnt.setValue(doneEnhancementCnt.getValue()+1);

        doneList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(doneList);
        done.setValue(listToSubmit);

        removeFromInProgress(ticket);
    }

    public void filterToDo(String s){
        List<Ticket> filteredList = toDoList.stream().filter(ticket -> ticket.getTitle().toLowerCase().startsWith(s.toLowerCase())).collect(Collectors.toList());
        toDo.setValue(filteredList);
    }
    public void filetrInProgress(String s){
        List<Ticket> filteredList = inProgressList.stream().filter(ticket -> ticket.getTitle().toLowerCase().startsWith(s.toLowerCase())).collect(Collectors.toList());
        inProgress.setValue(filteredList);
    }
    public void filterDone(String s){
        List<Ticket> filteredList = doneList.stream().filter(ticket -> ticket.getTitle().toLowerCase().startsWith(s.toLowerCase())).collect(Collectors.toList());
        done.setValue(filteredList);
    }

    public void updateInToDo(Ticket ticket){
        int i = 0;
        for(;i<toDoList.size();i++){
            if(ticket.getId() == toDoList.get(i).getId()) {
                Ticket tmp = toDoList.get(i);
                tmp.setLoggedTime(ticket.getLoggedTime());
                tmp.setEstemated(ticket.getEstemated());
                tmp.setPriority(ticket.getPriority());
                tmp.setTitle(ticket.getTitle());
                tmp.setTicetDesc(ticket.getTicetDesc());
                if(tmp.getType() != ticket.getType()) {
                    if(tmp.getType() == TicketType.BUG) {
                        toDoBugCnt.setValue(toDoBugCnt.getValue()-1);
                        toDoEnhancementCnt.setValue(toDoEnhancementCnt.getValue()+1);
                    }
                    else{
                        toDoBugCnt.setValue(toDoBugCnt.getValue()+1);
                        toDoEnhancementCnt.setValue(toDoEnhancementCnt.getValue()-1);
                    }
                    tmp.setType(ticket.getType());
                }
                break;
            }
        }
        ArrayList<Ticket> listToSubmit = new ArrayList<>(toDoList);
        toDo.setValue(listToSubmit);

    }

    public void updateInInProgress(Ticket ticket){
        int i = 0;
        for(;i<inProgressList.size();i++){
            if(ticket.getId() == inProgressList.get(i).getId()) {
                Ticket tmp = inProgressList.get(i);
                tmp.setLoggedTime(ticket.getLoggedTime());
                tmp.setEstemated(ticket.getEstemated());
                tmp.setPriority(ticket.getPriority());
                tmp.setTitle(ticket.getTitle());
                tmp.setTicetDesc(ticket.getTicetDesc());
                if(tmp.getType() != ticket.getType()) {
                    if(tmp.getType() == TicketType.BUG) {
                        inProgressBugCnt.setValue(inProgressBugCnt.getValue()-1);
                        inProgressEnhancementCnt.setValue(inProgressEnhancementCnt.getValue()+1);
                    }
                    else{
                        inProgressBugCnt.setValue(inProgressBugCnt.getValue()+1);
                        inProgressEnhancementCnt.setValue(inProgressEnhancementCnt.getValue()-1);
                    }
                    tmp.setType(ticket.getType());
                }
                break;
            }
        }
        ArrayList<Ticket> listToSubmit = new ArrayList<>(inProgressList);
        inProgress.setValue(listToSubmit);
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

    public List<Ticket> getToDoList() {
        return toDoList;
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
