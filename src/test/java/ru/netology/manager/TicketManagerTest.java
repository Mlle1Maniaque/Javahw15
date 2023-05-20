package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.manager.TicketManager;
import ru.netology.ticket.Ticket;
import ru.netology.ticket.TicketTimeComparator;

import java.util.Comparator;

public class TicketManagerTest {
    Ticket ticket1 = new Ticket("Saint-Petersburg", "Moscow", 1500, 16, 18);
    Ticket ticket2 = new Ticket("Saint-Petersburg", "Moscow", 6500, 17, 19);
    Ticket ticket3 = new Ticket("Moscow", "Saratov", 4500, 8, 10);
    Ticket ticket4 = new Ticket("Saratov", "Moscow", 3000, 10, 11);
    Ticket ticket5 = new Ticket("Saint-Petersburg", "Moscow", 4500, 14, 18);
    Ticket ticket6 = new Ticket("Saint-Petersburg", "Saratov", 5500, 18, 20);

    TicketManager manager = new TicketManager();

    @BeforeEach
    public void shouldAdd() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
    }

    @Test
    public void shouldFindTicket() {
        Ticket[] expected = {ticket6};
        Ticket[] actual = manager.search("Saint-Petersburg", "Saratov");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindTicket() {
        Ticket[] expected = {};
        Ticket[] actual = manager.search("Moscow", "Voronezh");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareHigherPrice() {
        System.out.println(ticket3.compareTo(ticket1));

        int expected = 1;
        int actual = ticket3.compareTo(ticket1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareLowerPrice() {
        System.out.println(ticket4.compareTo(ticket2));

        int expected = -1;
        int actual = ticket4.compareTo(ticket2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareEqualPrice() {
        System.out.println(ticket5.compareTo(ticket5));

        int expected = 0;
        int actual = ticket5.compareTo(ticket5);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByPrice() {
        Ticket[] expected = {ticket1, ticket5, ticket2};
        Ticket[] actual = manager.search("Saint-Petersburg", "Moscow");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortByTime() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket1, ticket2, ticket5};
        Ticket[] actual = manager.searchAndSortBy("Saint-Petersburg", "Moscow", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}

