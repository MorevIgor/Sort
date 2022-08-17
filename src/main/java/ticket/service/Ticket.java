package ticket.service;

public class Ticket implements Comparable<Ticket> {

    private int id;
    private int price;
    private String From;
    private String To;
    private int timeMinut;


    public Ticket(int id, int price, String From, String To, int timeMinut) {
        this.id = id;
        this.price = price;
        this.From = From;
        this.To = To;
        this.timeMinut = timeMinut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String aPortOut) {
        this.From = aPortOut;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String aPortIn) {
        this.To = aPortIn;
    }

    public int getTimeMinut() {
        return timeMinut;
    }

    public void setTimeMinut(int timeMinut) {
        this.timeMinut = timeMinut;
    }

    @Override
    public int compareTo(Ticket o) {
        if (this.price < o.price) {
            return -1;
        } else if (this.price > o.price) {
            return 1;
        } else {
            return 0;
        }
    }
}
