package ticket.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerTest {
    private Repository repo = new Repository();
    private ManagerTicket manager = new ManagerTicket(repo);

    Ticket ticket1 = new Ticket(1, 5000, "AAA", "BBB", 120);
    Ticket ticket2 = new Ticket(2, 12000, "AAA", "CCC", 180);
    Ticket ticket3 = new Ticket(3, 6000, "BBB", "CCC", 60);
    Ticket ticket4 = new Ticket(4, 3000, "CCC", "DDD", 30);
    Ticket ticket5 = new Ticket(5, 5000, "BBB", "AAA", 120);
    Ticket ticket6 = new Ticket(6, 4500, "AAA", "CCC", 180);

    @Test
    public void findTicket() { //находит несколько результатов соответсвенно запросу, с сортировкой
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        Ticket[] expected = {ticket6, ticket2};
        Ticket[] actual = manager.searchBy("AAA", "CCC");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void findTicketNotOne() { //ненаходит результатов соответсвенно запросу,
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("AAA", "DDD");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void findTicketNotTwo() { //ненаходит результатов соответсвенно запросу
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("DDD", "AAA");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void findTicketNotUseTwo() { //в качестве второго параметра пустоб с сортировкой
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        Ticket[] expected = {ticket6, ticket1, ticket2};
        Ticket[] actual = manager.searchBy("AAA", "");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void findTicketNotUseOne() { //в качестве первого параметра пусто с сортировкой
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        Ticket[] expected = {ticket6, ticket3, ticket2};
        Ticket[] actual = manager.searchBy("", "CCC");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void findTicketNotUse() { //в качестве параметров пусто с сортировкой(у Т1 и Т5 одинаковая цена)
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        Ticket[] expected = {ticket4, ticket6, ticket1, ticket5, ticket3, ticket2};
        Ticket[] actual = manager.searchBy("", "");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void removeId() { //удвление id с сортировкой
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        repo.removeTicketId(5);

        Ticket[] expected = {ticket4, ticket6, ticket1, ticket3, ticket2};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void removeNotId() { //удаление не существуещего id с сортировкой
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        //manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        Assertions.assertThrows(NotFoundException.class, () -> repo.removeTicketId(5));

    }

    @Test
    public void creatTicket() {
        Ticket newticket = new Ticket(0, 0, "", "", 0);

        manager.addTicket(newticket);

        newticket.setId(7);
        newticket.setPrice(6500);
        newticket.setFrom("AAA");
        newticket.setTo("BBB");
        newticket.setTimeMinut(120);

        int[] expected = {7, 6500, 120};
        int[] actual = {newticket.getId(), newticket.getPrice(), newticket.getTimeMinut()};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void creatTicket2() {
        Ticket newticket = new Ticket(0, 0, "", "", 0);

        manager.addTicket(newticket);

        newticket.setId(7);
        newticket.setPrice(6500);
        newticket.setFrom("AAA");
        newticket.setTo("BBB");
        newticket.setTimeMinut(120);

        String[] expected = {"AAA", "BBB"};
        String[] actual = {newticket.getFrom(), newticket.getTo()};


        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void creatTicket3() {
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);

        Ticket newticket = new Ticket(0, 0, "", "", 0);

        manager.addTicket(newticket);

        newticket.setId(7);
        newticket.setPrice(6500);
        newticket.setFrom("AAA");
        newticket.setTo("BBB");
        newticket.setTimeMinut(120);

        Ticket[] expected = {ticket4, ticket6, ticket1, ticket5, ticket3, newticket, ticket2};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}
