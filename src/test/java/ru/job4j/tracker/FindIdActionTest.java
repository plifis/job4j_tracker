package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindIdActionTest {
    @Test
    public void whenFindIdThenNotFound() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Store tracker = new MemTracker();
        String name = "Nikita";
        Item item = new Item(name);
        tracker.add(item);
        FindIdAction find = new FindIdAction();

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(String.valueOf(0));
        find.execute(input, tracker);
        assertThat(out.toString(), is("Id not found" + System.lineSeparator()));
    }

    @Test
    public void whenFindIdThenFound() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Store tracker = new MemTracker();
        String name = "Nikita";
        Item item = new Item(name);
        tracker.add(item);
        FindIdAction find = new FindIdAction();

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(String.valueOf(item.getId()));
        find.execute(input, tracker);
        assertThat(out.toString(), is(item.toString() + System.lineSeparator()));
    }
}