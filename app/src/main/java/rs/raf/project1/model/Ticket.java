package rs.raf.project1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ticket implements Parcelable {

    private String title, ticetDesc;
    int estemated, id, loggedTime;
    TicketType type;
    TicketPriority priority;

    public Ticket(String title, String ticetDesc, int estemated, TicketType type, TicketPriority priority) {
        this.title = title;
        this.ticetDesc = ticetDesc;
        this.estemated = estemated;
        this.type = type;
        this.priority = priority;
        this. id = 0;
        this.loggedTime = 0;
    }

    public Ticket() {
    }

    protected Ticket(Parcel in) {
        title = in.readString();
        ticetDesc = in.readString();
        estemated = in.readInt();
        id = in.readInt();
        loggedTime = in.readInt();
        type = TicketType.values()[in.readInt()];
        priority = TicketPriority.values()[in.readInt()];
        loggedTime = in.readInt();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoggedTime() {
        return loggedTime;
    }

    public void setLoggedTime(int loggedTime) {
        this.loggedTime = loggedTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(ticetDesc);
        parcel.writeInt(estemated);
        parcel.writeInt(id);
        parcel.writeInt(loggedTime);
        parcel.writeInt(type.ordinal());
        parcel.writeInt(priority.ordinal());
        parcel.writeInt(loggedTime);
    }
}
