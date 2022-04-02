package rs.raf.project1.model;

public class Ticket {

    private String title, ticetDesc;
    int estemated;
    TicketType type;
    TicketPriority priority;

    public Ticket(String title, String ticetDesc, int estemated, TicketType type, TicketPriority priority) {
        this.title = title;
        this.ticetDesc = ticetDesc;
        this.estemated = estemated;
        this.type = type;
        this.priority = priority;
    }

    public Ticket() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTicetDesc() {
        return ticetDesc;
    }

    public void setTicetDesc(String ticetDesc) {
        this.ticetDesc = ticetDesc;
    }

    public int getEstemated() {
        return estemated;
    }

    public void setEstemated(int estemated) {
        this.estemated = estemated;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }
}
