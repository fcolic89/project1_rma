package rs.raf.project1.model;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TicketViewModel {
    private final MutableLiveData<List<Ticket>> toDoList = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> inProgressList = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> doneList = new MutableLiveData<>();



    public MutableLiveData<List<Ticket>> getToDoList() {
        return toDoList;
    }

    public MutableLiveData<List<Ticket>> getInProgressList() {
        return inProgressList;
    }

    public MutableLiveData<List<Ticket>> getDoneList() {
        return doneList;
    }
}
