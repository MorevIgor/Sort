package ticket.service;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ManagerTicket {


    private Repository repository;

    public ManagerTicket(Repository repository) {
        this.repository = repository;
    }

    public void addTicket(Ticket ticket) {
        repository.addTicket(ticket);
    }

    public Ticket[] searchBy(String text1, String text2) {
        Ticket[] result = new Ticket[0]; // тут будем хранить подошедшие запросу продукты
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, text1, text2)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
                // "добавляем в конец" массива result продукт product
            }
        }
        Arrays.sort(result);
        return result;
    }

    // метод определения соответствия товара product запросу search
    public boolean matches(Ticket ticket, String search1, String search2) {
        if (ticket.getFrom().contains(search1)) {
            if (ticket.getTo().contains(search2)) {
                return true;
            }
        }
        return false;
    }

}


