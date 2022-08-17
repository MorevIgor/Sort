package ticket.service;

import java.util.Arrays;

public class Repository {
    private Ticket[] tickets = new Ticket[0];

    public void addTicket(Ticket ticket) {
        Ticket[] tmp = new Ticket[tickets.length + 1];
        for (int i = 0; i < tickets.length; i++) {
            tmp[i] = tickets[i];
        }
        tmp[tmp.length - 1] = ticket;
        tickets = tmp;

    }

    public void removeTicketId(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        int index = 0;
        Ticket[] tmp = new Ticket[tickets.length - 1];// удаление id
        for (
                Ticket ticket : tickets) {
            if (ticket.getId() != id) {
                tmp[index] = ticket;
                index++;
            }
            tickets = tmp;
        }
    }

    public Ticket findById(int id) {
        Ticket result = null;
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                result = ticket;
            }
        }
        return result;
    }

    public Ticket[] findAll() {
        Arrays.sort(tickets);
        return tickets;
    }
}
