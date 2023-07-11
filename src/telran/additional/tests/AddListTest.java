package telran.additional.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import telran.additional.AddList;

class AddListTest {
    static AddList.LinkedList ll = new AddList.LinkedList();

    @BeforeAll
    static void init() {
        for (int i = 0; i < 100_000; i++) {
            ll.add(i);
        }
        ll.setNext(ll.getNode(99_998), ll.getNode(5));
    }

    @Test
    void testFloyd() {
        ll.getIndexFloyd();
    }

    @Test
    void testHashSet() {
        ll.getIndexHashSet();
    }

    @Test
    void testWrapper() {
        ll.getIndexWrapper();
    }
}