 package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

 public class FindNameActionTest {

    @Test
    public void whenCheckOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Store tracker = new MemTracker();
        Item item = new Item("Nikita");
        tracker.add(item);
        FindNameAction fna = new FindNameAction();
        fna.execute(new StubInput(new String[] {"Nikita"}), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(item.getId() + " " + item.getName())
                .toString();
        assertThat(out.toString(), is(expect));
        System.setOut(def);
    }

    @Test
    public  void whenCheckOutputWithMock() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Store tracker = new MemTracker();
        String name = "Nikita";
        Item item = new Item(name);
        List<Item> items = List.of(item);
        tracker.add(item);

        FindNameAction find = new FindNameAction();
        Input input = mock(Input.class);
        MemTracker mem = mock(MemTracker.class);

        when(input.askStr(any(String.class))).thenReturn(name);
        when(mem.findByName(any(String.class))).thenReturn(items);

        find.execute(input, tracker);

        assertThat(out.toString(), is(item.getId() + " " + name + System.lineSeparator()));

    }
}